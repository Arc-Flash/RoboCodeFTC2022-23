package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.support.broadsupport.ConstantHeadingSpline;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.InsertMarker;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.Line;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.Path;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.Robot;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.SplinePath;
import org.firstinspires.ftc.teamcode.auto.support.broadsupport.Turn;
import com.outoftheboxrobotics.photoncore.PhotonCore;

@Autonomous(name="TestSplineAuto")
public class TestSplineAuto extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        PhotonCore.enable();
        initializeHardware();

        Path line = new Line(0.5, 1);
        Path turn = new Turn(-45, 0.75);
        double[] radii = {2, 1, 0.5};
        double[] arcLengths = {0.4,0.2,-0.1};
        Path ConstantHeadingSpline = new SplinePath(0.81, 0.2, radii, arcLengths);

        addPath(line);
        addPath(turn);
        addPath(ConstantHeadingSpline);

        initialize();
        executeAuto();
    }
}