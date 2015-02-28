package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Clamp extends Command {
	
	public Clamp(){requires(Robot.intake);}
	@Override
	protected void initialize() {Robot.intake.clamp();}
	@Override
	protected void execute() {}
	@Override
	protected boolean isFinished() {return true;}
	@Override
	protected void end() {}
	@Override
	protected void interrupted() {}
}
