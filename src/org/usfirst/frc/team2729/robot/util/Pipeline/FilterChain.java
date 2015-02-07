package org.usfirst.frc.team2729.robot.util.Pipeline;

public class FilterChain implements IFilter{
	
	private IFilter[] _chain;
	
	public FilterChain(IFilter[] chain){
		_chain = chain;
	}
	
	public double get() {
		return _chain[_chain.length-1].get();
	}

	public void addSample(double newVal, double dt) {
		_chain[0].addSample(newVal, dt);
		for(int i = 1; i < _chain.length; i++){
			_chain[i].addSample(_chain[i-1].get(), dt);
		}
	}
	public double getLength(){
		return _chain.length;
	}
	public double getStageAt(int i){
		return _chain[i].get();
	}
}
