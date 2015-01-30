package org.usfirst.frc.team2729.robot.util.Pipeline;

public class CutOffFilter implements IFilter{
	private double _prevVal, _val, _cutOff;
	
	public CutOffFilter(double cutOff){
		_cutOff = cutOff;
	}
	
	@Override
	public double get() {
		return _val;
	}

	@Override
	public void addSample(double newVal, double dt) {
		if(Math.abs(newVal - _prevVal) < _cutOff){
			_val = newVal;
		}
		_prevVal =  newVal;
	}
	public void tune(){
		//TODO: find way to do this
	}

}
