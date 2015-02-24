package org.usfirst.frc.team2729.robot;

import org.usfirst.frc.team2729.robot.autoModes.OneContainerAuto;
import org.usfirst.frc.team2729.robot.autoModes.OneToteOneContainer;
import org.usfirst.frc.team2729.robot.autoModes.TwoContainerAuto;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;
import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2729.robot.subsystems.Intake;
import org.usfirst.frc.team2729.robot.subsystems.LEDStrip;
import org.usfirst.frc.team2729.robot.subsystems.LinearArm;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;

	//public static rakeArm _rakeArm;
	public static LinearArm linearArm;
	public static LEDStrip LEDs;

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
		LEDs=new LEDStrip();
		//one of these will be chosen by mechanical soon
		//_rakeArm = new rakeArm();
		linearArm = new LinearArm();
		LEDs = new LEDStrip();
		LEDs.connect();
		//OI is init last to make sure it does not reference null subsystems
		oi = new OI();
		
		//complex commands for autoModes
		CommandGroup oneCan = new CommandGroup();
		oneCan.addSequential(new DriveVector(0, false, 1500, 1));
		//oneCan.addSequential(command);
		
		
		//The names and corresponding commands of Auto modes
		autoModeNames = new String[]{"Drive Forward", "1 Container", "2 Container", "1 Tote 1 Container"};
		autoModes = new Command[]{new DriveForward(.45, 1000), new OneContainerAuto(), new TwoContainerAuto(), new OneToteOneContainer()};
		
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
		SmartDashboard.putNumber("Center Encoder", driveTrain.getCenterDistance());
		SmartDashboard.putNumber("Hall Effect count", linearArm.getRawHallCount());
		//SmartDashboard.putBoolean("Bottomed Out", intake.isBottom());
		SmartDashboard.putNumber("String Pot", intake.getElevHeight());
		SmartDashboard.putNumber("Elevator Set Point", intake.getPoint());
		SmartDashboard.putBoolean("High Gear", driveTrain.isHighgear());
		SmartDashboard.putBoolean("Is Clamped", intake.isClamped());
		SmartDashboard.putBoolean("Auto Arm Raised", linearArm.isUp());
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
	}

	public void disabledInit() {
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
		if(DriverStation.getInstance().isOperatorControl()) 
			LEDStrip.stringPotLEDs(Intake.getElevHeight());
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
