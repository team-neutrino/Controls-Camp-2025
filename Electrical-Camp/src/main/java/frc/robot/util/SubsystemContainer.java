package frc.robot.util;

import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LEDSubsystem;

public class SubsystemContainer {
    public static final LEDSubsystem LEDSubsystem = new LEDSubsystem(LEDConstants.LED);
}