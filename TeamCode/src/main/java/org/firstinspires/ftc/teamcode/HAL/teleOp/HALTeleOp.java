package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@TeleOp(name = "HAL TeleOp", group = "HAL")
public class HALTeleOp extends OpMode {

    private mecanumDrivetrain robot;

    @Override
    public void init() {

        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();

    }

    @Override
    public void loop() {

        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\

        robot.winch(gamepad1.right_trigger - gamepad1.left_trigger);

        if(gamepad1.left_bumper){
            robot.winchMotor.setPower(0);
        } else {
            robot.winch(gamepad1.right_trigger - gamepad1.left_trigger);
        }
    }

}
