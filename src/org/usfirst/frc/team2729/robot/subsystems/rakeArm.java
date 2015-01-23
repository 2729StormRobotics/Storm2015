package org.usfirst.frc.team2729.robot.subsystems;
import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class rakeArm extends Subsystem{
	private final Talon _arm = new Talon(RobotMap.PORT_MOTOR_ARM);
	private final Encoder _armEncoder =  new Encoder(RobotMap.PORT_ENCODER_ARM_1, RobotMap.PORT_ENCODER_ARM_2);
	private final DigitalInput _switch = new DigitalInput(PORT_LIMIT_SWITCH);
	public rakeArm(){
		LiveWindow.addActuator("Arm1", "Arm", _arm);
		LiveWindow.addSensor("Arm1", "Arm Encoder", _armEncoder);
	}
	public Encoder get_ArmEncoder(){
		return _armEncoder;
	}
	public boolean isPressed(){
		return _switch.get();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
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

