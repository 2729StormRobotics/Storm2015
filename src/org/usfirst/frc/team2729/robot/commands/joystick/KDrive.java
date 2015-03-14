package org.usfirst.frc.team2729.robot.commands.joystick;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class KDrive extends Command{

	public KDrive(){
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double left = Robot.oi.getLeftDrive(),
			   right = Robot.oi.getRightDrive();
		Robot.driveTrain.kDrive(left, right);
		
		double cardinal = Robot.oi.getCardinalDrive();
		if(left==0 && right==0) Robot.driveTrain.kDrive(.8*Math.abs(cardinal)/cardinal, .8*Math.abs(cardinal)/cardinal);
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
