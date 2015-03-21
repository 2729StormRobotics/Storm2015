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
		addSequential(new Turn(120));
		addSequential(new DriveForward(0.8, 650));
		addSequential(new Turn(-120));
	}
}
