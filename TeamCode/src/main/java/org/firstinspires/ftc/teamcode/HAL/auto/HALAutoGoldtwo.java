package org.firstinspires.ftc.teamcode.HAL.auto;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;

@Autonomous
public class HALAutoGoldtwo extends LinearOpMode {

/* Code for HAL */


    encoderLibrary enc;
    visionLibrary vis;
    int position;
    DcMotor armR;
    DcMotor armL;
    DcMotor shoulderR;
    DcMotor shoulderL;
    CRServo intR, wristR, wristL;
    CRServo intL;
    CRServo hook;
    Servo lift1;
    Servo lift2;
    Servo lift3;
    Servo lift4;

    ElapsedTime etime = new ElapsedTime();

    public void waitFor(int time){
        time = time/1000;
        etime.reset();
        while ((etime.time() < time)&&(opModeIsActive())) {
            idle();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {


        armR = hardwareMap.dcMotor.get("armR");
        armL = hardwareMap.dcMotor.get("armL");
        armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        shoulderL = hardwareMap.dcMotor.get("shoulderL");
        shoulderR = hardwareMap.dcMotor.get("shoulderR");

        intL = hardwareMap.crservo.get("intL");
        intR = hardwareMap.crservo.get("intR");

        hook = hardwareMap.crservo.get("hook");

        lift1 = hardwareMap.servo.get("lift1");
        lift2 = hardwareMap.servo.get("lift2");
        lift3 = hardwareMap.servo.get("lift3");
        lift4 = hardwareMap.servo.get("lift4");

        wristR = hardwareMap.crservo.get("wristR");
        wristL = hardwareMap.crservo.get("wristL");


        lift1.setPosition(0.21);
        lift1.setPosition(0.21);
        lift2.setPosition(0.21);
        lift3.setPosition(0.21);
        lift4.setPosition(0.21);





        enc = new encoderLibrary(hardwareMap, telemetry,this);
        enc.init();

        vis = new visionLibrary(hardwareMap, telemetry);
        vis.initVuforia();
        vis.initTfod();
        vis.camFlash(true);
        while(!isStarted()){
            position = vis.objectVision();
        }

        vis.tensorflowclose();

        vis.camFlash(false);

        //        enc.gyroHold(-0.2, 45, 3);
        //        enc.gyroHold(0.2, 45, 3);
        //        enc.gyroHold(-0.2, -45, 3);
//        enc.gyroHold(0.2, -45, 3);





        //1. Drop down from the latch

        lift1.setPosition(0.8);
        lift2.setPosition(0.8);
        lift3.setPosition(0.8);
        lift4.setPosition(0.8);
        waitFor(4500);


        hook.setPower(-0.65);
        waitFor(1000);

        //2. Strafe to the left

        enc.gyroStrafeDistance(0.4, 6, 0, false);
        enc.gyroStrafeDistance(0.4, -8, 0, false);
        waitFor(1000);
        lift1.setPosition(0.21);
        lift2.setPosition(0.21);
        lift3.setPosition(0.21);
        lift4.setPosition(0.21);
        waitFor(4500);



        //3.1. Come forward


        //3.2. Re-center

//        enc.gyroStrafeDistance(0.4,-3, 0, false);
//        waitFor(1000);
//        waitFor(500);


        //4. Sample gold mineral


        waitFor(500);
        if(position == 0){
            enc.gyroDrive(0.4,-8,0,false);
            enc.gyroStrafeDistance(0.3,-16.97-3, 0,false);
            waitFor(500);
            enc.gyroDrive(0.4, -18, 0, false);
            waitFor(500);
            enc.gyroDrive(0.4, 18, 0, false);
            waitFor(1000);
//            enc.gyroDrive(0.4, -24, 0, false);
//            waitFor(1000);

//            enc.gyroHold(0.3, 47,2);
//            waitFor(100);
//            armR.setPower(0.5);
//            armL.setPower(-0.5);
//            waitFor(100);
//            armR.setPower(-0.5);
//            armL.setPower(0.5);
//            waitFor(100);
//            enc.gyroHold(0.3, -47,2);

//            enc.gyroHold(0.2, 45, 3);
//            enc.gyroDrive(0.4,6,0,false);
            waitFor(500);
            enc.gyroStrafeDistance(0.3,16.97+3,0,false);


        } else if (position == 2) {
           enc.gyroDrive(0.4,-8,0,false);
           enc.gyroStrafeDistance(0.3,16.97+3,0,false);
           waitFor(1000);
           enc.gyroDrive(0.4, -18, 0, false);
           waitFor(1000);
           enc.gyroHold(-0.2, 45, 3);
           enc.gyroDrive(0.4,18,0,false);
           enc.gyroStrafeDistance(0.3,-16.97-3,0,false);
           waitFor(500);

        } else {
            waitFor(5000);
            enc.gyroDrive(0.3, -31,0, false);
            enc.gyroDrive(0.3, 17,0, false);

//            intR.setPower(-0.7);
//            intL.setPower(0.7);
//            armL.setTargetPosition(500);
//            armR.setTargetPosition(500);
//            armL.setPower(0.25);
//            armR.setPower(-0.25);
//            armR.setTargetPosition(2100);
//            armL.setTargetPosition(2100);
//            armL.setPower(0.5);
//            armR.setPower(-0.5)-


        }
//        enc.gyroDrive(0.4,-8,0,false);
//        enc.gyroStrafeDistance(0.3,16.97+3,0,false);
//            waitFor(1000);
//            enc.gyroDrive(0.4, -18, 0, false);
//            waitFor(1000);
////            enc.gyroHold(-0.2, 45, 3);
//            enc.gyroDrive(0.4,18,0,false);
//            enc.gyroStrafeDistance(0.3,-16.97-3,0,false);
//            waitFor(500);

            //enc.gyroHold(0.3, 50,2);
//            armR.setTargetPosition(2100);
//            armL.setTargetPosition(2100);
//            waitFor(900);
//            armR.setTargetPosition(-2100);
//            armL.setTargetPosition(-2100);
//            waitFor(900);
            //enc.gyroHold(0.3, -50,2);

        //5. Drop off team marker
//        intL.setPower(-1);
//        intR.setPower(1);
//        waitFor(2000);
//        armR.setTargetPosition(-2100);
//        armL.setTargetPosition(-2100);
//        waitFor(250);
//

        //6. Drive clear of sample field
        //enc.gyroDrive(0.4, -5,0,false);
        enc.gyroStrafeDistance(0.4, -60, 0, false);
        waitFor(250);
        enc.gyroHold(-0.2, -50, 3);
        enc.gyroStrafeDistance(0.4, -2.5, 0, false);
        enc.gyroDrive(0.4, -20, 0, false);
        waitFor(250);
        wristL.setPower(0.18);
        wristR.setPower(-0.18);
        waitFor(1000);
        intL.setPower(-0.8);
        intL.setPower(0.8);
        waitFor(2000);
        enc.gyroDrive(0.4, 72, 0,false);
//
//
//        waitFor(1000);
//        enc.gyroDrive(0.8, 75 / 2, 0, false);
//        waitFor(500);

//        //8. Turn towards crater

//        enc.gyroHold(-0.2,135, 5);
//        enc.gyroDrive(0.8, 30, 0, false);


//




    }

}