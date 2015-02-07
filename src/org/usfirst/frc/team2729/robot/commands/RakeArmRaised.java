package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RakeArmRaised extends Command{
	private double speed;
	private double target;
	private static final double FINAL_RANGE = 1;
	public RakeArmRaised(boolean raise, double _speed){//raise specifies if the target 
		requires(Robot.rakeArm);					   //is the high target
		speed = _speed;
		target = raise ? Robot.rakeArm.VAL_POS_UP : Robot.rakeArm.VAL_POS_DOWN;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.rakeArm.moveArm(Robot.rakeArm.readStringPot() < target ? speed : -speed);	
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(Robot.rakeArm.readStringPot() - target) < FINAL_RANGE){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
		Robot.rakeArm.moveArm(0);
	}
	
	@Override
	protected void interrupted() {
		Robot.rakeArm.moveArm(0);
	}
}
