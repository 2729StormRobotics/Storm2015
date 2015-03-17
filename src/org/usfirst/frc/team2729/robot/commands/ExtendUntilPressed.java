package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendUntilPressed extends Command{
	
	private double _speed;
	
	public ExtendUntilPressed(double speed) {
		_speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.linearArm.moveArm(_speed);
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return Robot.linearArm.isPressed();
	}

	@Override
	protected void end() {
		Robot.linearArm.moveArm(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
