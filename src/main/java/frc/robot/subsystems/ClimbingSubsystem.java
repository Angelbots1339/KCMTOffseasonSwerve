// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbingConstants;

public class ClimbingSubsystem extends SubsystemBase {

  private WPI_TalonFX climbMotor = new WPI_TalonFX(ClimbingConstants.climberMotorID);

  /** Creates a new ClimberSubsystem. */
  public ClimbingSubsystem() {
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getClimberPosition() {
    return climbMotor.getSelectedSensorPosition() * ClimbingConstants.lengthPerClick;
  }

  public void setExtentionSpeed(double speed) {
   
    climbMotor.set(ControlMode.PercentOutput, speed);

  }

  public void resetEncoder() {

    climbMotor.setSelectedSensorPosition(0);
    
  }

}