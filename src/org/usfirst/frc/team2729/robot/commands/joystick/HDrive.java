package org.usfirst.frc.team2729.robot.commands.joystick;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class HDrive extends Command {

	public HDrive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double xDrive = Robot.oi.getXDrive(), yDrive = Robot.oi.getYDrive(), spin = Robot.oi
				.getSpin();
		new PrintCommand("x: " + xDrive + " y: " + yDrive).start();
		//joystick values [-1, 1]
		System.out.println("x: " + xDrive + " y: " + yDrive);
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
