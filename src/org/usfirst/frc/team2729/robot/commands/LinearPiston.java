package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LinearPiston extends Command {
	private final boolean _extend;

	public LinearPiston(boolean extend) {
		requires(Robot.linearArm);
		_extend = extend;
	}
	@Override
	protected void initialize() {
		if(_extend) Robot.linearArm.raise();
		else Robot.linearArm.lower();
	}
	@Override
	protected void execute() {

	}
	@Override
	protected boolean isFinished() {
		return true;
	}
	@Override
	protected void end() {

	}
	@Override
	protected void interrupted() {

	}

}
