package frc.robot.factories;

import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.Constants.ElevatorConstants.*;
import static frc.robot.util.Subsystem.elevator;

public class ElevatorFactory {

    public static Command moveL1() {
        return elevator.moveElevatorCommand(L1);
    }

    public static Command moveL2() {
        return elevator.moveElevatorCommand(L2);
    }

    public static Command moveL3() {
        return elevator.moveElevatorCommand(L3);
    }

    public static Command moveL4() {
        return elevator.moveElevatorCommand(L4);
    }

}