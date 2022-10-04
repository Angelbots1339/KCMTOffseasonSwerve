package frc.lib.util;

public class SwerveModuleConstants {
    public final int driveMotorID;
    public final int angleMotorID;
    public final int cancoderID;
    public final double angleOffset;
    public final boolean driveMotorInvert;
    public final boolean angleMotorInvert;
    public final boolean canCoderInvert;

    /**
     * Swerve Module Constants to be used when creating swerve modules.
     * @param driveMotorID
     * @param angleMotorID
     * @param canCoderID
     * @param angleOffset
     * @param driveMotorInvert
     * @param angleMotorInvert
     * @param canCoderInvert
     */
    public SwerveModuleConstants(int driveMotorID, int angleMotorID, int canCoderID, double angleOffset, boolean driveMotorInvert, boolean angleMotorInvert, boolean canCoderInvert) {
        this.driveMotorID = driveMotorID;
        this.angleMotorID = angleMotorID;
        this.cancoderID = canCoderID;
        this.angleOffset = angleOffset;
        this.driveMotorInvert = driveMotorInvert;
        this.angleMotorInvert = angleMotorInvert;
        this.canCoderInvert = canCoderInvert;
    }
}
