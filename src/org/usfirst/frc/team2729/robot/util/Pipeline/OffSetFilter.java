package org.usfirst.frc.team2729.robot.util.Pipeline;

import edu.wpi.first.wpilibj.Timer;

public class OffSetFilter implements IFilter{
	private double _offset, _range, _confidence;
	private double _val;
	
	public OffSetFilter(double confidence){
		_confidence = confidence;
	}
	
	@Override
	public double get(){
		return _val;
	}

	@Override
	public void addSample(double newVal, double dt) {
		_val = Math.abs(newVal - _offset) > _range/2 ? newVal - _offset : _val;
	}
	
	public void tune(double tuneTime, double tuneDT, ISource s, IFilter f){
		double accum = 0;
		double largest = 0, smallest = 0;
		FilterTask tune = new FilterTask(f, s, tuneTime/tuneDT);
		for(int i = 0; i < tuneTime/tuneDT; i++){
			Timer.delay(.01);
			accum += s.get();
			if(s.get() < smallest){
				smallest = tune.get();
			}
			if(s.get() < largest){
				largest = tune.get();
			}
		}
		_offset = accum/(tuneTime/tuneDT);
		_range = largest - smallest;
	}

}
