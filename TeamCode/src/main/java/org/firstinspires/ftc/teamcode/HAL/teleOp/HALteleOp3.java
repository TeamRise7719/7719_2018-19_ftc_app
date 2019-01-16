package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.HAL_Articulator;
import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@TeleOp(name = "HAL TeleOp3", group = "HAL")
public class HALteleOp3 extends OpMode {

    private mecanumDrivetrain robot;
    private HAL_Articulator scoring;
    Servo lift1, lift2, lift3, lift4;
    CRServo hook;


    Telemetry tele;

    @Override
    public void init() {


        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();
        scoring = new HAL_Articulator(hardwareMap, telemetry);
        lift1 = hardwareMap.servo.get("lift1");
        lift2 = hardwareMap.servo.get("lift2");
        lift3 = hardwareMap.servo.get("lift3");
        lift4 = hardwareMap.servo.get("lift4");

        hook = hardwareMap.crservo.get("hook");

        lift1.setPosition(0.21);
        lift2.setPosition(0.21);
        lift3.setPosition(0.21);
        lift4.setPosition(0.21);


    }

    @Override
    public void loop() {


        //------------------------------------=+(Drivetrain)+=------------------------------------\\

        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //------------------------------------=+(Drivetrain)+=------------------------------------\\


        scoring.articulator(gamepad2);


        if (gamepad1.right_bumper) {
            lift1.setPosition(0.8);
            lift2.setPosition(0.8);
            lift3.setPosition(0.8);
            lift4.setPosition(0.8);
        } else if (gamepad1.left_bumper) {
            lift1.setPosition(0.21);
            lift2.setPosition(0.21);
            lift3.setPosition(0.21);
            lift4.setPosition(0.21);
        }

        if (gamepad1.b) {
            hook.setPower(-1);
        }
        }
    }
