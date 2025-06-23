// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;

public class FakeSubsystem extends SubsystemBase {

  public FakeSubsystem() {

  }

  @Override
  public void periodic() {
  }


  public Command countUpCommand() {
    return run(
        () -> {
        });
  }
}
