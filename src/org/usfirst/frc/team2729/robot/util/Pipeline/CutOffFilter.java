package org.usfirst.frc.team2729.robot.util.Pipeline;

import java.util.ArrayList;

public class CutOffFilter implements IFilter{
	private double _prevVal, _val, _cutOff;
	private double runAvg, runNUM;
	private ArrayList prevs = new ArrayList<Double>();
	public CutOffFilter(double cutOff){
		_cutOff = cutOff;
		runAvg = 0;
	}
	
	@Override
	public double get() {
		return _val;
	}

	@Override
	public void addSample(double newVal, double dt) {
		if(prevs.size() > 100){
			if(Math.abs(newVal - runAvg) < _cutOff){
				_val = newVal;
				prevs.add(newVal);
				prevs.remove(0);
				runAvg = average(prevs);
			}
		} else {
			if(Math.abs(newVal - _prevVal) < _cutOff){
				_val = newVal;
			}
			_prevVal =  newVal;
		}
	}
	public void tune(){
		//TODO: find way to do this
	}
	private double average(ArrayList<Double> a){
		double accum = 0;
		for(int i = 0; i < a.size(); i++){
			accum += a.get(i);
		}
		return (accum/a.size());
	}
}
