package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.Stack;
import org.usfirst.frc.team2729.robot.commands.Unclamp;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;
import org.usfirst.frc.team2729.robot.commands.auto.ToteAlignHor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneToteAuto extends CommandGroup {

	public OneToteAuto() {
		addSequential(new ToteAlignHor(.5));
		addSequential(new DriveVector(0, false, 20, 1));
		addSequential(new Stack());
		addSequential(new MoveToAuto());
		//TODO lower arms
		addSequential(new Unclamp());
	}

}
