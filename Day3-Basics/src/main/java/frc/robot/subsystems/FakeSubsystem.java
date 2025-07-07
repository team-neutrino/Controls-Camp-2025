// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.*;

public class FakeSubsystem extends SubsystemBase {
  // create your member variable here

  public FakeSubsystem() {

  }

// create your method here

  @Override
  public void periodic() {

  }

// Problem #2
  public Command countUpCommand() {
    return run(
        () -> {

        });
  }

// Problem #3
  public Command resetCount(){
    return run(
      () -> {

      });
  }
}
