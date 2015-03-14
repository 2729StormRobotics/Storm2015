package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerClamp extends Command {
	private boolean clamp;
	
	public RollerClamp(boolean clamp) {
		requires(Robot.roller);
		clamp = clamp;
	}
	
	@Override
	protected void initialize() {
		if(clamp) Robot.roller.clamp(); 
		else Robot.roller.unclamp(); 
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
