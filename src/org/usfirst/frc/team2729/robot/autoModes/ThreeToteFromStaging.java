package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.Clamp;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteFromStaging extends CommandGroup{

	public ThreeToteFromStaging(){
		//addParralel(spin in);
		addSequential(new Command() {
			@Override
			protected boolean isFinished() {return Robot.driveTrain.getLeftDistance() <= 112;}
			@Override
			protected void interrupted() {}
			@Override
			protected void initialize() {
				Robot.driveTrain.resetLeftEnc();
				Robot.driveTrain.resetRightEnc();
			}
			@Override
			protected void execute() {Robot.driveTrain.kDrive(-0.3, -0.3);}
			@Override
			protected void end() {Robot.driveTrain.halt();}
		});
		//addParralel(spin halt);
		//addParallel(new spin open);
		//addParallel(new ElevatorDownToBottom)
		addSequential(new DriveForward(1.0, 500));//todo determine this distance
		addSequential(new ElevatorClamp(true));
		//addParallel(new )
	}
}
