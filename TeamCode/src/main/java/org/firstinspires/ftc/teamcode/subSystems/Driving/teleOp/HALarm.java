package org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HALarm {
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    public DcMotor slideRotL;
    public DcMotor slideRotR;
    public DcMotor slideMotL;
    public DcMotor slideMotR;

    public HALarm(final HardwareMap _hardwareMap, final Telemetry _telemetry) {
        hardwareMap = _hardwareMap;
        telemetry = _telemetry;

        slideRotL = hardwareMap.dcMotor.get("slideRotL");
        slideRotR = hardwareMap.dcMotor.get("slideRotR");
        slideRotL.setDirection(DcMotorSimple.Direction.FORWARD);
        slideRotR.setDirection(DcMotorSimple.Direction.REVERSE);
        slideRotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRotR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRotL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slideMotL = hardwareMap.dcMotor.get("slideMotL");
        slideMotR = hardwareMap.dcMotor.get("slideMotR");
        slideMotL.setDirection(DcMotorSimple.Direction.FORWARD);
        slideMotR.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void slideMove(Gamepad gamepad) {
        slideMotR.setPower(gamepad.left_stick_y);
        slideMotL.setPower(gamepad.left_stick_y);
    }
    public void slideRot() {

    }
}
