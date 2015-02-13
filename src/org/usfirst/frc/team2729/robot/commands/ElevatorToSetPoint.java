package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToSetPoint extends Command {

	boolean waitUntilButton;
	
	public ElevatorToSetPoint(double setPoint){
		//Robot.intake.setStringPotSP(setPoint);
	}
	
	@Override
	protected void initialize() {
		requires(Robot.intake);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		
	}

}
