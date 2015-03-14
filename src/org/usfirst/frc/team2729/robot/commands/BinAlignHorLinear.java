package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BinAlignHorLinear extends Command{
	public double _speed;
	public int _hall;
	
	
	public BinAlignHorLinear(double speed, int hallCount){
		requires(Robot.linearArm);
		_speed = speed;
		_hall = hallCount;
	}
	
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		Robot.linearArm.moveArm(_speed);
	}

	@Override
	protected boolean isFinished() {
		return Robot.linearArm.getRawHallCount() >= _hall;
	}

	@Override
	protected void end() {
		Robot.linearArm.moveArm(0);
	}
	@Override
	protected void interrupted() {
		Robot.linearArm.moveArm(0);	
	}
}
