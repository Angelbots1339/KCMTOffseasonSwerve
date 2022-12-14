// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimbingConstants;
import frc.robot.subsystems.ClimbingSubsystem;

public class RunClimber extends CommandBase {

  private ClimbingSubsystem climbingSubsystem;
  private DoubleSupplier leftTrigger;
  private DoubleSupplier rightTrigger;

  /** Creates a new RunClimber. */
  public RunClimber(ClimbingSubsystem climbingSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climbingSubsystem);
    this.climbingSubsystem = climbingSubsystem;
    this.leftTrigger = leftTrigger;
    this.rightTrigger = rightTrigger;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = leftTrigger.getAsDouble() - rightTrigger.getAsDouble();
       


    climbingSubsystem.setExtentionSpeed(speed * 0.5);
 

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
