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

    climbMotor.configFactoryDefault();
    climbMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, ClimberConstants.kTimeoutMs);

    climbMotor.configNominalOutputForward(0, ClimberConstants.kTimeoutMs);
		climbMotor.configNominalOutputReverse(0, ClimberConstants.kTimeoutMs);
		climbMotor.configPeakOutputForward(1, ClimberConstants.kTimeoutMs);
		climbMotor.configPeakOutputReverse(-1, ClimberConstants.kTimeoutMs);
    
    climbMotor.configAllowableClosedloopError(0, ClimberConstants.kPIDLoopIdx, ClimberConstants.kTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		climbMotor.config_kF(ClimberConstants.kPIDLoopIdx, ClimberConstants.kF, ClimberConstants.kTimeoutMs);
		climbMotor.config_kP(ClimberConstants.kPIDLoopIdx, ClimberConstants.kP, ClimberConstants.kTimeoutMs);
		climbMotor.config_kI(ClimberConstants.kPIDLoopIdx, ClimberConstants.kI, ClimberConstants.kTimeoutMs);
		climbMotor.config_kD(ClimberConstants.kPIDLoopIdx, ClimberConstants.kD, ClimberConstants.kTimeoutMs);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getClimberPosition() {
    return climbMotor.getSelectedSensorPosition();
  }

  public void setClimberPosition(double targetPos){

    climbMotor.set(ControlMode.Position, targetPos);

  }

}
