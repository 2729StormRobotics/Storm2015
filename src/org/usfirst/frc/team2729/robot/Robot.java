
package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.commands.runAccel;
import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.rakeArm;
import org.usfirst.frc.team2729.robot.subsystems.senseAccel;
import org.usfirst.frc.team2729.robot.subsystems.senseGyro;
import org.usfirst.frc.team2729.robot.subsystems.linearArm;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
	public static rakeArm _rakeArm;
	public static linearArm _linearArm;
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		intake = new Intake();
		_rakeArm = new rakeArm();
		_linearArm = new linearArm();
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	CommandGroup Accelerometer = new CommandGroup();
    	Accelerometer.addParallel(new runAccel());
    	Accelerometer.addSequential(new WaitForChildren());
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
