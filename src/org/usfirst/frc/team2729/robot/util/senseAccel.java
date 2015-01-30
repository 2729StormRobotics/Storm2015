package org.usfirst.frc.team2729.robot.util;

import org.usfirst.frc.team2729.robot.util.Pipeline.*;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class senseAccel{
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	
	private final double g = 9.80665; //g in m/s/s 
	
	public double _hpRC, _lpRC;
	private double _dt;
	
	ISource accelX, accelY;
	FilterChain accelXFilter, accelYFilter;
	FilterTask accelXTask, accelYTask;
	public HighPassFilter Tuning1, Tuning2;
	
	public senseAccel(double deltaT, double HPc, double LPc){
		_dt = deltaT;
		_hpRC = HPc;
		_lpRC = LPc;
		_Accel.setRange(Accelerometer.Range.k4G);
		accelX = new ISource(){public double get(){return _Accel.getX();}};
		accelY = new ISource(){public double get(){return _Accel.getY();}};
		OffSetFilter xOff = new OffSetFilter(0.99);
		OffSetFilter yOff = new OffSetFilter(0.99);
		xOff.tune(2, 0.01, accelX, new CutOffFilter(0.4));
		yOff.tune(2, 0.01, accelY, new CutOffFilter(0.4));
		accelXFilter = new FilterChain(new IFilter[]{xOff, new CutOffFilter(0.5), new Integrator(0), new Integrator(0)});
		accelYFilter = new FilterChain(new IFilter[]{yOff, new CutOffFilter(0.5), new Integrator(0), new Integrator(0)});
		accelXTask = new FilterTask(accelXFilter, accelX, _dt);
		accelYTask = new FilterTask(accelYFilter, accelY, _dt);
	}
	
	public double getXPos(){
		return  accelXTask.get() * g;
	}
	public double getYPos(){
		return accelYTask.get() * g;
	}
	public double getRawXAccel(){
		return  _Accel.getX() * g;
	}
	public double getRawYAccel(){
		return _Accel.getY() * g;
	}
	public double getXPrimeAccel(){
		return accelXFilter.getStageAt(0) * g;
	}
	public double getYPrimeAccel(){
		return accelYFilter.getStageAt(0) * g;
	}
	public double getXAccel(){
		return accelXFilter.getStageAt(1) * g;
	}
	public double getYAccel(){
		return accelYFilter.getStageAt(1) * g;
	}
	public double getXVel() {
		return accelXFilter.getStageAt(2) * g;
	}
	public double getYVel() {
		return accelYFilter.getStageAt(2) * g;
	}
}
