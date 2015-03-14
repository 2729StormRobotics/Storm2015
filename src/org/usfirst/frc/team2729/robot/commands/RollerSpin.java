package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerSpin extends Command {

	private boolean spin;
	
	public RollerSpin(boolean spin) {
		requires(Robot.roller);
		spin = spin;
	}
	
	@Override
	protected void initialize() {
		if(spin) Robot.roller.spinIn(); 
		else Robot.roller.spinOut(); 
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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
