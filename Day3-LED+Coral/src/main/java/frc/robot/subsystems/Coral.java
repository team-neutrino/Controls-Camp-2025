// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;

public class Coral extends SubsystemBase {

  private SparkMax m_coralMotor = new SparkMax(3, MotorType.kBrushless);
  private RelativeEncoder m_encoder = m_coralMotor.getEncoder();
  private SparkMaxConfig m_coralConfig = new SparkMaxConfig(); 

  public Coral() {
    m_coralConfig.smartCurrentLimit(40);
    m_coralConfig.inverted(false);
    m_coralConfig.idleMode(IdleMode.kCoast);
  }

  // make methods here

  @Override
  public void periodic() {
  }

  public Command doNothing() {
    return run(
        () -> {
        });
  }

  public Command runMotor() {
    return run(
        () -> {
        });
  }
}
