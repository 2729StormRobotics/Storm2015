package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoElevator extends Command {
	double _power;
	public AutoElevator(double power){
		_power = power;
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		Robot.intake.setElevatorPower(_power);
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
