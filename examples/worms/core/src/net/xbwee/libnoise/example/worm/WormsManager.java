package net.xbwee.libnoise.example.worm;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import net.xbwee.libnoise.noise.Noise;

import java.util.ArrayList;

public class WormsManager {

    // Default worm count
    public static final int WORM_CURRENT_COUNT = 32;
    // Default segment count for each worm.
    public static final int WORM_SEGMENT_COUNT = 112;
    // Default worm lateral speed.
    public static final double WORM_LATERAL_SPEED = (2.0 / 8192.0);
    // Default length of a worm segment, in screen units.
    public static final double WORM_SEGMENT_LENGTH = (1.0 / 64.0);
    // Default worm speed.
    public static final double WORM_SPEED = (3.0 / 2048.0);
    // Default worm thickness.
    public static final double WORM_THICKNESS = (4.0 / 256.0);
    // Default "twistiness" of the worms.
    public static final double WORM_TWISTINESS = (4.0 / 256.0);



    private static final int MAX_WORM_COUNT = 1024;

    private static volatile WormsManager sInstance;

    private int mWormsCurrentCount;
    private int mWormSegmentCount;
    private double mWormSpeed;
    private double mWormLateralSpeed;
    private double mWormThickness;
    private double mWormTwistiness;

    private ArrayList<Worm> mWormsArray;


    /**
     * Single Instance
     */
    private WormsManager() {
        mWormsArray = new ArrayList<Worm>(MAX_WORM_COUNT);
        for (int i = 0; i < MAX_WORM_COUNT; i++) {
            Worm worm = new Worm();
            Vector2 pos = new Vector2();
            pos.x = (float) Noise.ValueNoise3D (i + 1000, i + 2000, i + 3000);
            pos.y = (float) Noise.ValueNoise3D (i + 1001, i + 2001, i + 3001);
            worm.SetSeed(i);
            worm.SetHeadScreenPos(pos);

            mWormsArray.add(worm);
        }

        mWormsCurrentCount = WORM_CURRENT_COUNT;
        mWormSegmentCount = WORM_SEGMENT_COUNT;
        mWormSpeed = WORM_SPEED;
        mWormLateralSpeed = WORM_LATERAL_SPEED;
        mWormThickness = WORM_THICKNESS;
        mWormTwistiness = WORM_TWISTINESS;

        for (int i = 0; i < mWormsCurrentCount; i++) {
            mWormsArray.get(i).SetSegmentCount(mWormSegmentCount);
            mWormsArray.get(i).SetSpeed(mWormSpeed);
            mWormsArray.get(i).SetLateralSpeed(mWormLateralSpeed);
            mWormsArray.get(i).SetThickness(mWormThickness);
            mWormsArray.get(i).SetTwistiness(mWormTwistiness);
        }
    }

    public static WormsManager newInstance() {
        if (sInstance == null) {
            synchronized (WormsManager.class) {
                if (sInstance == null)
                    sInstance = new WormsManager();
            }
        }
        return sInstance;
    }

    public void drawWorms(Camera camera) {
        for (int i = 0; i < mWormsCurrentCount; i++) {
            mWormsArray.get(i).Draw(camera);
        }
    }

    public void updateWorms() {
        for (int i = 0; i < mWormsCurrentCount; i++) {
            mWormsArray.get(i).Update();
        }
    }

    public int getmWormsCurrentCount() {
        return mWormsCurrentCount;
    }

    public void setmWormsCurrentCount(int mWormsCurrentCount) {
        this.mWormsCurrentCount = clamp(mWormsCurrentCount, 1, 1024);
        resetWormsParams();
    }

    public int getmWormSegmentCount() {
        return mWormSegmentCount;
    }

    public void setmWormSegmentCount(int mWormSegmentCount) {
        this.mWormSegmentCount = clamp(mWormSegmentCount, 1, 256);
        resetWormsParams();
    }

    public double getmWormSpeed() {
        return mWormSpeed;
    }

    public void setmWormSpeed(double mWormSpeed) {
        this.mWormSpeed = clamp(mWormSpeed, 1.0 / 2048.0, 1024.0 / 2048.0);
        resetWormsParams();
    }

    public double getmWormLateralSpeed() {
        return mWormLateralSpeed;
    }

    public void setmWormLateralSpeed(double mWormLateralSpeed) {
        this.mWormLateralSpeed = clamp(mWormLateralSpeed, 1.0 / 8192.0, 64.0 / 8192.0);
        resetWormsParams();
    }

    public double getmWormThickness() {
        return mWormThickness;
    }

    public void setmWormThickness(double mWormThickness) {
        this.mWormThickness = clamp(mWormThickness, 1.0 / 256.0, 16.0 / 256.0);
        resetWormsParams();
    }

    public double getmWormTwistiness() {
        return mWormTwistiness;
    }

    public void setmWormTwistiness(double mWormTwistiness) {
        this.mWormTwistiness = clamp(mWormTwistiness, 1.0 / 256.0, 16.0 / 256.0);
        resetWormsParams();
    }

    private void resetWormsParams() {
        for (int i = 0; i < mWormsCurrentCount; i++) {
            mWormsArray.get(i).SetSegmentCount(mWormSegmentCount);
            mWormsArray.get(i).SetSpeed(mWormSpeed);
            mWormsArray.get(i).SetLateralSpeed(mWormLateralSpeed);
            mWormsArray.get(i).SetThickness(mWormThickness);
            mWormsArray.get(i).SetTwistiness(mWormTwistiness);
        }
    }

    private int clamp(int input, int min, int max) {
        return Math.max(min, Math.min(input, max));
    }

    private float clamp(float input, float min, float max) {
        return Math.max(min, Math.min(input, max));
    }

    private double clamp(double input, double min, double max) {
        return Math.max(min, Math.min(input, max));
    }

}