package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj.XboxController;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class TeleopSwerve extends CommandBase {

    private double rotation;
    private Translation2d translation;
    private boolean fieldRelative;
    private boolean openLoop;
    
    private Swerve s_Swerve;
    private DoubleSupplier leftX;
    private DoubleSupplier leftY;
    private DoubleSupplier rightX;
 


    /**
     * Driver control
     */
    public TeleopSwerve(Swerve s_Swerve, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX, boolean fieldRelative, boolean openLoop) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);


        this.fieldRelative = fieldRelative;
        this.openLoop = openLoop;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
    }

    @Override
    public void execute() {
        double yAxis = -leftX.getAsDouble();
        double xAxis = -leftY.getAsDouble();
        double rAxis = -rightX.getAsDouble();
        
        /* Deadbands */
        yAxis = (Math.abs(yAxis) < Constants.stickDeadband) ? 0 : yAxis;
        xAxis = (Math.abs(xAxis) < Constants.stickDeadband) ? 0 : xAxis;
        rAxis = (Math.abs(rAxis) < Constants.stickDeadband) ? 0 : rAxis;

        translation = new Translation2d(yAxis, xAxis).times(Constants.Swerve.maxSpeed);
        rotation = rAxis * Constants.Swerve.maxAngularVelocity;
        s_Swerve.drive(translation, rotation, fieldRelative, openLoop);

        
    }
}
