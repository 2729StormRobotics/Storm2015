package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArmsToPickUpPos  extends Command {

	public LowerArmsToPickUpPos(){
		requires(Robot.intake);
	}
	
	@Override
	protected void initialize(){
		
	}

	@Override
	protected void execute() {
	Robot.intake.setElevator(-1);
	
		
	}

	@Override
	protected boolean isFinished() {
		//TODO FIND REAL VALUES FOR ELEVATOR DISTANCE
    	if(Robot.intake.getElevatorDistance() > 1 && Robot.intake.getElevatorDistance() < 2){
    		return true;
    	}
    	return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
