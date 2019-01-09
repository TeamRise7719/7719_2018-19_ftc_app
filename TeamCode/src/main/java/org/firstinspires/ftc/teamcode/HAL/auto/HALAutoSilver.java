package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;
@Autonomous

public class HALAutoSilver extends LinearOpMode {

    encoderLibrary enc;
    visionLibrary vis;
    int position;
    DcMotor winchMotor;
    DcMotor plow;
    CRServo marker,crater;
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
//
//        winchMotor = hardwareMap.dcMotor.get("winch");
//        winchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        plow = hardwareMap.dcMotor.get("plow");
        plow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        marker = hardwareMap.crservo.get("marker");
        crater = hardwareMap.crservo.get("crater");

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


        //1. Drop down from the latch


//        winchMotor.setPower(1);
//        waitFor(3400);
//        plow.setPower(-0.75);
//        waitFor(200);
//        winchMotor.setPower(0);
//        plow.setPower(0);



        //2. Strafe to the left

        enc.gyroDrive(0.4, 4, 0, false);
        waitFor(200);



        //3.1. Come forward

        enc.gyroStrafeDistance(0.4, 5, 0, false);
        waitFor(200);
        enc.gyroHold(0.4, -90, 1);

        //3.2. Re-center

        enc.gyroDrive(0.4, -4, 0, false);
        waitFor(200);


        //4. Sample gold mineral


        enc.gyroDrive(0.3, 9, 0, false);
        waitFor(200);

        if (position == 0) {
            enc.gyroDrive(0.3, 16.97 + 3, 0, false);
            waitFor(500);
            enc.gyroStrafeDistance(0.4, 15, 0, false);
            waitFor(500);
            enc.gyroStrafeDistance(0.4, -15, 0, false);
            enc.gyroDrive(0.3, -16.97 + 3, 0, false);

        } else if (position == 2) {
            enc.gyroDrive(0.3, -16.97 + 3, 0, false);
            waitFor(500);

            enc.gyroStrafeDistance(0.4, 15, 0, false);
            waitFor(500);
            enc.gyroStrafeDistance(0.4, -15, 0, false);
            waitFor(500);
            enc.gyroDrive(0.3, 16.97 + 3, 0, false);

        } else {
            enc.gyroStrafeDistance(0.4, 15, 0, false);
            waitFor(500);
            enc.gyroStrafeDistance(0.4, -15, 0, false);
        }

            enc.gyroDrive(0.4, 45, 0, false);
            waitFor(200);
            enc.gyroHold(0.2, -215 + 195    , 3);
            waitFor(200);
            enc.gyroStrafeDistance(0.2,-5,0,false);
            waitFor(200);
            enc.gyroDrive(0.4, 36, 0, false);
            waitFor(200);
            marker.setPower(1);
            waitFor(1000);

        enc.gyroDrive(0.4, -62, 0, false);
        waitFor(200);
        crater.setPower(0.7);

//            enc.gyroDrive(0.4, 8, 0, false);
//            waitFor(250);
//            enc.gyroHold(0.2, 315, 3);
//            waitFor(500);
//            enc.gyroDrive(0.8, -80, 0, false);


            plow.setPower(0.85);
            waitFor(800);

            //enc.gyroHold(0.4,90,500);
            //enc.gyroDrive(0.4, 36, 0, false);

            //enc.gyroHold(-0.4,45,500);
            //enc.gyroDrive(0.4, 26, 0, false);
            //marker.setPower(-0.5);

            //waitFor(1000);

            //marker.setPower(1);

            //enc.gyroHold(-0.4,45,500);

            //enc.gyroDrive(-0.4, 45, 0, false);


            //waitFor(4000);

            //plow.setPower(-0.85);


            //5. Drive clear of sample field


            //7. Drop off team marker

            //8. Turn towards crater


            //9. Score in crater


        }


    }

