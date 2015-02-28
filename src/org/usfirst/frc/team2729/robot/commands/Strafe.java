package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Strafe extends Command{

	private double _power;
	
	public Strafe(double power){_power = power;}
	protected void initialize() {}
	protected void execute() {
		Robot.driveTrain.strafeLeft(_power);
	}
	protected boolean isFinished() {return false;}
	protected void end() {Robot.driveTrain.strafeLeft(0);}
	protected void interrupted() {end();}
}
