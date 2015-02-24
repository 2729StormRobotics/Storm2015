package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.ElevatorToSetPoint;
import org.usfirst.frc.team2729.robot.commands.LEDChangeMode;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.Strafe;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
						   armJoystick = new Joystick(RobotMap.PORT_JOYSTICK_ARMS);
	
	private final Button shiftHighDrive = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_DRIVE_HIGH),
						 shiftLowDrive = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_DRIVE_LOW),
						 clampIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_CLAMP_IN),
						 clampOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_CLAMP_OUT), 
						 armIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_ARM_IN), 
						 armOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_ARM_OUT),
						 halveOne = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_HALVE_1),
						 halveTwo = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_HALVE_2),
						 strafeLeft = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_STRAFE_LEFT),
						 strafeRight = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_STRAFE_RIGHT),
						 spinIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_SPIN_IN),
						 spinOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_SPIN_OUT),
						 test1 = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_TEST_1),
						 test2 = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_TEST_2),
						 rainbow = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_RAINBOW);

	public OI() {
		shiftHighDrive.whenPressed(new Shift(true));
		shiftLowDrive.whenPressed(new Shift(false));

		clampIn.whenPressed(new ElevatorClamp(true));
		clampOut.whenPressed(new ElevatorClamp(false));
		
		armIn.whenPressed(new LinearPiston(true));
		armOut.whenPressed(new LinearPiston(false));
		test1.whenPressed(new ChangeElevPosition(-1));
		test2.whenPressed(new ChangeElevPosition(1));
		
		rainbow.whileHeld(new LEDChangeMode((byte) 7));
		
		halveOne.whileHeld(new Command() {

			@Override
			protected void initialize() {
				Robot.driveTrain.halveOne(true);
			}

			@Override
			protected void execute() {}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void end() {
				Robot.driveTrain.halveOne(false);
			}

			@Override
			protected void interrupted() {
				end();
			}
			
		});
		
		halveTwo.whileHeld(new Command() {

			@Override
			protected void initialize() {
				Robot.driveTrain.halveTwo(true);
			}

			@Override
			protected void execute() {}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void end() {
				Robot.driveTrain.halveTwo(false);
			}

			@Override
			protected void interrupted() {
				end();
			}
			
		});
		
		strafeLeft.whileHeld(new Strafe(-1.0));
		strafeRight.whileHeld(new Strafe(1.0));
		/*if(driveJoystick.getPOV() == 0){
			new Shift(true);
		}else if(driveJoystick.getPOV() == 4){
			new Shift(false);
		}*/
	}
	
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    public double getXDrive() {
        return _zeroDeadzone(driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_X),0.15);
    }
    public double getYDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_Y),0.15);
    }
    public double getSpin() {
    	return _zeroDeadzone(driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_SPIN), 0.15);
    }
    //This should be negated, but the motor is backwards, so negating here works
    public double getElevator(){
    	return _zeroDeadzone(armJoystick.getRawAxis(RobotMap.JOYARM_AXIS_ELEVATOR), 0.15);
    }
    public double getRake(){
    	return _zeroDeadzone(-armJoystick.getRawAxis(RobotMap.JOYARM_AXIS_RAKE), 0.15);
    }
    public double getLeftDrive(){
    	return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT), 0.15);
    }
    public double getRightDrive(){
    	return _zeroDeadzone(driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT), 0.15);
    }
    public boolean DPadPressedUp(){
    	return shiftHighDrive.get();
    }
    public double getCardinalDrive(){
    	return _zeroDeadzone(driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_CARDINAL), 0.15);
    }
}
