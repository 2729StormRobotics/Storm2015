package org.usfirst.frc.team2729.robot.util;

import org.usfirst.frc.team2729.robot.util.Pipeline.*;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class senseAccel{
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	
	public double hpRC = 0.5, lpRC = 0.1;
	
	ISource accelX = new ISource(){public double get(){return _Accel.getX();}};
	ISource accelY = new ISource(){public double get(){return _Accel.getY();}};
	FilterChain accelXFilter = new FilterChain(new IFilter[]{new HighPassFilter(hpRC, 0), new LowPassFilter(lpRC,0), new Integrator(0), new Integrator(0)});
	FilterChain accelYFilter = new FilterChain(new IFilter[]{new HighPassFilter(hpRC, 0), new LowPassFilter(lpRC,0), new Integrator(0), new Integrator(0)});
	FilterTask accelXTask = new FilterTask(accelXFilter, accelX, 0.01);
	FilterTask accelYTask = new FilterTask(accelXFilter, accelY, 0.01);
	
	public senseAccel(){
		_Accel.setRange(Accelerometer.Range.k4G);
	}
	
	public double getXPos(){
		return  accelXTask.get();
	}
	public double getYPos(){
		return accelYTask.get();
	}
	public double getRawXAccel(){
		return  _Accel.getX();
	}
	public double getRawYAccel(){
		return _Accel.getY();
	}
	public double getXAccel(){
		return accelXFilter.getStageAt(1);
	}
	public double getYAccel(){
		return accelYFilter.getStageAt(1);
	}
	public double getXVel() {
		return accelXFilter.getStageAt(2);
	}
	public double getYVel() {
		return accelYFilter.getStageAt(2);
	}
}