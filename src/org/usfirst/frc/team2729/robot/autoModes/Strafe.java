package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Strafe extends Command{

	private double _power;
	
	public Strafe(double power){
		_power = power;
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.driveTrain.strafeLeft(_power);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.strafeLeft(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
