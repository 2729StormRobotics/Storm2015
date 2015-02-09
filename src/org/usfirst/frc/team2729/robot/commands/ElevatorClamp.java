package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorClamp extends Command {
	private final boolean _clamp;

	public ElevatorClamp(boolean clamp) {
		requires(Robot.intake);
		_clamp = clamp;
	}

	protected void initialize() {
		if (_clamp)
			Robot.intake.clamp();
		else
			Robot.intake.unclamp();
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {

	}

}
