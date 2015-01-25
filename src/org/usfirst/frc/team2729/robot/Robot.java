
package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.senseAccel;
import org.usfirst.frc.team2729.robot.subsystems.senseGyro;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Joystick _driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
	public static senseGyro _gyro;
	public static senseAccel _accel;
	
    Command autonomousCommand;
    
    private final Timer _timer = new Timer();
    
    public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		_accel = new senseAccel();
		_gyro  = new senseGyro(0, RobotMap.PORT_SENSOR_GYRO);
		intake = new Intake();
		_timer.schedule(new TimerTask() {
			public void run() {
				_accel.update();
			}
		}, 0, 10);
		new Command("Sensor feedback"){
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {sendSensorData();}
			@Override
			protected boolean isFinished() {return false;}
			@Override
			protected void end() {}
			@Override
			protected void interrupted() {}
		}.start();
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
    }
	public void sendSensorData(){
		SmartDashboard.putNumber("xPos", _accel.getxPos());
		SmartDashboard.putNumber("yPos", _accel.getyPos());
		SmartDashboard.putNumber("xVel",_accel.getxVel());
		SmartDashboard.putNumber("yVel", _accel.getyVel());
		SmartDashboard.putNumber("Accel X", _accel.getXAccel());
		SmartDashboard.putNumber("Accel Y", _accel.getYAccel());
		SmartDashboard.putNumber("Raw Accel X", _accel.getRawXAccel());
		SmartDashboard.putNumber("Raw Accel Y", _accel.getRawYAccel());
		SmartDashboard.putNumber("RC", _accel.rc);
	}
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	CommandGroup Accelerometer = new CommandGroup();
    	Accelerometer.addSequential(new PrintCommand("test1"));
    	Accelerometer.addSequential(new WaitForChildren());
    	Accelerometer.addSequential(new PrintCommand("test2"));
    	Accelerometer.start();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        new PrintCommand(_accel.getXAccel() + " " + _accel.getYAccel()).start();
        sendSensorData();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit(){
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
