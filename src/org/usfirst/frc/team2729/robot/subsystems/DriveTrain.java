package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.HDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT);
	
	private final RobotDrive _drive = new RobotDrive(_left,_right);
	
	private final Encoder _leftEncoderFront = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder _rightEncoderFront = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
	private double _gyroOffset = 0;
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
	private boolean _isHighGear = false;
	
	private boolean _reverseDrive = false;
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		
		LiveWindow.addSensor ("Drive Train", "Gyro", _gyro);
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoderFront);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoderFront);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HDrive());
	}
	
	//Averages the two left encoders and returns the result
	public double getLeftDistance(){
		return(_leftEncoderFront.get());
	}
	
	//Averages the two right encoders and returns the result
	public double getRightDistance(){
		return(_rightEncoderFront.get());
	}
	
	//Raw center value is returned
	public double getCenterDistance(){
		return _centerEncoder.get();
	}
	
	public double getLeftSpeedEnc() {
		return ( _leftEncoderFront.getRate());
	}
		 /** Reads the right encoder (+ = forward,- = back). */
	public double getRightSpeedEnc() {
		return ( _rightEncoderFront.getRate());
	}
	public void resetGyro(){
		_gyro.reset();
		clearGyro();
	}
		 
	public void clearGyro(){
		 _gyroOffset = _gyro.getAngle();
	}	
	
	public double getGyroAngle(){
		return _gyro.getAngle()-_gyroOffset;
	}
	
	public double getLeftSpeed(){
		return -_left.get();
	}
		 
	public double getRightSpeed(){
		return _right.get();
	}
	
	public void setHighGear(boolean enabled) {
		_isHighGear = enabled;
		_shifter.set(enabled ? DoubleSolenoid.Value.kReverse :
					 DoubleSolenoid.Value.kForward);
	}
	
	public boolean isHighgear() {
		return _isHighGear;
	}
	
	public void setReverseDrive(boolean enabled) {
		_reverseDrive = enabled;
	}
	
	public boolean getReverseDrive() {
		return _reverseDrive;
	}
}
