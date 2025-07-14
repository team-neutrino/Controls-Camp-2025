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

  public static class ElevatorConstants {
    public static final int MOTOR_ID = 2; // CAN ID of the leading elevator motor
    public static final int FOLLOWER_ID = 3; // CAN ID of the follower elevator motor
    public static final int CURRENT_LIMIT = 60; // Current limit on the elevator motor
    public static final double DEFAULT_HEIGHT = 0.0; // Default position of the elevator
    public static final double STAGE_ONE_HEIGHT = 26.0; // The height of the elevator's first stage
    public static final double L1 = 12.0; // Height to score in trough
    public static final double L2 = 17.0; // Height to score on L2
    public static final double L3 = 32.5; // Height to score on L3
    public static final double L4 = 55.5; // Height to score on L4
    public static final double P_VAL = 0.15; // P value
    public static final double I_VAL = 0.0; // I value
    public static final double D_VAL = 1.0; // D value
    public static final double STAGE_1_FF = 0.20; // Feedforward value for the first elevator stage
    public static final double STAGE_2_FF = 0.30; // Feedforward value for after the second stage is picked up
    public static final double HEIGHT_TOLERANCE = 0.6; // Tolerance for error in our height (target-actual)
  }

  public final class CANRateConstants {
    public static final int FASTEST_5MS = 5;
    public static final int FAST_10MS = 10;
  }

}
