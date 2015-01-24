package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RakeArm extends Command{

	public RakeArm(){
		requires(Robot._rakeArm);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double rake = Robot.oi.getRake();
		Robot._rakeArm.moveArm(rake);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	

//require dat rakearm
}
