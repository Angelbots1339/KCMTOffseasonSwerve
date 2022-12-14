// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ClimbingConstants;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* Controllers */
  private final XboxController driver = new XboxController(0);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  private final JoystickButton zeroGyro = new JoystickButton(driver, 4);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
  private final ClimbingSubsystem climbingSubsystem = new ClimbingSubsystem();

  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    boolean fieldRelative = true;
    boolean openLoop = true;
    s_Swerve.setDefaultCommand(new TeleopSwerve(s_Swerve, () -> driver.getLeftX(), () -> driver.getLeftY(), () -> driver.getRightX(), fieldRelative, openLoop));

    DoubleSupplier leftTrigger = () -> driver.getLeftTriggerAxis();
    DoubleSupplier rightTrigger = () -> driver.getRightTriggerAxis();
    climbingSubsystem.setDefaultCommand(new RunClimber(climbingSubsystem, leftTrigger, rightTrigger));

    autoChooser.setDefaultOption("None", new WaitCommand(0));
    autoChooser.addOption("Drive Forward", new DriveForward2M(s_Swerve)); // Drives forward 2M
    autoChooser.addOption("Wait Drive Forward", new WaitDriveForward2M(s_Swerve));
     // Waits for 5 seconds before driving forward 2M
     Shuffleboard.getTab("Auto").add(autoChooser);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    zeroGyro.whenPressed(new InstantCommand(() -> s_Swerve.zeroGyro()));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // TODO Characterize robot
    return autoChooser.getSelected(); 
  }

  public void resetClimbEncoder() {
    climbingSubsystem.resetEncoder();
  }

  public void testModeRunArms() {
    climbingSubsystem.setExtentionVolts((driver.getLeftTriggerAxis() * 6)
        - (driver.getRightTriggerAxis() * 6));
      
  }

  public void testModeCenterGyro() {
    zeroGyro.whenPressed(new InstantCommand(() -> s_Swerve.zeroGyro()));
  }


  public void resetAnglesToAbsolute(){
    for(int i = 0; i <= 3; i++){
      s_Swerve.mSwerveMods[i].resetToAbsolute();
    } 
  }

  public void resetGyro(){
    s_Swerve.zeroGyro();
  }
}
