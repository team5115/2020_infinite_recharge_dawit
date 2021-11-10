package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;

import static frc.team5115.Constants.*;

public class Drivetrain extends SubsystemBase{

    //instances of the speed controllers
    private VictorSPX frontLeft;
    private VictorSPX frontRight;
    private TalonSRX backLeft;
    private TalonSRX backRight;

    private double targetAngle; //during regular operation, the drive train keeps control of the drive. This is the angle that it targets.

    private double rightSpd;
    private double leftSpd;
    private double SpeedControl;
    private double StrafeX;
    private double RightLeft;
    private double LeftRight;


    public Drivetrain(RobotContainer x) {
        //this.locationator = x.locationator;

        frontLeft = new VictorSPX(FRONT_LEFT_MOTOR_ID);
        frontRight = new VictorSPX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

        //backRight.setInverted(true);

        //back motors are master

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    //@Override
    public void stop() {
        drive(0, 0, 0);
    }

    public void Strafe(double StrafeX){
        RightLeft = -StrafeX*SpeedControl;
        LeftRight = StrafeX*SpeedControl;

        frontLeft.set(ControlMode.PercentOutput, LeftRight);
        frontRight.set(ControlMode.PercentOutput, RightLeft);
        backLeft.set(ControlMode.PercentOutput, RightLeft);
        backRight.set(ControlMode.PercentOutput, LeftRight);
    }


    //@Override
    public void drive(double x, double y, double SpeedControl) { //Change the drive output
        //called lots of times per seconds.
        //System.out.println("Driving with X:" + x + " Y: " + y + " throttle: " + throttle);
        //Math.sqrt(3.4* Math.log(x + y + 1));
        //todome
        //System.out.println("x = " + x);
        //System.out.println("y = " + y);

        if (Math.abs(StrafeX)>0){
            Strafe(StrafeX);
        }
        else{


        leftSpd = (x+y) * SpeedControl;
        rightSpd = (y-x) * SpeedControl;
        System.out.println("Setting Right Pair to :" + (int) rightSpd * 100);
        System.out.println("Setting Left Pair to :" + (int) leftSpd * 100);

        frontLeft.set(ControlMode.PercentOutput, leftSpd);
        frontRight.set(ControlMode.PercentOutput, rightSpd);
        backLeft.set(ControlMode.PercentOutput, leftSpd);
        backRight.set(ControlMode.PercentOutput, rightSpd);
        }
    }

    public void StrafeLeft(){
        StrafeX = -1;
    }
    public void StrafeRight(){
        StrafeX = 1;
    }
    public void StrafeStop(){
      StrafeX = 0;
    }


    public void XBoxDrive(Joystick joy) {
        double x = joy.getRawAxis(XBOX_X_AXIS_ID);
        double y = -joy.getRawAxis(XBOX_Y_AXIS_ID);
        double SpeedControl = -joy.getRawAxis(JOYSTICK_SPEED_AXIS_ID);
        

        double throttle1 = joy.getRawAxis(XBOX_THROTTLE_1_ID);
        double throttle2 = joy.getRawAxis(XBOX_THROTTLE_2_ID);

        //throttle is between 0.5 and 1
        double throttle = (1 - throttle1) + (1 - throttle2);
        throttle /= 2;
        //throttle is between 0 (dont move) and 1, (full move)
        throttle = ((1 - MIN_XBOX_THROTTLE) * throttle) + MIN_XBOX_THROTTLE;
        //new Throttle is now max 1 and min 0.2
        //System.out.println("throttle = " + throttle);
        //drive(x,y,throttle);

       // driveByWire(x, y, throttle);
    }

    

}

