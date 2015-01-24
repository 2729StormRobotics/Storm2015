package org.usfirst.frc.team2729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2729.robot.commands.runAccel;

public class senseAccel extends Subsystem {

	final double g = 9.80665; //1 g in m/s
	
	private final Timer deltaT = new Timer();
	
	private final Accelerometer _Accel = new BuiltInAccelerometer(Accelerometer.Range.k8G);
	public double xPos = 0, yPos = 0, xVel = 0, yVel = 0;
	
	public senseAccel(){
		deltaT.start();
	}
	
	public void update(){
		deltaT.stop();
		xPos += xVel * deltaT.get() + (1/2 * _Accel.getX() * (deltaT.get() * deltaT.get()));
		yPos += yVel * deltaT.get() + (1/2 * _Accel.getY() * (deltaT.get() * deltaT.get()));
		xVel += _Accel.getX() * deltaT.get();
		yVel += _Accel.getY() * deltaT.get();
		deltaT.reset();
	}
	
	protected void initDefaultCommand() {
		//setDefaultCommand(new runAccel());
	}
	public double getXAccel(){
		return _Accel.getX();
	}
	public double getYAccel(){
		return _Accel.getY();
	}
	public double getxPos() {
		return xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public double getxVel() {
		return xVel;
	}
	public double getyVel() {
		return yVel;
	}
}