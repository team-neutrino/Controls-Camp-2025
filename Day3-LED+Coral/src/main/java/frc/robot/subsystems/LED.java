// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;

public class LED extends SubsystemBase {

  public LED() {

  }

  @Override
  public void periodic() {
  }

  public Command doNothing() {
    return runOnce(
        () -> {
        });
  }

  public Command colorCommand(int r, int g, int b) {
    return run(
        () -> {
        });
  }
}
