package org.usfirst.frc.team2729.robot.util;

import org.usfirst.frc.team2729.robot.util.Pipeline.*;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class senseAccel{
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	
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
		Tuning1 = new HighPassFilter(_hpRC, 0);
		Tuning2 = new HighPassFilter(_hpRC, 0);
		accelXFilter = new FilterChain(new IFilter[]{Tuning1, new LowPassFilter(_lpRC, 0), new Integrator(0), new Integrator(0)});
		accelYFilter = new FilterChain(new IFilter[]{Tuning2, new LowPassFilter(_lpRC, 0), new Integrator(0), new Integrator(0)});
		accelXTask = new FilterTask(accelXFilter, accelX, _dt);
		accelYTask = new FilterTask(accelYFilter, accelY, _dt);
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