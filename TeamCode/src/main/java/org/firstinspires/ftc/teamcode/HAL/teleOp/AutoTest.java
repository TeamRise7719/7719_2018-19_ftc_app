package org.firstinspires.ftc.teamcode.HAL.teleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;

@Autonomous

public class AutoTest extends LinearOpMode {

    encoderLibrary enc;
    DcMotor winchL;
    DcMotor winchR;
    CRServo intakeflipR, intakeflipL, intakeR,hook, intakeL;
    ElapsedTime etime = new ElapsedTime();

    public void waitFor(int time) {
        time = time / 1000;
        etime.reset();
        while ((etime.time() < time) && (opModeIsActive())) {
            idle();
        }
    }


        public void runOpMode() {
            intakeR = hardwareMap.crservo.get("intakeR");
            intakeL = hardwareMap.crservo.get("intakeL");
            intakeflipR = hardwareMap.crservo.get("intakeR");
            intakeflipL = hardwareMap.crservo.get("intakeL");
            hook = hardwareMap.crservo.get("hook");
            winchL = hardwareMap.dcMotor.get("winchL");
            winchR = hardwareMap.dcMotor.get("winchR");

            enc = new encoderLibrary(hardwareMap, telemetry, this);
            enc.init();


            winchR.setPower(1);
            winchR.setPower(1);
            waitFor(2500);


            intakeflipL.setPower(0.15);
            intakeflipR.setPower(0.15);
            waitFor(250);
            intakeL.setPower(0.8);
            intakeL.setPower(0.8);
            waitFor(2000);








            }







        }




