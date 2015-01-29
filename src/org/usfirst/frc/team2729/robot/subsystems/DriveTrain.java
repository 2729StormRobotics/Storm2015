package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.HDrive;
import org.usfirst.frc.team2729.robot.util.senseAccel;
import org.usfirst.frc.team2729.robot.util.senseGyro;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {
	//Frame of robot reference.
	//Theta is in radians
	//Theta = 0 is at the front of the robot
	//Theta increases clockwise
	//Y axis is parallel to the sides of the robot.
	//Positive Y is towards the front
	//X axis is parallel to the front of the robot
	//Positive X is towards the right
	public final senseGyro _gyro;
	public final senseAccel _accel;
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT),
					   	_center=new Talon(RobotMap.PORT_MOTOR_DRIVE_CENTER);
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
	private boolean _isHighGear = false;
	
	private final double _turningRatio=0.5;
	private final double RatioLow = .8;
	private final double RatioHigh = 1.4;
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		_accel = new senseAccel(0.01,0.2,0.05);
		_gyro  = new senseGyro(0, RobotMap.PORT_SENSOR_GYRO);
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new HDrive());
	}
	
	public void halt(){
		_left.set(0);
		_right.set(0);
		_center.set(0);
	}
	
	public void gradientDrive(double X, double Y, double rotMag){

		double transMag = Math.sqrt(X*X+Y*Y);
		
		if(Math.abs(Y) <= 1/4*(isHighgear() ? RatioHigh : RatioLow)){
			_right.set(Y);
			_left.set(Y);
			_center.set(X);
		} else if (Math.abs(X) >= 1/4*(isHighgear() ? RatioHigh : RatioLow)){
			_right.set((Y * (Y/X))/4);
			_left.set((Y * (Y/X))/4);
			_center.set(Y);
		} else {
			_right.set(Y > 0 ? 1 : -1);
			_left.set(Y > 0 ? 1 : -1);
			_center.set(4*X/Y);
		}
		
		//point turning
		if(rotMag>0&& transMag==0){
			_right.set(rotMag*_turningRatio);
			_left.set(-rotMag*_turningRatio);
		}else if(rotMag<0 && transMag==0){
			_right.set(-rotMag*_turningRatio);
			_left.set(rotMag*_turningRatio);
		//drift turning
		}else if(rotMag>0){ 
			_right.set(_right.get()-rotMag*_turningRatio);
		}else if(rotMag<0){
			_left.set(_left.get()-rotMag*_turningRatio);
		}
	}
	
	public void rawDrive(double x, double y, double turning){
		//X and Y are on the range [-1,1]
		//turning is on the range [-1,1] with 1 being positive
		_left.set(y > 0 ? y - (turning < 0 ? _turningRatio * turning:0): y + (turning < 0 ? _turningRatio * turning:0));
		_right.set(y > 0? y - (turning > 0 ? _turningRatio * turning:0): y + (turning > 0 ? _turningRatio * turning:0));
		_center.set(x);
	}
	
	public void stickyDrive(double x, double y, double turning){
		//Functionally identical to rawDrive EXCEPT if a parameter is 0, the old value is maintained.
		//turning with sticky drive is not recommended
		_left.set(y != 0 ? y > 0 ? y - (turning < 0 ? _turningRatio * turning:0): y + (turning < 0 ? _turningRatio * turning:0) : _left.get());
		_right.set(y != 0 ? y > 0? y - (turning > 0 ? _turningRatio * turning:0): y + (turning > 0 ? _turningRatio * turning:0) : _right.get());
		_center.set(x != 0 ? x: _center.get());
	}
	
	public double getLeftDistance(){
		return _leftEncoder.get();
	}
	public double getRightDistance(){
		return _rightEncoder.get();
	}
	public double getCenterDistance(){
		return _centerEncoder.get();
	}
	public double getLeftSpeedEnc() {
		return _leftEncoder.getRate();
	}
	/** Reads the right encoder (+ = forward,- = back). */
	public double getRightSpeedEnc() {
		return _rightEncoder.getRate();
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
}
