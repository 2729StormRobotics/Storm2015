package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.ElevatorToSetPoint;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.SpinIntake;
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
						 halveStrafe = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_HALVE_STRAFE),
						 strafeLeft = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_STRAFE_LEFT),
						 strafeRight = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_STRAFE_RIGHT),
						 spinIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_SPIN_IN),
						 spinOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_SPIN_OUT),
						 test1 = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_TEST_1),
						 test2 = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_TEST_2);
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public OI() {
		shiftHighDrive.whenPressed(new Shift(true));
		shiftLowDrive.whenPressed(new Shift(false));
		
		clampIn.whenPressed(new ElevatorClamp(true));
		clampOut.whenPressed(new ElevatorClamp(false));
		
		//armIn.whenPressed(new LinearPiston(true));
		//armOut.whenPressed(new LinearPiston(false));
		armIn.whenPressed(new ElevatorToSetPoint(.166));
		armOut.whenPressed(new ElevatorToSetPoint(.384));
		test1.whenPressed(new ElevatorToSetPoint(.605));
		test2.whenPressed(new ElevatorToSetPoint(.824));
		
		
		halveStrafe.whileHeld(new Command() {

			@Override
			protected void initialize() {
				Robot.driveTrain.halveStrafe(true);
			}

			@Override
			protected void execute() {}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void end() {
				Robot.driveTrain.halveStrafe(false);
			}

			@Override
			protected void interrupted() {
				end();
			}
			
		});
		
		strafeLeft.whileHeld(new Strafe(-1.0));
		strafeRight.whileHeld(new Strafe(1.0));
		
		spinIn.whileHeld(new SpinIntake(0.5));
		spinOut.whileHeld(new SpinIntake(-0.5));
		
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
}

