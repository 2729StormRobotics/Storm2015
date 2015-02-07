package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.commands.Stack;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftYellowStackMove extends CommandGroup{
	public LiftYellowStackMove(){
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
		addSequential(new LiftYellowMove()); //picks up last tote and goes to auto
		
	}
}
