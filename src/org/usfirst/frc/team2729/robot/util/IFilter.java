package org.usfirst.frc.team2729.robot.util;

public interface IFilter extends ISource {
	void addSample(double newVal,double dt);
}
