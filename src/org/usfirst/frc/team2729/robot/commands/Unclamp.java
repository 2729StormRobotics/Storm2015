package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Unclamp extends Command {

	public Unclamp(){requires(Robot.intake);}
	protected void initialize() {Robot.intake.unclamp();}
	protected void execute() {}
	protected boolean isFinished() {return true;}
	protected void end() {}
	protected void interrupted() {}
}
