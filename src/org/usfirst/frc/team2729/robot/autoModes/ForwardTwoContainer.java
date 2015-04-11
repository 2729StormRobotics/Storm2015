package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.BinAlignHorLinear;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ExtendUntilPressed;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ForwardTwoContainer extends CommandGroup{

	public ForwardTwoContainer(){
		addSequential(new OneContainerPiece());
		addSequential(new LinearPiston(false));
		addSequential(new WaitCommand(0.75));
		addSequential(new Command(){
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {Robot.linearArm.moveArm(-1);}
			@Override
			protected boolean isFinished() {return Robot.linearArm.getRawHallCount() >= 46;}
			@Override
			protected void end() {Robot.linearArm.moveArm(0);}
			@Override
			protected void interrupted() {}
			
		}, 1);
		addSequential(new DriveForward(1, 393));
		addSequential(new CommandGroup(){
			//Anonymous Constructor. Fun things to use
			{
				//addSequential(new BinAlignDepth(0.6));
				addSequential(new BinAlignHorLinear(0.9, 19+46), 2);
				addSequential(new ExtendUntilPressed(0.47), 3);
				addSequential(new WaitCommand(0.2));
				addSequential(new PrintCommand("Linear up"));
				addSequential(new LinearPiston(true));
				addSequential(new WaitCommand(0.75));
				addSequential(new Command(){
					@Override
					protected void initialize() {}
					@Override
					protected void execute() {Robot.linearArm.moveArm(-1);}
					@Override
					protected boolean isFinished() {
						return Robot.linearArm.getRawHallCount() >= 48+45;
					}
					@Override
					protected void end() {Robot.linearArm.moveArm(0);}
					@Override
					protected void interrupted() {}
				}, 1.5);
				addSequential(new Turn(120));
				addSequential(new DriveForward(0.8, 500));
				addSequential(new Turn(-170));
			}
		});
	}
}
