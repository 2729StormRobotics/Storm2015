package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.HDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class senseAccel extends Subsystem {

	final double g = 9.80665; //1 g in m/s
	
	private final Timer deltaT = new Timer();
	
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	double xPos = 0, yPos = 0, xVel = 0, yVel = 0;
	
	public void senseAccel(){
		SmartDashboard.putNumber("xPos", xPos);
		SmartDashboard.putNumber("yPos", yPos);
		SmartDashboard.putNumber("xVel", xVel);
		SmartDashboard.putNumber("yVel", yVel);
		deltaT.start();
	}
	
	public void updateGyro(){
		deltaT.stop();
		xPos += xVel * deltaT.get() + (1/2 * _Accel.getX() * (deltaT.get() * deltaT.get()));
		yPos += yVel * deltaT.get() + (1/2 * _Accel.getY() * (deltaT.get() * deltaT.get()));
		xVel += _Accel.getX() * deltaT.get();
		yVel += _Accel.getY() * deltaT.get();
		deltaT.reset();
	}
	
	protected void initDefaultCommand() {
	}
}
