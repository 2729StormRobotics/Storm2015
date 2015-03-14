package org.usfirst.frc.team2729.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.joystick.KDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {
	// Frame of robot reference.
	// Theta is in radians
	// Theta = 0 is at the front of the robot
	// Theta increases clockwise
	// Y axis is parallel to the sides of the robot.
	// Positive Y is towards the front
	// X axis is parallel to the front of the robot
	// Positive X is towards the right
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT);
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	//forwards is negative atm, so it's negated in calls
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_DRIVE_HIGH,RobotMap.PORT_SOLENOID_SHIFT_DRIVE_LOW);
	
	public double iGainHG = .05; //a lower iGain for the
	public double iGainLG = .03; //low gear prevents jerky movements
	private double leftPower = 0, rightPower = 0;

	private boolean _isHighGear = false;
	
	private boolean _halfOne = false, _halfTwo = false;
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		//Encoder: 0.09237 in/tick
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		Timer _timer = new Timer();
		_timer.schedule(new TimerTask() {
			public void run() {
				_right.set(getRightSP());
				_left.set(getLeftSP());
			}
		}, 5, 5);
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
		leftPower = 0;
		rightPower = 0;
	}
	
	public void stop(){
		leftPower = 0;
		rightPower = 0;
	}
	
	public void kDrive(double left, double right){
		setLeftSP((left/3) + (_halfOne ? (left/3) : 0) + (_halfTwo ? (left/3) : 0));
		setRightSP((right/3) + (_halfOne ? (right/3) : 0) + (_halfTwo ? (right/3) : 0));
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

	public void setHighGear(boolean enabled) {
		_isHighGear  = enabled;
		_shifter.set(enabled ? DoubleSolenoid.Value.kReverse
				: DoubleSolenoid.Value.kForward);
	}

	public boolean isHighgear() {
		return _isHighGear;
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
}