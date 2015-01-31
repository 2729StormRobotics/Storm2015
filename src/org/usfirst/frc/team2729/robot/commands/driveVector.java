package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class driveVector extends Command{
	
	private final double p = 50;
	private double _distance;
	private double _xVel, _yVel;
	private double _targetX, _targetY;
	private double _initRotTheta;
	public driveVector(double x, double y, double speed){
		//finds angle relative to top of robot such that clockwise is positive
		
	}
	public driveVector(double theta, boolean isRadians, double distance, double speed){
		_distance = distance;
		double transTheta = isRadians ? (Math.PI/2) - theta : (Math.PI/2) - (theta * (2*Math.PI / 360));
		//_targetX = distance * Math.sin(transTheta);
		//_targetY = distance * Math.cos(transTheta);
		_xVel = Math.cos(transTheta) * speed;
		_yVel = Math.sin(transTheta) * speed;
		_initRotTheta = Robot.driveTrain._gyro.getGyroAngle();
		Robot.driveTrain.resetCenterEnc();
		Robot.driveTrain.resetLeftEnc();
		Robot.driveTrain.resetCenterEnc();
	}
	@Override
	protected void initialize() {
		requires(Robot.driveTrain);
	}
	@Override
	protected void execute() {
		double deltaTheta = Math.abs(Robot.driveTrain._gyro.getGyroAngle() - _initRotTheta);
		Robot.driveTrain.gradientDrive(_xVel, _yVel,deltaTheta > 2 ? deltaTheta/p : 0);
	}
	@Override
	protected boolean isFinished() {
		//I am well aware of how nasty this line is
		//it's that long for efficiency
		if(_distance >= 0.99 * Math.sqrt(
				(Robot.driveTrain.getCenterDistance() *  Robot.driveTrain.getCenterDistance())
				+ (Robot.driveTrain.getLeftDistance()/Robot.driveTrain.getGearRatio()
				*Robot.driveTrain.getLeftDistance()/Robot.driveTrain.getGearRatio()))){
			return false;
		} else {
			return true;
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
