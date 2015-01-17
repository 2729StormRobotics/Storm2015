package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HDrive extends Command{

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double xDrive = Robot.oi.getXDrive(),
			   yDrive = Robot.oi.getYDrive(),
			   spin   = Robot.oi.getSpin();
		Robot.driveTrain.gradientDrive(xDrive, yDrive, spin);
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
