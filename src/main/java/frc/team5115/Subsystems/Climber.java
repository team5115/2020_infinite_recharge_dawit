package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Commands.StopClimb;
import edu.wpi.first.wpilibj.Timer;

import static frc.team5115.Constants.SCISSOR_MOTOR_ID;
import static frc.team5115.Constants.WINCH_MOTOR_ID;

public class Climber extends SubsystemBase {
    private TalonSRX climbermotor;
    private double climbspeed;

    public Climber(){
        climbermotor = new TalonSRX(WINCH_MOTOR_ID);
    }

    public void StartClimb(){
        climbermotor.set(ControlMode.PercentOutput,0.5);

    }
    
    public void StopClimb(){
        climbermotor.set(ControlMode.PercentOutput,0.0);
    }


}
