package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerSpinAuto extends Command {

	private double _spin;
	
	public RollerSpinAuto(double spin) {
		_spin = spin;
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.setRollerPower(_spin);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
