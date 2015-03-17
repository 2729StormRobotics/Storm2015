package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerClamp extends Command {
	private boolean _clamp;
	
	public RollerClamp(boolean clamp) {
		_clamp = clamp;
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		//TODO: Check that value below
		if(_clamp && Robot.intake.getElevHeight() > 0.0){
			Robot.roller.clamp(); 
		}else{
			Robot.roller.unclamp();
		}
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
