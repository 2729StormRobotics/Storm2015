package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.util.FilterTask;
import org.usfirst.frc.team2729.robot.util.HighPassFilter;
import org.usfirst.frc.team2729.robot.util.ISource;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

public class senseAccel extends Subsystem{
	private final Timer deltaT = new Timer();
	
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	private double xPos = 0, yPos = 0, xVel = 0, yVel = 0;
	
	public double rc = 1;
	
	private FilterTask accelXTask = new FilterTask(new HighPassFilter(rc, 0),(new ISource(){public double get(){return Robot._accel.getXAccel();}}), 0.01);
	private FilterTask accelYTask = new FilterTask(new HighPassFilter(rc, 0),(new ISource(){public double get(){return Robot._accel.getYAccel();}}), 0.01);
	
	public senseAccel(){
		_Accel.setRange(Accelerometer.Range.k4G);
	}
	
	public void update(){
		deltaT.stop();
		double dT = deltaT.get();
		xPos += xVel * dT + (1/2 * accelXTask.get() * (dT * dT));
		yPos += yVel * dT + (1/2 * accelYTask.get() * (dT * dT));
		xVel += _Accel.getX() * dT;
		yVel += _Accel.getY() * dT;
		deltaT.reset();
	}
	protected void initDefaultCommand() {
	}
	public double getXAccel(){
		return  accelXTask.get();
	}
	public double getYAccel(){
		return accelYTask.get();
	}
	public double getRawXAccel(){
		return  _Accel.getX();
	}
	public double getRawYAccel(){
		return _Accel.getY();
	}
		
	public double getxPos() {
		return xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public double getxVel() {
		return xVel;
	}
	public double getyVel() {
		return yVel;
	}
}