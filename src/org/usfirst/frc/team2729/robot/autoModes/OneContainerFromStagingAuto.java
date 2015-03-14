package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainerFromStagingAuto extends CommandGroup{
	public OneContainerFromStagingAuto(){
		addSequential(new DriveForward(.7, 500));
		addSequential(new ElevatorClamp(true));
		addSequential(new ChangeElevPosition(1));
		addSequential(new DriveForward(.4, 100));
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {return Robot.driveTrain.getLeftDistance() >= 225;}
			@Override
			protected void interrupted() {}
			@Override
			protected void initialize() {
				Robot.driveTrain.resetLeftEnc();
				Robot.driveTrain.resetRightEnc();
			}
			@Override
			protected void execute() {Robot.driveTrain.kDrive(0.3, 0.3);}
			@Override
			protected void end() {Robot.driveTrain.halt();}
		});
		addSequential(new DriveForward(.8, 600));
	}
}
