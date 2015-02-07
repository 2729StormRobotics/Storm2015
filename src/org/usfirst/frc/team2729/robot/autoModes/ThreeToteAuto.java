package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.Stack;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignDepth;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignHor;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteAuto extends CommandGroup{
	public ThreeToteAuto(){
		addSequential(new DriveVector(0,false,20,1));//moves forward a little
		addSequential(new Stack());//picks up tote
		addSequential(new DriveVector(0,false,-20,1));//moves back a little
		addSequential(new DriveVector(100, 0, 1));//moves to right
		addSequential(new BinAlignHor(.5)); //aligns
		addSequential(new BinAlignDepth(.5)); //aligns
		addSequential(new DriveVector(0,false, 20,1));//moves forward a little
		addSequential(new Stack()); //repeat
		addSequential(new DriveVector(0,false, -20,1));//moves back a little
		addSequential(new DriveVector(100, 0, 1));
		addSequential(new BinAlignHor(.5));
		addSequential(new BinAlignDepth(.5));
		addSequential(new OneToteAuto()); //picks up last tote and goes to auto
		
	}
}
