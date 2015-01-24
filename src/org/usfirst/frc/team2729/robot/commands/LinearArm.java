package org.usfirst.frc.team2729.robot.commands;
import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LinearArm extends Command {
	
	public LinearArm(){
	requires(Robot._linearArm);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double Linear = Robot.oi.get_linear();
		Robot._linearArm.moveArm(Linear);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
