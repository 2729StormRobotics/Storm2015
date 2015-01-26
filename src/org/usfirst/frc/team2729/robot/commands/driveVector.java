package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class driveVector extends Command{
	
	private double _theta, _distance, _speed;
	private double _initX, _initY;
	private double _targetZone;
	
	public driveVector(double x, double y, double speed, double targetZoneWidth){
		//finds angle relative to top of robot such that clockwise is positive
		_theta = Math.atan2(x, y) + Math.PI;
		_initX = Robot.driveTrain._accel.getXPos();
		_initY = Robot.driveTrain._accel.getYPos();
		_targetZone = targetZoneWidth;
	}
	public driveVector(double theta, boolean isRadians, double distance, double speed, double targetZoneWidth){
		_targetX = Math.sin(isRadians ? theta : 360.0/(2*Math.PI)) * distance;
		_targetY = Math.cos(isRadians ? theta : 360.0/(2*Math.PI)) * distance; 
		_speed = speed;
		_initX = Robot.driveTrain._accel.getXPos();
		_initY = Robot.driveTrain._accel.getYPos();
		_targetZone = targetZoneWidth;
	}
	@Override
	protected void initialize() {
		requires(Robot.driveTrain);
	}
	@Override
	protected void execute() {
	
	}
	@Override
	protected boolean isFinished() {
		if(Math.abs(_targetX - _initX) <= _targetZone && Math.abs(_targetY - _initY) <= _targetZone){
			return true;
		} else {
			return false;
		}
	}
	@Override
	protected void end() {
		Robot.driveTrain.halt();
	}
	@Override
	protected void interrupted() {
		Robot.driveTrain.halt();
	}
}
