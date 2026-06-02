package frc.robot.util;

import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.MotorSubsystem;

public class SubsystemContainer {
    public static final LED LEDSubsystem = new LED(LEDConstants.LED);
    public static final MotorSubsystem motorSubsystem = new MotorSubsystem();
}