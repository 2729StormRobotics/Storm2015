package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Elevator extends Command {

	protected void initialize() {
		requires(Robot.intake);
	}

	protected void execute() {
		double elevator = Robot.oi.getElevator();
		Robot.intake.setElevator(elevator);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		
	}

}
