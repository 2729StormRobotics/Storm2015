package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.HDrive;

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
	
	private final Talon _left = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
					   	_right= new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT),
					   	_center=new Talon(RobotMap.PORT_MOTOR_DRIVE_CENTER);
	
	private final Encoder _leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
	private final Encoder _centerEncoder = new Encoder(RobotMap.PORT_ENCODER_CENTER_1, RobotMap.PORT_ENCODER_CENTER_2);
	
	private final DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
	private boolean _isHighGear = false;
	
	private final double _turningRatio=0.5;
	private final double vertRatioLow = .8;
	private final double vertRatioHigh = 1.4;
	private final double loGearTanCoe = Math.tan(4 * vertRatioLow);
	private final double hiGearTanCoe = Math.tan(4 * vertRatioHigh);
	
	public DriveTrain(){
		//Encoders are started when they are initialized
		LiveWindow.addSensor ("Drive Train", "Left Front Encoder", _leftEncoder);
		LiveWindow.addSensor ("Drive Train", "Right Front Encoder", _rightEncoder);
		LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
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
		//X and Y are on the range [-1,1]
		double theta = Math.atan(Y/X);
		double transMag = Math.sqrt(X*X+Y*Y);
		
		double curTanCoef = 0;
		double curRatio = 0;
		if(_isHighGear){
			curTanCoef = hiGearTanCoe;
			curRatio = vertRatioHigh;
		} else {
			curTanCoef = loGearTanCoe; 
			curRatio = vertRatioLow;
		}
		//checks to see if theta is on the interval [0, Coef]U[pi-Coef,pi+Coef]U[pi - coef, 360]
		if(theta > (2*Math.PI - curTanCoef) || theta < curTanCoef || (theta < Math.PI + curTanCoef && theta > Math.PI - curTanCoef) && transMag!=0){ 
			_center.set((4*curRatio)*Math.tan(curTanCoef));
			if(theta > 3*Math.PI/2 || theta < Math.PI/2){
				_left.set(1.0 * transMag);
				_right.set(1.0 * transMag);
			} else {
				_left.set(-1.0 * transMag);
				_right.set(-1.0 * transMag);
			}
		} else {
			if(theta != Math.PI/2 || theta != 3*Math.PI/2){
				_right.set(((theta > Math.PI ? -1 : 1)/(Math.tan(theta)*4*curRatio))*transMag);
				_left.set(((theta > Math.PI ? -1 : 1)/(Math.tan(theta)*4*curRatio))*transMag);
				_center.set((theta > Math.PI ? -1 : 1)*transMag);
			} else {
				_right.set(0);
				_left.set(0);
				_center.set((theta > Math.PI ? -1 : 1)*transMag);
			}
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
