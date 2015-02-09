package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
						   armJoystick = new Joystick(RobotMap.PORT_JOYSTICK_ARMS);
	
	private final Button shiftHighDrive = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_HIGH_DRIVE),
						 shiftLowDrive = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_LOW_DRIVE),
						 clampIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_CLAMP_IN),
						 clampOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_CLAMP_OUT), 
						 armIn = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_ARM_IN), 
						 armOut = new JoystickButton(armJoystick, RobotMap.JOYARM_BUTTON_ARM_OUT);
	public OI() {
		shiftHighDrive.whenPressed(new Shift(true));
		shiftLowDrive.whenPressed(new Shift(false));

		clampIn.whenPressed(new ElevatorClamp(true));
		clampOut.whenPressed(new ElevatorClamp(false));

		armIn.whenPressed(new LinearPiston(true));
		armOut.whenPressed(new LinearPiston(false));
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
    	return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_SPIN), 0.15);
    }
    public double getElevator(){
    	return _zeroDeadzone(-armJoystick.getRawAxis(RobotMap.JOYARM_AXIS_ELEVATOR), 0.15);
    }
    public double getRake(){
    	return _zeroDeadzone(-armJoystick.getRawAxis(RobotMap.JOYARM_AXIS_RAKE), 0.15);
    }
}
