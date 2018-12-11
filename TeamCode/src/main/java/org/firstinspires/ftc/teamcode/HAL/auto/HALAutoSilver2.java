package org.firstinspires.ftc.teamcode.HAL.auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;

@Autonomous
public class HALAutoSilver2 extends LinearOpMode {


    encoderLibrary enc;
    visionLibrary vis;
    int position;
    DcMotor winchMotor;
    DcMotor plow;
    CRServo marker,crater;
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

        winchMotor = hardwareMap.dcMotor.get("winch");
        winchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        plow = hardwareMap.dcMotor.get("plow");
        plow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        marker = hardwareMap.crservo.get("marker");
        crater = hardwareMap.crservo.get("crater");

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

        winchMotor.setPower(1);
        waitFor(3250);
        winchMotor.setPower(0);
        plow.setPower(0);


        //2. turn to the center

        enc.gyroHold(0.4, 90, 250);
        winchMotor.setPower(0);

        //3.1. Come forward

        enc.gyroDrive(0.4, 3,0,false);
        waitFor(200);

        //3.2. Re-center

        enc.gyroStrafeDistance(0.4,-3, 0, false);
        waitFor(200);


        //4. Sample gold mineral

        enc.gyroDrive(0.3, 9, 0, false);
        waitFor(500);
        if(position == 0){
            enc.gyroHold(0.4, 45, 250);
//          put out outtake and pickup gold mineral.
            enc.gyroHold(-0.4,-45,250);
            enc.gyroStrafeDistance(0.4, 2,0,false);
            // put out outtake into depot
            enc.gyroStrafeDistance(0.4, -2,0,false);

        } else if (position == 2) {
            enc.gyroHold(-0.4, -45, 250);
//          put out outake and pickup gold mineral.
            enc.gyroHold(0.4,45,250);
            enc.gyroStrafeDistance(0.4, -2,0,false);
            // put out outtake into depot
            enc.gyroStrafeDistance(0.4, 2,0,false);


        } else
        {
            // put out outtake all the way to depot


        }

        enc.gyroStrafeDistance(0.4, 52, 0, false);
        waitFor(250);
        enc.gyroHold(0.2, -215 + 195    , 3);
        waitFor(200);
        enc.gyroStrafeDistance(0.2,-5,0,false);
        waitFor(200);
        enc.gyroDrive(0.4, 36, 0, false);
        waitFor(200);




        enc.gyroHold(0.4, 90, 250);
        enc.gyroDrive(0.4, 4, 0, false);
        enc.gyroStrafeDistance(0.4, 5,0,false);
        //put out intake and score in lander
        //do as many times as we can
        crater.setPower(1);
        waitFor(250);

    }
}