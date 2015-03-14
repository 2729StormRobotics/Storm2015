package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneToteOneContainer extends CommandGroup{
	public OneToteOneContainer(){
		addSequential(new ElevatorClamp(true));
		addSequential(new WaitCommand(0.5));
		addSequential(new ChangeElevPosition(1));
		addSequential(new ChangeElevPosition(1));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveForward(.7, 300));
		addSequential(new Command() {
			
			@Override
			protected boolean isFinished() {
				return Robot.driveTrain.getLeftDistance() <= -225;
			}
			
			@Override
			protected void interrupted() {
				end();
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
				Robot.driveTrain.kDrive(0, 0);
			}
		});
		addSequential(new DriveForward(0.8, 800));
	}
}
