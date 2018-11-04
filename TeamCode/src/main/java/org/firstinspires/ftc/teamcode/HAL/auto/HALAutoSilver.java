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
    CRServo marker;
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


        //1. Drop down from the latch


        winchMotor.setPower(1);
        waitFor(1950);
        plow.setPower(-0.75);
        waitFor(1000);
        winchMotor.setPower(0);
        plow.setPower(0);



        //2. Strafe to the left

        enc.gyroStrafeDistance(0.4, 3, 0, false);
        winchMotor.setPower(-1);
        waitFor(2750);
        winchMotor.setPower(0);


        //3.1. Come forward

        enc.gyroDrive(0.4, 3,0,false);
        waitFor(1000);

        //3.2. Re-center

        enc.gyroStrafeDistance(0.4,-3, 0, false);
        waitFor(1000);


        //4. Sample gold mineral

        enc.gyroDrive(0.3, 9, 0, false);
        waitFor(1000);

        if(position == 0){
            enc.gyroStrafeDistance(0.3,16.97+3, 0,false);
            waitFor(1000);
            enc.gyroDrive(0.4, 8, 0, false);
            waitFor(1000);

        } else if (position == 2) {
            enc.gyroStrafeDistance(0.3,-16.97+3, 0,false);
            waitFor(1000);

            enc.gyroDrive(0.4, 8, 0, false);
            waitFor(1000);

        } else {
            enc.gyroDrive(0.4, 8, 0, false);
            waitFor(1000);
        }


        waitFor(1000);
        plow.setPower(0.85);
        waitFor(800);


        enc.gyroDrive(0.4, 12, 0, false);
        waitFor(4000);

        plow.setPower(-0.85);





        //5. Drive clear of sample field





        //7. Drop off team marker

        //8. Turn towards crater



        //9. Score in crater





    }



}
