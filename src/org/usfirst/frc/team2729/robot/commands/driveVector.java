package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class driveVector extends Command{
	
	private double _targetX, _targetY, _speed;
	private double _initX, _initY;
	private double _targetZone;
	
	public driveVector(double x, double y, double speed, double targetZoneWidth){
		_targetX = x;
		_targetY = y;
		_speed = speed;
		_initX = Robot._accel.getxPos();
		_initY = Robot._accel.getyPos();
		_targetZone = targetZoneWidth;
	}
	public driveVector(double theta, boolean isRadians, double distance, double speed, double targetZoneWidth){
		_targetX = Math.sin(isRadians ? theta : 360.0/(2*Math.PI)) * distance;
		_targetY = Math.cos(isRadians ? theta : 360.0/(2*Math.PI)) * distance; 
		_speed = speed;
		_initX = Robot._accel.getxPos();
		_initY = Robot._accel.getyPos();
		_targetZone = targetZoneWidth;
	}
	@Override
	protected void initialize() {
		requires(Robot._accel);
		requires(Robot.driveTrain);
	}
	@Override
	protected void execute() {
		double dX = _targetX - _initX, dY = _targetY - _initY;
		Robot.driveTrain.rawDrive(Math.abs(dX) <= _targetZone ? (dX > 0 ? _speed : -_speed) : 0,
								  Math.abs(dY) <= _targetZone ? (dY > 0 ? _speed : -_speed) : 0, 0);
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
