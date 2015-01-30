package org.usfirst.frc.team2729.robot.util.Pipeline;

public class LowPassFilter implements IFilter {
	private double _rc;
	private double _val;
	
	public LowPassFilter(double rc, double initVal) {
		_rc = rc;
		_val = initVal;
	}
	public void addSample(double newVal,double dt) {
		double alpha = dt/(_rc+dt);
		_val = alpha*newVal + (1-alpha)*_val;
	}
	public void setRC(double rc) {
		_rc = rc;
	}
	public double get() {
		return _val;
	}
	public double getRC() {
		return _rc;
	}
}
