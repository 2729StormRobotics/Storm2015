package org.usfirst.frc.team2729.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int 
			PORT_JOYSTICK_DRIVE = 0,
            PORT_JOYSTICK_ARMS  = 1,
            PORT_JOYSTICK_DEBUG = 2;
	
	//Axis
	public static final int 
			JOYDRIVE_AXIS_DRIVE_X  			   = 0,
			JOYDRIVE_AXIS_DRIVE_Y 			   = 1,
			JOYDRIVE_AXIS_SPIN 				   = 2,
			JOYARM_AXIS_ELEVATOR			   = 1,
			JOYARM_AXIS_RAKE				   = 3,
			JOYDRIVE_AXIS_DRIVE_LEFT		   = 1,
			JOYDRIVE_AXIS_DRIVE_RIGHT		   = 3,
			JOYDRIVE_AXIS_SHIFT				   = 5;
	
	//Buttons
	public static final int 
			JOYDRIVE_BUTTON_STRAFE_RIGHT   	   = 6,
			JOYDRIVE_BUTTON_STRAFE_LEFT        = 5,
			JOYDRIVE_BUTTON_HALVE_STRAFE	   = 8,
			JOYDRIVE_BUTTON_SHIFT_DRIVE_HIGH   = 4,
			JOYDRIVE_BUTTON_SHIFT_DRIVE_LOW    = 2,
			JOYARM_BUTTON_CLAMP_IN             = 5,
			JOYARM_BUTTON_CLAMP_OUT            = 7,
			JOYARM_BUTTON_ARM_IN       		   = 6,
			JOYARM_BUTTON_ARM_OUT      		   = 8,
			JOYARM_BUTTON_SPIN_IN			   = 4,
			JOYARM_BUTTON_SPIN_OUT			   = 2;
	
	//PWM Ports 
	public static final int 
			PORT_MOTOR_DRIVE_CENTER	  = 2,
			PORT_MOTOR_DRIVE_LEFT     = 0,
			PORT_MOTOR_DRIVE_RIGHT    = 4,
			PORT_MOTOR_ELEVATOR	      = 1,
			PORT_MOTOR_ARM            = 3,
			PORT_MOTOR_SPIN			  = 5;
	
	//Digital I/O Ports
	public static final int 
			PORT_ENCODER_CENTER_1	  =  2,
			PORT_ENCODER_CENTER_2	  =  3,
			PORT_ENCODER_RIGHT_1      =  4,
			PORT_ENCODER_RIGHT_2      =  5,
			PORT_ENCODER_LEFT_1       =  0,
			PORT_ENCODER_LEFT_2       =  1,
			PORT_ENCODER_ELEVATOR_1	  =  7,
			PORT_ENCODER_ELEVATOR_2	  =  8,
			PORT_ENCODER_ARM_1		  =  9,
			PORT_ENCODER_ARM_2		  =  10,
			PORT_LIMIT_SWITCH_AUTO	  =  11,
			PORT_LIMIT_SWITCH_ELEVATOR=  6;
	
	//Relay
	public static final int PORT_RELAY_COMPRESSOR = 0;
	
	// Analog ports
    public static final int PORT_SENSOR_GYRO      = 1,
    		PORT_STRINGPOT						  = 0;
	
	//Solenoids
	public static final int 
			PORT_SOLENOID_CLAMP_IN	  		  = 0,
			PORT_SOLENOID_CLAMP_OUT			  = 1,
		    PORT_SOLENOID_SHIFT_DRIVE_HIGH    = 2,
		    PORT_SOLENOID_SHIFT_DRIVE_LOW     = 3,
		    PORT_SOLENOID_ARM_UP			  = 6,
		    PORT_SOLENOID_ARM_DOWN			  = 7,
		    PORT_SOLENOID_SHIFT_ELEVATOR_HIGH = 4,
		    PORT_SOLENOID_SHIFT_ELEVATOR_LOW  = 5;
		    
}
