package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@TeleOp(name = "HAL TeleOp", group = "HAL")
public class HALteleOp3 extends OpMode {

    private mecanumDrivetrain robot;
    Servo lift1,lift2,lift3,lift4;
    DcMotor shoulderL;
    DcMotor shoulderR;
    DcMotor armL, armR;
    CRServo wristR, wristL, intR, intL,hook;


    Telemetry tele;

    @Override
    public void init() {


        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();
        lift1 = hardwareMap.servo.get("lift1");
        lift2 = hardwareMap.servo.get("lift2");
        lift3 = hardwareMap.servo.get("lift3");
        lift4 = hardwareMap.servo.get("lift4");


        hook = hardwareMap.crservo.get("hook");


        intR = hardwareMap.crservo.get("intR");
        intL = hardwareMap.crservo.get("intL");


        wristR = hardwareMap.crservo.get("wristR");
        wristL = hardwareMap.crservo.get("wristL");


        shoulderL = hardwareMap.dcMotor.get("shoulderL");
        shoulderR = hardwareMap.dcMotor.get("shoulderR");
        shoulderL.setDirection(DcMotorSimple.Direction.FORWARD);
        shoulderR.setDirection(DcMotorSimple.Direction.REVERSE);

        armL = hardwareMap.dcMotor.get("armL");
        armR = hardwareMap.dcMotor.get("armR");
        armL.setDirection(DcMotorSimple.Direction.FORWARD);
        armR.setDirection(DcMotorSimple.Direction.REVERSE);
        armL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hook.setPower(1);



    }

    @Override
    public void loop() {

        //------------------------------------=+(Drivetrain)+=-----------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //------------------------------------=+(Drivetrain)+=-----------------------------------\\

        if (gamepad2.left_bumper) {
            lift1.setPosition(0.75);
            lift2.setPosition(0.75);
            lift3.setPosition(0.75);
            lift4.setPosition(0.75);
        } else if (gamepad2.right_bumper) {
            lift1.setPosition(0.25);
            lift2.setPosition(0.25);
            lift3.setPosition(0.25);
            lift4.setPosition(0.25);
        }
        if (gamepad2.a){
            hook.setPower(1);
        }
        if (gamepad2.b){
            hook.setPower(-1);
        }

        shoulderR.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        shoulderL.setPower(gamepad2.left_trigger - gamepad2.right_trigger);

        armR.setPower(gamepad2.right_stick_y);
        armL.setPower(gamepad2.right_stick_y);

        intL.setPower(gamepad2.left_stick_y);
        intR.setPower(-gamepad2.left_stick_y);


        }

        }

