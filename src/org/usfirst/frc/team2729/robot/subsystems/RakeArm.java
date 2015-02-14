package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.joystick.RakeArmJoy;
import org.usfirst.frc.team2729.robot.util.StringPot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RakeArm extends Subsystem{
	private final Talon _arm = new Talon(RobotMap.PORT_MOTOR_ARM);
	private final DigitalInput _switch = new DigitalInput(RobotMap.PORT_LIMIT_SWITCH_AUTO);
	private final StringPot _pot = new StringPot(RobotMap.PORT_STRINGPOT);
	public RakeArm(){
		LiveWindow.addActuator("Arm1", "Arm", _arm);
	}

	public boolean isPressed() {
		return _switch.get();
	}

	public double readStringPot() {
		return _pot.get();
	}
	public boolean isMax(){
		return _pot.get() > _pot.VAL_MAX_SAFE;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RakeArmJoy());
		//take in joystick values and motor it
	}

	public void moveArm(double power) {
		_arm.set(power); //Sets a power to move the arm
	}
}
