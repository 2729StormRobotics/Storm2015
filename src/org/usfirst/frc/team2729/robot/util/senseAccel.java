package org.usfirst.frc.team2729.robot.util;

import org.usfirst.frc.team2729.robot.util.Pipeline.*;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class senseAccel implements LiveWindowSendable{
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
		Tuning1 = new HighPassFilter(_hpRC, 0);
		Tuning2 = new HighPassFilter(_hpRC, 0);
		//accelXFilter = new FilterChain(new IFilter[]{Tuning1, new LowPassFilter(_lpRC, 0), new Integrator(0), new Integrator(0)});
		//accelYFilter = new FilterChain(new IFilter[]{Tuning2, new LowPassFilter(_lpRC, 0), new Integrator(0), new Integrator(0)});
		accelXFilter = new FilterChain(new IFilter[]{new Integrator(0), new Integrator(0)});
		accelYFilter = new FilterChain(new IFilter[]{new Integrator(0), new Integrator(0)});
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

	private final ITableListener listener = new ITableListener() {
		public void valueChanged(ITable table, String key, Object value, boolean isNew){
			if (key.equals("hpRC")){
				if(_hpRC != ((Double) value).doubleValue()){
					_hpRC = ((Double) value).doubleValue();
				}
			} else if (key.equals("lpRC")) {
				if(_lpRC != ((Double) value).doubleValue()){
					_lpRC = ((Double) value).doubleValue();
				}
			}
		}
	};
	
	private ITable table;
	public void initTable(ITable table) {
		if(this.table!=null){
			this.table.removeTableListener(listener);
		}
		this.table = table;
		if(table!=null){
			table.putNumber("hpRC", Tuning1._rc);
			table.putNumber("lpRC", Tuning2._rc);
		}
	}

	@Override
	public ITable getTable() {
		return table;
	}

	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return "Sense accel";
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startLiveWindowMode() {	
	}

	@Override
	public void stopLiveWindowMode() {
	}
}
