package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class runAccel extends Command{

	public runAccel(){
		requires(Robot._accel);
	}
	
	protected void initialize(){}

	protected void execute() {
		Robot._accel.updateGyro();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {}
	protected void interrupted() {}

}
