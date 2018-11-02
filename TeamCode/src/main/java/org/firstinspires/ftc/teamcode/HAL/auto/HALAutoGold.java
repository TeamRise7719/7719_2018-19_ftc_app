package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;


@Autonomous
public class HALAutoGold extends LinearOpMode {


    encoderLibrary enc;
    visionLibrary vis;
    int position;
    DcMotor winchMotor;
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
        marker = hardwareMap.crservo.get("marker");

        enc = new encoderLibrary(hardwareMap, telemetry,this);
        enc.init();

        //1. Drop down from the latch


        waitForStart();

        winchMotor.setPower(1);
        waitFor(2750);
        winchMotor.setPower(0);

        //2. Strafe to the left

        enc.gyroStrafeDistance(0.25, 6, 0, true);

        winchMotor.setPower(-1);
        waitFor(2750);
        winchMotor.setPower(0);


        //3. Come forward


        //4. Sample gold mineral

        //5. Drive clear of sample field


        //6. Drive to depot

        enc.gyroDrive(0.2,40,0,false);
        marker.setPower(0.5);
        waitFor(400);

        //7. Drop off team marker

        marker.setPower(0.8);

        //8. Turn towards crater

        enc.gyroTurn(0.2,-135);


        //9. Score in crater





    }


}