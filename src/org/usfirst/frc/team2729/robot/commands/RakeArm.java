package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RakeArm extends Command{

	public RakeArm(){
		requires(Robot._rakeArm);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double rake = Robot.oi.getRake();
		Robot._rakeArm.moveArm(rake);	
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}

//require dat rakearm
}
