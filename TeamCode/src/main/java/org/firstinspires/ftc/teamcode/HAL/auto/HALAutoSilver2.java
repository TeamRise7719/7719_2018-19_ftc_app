package org.firstinspires.ftc.teamcode.HAL.auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;

@Autonomous
public class HALAutoSilver2 extends LinearOpMode {


    encoderLibrary enc;
    visionLibrary vis;
    int position;
    DcMotor shoulderL;
    DcMotor shoulderR;
    DcMotor armL;
    DcMotor armR;
    CRServo intR;
    CRServo intL;
    CRServo hook;
    Servo lift1;
    Servo lift2;
    Servo lift3;
    Servo lift4;
    CRServo wristR, wristL;


    ElapsedTime etime = new ElapsedTime();


    public void waitFor(int time) {
        time = time / 1000;
        etime.reset();
        while ((etime.time() < time) && (opModeIsActive())) {
            idle();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {

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
        enc = new encoderLibrary(hardwareMap, telemetry, this);
        enc.init();

        vis = new visionLibrary(hardwareMap, telemetry);
        vis.initVuforia();
        vis.initTfod();
        vis.camFlash(true);
        while (!isStarted()) {
            position = vis.objectVision();
        }

        vis.tensorflowclose();

        vis.camFlash(false);

//        enc.gyroHold(-0.2, 45, 3);
//        enc.gyroHold(0.2, 45, 3);
//        enc.gyroHold(-0.2, -45, 3);
//        enc.gyroHold(0.2, -45, 3);


        //1. Drop down from the latch
//        lift1.setPosition(0.75);
//        lift2.setPosition(0.75);
//        lift3.setPosition(0.75);
//        lift4.setPosition(0.75);
//        hook.setPower(-1);
//


        //2. strafe off
        // enc.gyroStrafeDistance(0.4,-3,0,false);


        //3.1. Come forward

        //enc.gyroDrive(0.4, -3,0,false);
        // waitFor(200);

        //3.2. Re-center

        //enc.gyroStrafeDistance(0.4,-3, 0, false);
        //waitFor(200);


        //4. Sample gold mineral

        enc.gyroDrive(0.3, -6, 0, false);
        waitFor(500);

        if (position == 0) {
            enc.gyroDrive(0.4,-6,0,false);
            enc.gyroStrafeDistance(0.3,-16.97-3, 0,false);
            waitFor(500);
            enc.gyroDrive(0.4, -15, 0, false);
            waitFor(500);
            enc.gyroDrive(0.4, 13, 0, false);
            waitFor(1000);

        } else if (position == 2) {
            enc.gyroHold(-0.4, -45, 250);
//          put out outake and pickup gold mineral.
            enc.gyroHold(0.4, 45, 250);
            enc.gyroStrafeDistance(0.4, -2, 0, false);
            // put out outtake into depot
            enc.gyroStrafeDistance(0.4, 2, 0, false);


        } else {

            enc.gyroDrive(0.4, -18, 0, false);
            waitFor(1000);
            enc.gyroDrive(0.4,12,0,false);
        }




            enc.gyroStrafeDistance(0.4, -45, 0, false);
            waitFor(250);
            enc.gyroHold(-0.2, -230 , 3);
            enc.gyroStrafeDistance(0.4, 3, 0, false);
            enc.gyroDrive(0.4, -30, 0, false);
            waitFor(2000);
            enc.gyroDrive(0.4, 60, 0, false);

//

    }
}