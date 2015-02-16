package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {
	private static final double P_GAIN = 0.02;
	static {
		SmartDashboard.putNumber("Encoder feedback gain", P_GAIN);
	}
	final double _speed;
	final double _distance;
	public DriveForward(double speed, double distance) {
		requires(Robot.driveTrain);
		_speed = speed;
		_distance = distance;
	}
	protected void initialize() {
		Robot.driveTrain.resetCenterEnc();
		Robot.driveTrain.resetRightEnc();
		Robot.driveTrain.resetLeftEnc();
	}
	
	protected void execute() {
		double gain = SmartDashboard.getNumber("Encoder feedback gain",P_GAIN);
		double err = Robot.driveTrain.getRightDistance()-Robot.driveTrain.getLeftDistance();
		double diff = err*gain;
		double left = _speed + diff/2,
		right = _speed - diff/2;
		if(right < -1) {
			left -= (right+1);
			right = -1;
		} else if(right > 1) {
			left -= (right-1);
			right = 1;
		}
		if(left < -1) {
			right -= (left+1);
			left = -1;
		} else if(left > 1) {
			right -= (left-1);
			left = 1;
		}
		Robot.driveTrain.kDrive(Math.max(-1, Math.min(1, left )),
								-Math.max(-1, Math.min(1, right)));
	}
	protected boolean isFinished() {
		double left = Robot.driveTrain.getLeftDistance(),
			   right = Robot.driveTrain.getRightDistance();
		double distance = Math.abs(left) > Math.abs(right) ? left : right;
		
		return (_speed >= 0 && distance >= _distance)
			|| (_speed < 0 && distance <= -_distance);
	}
	protected void end() {
		Robot.driveTrain.kDrive(0, 0);
	}
	protected void interrupted() {
		end();
	}
}