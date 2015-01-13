package org.usfirst.frc.team2729.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int PORT_JOYSTICK_DRIVE = 0,
            PORT_JOYSTICK_SHOOT = 1,
            PORT_JOYSTICK_DEBUG = 2;
	
	public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 1;
	
	//PWM Ports 
	public static final int PORT_MOTOR_DRIVE_CENTER	  = 0,
			PORT_MOTOR_DRIVE_LEFT     = 1,
			PORT_MOTOR_DRIVE_RIGHT    = 2,
			PORT_MOTOR_ELEVATOR_1	  = 3,
			PORT_MOTOR_ELEVATOR_2	  = 4;
	
	//Digital I/O Ports
	public static final int PORT_ENCODER_CENTER_1		=  0,
			PORT_ENCODER_CENTER_2	=  1,
			PORT_ENCODER_RIGHT_1    =  2,
			PORT_ENCODER_RIGHT_2    =  3,
			PORT_ENCODER_LEFT_1     =  4,
			PORT_ENCODER_LEFT_2     =  5,
			PORT_ENCODER_ELEVATOR_1	  =  6,
			PORT_ENCODER_ELEVATOR_2   =  7;
			
	
	//Relay
	public static final int PORT_RELAY_COMPRESSOR = 0;
	
	//Solenoids
	public static final int PORT_SOLENOID_CLAMP	  = 0,
		    PORT_SOLENOID_SHIFT_HIGH        = 1,
		    PORT_SOLENOID_SHIFT_LOW         = 2;
}
