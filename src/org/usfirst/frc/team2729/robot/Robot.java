package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.autoModes.OneContainerAuto;
import org.usfirst.frc.team2729.robot.autoModes.OneContainerPiece;
import org.usfirst.frc.team2729.robot.autoModes.OneContainerFromStagingAuto;
import org.usfirst.frc.team2729.robot.autoModes.OneToteAuto;
import org.usfirst.frc.team2729.robot.autoModes.OneToteOneContainer;
import org.usfirst.frc.team2729.robot.autoModes.SecretProject;
import org.usfirst.frc.team2729.robot.autoModes.ThreeToteFromStaging;
import org.usfirst.frc.team2729.robot.autoModes.TwoContainerAuto;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.LinearArm;
import org.usfirst.frc.team2729.robot.subsystems.Roller;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Roller roller;

	//public static rakeArm _rakeArm;
	public static LinearArm linearArm;

	public static Command teleop;
	Compressor compressor;

	Command autoCommand;
	String[] autoModeNames;
	Command[] autoModes;
	SendableChooser chooser = new SendableChooser();
	Command selectedAutoMode;
	//Test code
	double maxRightSpeed = 0;
	double maxLeftSpeed = 0;
	double maxCenterSpeed =0;
	public void robotInit() {
		driveTrain = new DriveTrain();
		intake = new Intake();
		compressor = new Compressor();
		compressor.start();
		//one of these will be chosen by mechanical soon
		//_rakeArm = new rakeArm();
		linearArm = new LinearArm();
		//OI is init last to make sure it does not reference null subsystems
		oi = new OI();	
		roller = new Roller();
		//The names and corresponding commands of Auto modes
		autoModeNames = new String[]{"Drive Forward", "1 Container", "2 Container", "1 Tote 1 Container", 
				"1 Container Staging", "1 Tote", "3 Tote", "Secretive"};
		autoModes = new Command[]{new DriveForward(.45, 500), new OneContainerAuto(), new TwoContainerAuto(), 
				new OneToteOneContainer(), new OneContainerFromStagingAuto(), new OneToteAuto(), new ThreeToteFromStaging(), new SecretProject()};
		
		//configure and send the sendableChooser, which allows the modes
		//to be chosen via radio button on the SmartDashboard
		for(int i = 0; i < autoModes.length; ++i){
			chooser.addObject(autoModeNames[i], autoModes[i]);
		}
		SmartDashboard.putData("Which Autonomouse mode?", chooser);
		SmartDashboard.putData(Scheduler.getInstance());

		new Command("Sensor feedback") {
			@Override
			protected void initialize() {
			}

			@Override
			protected void execute() {
				sendSensorData();
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void end() {
			}

			@Override
			protected void interrupted() {
			}
		}.start();
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
    }
	public void sendSensorData(){
		SmartDashboard.putNumber("Right Encoder", driveTrain.getRightDistance());
		SmartDashboard.putNumber("Left Encoder", driveTrain.getLeftDistance());
		SmartDashboard.putNumber("Hall Effect count", linearArm.getRawHallCount());
		SmartDashboard.putNumber("String Pot", intake.getElevHeight());
		SmartDashboard.putNumber("Elevator Set Point", intake.getPoint());
		SmartDashboard.putBoolean("High Gear", driveTrain.isHighgear());
		SmartDashboard.putBoolean("Is Clamped", intake.isClamped());
		SmartDashboard.putBoolean("Auto Arm Raised", linearArm.isUp());
		SmartDashboard.putBoolean("Linear Arm Pressed", linearArm.isPressed());
		SmartDashboard.putBoolean("Can Clamp", intake.getElevHeight() > 0.0);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	public void autonomousInit() {
		if (teleop != null)
			teleop.cancel();
		selectedAutoMode = (Command) chooser.getSelected();
		if (selectedAutoMode != null)
			selectedAutoMode.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	public void teleopInit() {
		if (selectedAutoMode != null)
			selectedAutoMode.cancel();
		if (teleop != null)
			teleop.start();
		driveTrain.setHighGear(false);
	}

	public void disabledInit() {
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
