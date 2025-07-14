// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;

public class LedSubsystem extends SubsystemBase {

  private static final int ROBORIO_LED_PWM_PORT = 7;
  private static final int LED_STRIP_PIXELS = 8;

  private AddressableLED m_addressableLED;
  private AddressableLEDBuffer m_LEDBuffer;
  private Timer m_timer = new Timer();

  public LedSubsystem() {
    m_addressableLED = new AddressableLED(ROBORIO_LED_PWM_PORT);
    m_LEDBuffer = new AddressableLEDBuffer(LED_STRIP_PIXELS);
    m_addressableLED.setLength(m_LEDBuffer.getLength());
    m_addressableLED.setData(m_LEDBuffer);
    m_addressableLED.start();
    m_timer.start();
  }

  @Override
  public void periodic() {
    m_addressableLED.setData(m_LEDBuffer);
  }

  public Command doNothing() {
    return runOnce(
        () -> {
        });
  }

  public Command colorCommand(int r, int g, int b) {
    return run(
        () -> {
          for (int i = 0; i < m_LEDBuffer.getLength(); i++) {
            m_LEDBuffer.setRGB(i, r, g, b);
          }
        });
  }
}
