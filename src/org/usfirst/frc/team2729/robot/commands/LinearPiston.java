package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LinearPiston extends Command {
	private final boolean _extend;
	
	public LinearPiston(boolean extend) {
		requires(Robot.linearArm);
		_extend = extend;
	}
	
	protected void initialize() {
		if(_extend) Robot.linearArm.extend();
		else Robot.linearArm.retract();
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		
	}

}
