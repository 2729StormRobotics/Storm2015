package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Elevator extends Command {

	public Elevator(){
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		double elevator = Robot.oi.getElevator();
		
		if((elevator == 0.0) || (Math.abs(elevator) == elevator 
				&& Robot.intake.getElevHeight() <= 0.0 && Robot.roller.isClamped())){
			Robot.intake.setElevatorPower(0);
		}else{
			Robot.intake.setElevatorPower(elevator);
		}
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
