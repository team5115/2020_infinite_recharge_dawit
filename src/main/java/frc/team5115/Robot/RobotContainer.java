package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
//import frc.team5115.Robot.*;
import static frc.team5115.Constants.*;

public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    //Subsystems
    public Drivetrain drivetrain;
    public final Limelight limelight = new Limelight();
    public final Shooter shooter = new Shooter();
    public final Intake intake = new Intake();
    public final Climber climber = new Climber();
    public final Feeder feeder = new Feeder();

    public final Joystick joy = new Joystick(0);

    public RobotContainer() {
        // Configure the button bindings
        //sets the navx to work.
        drivetrain = new Drivetrain(this);
    
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(joy, STRAFE_RIGHT_ID)
    .whenHeld(new InstantCommand(drivetrain::StrafeRight)).whenReleased(new InstantCommand(drivetrain::StrafeStop));
        new JoystickButton(joy, STRAFE_LEFT_ID)
    .whenHeld(new InstantCommand(drivetrain::StrafeLeft)).whenReleased(new InstantCommand(drivetrain::StrafeStop));


        drivetrain.setDefaultCommand(new driveDefaultCommand(drivetrain, joy).perpetually());
    }

    static class driveDefaultCommand extends CommandBase {

        Drivetrain drivetrain;
        Joystick joystick;

        public driveDefaultCommand(Drivetrain drivetrain, Joystick joystick) {
            addRequirements(drivetrain);
            this.drivetrain = drivetrain;
            this.joystick = joystick;
        }

        @Override
        public void execute() {
            drivetrain.drive(joystick.getRawAxis(XBOX_X_AXIS_ID), joystick.getRawAxis(XBOX_Y_AXIS_ID), 1);
            //drivetrain.drive(-.5, -.5, 1);
        }
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public void startTeleop() {
        //bind the wheels.
        System.out.println("Starting teleop");
    }
}