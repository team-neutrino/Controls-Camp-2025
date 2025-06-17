// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;

public class Coral extends SubsystemBase {

  private SparkMax m_coralMotor = new SparkMax(2, MotorType.kBrushless);

  public Coral() {

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
