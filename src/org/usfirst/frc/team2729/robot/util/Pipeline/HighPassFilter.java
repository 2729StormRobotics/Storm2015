package org.usfirst.frc.team2729.robot.util.Pipeline;

public class HighPassFilter implements IFilter {
	private double _rc;
	private double _val;
	private double _prevInput = 0;

	public HighPassFilter(double rc, double initVal) {
		_rc = rc;
		_val = initVal;
	}
	public void addSample(double input,double dt) {
		double alpha = _rc/(_rc+dt);
		_val = alpha*(input-_prevInput) + alpha*_val;
		_prevInput = input;
	}
	public void setRC(double rc) {
		_rc = rc;
	}
	public double get() {
		return _val;
	}
}