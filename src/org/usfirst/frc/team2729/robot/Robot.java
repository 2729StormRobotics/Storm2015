package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.rakeArm;
import org.usfirst.frc.team2729.robot.subsystems.linearArm;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Joystick _driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
	public static rakeArm _rakeArm;
	public static linearArm _linearArm;
	Compressor compressor;
    Command autonomousCommand;
    
    public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		intake = new Intake();
		compressor = new Compressor();
		compressor.start();
		//_rakeArm = new rakeArm();
		//_linearArm = new linearArm();
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
