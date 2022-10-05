// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

  private WPI_TalonFX climbMotor = new WPI_TalonFX(ClimberConstants.climberMotorID);

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getClimberPosition() {
    return climbMotor.getSelectedSensorPosition();
  }

  /**
   * 
   * Sets climbing motor to specified speed
   * 
   * @param speed between -1 and 1
   */
  public void setClimberSpeed(double speed){

    climbMotor.set(ControlMode.PercentOutput, speed);

  }

}
