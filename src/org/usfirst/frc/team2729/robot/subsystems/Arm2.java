package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Arm2 extends Subsystem{
	private final Solenoid _piston = new Solenoid(RobotMap.PORT_SOLENOID_ARM);
	private final Talon _arm = new Talon(RobotMap.PORT_MOTOR_ARM);
	private final Encoder _armEncoder =  new Encoder(RobotMap.PORT_ENCODER_ARM_1, RobotMap.PORT_ENCODER_ARM_2);
	
	public Arm2(){
		LiveWindow.addActuator("Arm2", "Arm", _piston);
		LiveWindow.addActuator("Arm2", "Arm", _arm);
		LiveWindow.addSensor("Arm2", "Arm Encoder", _armEncoder);
	}
	
	public Solenoid getPiston(){
		return _piston;
	}
	
	public Encoder get_ArmEncoder(){
		return _armEncoder;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	public void retract(){
		_piston.set(false);
	}
	public void extend(){
		_piston.set(true);
	}
	public void moveArm(double power){
		_arm.set(power);
	}
	public void resetEncoders(){
		_armEncoder.reset();
	}
	public double getArmDistance() {
		return _armEncoder.get();
	}
	public double getArmRate() {
		return _armEncoder.getRate();
	}
	
}

