package org.usfirst.frc.team2729.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.joystick.KDrive;
import org.usfirst.frc.team2729.robot.util.senseGyro;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	// Frame of robot reference.
	// Theta is in radians
	// Theta = 0 is at the front of the robot
	// Theta increases clockwise
	// Y axis is parallel to the sides of the robot.
	// Positive Y is towards the front
	// X axis is parallel to the front of the robot
	// Positive X is towards the right
	public final senseGyro _gyro;

	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT),
					   	_center=new Talon(RobotMap.PORT_MOTOR_DRIVE_CENTER);
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	//forwards is negative atm, so it's negated in calls
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_DRIVE_HIGH,RobotMap.PORT_SOLENOID_SHIFT_DRIVE_LOW);

	private final double _turningRatio = 0.5;
	private final double RatioLow = 1.2;
	private final double RatioHigh = 1.63636363;

	private final double HGMax = 1640;
	private final double LGMax = 700;
	private final double CMax = 300;
	
	public double iGainHG = .05; //a lower iGain for the
	public double iGainLG = .03; //low gear prevents jerky movements
	private double leftPower = 0, rightPower = 0, centerPower = 0;

	private boolean _isHighGear = false;
	
	private boolean _halfOne = false, _halfTwo = false;
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		//Encoder: 0.09237 in/tick
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		_gyro  = new senseGyro(0, RobotMap.PORT_SENSOR_GYRO);
		Timer _timer = new Timer();
		_timer.schedule(new TimerTask() {
			public void run() {
				iGainHG = SmartDashboard.getNumber("HG iGain");
				iGainLG = SmartDashboard.getNumber("LG iGain");
				if(getRightSP() == getLeftSP() && Math.abs(getRightSP()) > .9 &&  Math.abs(getLeftSP()) > .9){
					double diff = (isHighgear() ? iGainHG : iGainLG) * ((getRightSpeed()/(isHighgear() ? HGMax : LGMax)) - (getLeftSpeed()/(isHighgear() ? HGMax : LGMax))); 
					double left = getLeftSP() + diff/2,
						   right = getRightSP() - diff/2;
					if(right < -1) {
						left -= (right+1);
						right = -1;
					} else if(right > 1) {
						left -= (right-1);
						right = 1;
					}
					if(left < -1) {
						right -= (left+1);
						left = -1;
					} else if(left > 1) {
						right -= (left-1);
						left = 1;
					}
					_left.set(Math.max(-1, Math.min(1, left )));
					_right.set(-Math.max(-1, Math.min(1, right))); //negated due to motor being backwards
					_center.set(_center.get() + ((getCenterSP() - (getCenterSpeed()/CMax))*iGainHG));
				} else {
					//right is negated doing to being backwards
					_right.set(-((-_right.get()) + ((getRightSP() - (getRightSpeedEnc()/(isHighgear() ? HGMax : LGMax)))*(isHighgear() ? iGainHG : iGainLG))));
					_left.set(_left.get() + ((getLeftSP() - (getLeftSpeedEnc()/(isHighgear() ? HGMax : LGMax)))*(isHighgear() ? iGainHG : iGainLG)));
					_center.set(_center.get() + ((getCenterSP() - (getCenterSpeedEnc()/CMax))*iGainLG));	
				}
			}
		}, 10, 10);
		_shifter.set(DoubleSolenoid.Value.kForward);
		_isHighGear = false;
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new KDrive());
	}
	
	public void halveOne(boolean half){
		_halfOne = half;
	}
	
	public void halveTwo(boolean half){
		_halfTwo = half;
	}

	public void halt() {
		_left.set(0);
		_right.set(0);
		_center.set(0);
		leftPower = 0;
		rightPower = 0;
		centerPower= 0;
	}
	
	public void strafeLeft(double _power){
		setCenterSP(_power * (_halfOne ? 0.5 : 1) * (_halfTwo ? 0.5 : 1));
	}
	
	public void stop(){
		leftPower = 0;
		rightPower = 0;
		centerPower= 0;
	}
	public void gradientDrive(double X, double Y, double rotMag) {
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
		if(rotMag>0&& transMag==0){ //Clockwise rotation
			setRightSP(-rotMag*_turningRatio);
			setLeftSP(rotMag*_turningRatio);
		}else if(rotMag<0 && transMag==0){ //anti-clockwise rotation
			setRightSP(rotMag*_turningRatio);
			setLeftSP(-rotMag*_turningRatio);
		//drift turning
		}else if(rotMag>0){ 
			setRightSP(getRightSP()-(getRightSP()>0 ? 1:-1)*rotMag*_turningRatio);
		}else if(rotMag<0){
			setLeftSP(getLeftSP()-(getLeftSP()>0 ? 1:-1)*rotMag*_turningRatio);
		}
	}
	
	public void kDrive(double left, double right){
		setLeftSP(left * (_halfOne ? 0.5 : 1) * (_halfTwo ? 0.5 : 1));
		setRightSP(right * (_halfOne ? 0.5 : 1) * (_halfTwo ? 0.5 : 1));
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
		/*
		if(turning >0){
			_right.set(_right.get()-(_right.get()>0 ? 1:-1)*turning*_turningRatio);
		}else {
			_left.set(_left.get()-(_left.get()>0 ? 1:-1)*turning*_turningRatio);
		}
		*/
	}
	
	public void stickyDrive(double x, double y, double turning){
		//Functionally identical to rawDrive EXCEPT if a parameter is 0, the old value is maintained.
		//turning with sticky drive is not recommended
		setLeftSP(y != 0 ? y > 0 ? y - (turning < 0 ? _turningRatio * turning:0): y + (turning < 0 ? _turningRatio * turning:0) : getLeftSP());
		setRightSP(y != 0 ? y > 0? y - (turning > 0 ? _turningRatio * turning:0): y + (turning > 0 ? _turningRatio * turning:0) : getRightSP());
		setCenterSP(x != 0 ? x: getCenterSP());
	}
	
	public double getLeftDistance(){
		return _leftEncoder.get();
	}
	//negated due to encoder being backwards
	public double getRightDistance(){
		return -_rightEncoder.get();
	}
	public double getLeftSpeedEnc() {
		return _leftEncoder.getRate();
	}

	public void resetLeftEnc() {
		_leftEncoder.reset();
	}

	public void resetRightEnc() {
		_rightEncoder.reset();
	}
	/** Reads the right encoder (+ = forward,- = back), might need to be negated */
	public double getRightSpeedEnc() {
		return -_rightEncoder.getRate();
	}

	public double getLeftSpeed() {
		return _left.get();
	}

	public double getRightSpeed() {
		return _right.get();
	}

	public double getCenterSpeed() {
		return _center.get();
	}

	public void setHighGear(boolean enabled) {
		_isHighGear  = enabled;
		_shifter.set(enabled ? DoubleSolenoid.Value.kReverse
				: DoubleSolenoid.Value.kForward);
	}

	public boolean isHighgear() {
		return _isHighGear;
	}

	public double getGearRatio() {
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

	public double getCenterDistance() {
		//return center encoder once attached
		return _centerEncoder.get();
	}
	public double getCenterSpeedEnc(){
		return _centerEncoder.getRate();
	}
	public void resetCenterEnc(){
		_centerEncoder.reset();
	}
}
