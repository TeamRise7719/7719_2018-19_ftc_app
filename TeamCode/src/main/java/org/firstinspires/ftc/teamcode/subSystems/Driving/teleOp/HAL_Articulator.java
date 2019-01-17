package org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HAL_Articulator {

    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    private final DcMotor shoulderL, shoulderR, armL, armR;
    private final CRServo wristR, wristL, intR, intL;

    private final AnalogInput shoulderPot;
    double potMin = 0, potMax = 2000;

    int targetR, targetL;



    public HAL_Articulator(final HardwareMap _hardwareMap, final Telemetry _telemetry) {

        hardwareMap = _hardwareMap;
        telemetry = _telemetry;

        intR = hardwareMap.crservo.get("intR");
        intL = hardwareMap.crservo.get("intL");


        wristR = hardwareMap.crservo.get("wristR");
        wristL = hardwareMap.crservo.get("wristL");


        shoulderL = hardwareMap.dcMotor.get("shoulderL");
        shoulderR = hardwareMap.dcMotor.get("shoulderR");
        shoulderL.setDirection(DcMotorSimple.Direction.REVERSE);
        shoulderR.setDirection(DcMotorSimple.Direction.FORWARD);


        armL = hardwareMap.dcMotor.get("armL");
        armR = hardwareMap.dcMotor.get("armR");
        armL.setDirection(DcMotorSimple.Direction.FORWARD);
        armR.setDirection(DcMotorSimple.Direction.REVERSE);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        shoulderPot = hardwareMap.analogInput.get("shoulderPot");


        targetR = armR.getCurrentPosition();
        targetL = armL.getCurrentPosition();
    }

    private void arm(Gamepad gamepad2){

        if (gamepad2.right_stick_y != 0) {

            armR.setPower(gamepad2.right_stick_y / 2);
            armL.setPower(gamepad2.right_stick_y / 2);

            targetR = armR.getCurrentPosition();
            targetL = armL.getCurrentPosition();

        } else if (gamepad2.right_stick_y == 0 && (targetR != armR.getCurrentPosition() || targetL != armL.getCurrentPosition())) {

            if (targetR - armR.getCurrentPosition() > 0) {
                if (targetL - armL.getCurrentPosition() > 0) {

                    armR.setPower(.1);
                    armL.setPower(.1);

                } else if (targetL - armL.getCurrentPosition() < 0) {

                    armR.setPower(.1);
                    armL.setPower(-.1);

                }
            } else if (targetR - armR.getCurrentPosition() < 0) {
                if (targetL - armL.getCurrentPosition() > 0) {

                    armR.setPower(-.1);
                    armL.setPower(.1);

                } else if (targetL - armL.getCurrentPosition() < 0) {

                    armR.setPower(-.1);
                    armL.setPower(-.1);

                }

            }
        } else {

            armR.setPower(0);
            armL.setPower(0);

        }

    }

    private void shoulder(Gamepad gamepad2){

        // TO BE IMPLEMENTED IMMEDIATELY

//        if (shoulderPot.getVoltage() < potMin) {
//
//            if (gamepad2.left_stick_y < 0){
//
//                shoulderR.setPower(0);
//                shoulderL.setPower(0);
//
//            } else {
//
//                shoulderR.setPower(gamepad2.left_stick_y);
//                shoulderL.setPower(gamepad2.left_stick_y);
//
//            }
//
//        } else if (shoulderPot.getVoltage() > potMin){
//
//            if (gamepad2.left_stick_y > 0){
//
//                shoulderR.setPower(0);
//                shoulderL.setPower(0);
//
//            } else {
//
//                shoulderR.setPower(gamepad2.left_stick_y);
//                shoulderL.setPower(gamepad2.left_stick_y);
//
//            }
//
//        } else {
//
//            shoulderR.setPower(gamepad2.left_stick_y);
//            shoulderL.setPower(gamepad2.left_stick_y);
//
//        }

        shoulderR.setPower(gamepad2.left_stick_y);
        shoulderL.setPower(gamepad2.left_stick_y);

    }

    private void wrist(Gamepad gamepad2){

//        if (gamepad2.a) { // Intake position
//
//            wristL.setPower(0.18);
//            wristR.setPower(-0.18);
//
//        } else if (gamepad2.y) { // Up (rest) position
//
//            wristL.setPower(-0.25);
//            wristR.setPower(0.25);
//
//        } else if (gamepad2.b) { // Scoring position
//
//            wristL.setPower(-0.25);
//            wristR.setPower(0.25);
//
//        }

        //This is for minor, on the fly adjustments

        if (gamepad2.a) {

            wristL.setPower(wristL.getPower() + 0.05);
            wristR.setPower(wristR.getPower() - 0.05);

        } else if (gamepad2.y) {

            wristL.setPower(wristL.getPower() - 0.05);
            wristR.setPower(wristR.getPower() + 0.05);

        }

    }

    private void intake(Gamepad gamepad2){

        if((gamepad2.right_trigger < 0.25) && (gamepad2.right_trigger > 0)){

            intL.setPower(0.25);
            intR.setPower(-0.25);

        } else if (gamepad2.right_trigger > 0.8) {

            intL.setPower(0.8);
            intR.setPower(-0.8);

        } if((gamepad2.left_trigger < 0.25) && (gamepad2.left_trigger > 0)){

            intL.setPower(-0.25);
            intR.setPower(0.25);

        } else if (gamepad2.left_trigger > 0.8) {

            intL.setPower(-0.8);
            intR.setPower(0.8);

        } else {

            intL.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
            intR.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        }

    }

    public void articulator(Gamepad gamepad2){

        arm(gamepad2);
        shoulder(gamepad2);
        wrist(gamepad2);
        intake(gamepad2);

    }

}
