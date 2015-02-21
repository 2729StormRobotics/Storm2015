package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDChangeMode extends Command{
	private byte _mode;
	public LEDChangeMode(byte mode){
		_mode=mode;
	}
	
	protected void initialize() {
		Robot.LEDs.setMode(_mode);
	}
	protected void execute() {}

	
	protected boolean isFinished() {
		return true;
	}

	protected void end() {}

	protected void interrupted() {}
	
}