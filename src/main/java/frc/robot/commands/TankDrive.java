// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

/** Have the robot drive tank style. */
public class TankDrive extends CommandBase {
  private final DriveTrain m_drivetrain;

  private SendableChooser<DoubleSupplier> m_left;
  private SendableChooser<DoubleSupplier> m_right;

  /**
   * Creates a new TankDrive command.
   *
   * @param left The control input for the left side of the drive
   * @param right The control input for the right sight of the drive
   * @param drivetrain The drivetrain subsystem to drive
   */
  public TankDrive(SendableChooser<DoubleSupplier> left, SendableChooser<DoubleSupplier> right, DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    m_left = left;
    m_right = right;
    addRequirements(drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    double leftSpeed = m_left.getSelected().getAsDouble() * OIConstants.kDriveSpeedMult1.getDouble(1.0);
    double rightSpeed = m_right.getSelected().getAsDouble() * OIConstants.kDriveSpeedMult2.getDouble(1.0);

    if(Math.abs(leftSpeed) < 0.2) {
      leftSpeed = 0;
      }
  
    if(Math.abs(rightSpeed) < 0.2) {
      rightSpeed = 0;
    }

    m_drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted
  }
}
