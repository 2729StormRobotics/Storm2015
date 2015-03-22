package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetHall extends Command{

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.linearArm.resetHall();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

	
	
}
