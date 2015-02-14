package org.usfirst.frc.team2729.robot.commands;
import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LinearArm extends Command {
	
	public LinearArm(){
		requires(Robot.linearArm);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double Linear = Robot.oi.getRake();
		Robot.linearArm.moveArm(Linear);
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
