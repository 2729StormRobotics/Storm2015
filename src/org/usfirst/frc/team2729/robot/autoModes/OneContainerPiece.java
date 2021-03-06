package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneContainerPiece extends CommandGroup{
	public OneContainerPiece(){
		addParallel(new LinearPiston(false));
		addSequential(new BinAlignHorLinear(0.9, 20), 1.5);
		addSequential(new ExtendUntilPressed(0.5), 4.5);
		addSequential(new WaitCommand(0.4));
		addSequential(new LinearPiston(true));
		addSequential(new WaitCommand(0.2));
		addSequential(new Command(){
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {Robot.linearArm.moveArm(-0.75);}
			@Override
			protected boolean isFinished() {return Robot.linearArm.getRawHallCount() >= 45;}
			@Override
			protected void end() {Robot.linearArm.moveArm(0);}
			@Override
			protected void interrupted() {}
		}, 1.9);
	}
}
