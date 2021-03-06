package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;
import java.lang.Math;

public class DriveTrain extends Subsystem {    
    RobotDrive driveTrain;
    int speedIndex;
    
    
    public DriveTrain() {
        speedIndex = 0;
        driveTrain = new RobotDrive(RobotMap.LEFT_MOTOR_PORT, RobotMap.RIGHT_MOTOR_PORT);
        driveTrain.setSafetyEnabled(false);
    }

    public void setSensitivity(double sensitivity){
        driveTrain.setSensitivity(sensitivity);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public void driveWithJoystick(Joystick driveStick) {
        driveTrain.arcadeDrive(driveStick);
    }
    
    public void driveWithJoystick(Joystick driveStick1, Joystick driveStick2) {
        driveTrain.tankDrive(driveStick1, driveStick2);
    }
    
    public void setSpeed(double speed) {
        driveTrain.setMaxOutput(speed);
    }
    
    public void setSpeed(int speedIndex) {
        if ((speedIndex >= 0) && (speedIndex <= RobotMap.SPEED_ARRAY.length)) {
            this.speedIndex = speedIndex;
            setSpeed(RobotMap.SPEED_ARRAY[speedIndex]);
        }
    }
    
    public void setDefaultSpeed() {
        setSpeed(RobotMap.DEFAULT_SPEED_INDEX);
    }
    
    public void setSlowSpeed() {
        setSpeed(RobotMap.SLOW_SPEED_INDEX);
    }
    
    public void setFastSpeed() {
        setSpeed(RobotMap.FAST_SPEED_INDEX);
    }
    
    public void toggleSpeed(int direction) {
        if (direction != 0) {
            direction = direction / Math.abs(direction);
            int newSpeedIndex = speedIndex + direction;
            newSpeedIndex = newSpeedIndex % RobotMap.SPEED_ARRAY.length;
            setSpeed(newSpeedIndex);
        }
    }
    
    public void toggleSpeed() {
        toggleSpeed(1);
    }
    
    public void driveForward() {
        driveTrain.drive(0.5, 0);
    }
    
    public void stopMovement() {
        driveTrain.drive(0.0, 0.0);
    }
}
