package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.Strafe;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignHorLinear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoContainerAuto extends CommandGroup{

	public TwoContainerAuto(){
		addSequential(new OneContainerAuto());
		addSequential(new DriveForward(-0.5, 530));
		addSequential(new WaitCommand(1.5));
		addSequential(new CommandGroup(){
			//Anonymous Constructor. Fun things to use
			{
				addSequential(new BinAlignHorLinear(0.5, 35+46), 2.5);
				addSequential(new PrintCommand("Waiting"));
				addSequential(new WaitCommand(0.2));
				addSequential(new PrintCommand("Linear up"));
				addSequential(new LinearPiston(true));
				addSequential(new WaitCommand(0.35));
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
			addSequential(new Strafe(-1), 1);
			}
		});
	}
}
