// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final VictorSPX m_leftDrive1 = new VictorSPX(1);
  private final VictorSPX m_leftDrive2 = new VictorSPX(2);
  private final VictorSPX m_rightDrive1 = new VictorSPX(0);
  private final VictorSPX m_rightDrive2 = new VictorSPX(3);
  private final Joystick m_joystick = new Joystick(0);
  private final Timer m_timer = new Timer();

  private static final double AXIS_THRESHOLD = 0.1; // Threshold to avoid noise from joysticks

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_rightDrive1.setInverted(true);
    m_rightDrive2.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
    if (DriverStation.isEnabled()) {
    }
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 2.0) {
      // Move forward slowly
      setMotorOutputs(0.15, 0.15);
    } else if (m_timer.get() < 4.0) {
      // Spin at full speed
      setMotorOutputs(1.0, -1.0);
    } else {
      setMotorOutputs(0.0, 0.0);
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {}

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
      // Read joystick values for forward/backward (Y-axis) and turning (X-axis)
      double forward = -m_joystick.getRawAxis(1);  // Left joystick Y-axis for forward/backward
      double turn = m_joystick.getRawAxis(2);      // Right joystick X-axis for turning
  
      // Apply a deadzone threshold to avoid noise from small joystick movements
      if (Math.abs(forward) < AXIS_THRESHOLD) {
          forward = 0;
      }
      if (Math.abs(turn) < AXIS_THRESHOLD) {
          turn = 0;
      }
  
      // Arcade drive logic: combine forward movement and turning
      double leftOutput = forward + turn;  // Left motor output
      double rightOutput = forward - turn; // Right motor output
  
      // Set motor outputs for both sides
      setMotorOutputs(leftOutput, rightOutput);
  
      // Log controller inputs for debugging
      logControllerInputs();
  }
  

  /**
   * Sets motor outputs for tank drive.
   */
  private void setMotorOutputs(double leftOutput, double rightOutput) {
    m_leftDrive1.set(ControlMode.PercentOutput, leftOutput);
    m_leftDrive2.set(ControlMode.PercentOutput, leftOutput);
    m_rightDrive1.set(ControlMode.PercentOutput, rightOutput);
    m_rightDrive2.set(ControlMode.PercentOutput, rightOutput);
  }

  /**
   * Logs joystick inputs only when there is significant movement.
   */
  private void logControllerInputs() {
    boolean logged = false;

    // Axis logging with threshold
    if (Math.abs(m_joystick.getRawAxis(1)) > AXIS_THRESHOLD) {
      System.out.printf("Left Joystick Axis (forward/back): %.2f%n", m_joystick.getRawAxis(1));
      logged = true;
    }
    if (Math.abs(m_joystick.getRawAxis(2)) > AXIS_THRESHOLD) {
      System.out.printf("Right Joystick (turning): %.2f%n", m_joystick.getRawAxis(2));
      logged = true;
    }

    // Log separator only if something was logged
    if (logged) {
      System.out.println("=============================");
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    System.out.println("Test Init");
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

    setMotorOutputs(0.2, -0.2);


  }
}
 