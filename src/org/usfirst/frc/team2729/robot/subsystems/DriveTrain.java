package org.usfirst.frc.team2729.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.joystick.HDrive;
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
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT),
					   	_center=new Talon(RobotMap.PORT_MOTOR_DRIVE_CENTER);
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_DRIVE_HIGH,RobotMap.PORT_SOLENOID_SHIFT_DRIVE_LOW);
	private boolean _isHighGear = false;
	
	private final double _turningRatio=0.5;
	private final double RatioLow = 1.2;
	private final double RatioHigh = 1.63636363;
	
	private final double HGMax = 1000;
	private final double LGMax = 500;
	private final double CMax = 1500;
	
	public double pGain = .005;
	
	private double leftPower = 0, rightPower = 0, centerPower = 0;
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		_gyro  = new senseGyro(0, RobotMap.PORT_SENSOR_GYRO);
		Timer _timer = new Timer();
		_timer.schedule(new TimerTask() {
			public void run() {
				_right.set(_right.get() + ((getRightSP() - (getRightSpeed()/(isHighgear() ? HGMax : LGMax)))*pGain));
				_left.set(_left.get() + ((getLeftSP() - (getLeftSpeed()/(isHighgear() ? HGMax : LGMax)))*pGain));
				_center.set(_center.get() + ((getCenterSP() - (getCenterSpeed()/CMax))*pGain));
			}
		}, 0, 10);
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new HDrive());
	}
	
	public void halt(){
		_left.set(0);
		_right.set(0);
		_center.set(0);
		leftPower = 0;
		rightPower = 0;
		centerPower= 0;
	}
	
	public void gradientDrive(double X, double Y, double rotMag){

		double transMag = Math.sqrt(X*X+Y*Y);
		
		if(Math.abs(Y) <= Math.abs(X)){
			setRightSP(Y/4);
			setLeftSP(Y/4);
			setCenterSP(X);
		} else if (Math.abs(X) >= 1/4*Math.abs(Y)){
			setRightSP((Y * Math.abs(Y/X))/4);//Arcane black magic:
			setLeftSP((Y * Math.abs(Y/X))/4); //Do not touch.
			setCenterSP(Y);					  //Do not feed after midnight.
		} else {
			setRightSP(Y);
			setLeftSP(Y);
			setCenterSP(4*X);
		}
		
		//point turning
		if(rotMag>0&& transMag==0){
			setRightSP(rotMag*_turningRatio);
			setLeftSP(-rotMag*_turningRatio);
		}else if(rotMag<0 && transMag==0){
			setRightSP(-rotMag*_turningRatio);
			setLeftSP(rotMag*_turningRatio);
		//drift turning
		}else if(rotMag>0){ 
			setLeftSP(_right.get()-(_right.get()>0 ? 1:-1)*rotMag*_turningRatio);
		}else if(rotMag<0){
			setRightSP(_left.get()-(_left.get()>0 ? 1:-1)*rotMag*_turningRatio);
		}
	}
	
	public void rawDrive(double x, double y, double turning){
		//X and Y are on the range [-1,1]
		//turning is on the range [-1,1] with 1 being positive
		setLeftSP(y);
		setRightSP(y);
		setCenterSP(x);
		if(turning!=0){
			setRightSP(turning);
			setLeftSP(-turning);
		}
	}
	
	public void stickyDrive(double x, double y, double turning){
		//Functionally identical to rawDrive EXCEPT if a parameter is 0, the old value is maintained.
		//turning with sticky drive is not recommended
		setLeftSP(y != 0 ? y > 0 ? y - (turning < 0 ? _turningRatio * turning:0): y + (turning < 0 ? _turningRatio * turning:0) : _left.get());
		setRightSP(y != 0 ? y > 0? y - (turning > 0 ? _turningRatio * turning:0): y + (turning > 0 ? _turningRatio * turning:0) : _right.get());
		setCenterSP(x != 0 ? x: _center.get());
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
	public double getCenterSpeedEnc() {
		return _centerEncoder.getRate();
	}
	public double getLeftSpeedEnc() {
		return _leftEncoder.getRate();
	}
	public void resetLeftEnc(){
		_leftEncoder.reset();
	}
	public void resetRightEnc(){
		_rightEncoder.reset();
	}
	public void resetCenterEnc(){
		_centerEncoder.reset();
	}
	public double getRightSpeedEnc() {
		return _rightEncoder.getRate();
	}
	public double getLeftSpeed(){
		return -_left.get();
	}	 
	public double getRightSpeed(){
		return _right.get();
	}
	public double getCenterSpeed(){
		return _center.get();
	}
	public void setHighGear(boolean enabled) {
		_isHighGear = enabled;
		_shifter.set(enabled ? DoubleSolenoid.Value.kReverse :
					 DoubleSolenoid.Value.kForward);
	}
	public boolean isHighgear() {
		return _isHighGear;
	}
	public double getGearRatio(){
		return _isHighGear ? RatioHigh : RatioLow;
	}
	public double getLeftSP() {
		return leftPower;
	}
	public void setLeftSP(double leftPower) {
		this.leftPower = leftPower;
	}
	public double getRightSP() {
		return rightPower;
	}
	public void setRightSP(double rightPower) {
		this.rightPower = rightPower;
	}
	public double getCenterSP() {
		return centerPower;
	}
	public void setCenterSP(double centerPower) {
		this.centerPower = centerPower;
	}
}
