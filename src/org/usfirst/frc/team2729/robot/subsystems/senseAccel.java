package org.usfirst.frc.team2729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2729.robot.commands.runAccel;

public class senseAccel extends Subsystem {

	final double g = 9.80665; //1 g in m/s
	
	private final Timer deltaT = new Timer();
	
	private final BuiltInAccelerometer _Accel = new BuiltInAccelerometer();
	public double xPos = 0, yPos = 0, xVel = 0, yVel = 0;
	
	public senseAccel(){
		_Accel.setRange(Accelerometer.Range.k2G);
		//SmartDashboard.putNumber("xPos", this.getxPos());
		//SmartDashboard.putNumber("yPos", this.getyPos());
		//SmartDashboard.putNumber("xVel", this.getxVel());
		//SmartDashboard.putNumber("yVel", this.getyVel());
		//SmartDashboard.putNumber("Accel X", this.getXAccel());
		//SmartDashboard.putNumber("Accel Y", this.getYAccel());
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