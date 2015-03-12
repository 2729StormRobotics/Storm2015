package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignHorLinear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoContainerAuto extends CommandGroup{

	public TwoContainerAuto(){
		addSequential(new OneContainerAuto());
		addParallel(new Command() {
			@Override
			protected boolean isFinished() {
				return Robot.linearArm.getRawHallCount() >=46;
			}
			@Override
			protected void interrupted() {
				end();
			}
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {
				Robot.linearArm.moveArm(-1);
			}			
			@Override
			protected void end() {
				Robot.linearArm.moveArm(0);
			}
		});
		addSequential(new DriveForward(-0.5, 530));
		addSequential(new CommandGroup(){
			//Anonymous Constructor. Fun things to use
			{
				//addSequential(new BinAlignDepth(0.6));
				addSequential(new BinAlignHorLinear(0.9, 35+46), 2.5);
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
			addSequential(new Command() {
				
				@Override
				protected boolean isFinished() {
					// TODO Auto-generated method stub
					return Robot.driveTrain.getLeftDistance() <= -225;
				}
				
				@Override
				protected void interrupted() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				protected void initialize() {
					Robot.driveTrain.resetLeftEnc();
					Robot.driveTrain.resetRightEnc();
				}
				
				@Override
				protected void execute() {
					Robot.driveTrain.kDrive(-0.3, -0.3);
				}
				
				@Override
				protected void end() {
					Robot.driveTrain.halt();
				}
			});
			addSequential(new DriveForward(0.8, 800));
			}
		});
	}
}
