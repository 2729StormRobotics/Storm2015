package org.usfirst.frc.team2729.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionSystem {
	public static final double BIN_TARGET_ANGLE = 0;
	public static final double TOTE_TARGET_ANGLE = 0;
	public static boolean getBinFound(){
		return SmartDashboard.getBoolean("Bin detected");
	}
	public static double getBinAngle(){
		return SmartDashboard.getNumber("Bin angle");
	}
	public static boolean getToteFound(){
		return SmartDashboard.getBoolean("Tote detected");
	}
	public static double getToteAngle(){
		return SmartDashboard.getNumber("Tote angle");
	}
}
