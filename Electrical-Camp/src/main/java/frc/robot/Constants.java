// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public final class LEDConstants {
    public static final int LEDBufferLen = 10;
    public static final int LED = 0;
    public static final int BEAM_BREAK = 1;
  }

  public final class MotorConstants {
    public static final int NEO_ID = 1;
    public static final int VORTEX_ID = 2;
    public static final int KRAKEN_ID = 3;
    public static final int LIMIT_SWITCH = 0;
    public static final int NEO_CURRENT_LIMIT = 40;
    public static final int VORTEX_CURRENT_LIMIT = 60;
    public static final int KRAKEN_CURRENT_LIMIT = 40;
    public static final int VORTEX_MAX_VELOCITY = 40000;
    public static final int VORTEX_MAX_ACCELERATION = 50000;
  }
}
