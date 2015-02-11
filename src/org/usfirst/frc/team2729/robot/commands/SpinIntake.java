package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinIntake extends Command{

	double _power;
	
	public SpinIntake(double power){
		_power = power;
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.intake.spin(_power);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.intake.spin(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
