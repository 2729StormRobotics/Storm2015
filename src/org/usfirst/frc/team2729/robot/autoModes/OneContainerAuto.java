package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainerAuto extends CommandGroup{
	public OneContainerAuto(){
		addSequential(new OneContainerPiece());
		addSequential(new Shift(false));
		addParallel(new Command(){
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {Robot.linearArm.moveArm(-0.75);}
			@Override
			protected boolean isFinished() {return Robot.linearArm.getRawHallCount() >= 46;}
			@Override
			protected void end() {Robot.linearArm.moveArm(0);}
			@Override
			protected void interrupted() {}
		}, 1.2);
		addSequential(new Turn(120));
		addSequential(new DriveForward(0.8, 500));
		addSequential(new Turn(-170));
	}
}
