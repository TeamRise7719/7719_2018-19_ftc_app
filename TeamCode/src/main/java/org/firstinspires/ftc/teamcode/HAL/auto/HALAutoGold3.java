package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;
@Disabled
@Autonomous
public class HALAutoGold3 extends LinearOpMode {


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

//        winchMotor = hardwareMap.dcMotor.get("winch");
//        winchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

//        winchMotor.setPower(1);
//        waitFor(3350);
//        winchMotor.setPower(0);
//        plow.setPower(0);





        //2. Strafe to the left

        enc.gyroDrive(0.4, 3, 0, false);
        waitFor(250);
        winchMotor.setPower(0);



        //3.1. Come forward

        enc.gyroStrafeDistance(0.4, -3,0,false);
        waitFor(200);

        //3.2. Re-center

        enc.gyroDrive(0.4,-3, 0, false);
        waitFor(200);

        enc.gyroStrafeDistance(0.4, -10,0,false);
        waitFor(250);

        enc.gyroHold(0.4, -90, 3);



        //4. Sample gold mineral



        if(position == 0){
            enc.gyroStrafeDistance(0.3,16.97+3, 0,false);
            waitFor(250);
            enc.gyroDrive(0.4, 21, 0, false);
            waitFor(250);
            enc.gyroDrive(0.4, -21,0, false);
            waitFor(250);
            enc.gyroStrafeDistance(0.3,-16.97-3, 0,false);
        } else if (position == 2) {
            enc.gyroStrafeDistance(0.3,16.97-3, 0,false);
            waitFor(250);
            enc.gyroDrive(0.4, 21, 0, false);
            waitFor(250);
            enc.gyroDrive(0.4, -21, 0, false);
            waitFor(250);
            enc.gyroStrafeDistance(0.3,-16.97+3, 0,false);
        } else {
            enc.gyroDrive(0.4, 18, 0, false);
            waitFor(250);
            enc.gyroDrive(0.4, 1-8, 0, false);
            waitFor(2520);

        }

        enc.gyroStrafeDistance(0.3, 51, 0, false);
        waitFor(250);
        enc.gyroHold(-0.2, -135, 3);
        enc.gyroStrafeDistance(0.4, 1.5, 0, false);
        enc.gyroDrive(0.4, 55, 0, false);
//
//        waitFor(1000);l
//
        marker.setPower(1);
        waitFor(900);
        enc.gyroDrive(0.4, -62, 0, false);
        waitFor(500);

        crater.setPower(1);
        waitFor(1000);





//        plow.setPower(1);
//        waitFor(500);
//        plow.setPower(0);
//        //8. Turn towards crater
        //9. Score in crater
    }
}