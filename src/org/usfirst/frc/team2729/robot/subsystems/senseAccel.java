package org.usfirst.frc.team2729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team2729.robot.commands.runAccel;

public class senseAccel extends Subsystem {

	final double g = 9.80665; //1 g in m/s
	
	private final Timer deltaT = new Timer();
	
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	double xPos = 0, yPos = 0, xVel = 0, yVel = 0;
	
	public senseAccel(){
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
		setDefaultCommand(new runAccel());
	}
}