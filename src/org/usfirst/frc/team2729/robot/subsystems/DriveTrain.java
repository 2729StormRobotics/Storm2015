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
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain extends Subsystem {
	//All units are metric base units
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
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final BuiltInAccelerometer accel = new BuiltInAccelerometer();
	private double xDisplac = 0, yDisplac = 0;
	private double xOff = accel.getX(), yOff = accel.getY(), zOff = accel.getZ();
	private double xVel = 0, yVel = 0, zVel = 0;
	private final Timer accelDeltaT = new Timer();
	
	private final Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
	private double _gyroOffset = 0;
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
	private boolean _isHighGear = false;
	
	private boolean _reverseDrive = false;
	
	private final double vertRatioLow = .8;
	private final double vertRatioHigh = 1.4;
	private final double LoGTanCoef = Math.tan(4 * vertRatioLow);
	private final double HiGTanCoef = Math.tan(4 * vertRatioHigh);
	
	public void accelUpdate(){
		if(accelDeltaT.get() != 0.0){
			
		}
	}
	
	public void directDrive(double left, double right, double center, double rotate){
		//clockwise is positive
		//all values must be on the interval [-1,1]
		_left.set(left - (rotate < 0 ? _turningRate * rotate : 0));
		_right.set(right - (rotate > 0 ? _turningRate * rotate : 0));
		_center.set(center);
		
	}
	
	public void gradientDrive(double mag, double theta, boolean isHighGear){
		if(theta > 0){
			while(theta >= 360.0){ //ensures that no theta exceeds 360 degrees
				theta-= 360.0;
			}	
		} else {
			while(theta <= -360.0){ //ensures that no theta exceeds 360 degrees
				theta+= 360.0;
			}	
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
			_center.set((4*curRatio)*Math.tan(theta));
			if(theta > 270 || theta < 90){
				_left.set(1.0 * mag);
				_right.set(1.0 * mag);
			} else {
				_left.set(-1.0 * mag);
				_right.set(-1.0 * mag);
			}
		} else {
			if(theta != 90 || theta != 270){
			_right.set(((theta > 180 ? -1 : 1)/(Math.tan(90-theta)*4*curRatio))*mag);
			_left.set(((theta > 180 ? -1 : 1)/(Math.tan(90-theta)*4*curRatio))*mag);
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
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
		
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HDrive());
	}
	
	//Averages the two left encoders and returns the result
	public double getLeftDistance(){
		return _leftEncoder.get();
	}
	
	//Averages the two right encoders and returns the result
	public double getRightDistance(){
		return _rightEncoder.get();
	}
	
	//Raw center value is returned
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
