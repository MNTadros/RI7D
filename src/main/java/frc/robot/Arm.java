package frc.robot;
//This class handles the Claw mechanisms of the bot
//The bot has 3 pivot points; shoulder, wrist, and top hand

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ControllerMap;

@SuppressWarnings("unused")
public class Arm extends SubsystemBase {

    public static final double deadzone = 0.1;

    //Arm motor
    public Talon shoulderMotor;
    public Talon clawMotor;

    //Claw rollers
    public Talon topFlywheelRollers;
    public Talon bottomFlywheelRollers;

    PS4Controller controller = new PS4Controller(0);
    
    public Arm() {
        shoulderMotor = new Talon(0);
        topFlywheelRollers = new Talon(1);
        bottomFlywheelRollers = new Talon(2);
    }

    public void rollers() {
        while (controller.getR1ButtonPressed()) {
            topFlywheelRollers.set(1);
            bottomFlywheelRollers.set(1);
        } 
        while (controller.getL1ButtonPressed()) {
            topFlywheelRollers.set(-1);
            bottomFlywheelRollers.set(-1);
        } 
        if (!controller.getR1ButtonPressed() && !controller.getL1ButtonPressed()) {
            topFlywheelRollers.set(0);
            bottomFlywheelRollers.set(0);
        }
    }

    public void shoulder() {
        if (controller.getRightY() > deadzone) {
            shoulderMotor.set(controller.getRightY());
        } else if (controller.getRightY() < -deadzone) {
            shoulderMotor.set(-controller.getRightY());
        } else if (controller.getRightY() < deadzone && controller.getRightY() > -deadzone) {
            shoulderMotor.set(0);
        }
    }

    public void claw() {
        if (controller.getCrossButtonPressed()) {
            clawMotor.set(1);
        } else if (controller.getCircleButtonPressed()) {
            clawMotor.set(-1);
        } else {
            clawMotor.set(0);
        }
    }
}