package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.HDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Gyro;


public class senseGyro extends Subsystem{

	private final Gyro _gyro;
	private double _gyroOffset = 0;
	
	public senseGyro(double offSet, int channel){
		_gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
		_gyroOffset = offSet;
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
	protected void initDefaultCommand() {
	
	}
}
