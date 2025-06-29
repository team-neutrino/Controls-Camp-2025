// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.MotorConstants.*;

public class MotorSubsystem extends SubsystemBase {
  /** Creates a new MotorSubsystem. */
  private SparkMax m_Neo550 = new SparkMax(NEO_ID, MotorType.kBrushless);
  private SparkMaxConfig m_neoConfig = new SparkMaxConfig();
  private SparkFlex m_Vortex = new SparkFlex(VORTEX_ID, MotorType.kBrushless);
  private SparkFlexConfig m_vortexConfig = new SparkFlexConfig();
  private TalonFX m_Kraken = new TalonFX(KRAKEN_ID);
  private TalonFXConfiguration m_krakenConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs m_currentLimitConfig = new CurrentLimitsConfigs();
  private DigitalInput m_limitSwitch = new DigitalInput(LIMIT_SWITCH);

  public MotorSubsystem() {
    configureNeo550();
    configureVortex();
    configureKraken();
  }

  public boolean limitSwitchPressed() {
    return m_limitSwitch.get();
  }

  private void configureNeo550() {

    m_neoConfig.smartCurrentLimit(NEO_CURRENT_LIMIT);
    m_neoConfig.inverted(false);
    m_neoConfig.idleMode(IdleMode.kCoast);

    m_Neo550.configure(m_neoConfig, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  private void configureVortex() {
    m_vortexConfig.idleMode(IdleMode.kBrake);
    m_vortexConfig.smartCurrentLimit(VORTEX_CURRENT_LIMIT);

    m_vortexConfig.closedLoop.maxMotion
        .maxVelocity(VORTEX_MAX_VELOCITY)
        .maxAcceleration(VORTEX_MAX_ACCELERATION);

    m_Vortex.configure(m_vortexConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  private void configureKraken() {
    m_currentLimitConfig.withSupplyCurrentLimit(KRAKEN_CURRENT_LIMIT)
        .withSupplyCurrentLimitEnable(true)
        .withStatorCurrentLimit(KRAKEN_CURRENT_LIMIT)
        .withStatorCurrentLimitEnable(true);
    m_krakenConfig.CurrentLimits = m_currentLimitConfig;
    m_Kraken.setNeutralMode(NeutralModeValue.Brake);
    m_Kraken.getConfigurator().apply(m_krakenConfig);
  }

  @Override
  public void periodic() {

    if (limitSwitchPressed()) {
      m_Neo550.set(0.6);
      m_Kraken.set(0.3);
    } else {
      m_Neo550.set(0.4);
      m_Vortex.set(0.5);
      m_Kraken.set(0.5);
    }
  }
}
