package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.LinearArm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class linearArm extends Subsystem{
	private final DoubleSolenoid _piston = new DoubleSolenoid(RobotMap.PORT_SOLENOID_ARM_UP, RobotMap.PORT_SOLENOID_ARM_DOWN);
	private final Talon _arm = new Talon(RobotMap.PORT_MOTOR_ARM);
	private boolean _up = false;
	
	public linearArm(){
		LiveWindow.addActuator("Arm2", "Arm", _piston);
		LiveWindow.addActuator("Arm2", "Arm", _arm);
		_piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isUp(){
		return _up;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LinearArm());
	}
	public void lower(){
		_piston.set(DoubleSolenoid.Value.kReverse);
		_up = false;
	}
	public void raise(){
		_piston.set(DoubleSolenoid.Value.kForward);
		_up = true;
	}
	public void moveArm(double power){
		_arm.set(power);
	}
	
}

