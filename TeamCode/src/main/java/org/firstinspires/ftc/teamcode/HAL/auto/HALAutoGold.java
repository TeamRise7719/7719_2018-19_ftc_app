package org.firstinspires.ftc.teamcode.HAL.auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;


    @Autonomous
     public class HALAutoGold extends LinearOpMode {

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

//            winchMotor = hardwareMap.dcMotor.get("winch");
//            winchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            plow = hardwareMap.dcMotor.get("plow");
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

//        enc.gyroHold(-0.2, 45, 3);
//        enc.gyroHold(0.2, 45, 3);
//        enc.gyroHold(-0.2, -45, 3);
//        enc.gyroHold(0.2, -45, 3);


            //1. Drop down from the latch

//            winchMotor.setPower(1);
//            waitFor(1750);
//            plow.setPower(-0.75);
//            waitFor(1000);
//            waitFor(3250);
//            winchMotor.setPower(0);
//            plow.setPower(0);


            //2. Strafe to the left

            enc.gyroStrafeDistance(0.4, -3, 0, false);
//        enc.gyroStrafeDistance(0.4, 3, 0, false);
//            winchMotor.setPower(-1);
//            waitFor(2750);
//            winchMotor.setPower(0);

            //3.1. Come forward

            enc.gyroDrive(0.4, 3, 0, false);
            waitFor(1000);
            waitFor(500);

            //3.2. Re-center

            enc.gyroStrafeDistance(0.4, 3, 0, false);
            waitFor(1000);
            waitFor(500);


            //4. Sample gold mineral

            enc.gyroDrive(0.3, 9, 0, false);
            waitFor(1000);

            waitFor(500);
            if (position == 0) {
                enc.gyroStrafeDistance(0.3, 16.97 + 3, 0, false);
                waitFor(500);
                enc.gyroDrive(0.4, 18, 0, false);
                waitFor(500);
                enc.gyroDrive(0.4, -18, 0, false);
                waitFor(1000);
                enc.gyroDrive(0.4, 24, 0, false);
                waitFor(1000);
//            enc.gyroHold(0.2, 45, 3);
//            enc.gyroDrive(0.4,6,0,false);
//            waitFor(500);
                enc.gyroStrafeDistance(0.3, -16.97 - 3, 0, false);
            } else if (position == 2) {
                enc.gyroStrafeDistance(0.3, -16.97 + 3, 0, false);
                waitFor(1000);
                enc.gyroDrive(0.4, 24, 0, false);
                waitFor(1000);
                enc.gyroHold(-0.2, 45, 3);
                enc.gyroDrive(0.4, 6, 0, false);
                enc.gyroStrafeDistance(0.3, -16.97 - 3, 0, false);
                waitFor(500);
            } else {
                enc.gyroDrive(0.4, 26, 0, false);
                enc.gyroDrive(0.4, 18, 0, false);
                waitFor(500);
                enc.gyroDrive(0.4, -18, 0, false);
                waitFor(1000);
            }





                //5. Drive clear of sample field





                //7. Drop off team marker

        marker.setPower(-0.5);
            enc.gyroStrafeDistance(0.3,16.97+3, 0,false);
              {
                enc.gyroDrive(0.4, 18, 0, false);
                waitFor(250);
                enc.gyroDrive(0.4, -18, 0, false);
                waitFor(500);

                waitFor(1000);
            }

        enc.gyroStrafeDistance(0.4, 52, 0, false);
            waitFor(250);
        enc.gyroHold(-0.2, -50, 3);
        enc.gyroStrafeDistance(0.4, 1.5, 0, false);
        enc.gyroDrive(0.4, 50, 0, false);
//
//        waitFor(1000);
//
        marker.setPower(1);

        plow.setPower(1);
            waitFor(1000);
        enc.gyroDrive(0.8, -75 / 2, 0, false);
            waitFor(500);
        plow.setPower(0);
//        //8. Turn towards crater

        enc.gyroHold(-0.2,135, 5);
        enc.gyroDrive(0.8, 30, 0, false);


//        plow.setPower(1);
//        waitFor(500);
//        plow.setPower(0);
//        //8. Turn towards crater
            //9. Score in crater





        }


    }
