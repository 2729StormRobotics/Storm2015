package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.RakeArmRaised;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignDepth;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignHor;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;
import org.usfirst.frc.team2729.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneContainerRake extends CommandGroup{
	//TODO: convert encoder ticks (with respect to x) to feet
	public OneContainerRake(){
		addSequential(new Shift(true));
		addParallel(new RakeArmRaised(false, 1));
		addSequential(new DriveVector(0, true, 2000, 1));//TODO: find 3rd arg
		addSequential(new BinAlignDepth(0.25));
		addSequential(new BinAlignHor(0.5));
		addSequential(new RakeArmRaised(true, 1));
		addSequential(new DriveVector(-90, false, 500, 0.75));
		addSequential(new TurnToAngle(-90, .9));
		addSequential(new RakeArmRaised(false, 1));
		addSequential(new DriveVector(-90, false, 3000, 1)); //Drive into the auto zone
	}
}