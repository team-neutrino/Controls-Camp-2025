package frc.robot.util;

import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LED;

public class SubsystemContainer {
    public static final LED LED = new LED(LEDConstants.LED);
}