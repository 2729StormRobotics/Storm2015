
package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.commands.runAccel;
import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.senseAccel;
import org.usfirst.frc.team2729.robot.subsystems.senseGyro;

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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Joystick _driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
	public static senseGyro _gyro;
	public static senseAccel _accel;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		_accel = new senseAccel();
		_gyro  = new senseGyro();
		intake = new Intake();
		new Command("Sensor feedback"){

			@Override
			protected void initialize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void execute() {
				sendSensorData();
			}

			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub
				
			}
			
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
	}
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	CommandGroup Accelerometer = new CommandGroup();
    	Accelerometer.addSequential(new PrintCommand("test1"));
    	Accelerometer.addParallel(new runAccel());
    	Accelerometer.addSequential(new PrintCommand("test2"));
    	Accelerometer.addSequential(new WaitForChildren());
    	Accelerometer.addSequential(new PrintCommand("test3"));
    	Accelerometer.start();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
