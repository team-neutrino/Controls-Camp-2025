package frc.robot.commands;

import frc.robot.subsystems.LED;
import frc.robot.util.SubsystemContainer;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDDefaultCommand extends Command {

    private LED m_LED;

    public LEDDefaultCommand() {
        m_LED = SubsystemContainer.LED;
        addRequirements(m_LED);
    }

    @Override
    public void initialize() {
        m_LED.setToOrange();
    }

    @Override
    public void execute() {
        if (m_LED.isBeamBroken()) {
            m_LED.setToGreen();
        }
        m_LED.setToOrange();

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}