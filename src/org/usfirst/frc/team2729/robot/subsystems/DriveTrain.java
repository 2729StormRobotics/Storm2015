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
	//Frame of robot reference.
	//Theta is in degrees
	//Theta = 0 is at the front of the robot
	//Theta increases clockwise
	//Y axis is parallel to the sides of the robot.
	//Positive Y is towards the front
	//X axis is parallel to the front of the robot
	//Positive X is towards the right
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT),
					   	_center=new Talon(RobotMap.PORT_MOTOR_DRIVE_CENTER);
	
	private final RobotDrive _drive = new RobotDrive(_left,_right);
	
	private final Encoder _leftEncoderFront = new Encoder(RobotMap.PORT_ENCODER_LEFT_1_1, RobotMap.PORT_ENCODER_LEFT_1_2);
	private final Encoder _leftEncoderRear = new Encoder(RobotMap.PORT_ENCODER_LEFT_2_1, RobotMap.PORT_ENCODER_LEFT_2_2);
	private final Encoder _rightEncoderFront = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1_1, RobotMap.PORT_ENCODER_RIGHT_1_2);
	private final Encoder _rightEncoderRear = new Encoder(RobotMap.PORT_ENCODER_RIGHT_2_1, RobotMap.PORT_ENCODER_RIGHT_2_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
	private double _gyroOffset = 0;
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
	private boolean _isHighGear = false;
	
	private boolean _reverseDrive = false;
	
	private final double vertRatioLow = .8;
	private final double vertRatioHigh = 1.4;
	private final double LoGTanCoef = Math.tan(4 * vertRatioLow);
	private final double HiGTanCoef = Math.tan(4 * vertRatioHigh);
	
	public void gradientDrive(double mag, double theta, boolean isHighGear){
		while(theta >= 360.0){ //ensures that no theta exceeds 360 degrees
			theta-= 360.0;
		}
		double curTanCoef = 0;
		double curRatio = 0;
		if(isHighGear){
			curTanCoef = HiGTanCoef;
			curRatio = vertRatioHigh;
		} else {
			curTanCoef = LoGTanCoef; 
			curRatio = vertRatioLow;
		}
		//checks to see if theta is on the interval [0, Coef]U[180-Coef,180+Coef]U[360 - coef, 360]
		if(theta > (360.0 - curTanCoef) || theta < curTanCoef || (theta < 180 + curTanCoef && theta > 180 - curTanCoef)){ 
			_center.set((4*curRatio)*Math.tan(curTanCoef));
			if(theta > 270 || theta < 90){
				_left.set(1.0 * mag);
				_right.set(1.0 * mag);
			} else {
				_left.set(-1.0 * mag);
				_right.set(-1.0 * mag);
			}
		} else {
			if(theta != 90 || theta != 270){
			_right.set(((theta > 180 ? -1 : 1)/(Math.tan(theta)*4*curRatio))*mag);
			_left.set(((theta > 180 ? -1 : 1)/(Math.tan(theta)*4*curRatio))*mag);
			_center.set((theta > 180 ? -1 : 1)*mag);
			} else {
			_right.set(0);
			_left.set(0);
			_center.set((theta > 180 ? -1 : 1)*mag);
			}
		}
		
	}
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		
		LiveWindow.addSensor ("Drive Train", "Gyro", _gyro);
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoderFront);
		LiveWindow.addSensor ("Drive Train", "Left Rear Encoder", _leftEncoderRear);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoderFront);
		LiveWindow.addSensor ("Drive Train", "Right Rear Encoder", _rightEncoderRear);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HDrive());
	}
	
	//Averages the two left encoders and returns the result
	public double getLeftDistance(){
		return(( _leftEncoderFront.get() + _leftEncoderRear.get())/2);
	}
	
	//Averages the two right encoders and returns the result
	public double getRightDistance(){
		return(( _rightEncoderFront.get() + _rightEncoderRear.get())/2);
	}
	
	//Raw center value is returned
	public double getCenterDistance(){
		return _centerEncoder.get();
	}
	
	public double getLeftSpeedEnc() {
		return (( _leftEncoderFront.getRate() + _leftEncoderRear.getRate())/2);
	}
		 /** Reads the right encoder (+ = forward,- = back). */
	public double getRightSpeedEnc() {
		return (( _rightEncoderFront.getRate() + _rightEncoderRear.getRate())/2);
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
