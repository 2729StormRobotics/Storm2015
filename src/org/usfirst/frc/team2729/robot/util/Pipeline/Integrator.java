package org.usfirst.frc.team2729.robot.util.Pipeline;

public class Integrator implements IFilter {
	// Trapezoidal integrator
	double _prevInput = 0;
	double _accum = 0;

	public Integrator(double initValue) {
		_accum = initValue;
	}
	public double get() {
		return _accum;
	}

	public void addSample(double newVal, double dt) {
		_accum += dt * ((newVal + _prevInput) / 2);
		_prevInput = newVal;
	}

}
