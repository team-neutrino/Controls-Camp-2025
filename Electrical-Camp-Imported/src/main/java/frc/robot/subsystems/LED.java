package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LED extends SubsystemBase {
    private AddressableLED m_addressableLED;
    private AddressableLEDBuffer m_LEDBuffer;
    private Timer timer = new Timer();
    private int m_port;
    private DigitalInput m_beamBreak = new DigitalInput(LEDConstants.BEAM_BREAK);

    public LED(int p_port) {
        m_port = p_port;
        m_addressableLED = new AddressableLED(m_port);
        m_LEDBuffer = new AddressableLEDBuffer(LEDConstants.LEDBufferLen);
        m_addressableLED.setLength(m_LEDBuffer.getLength());
        m_addressableLED.setData(m_LEDBuffer);
        m_addressableLED.start();
        setToOrange();
        timer.start();
    }

    public boolean isBeamBroken() {
        return m_beamBreak.get();
    }

    private void setToColor(int r, int g, int b) {
        for (int i = 0; i < m_LEDBuffer.getLength(); i++) {
            m_LEDBuffer.setRGB(i, r, g, b);
        }
    }

    public void setToPink() {
        setToColor(255, 141, 161);
    }

    public void setToOrange() {
        setToColor(235, 20, 0);
    }

    public void setToGreen() {
        setToColor(0, 255, 0);
    }

    public void setToWhite() {
        setToColor(255, 255, 255);
    }

    public void setToBlue() {
        setToColor(0, 0, 255);
    }

    public void setToYellow() {
        setToColor(255, 255, 0);
    }

    public void setToRed() {
        setToColor(255, 0, 0);
    }

    public void setToPurple() {
        setToColor(255, 0, 255);
    }

    public void setToCyan() {
        setToColor(0, 255, 255);
    }

    public void periodic() {
        m_addressableLED.setData(m_LEDBuffer);
    }

}