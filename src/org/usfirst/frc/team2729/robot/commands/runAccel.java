package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.Timer;

public class runAccel extends Command{

	public runAccel(){
		requires(Robot._accel);
		new PrintCommand("runAccel init").start();
	}
	
	protected void initialize(){}

	protected void execute() {
		Robot._accel.setDTEnabled(true);
		Timer.delay(0.01);
		new PrintCommand("runAccel run").start();
		Robot._accel.update();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {}
	protected void interrupted() {}

}
