package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@TeleOp(name = "HAL TeleOp3", group = "HAL")
public class HALteleOp3 extends OpMode {

    private mecanumDrivetrain robot;
    Servo lift1, lift2, lift3, lift4;
    DcMotor shoulderL;
    DcMotor shoulderR;
    DcMotor armL, armR;
    CRServo wristR, wristL, intR, intL, hook;

    int targetR, targetL;


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
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        targetR = armR.getCurrentPosition();
        targetL = armL.getCurrentPosition();

        hook.setPower(1);

        lift1.setPosition(0.25);
        lift1.setPosition(0.25);
        lift2.setPosition(0.25);
        lift3.setPosition(0.25);
        lift4.setPosition(0.25);


    }

    @Override
    public void loop() {


        //------------------------------------=+(Drivetrain)+=------------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //------------------------------------=+(Drivetrain)+=------------------------------------\\

        if (gamepad1.right_trigger > 0) {
            lift1.setPosition(0.75);
            lift2.setPosition(0.75);
            lift3.setPosition(0.75);
            lift4.setPosition(0.75);
        } else if (gamepad1.left_trigger > 0) {
            lift1.setPosition(0.25);
            lift2.setPosition(0.25);
            lift3.setPosition(0.25);
            lift4.setPosition(0.25);
        }

        if (gamepad1.b) {
            hook.setPower(-1);
        }


        shoulderR.setPower(gamepad2.left_stick_y);
        shoulderL.setPower(gamepad2.left_stick_y);


        armR.setPower(gamepad2.right_stick_y / 2.0);
        armL.setPower(gamepad2.right_stick_y / 2.0);

        if (gamepad2.right_stick_y != 0) {

            armR.setPower(gamepad2.right_stick_y / 2);
            armL.setPower(gamepad2.right_stick_y / 2);
            targetR = armR.getCurrentPosition();
            targetL = armL.getCurrentPosition();
            if (gamepad2.right_stick_y == 0 && (targetR != armR.getCurrentPosition() || targetL != armL.getCurrentPosition())) {

                if (targetR - armR.getCurrentPosition() > 0) {
                    if (targetL - armL.getCurrentPosition() > 0) {
                        armR.setPower(-.5);
                        armL.setPower(-.5);
                    } else if (targetL - armL.getCurrentPosition() < 0) {
                        armR.setPower(-.5);
                        armL.setPower(.5);
                    }
                } else if (targetR - armR.getCurrentPosition() < 0) {
                    if (targetL - armL.getCurrentPosition() > 0) {
                        armR.setPower(.5);
                        armL.setPower(-.5);
                    } else if (targetL - armL.getCurrentPosition() < 0) {
                        armR.setPower(.5);
                        armL.setPower(.5);
                    }

                }
            }
        }


            intR.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            intL.setPower(-gamepad2.left_trigger + gamepad2.right_trigger);


//        telemetry.addData("armR", armR.getCurrentPosition());
//        telemetry.addData("armL", armL.getCurrentPosition());

            if (gamepad2.a) {
                wristL.setPower(0.25);
                wristR.setPower(-0.25);
            } else if (gamepad2.y) {
                wristL.setPower(-0.25);
                wristR.setPower(0.25);
            }


            if (gamepad2.x) {
                armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

//        if (armR.getTargetPosition()  >= 4200 || armL.getTargetPosition() >= 4200){
//          Math.abs(gamepad2.right_stick_y * -1);
//
//        } else if (armR.getTargetPosition()  <= 150 || armL.getTargetPosition() <=150){
//          Math.abs(gamepad2.right_stick_y);
//        }


//        if (gamepad2.dpad_down){
//          intL.setPower(0.2);
//          intR.setPower(-0.2);
//        }
//        if (gamepad2.dpad_up){
//            intL.setPower(0.8);
//            intR.setPower(-0.8);
//
//            intL.setPower(gamepad2.left_stick_y);
//            intR.setPower(-gamepad2.left_stick_y);
//        }


        }


    }
