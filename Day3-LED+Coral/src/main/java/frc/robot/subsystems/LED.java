// Copyright 2025 FRC Team Neutrino

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
  private static final int BEAMBREAK_PORT = 1;
  private static final int ROBORIO_LED_PWM_PORT = 7;
  private static final int LED_STRIP_PIXELS = 8;
  private AddressableLED m_addressableLED = new AddressableLED(ROBORIO_LED_PWM_PORT);
  private AddressableLEDBuffer m_LEDBuffer = new AddressableLEDBuffer(LED_STRIP_PIXELS);
  private DigitalInput m_beambreak = new DigitalInput(BEAMBREAK_PORT);

  public LED() {
    m_addressableLED.setLength(m_LEDBuffer.getLength());
    m_addressableLED.setData(m_LEDBuffer);
    m_addressableLED.start();
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
          for(int i = 0; i < m_LEDBuffer.getLength(); ++i) {
            m_LEDBuffer.setRGB(i, r, g, b);
        }});
  }
}
