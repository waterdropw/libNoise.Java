package net.xbwee.libnoise.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import net.xbwee.libnoise.example.worm.Worm;
import net.xbwee.libnoise.example.worm.WormsManager;

public class InputHandler extends InputAdapter {

    @Override
    public boolean keyTyped(char character) {
        WormsManager manager = WormsManager.newInstance();
        switch (character) {
            case 'Q':
            case 'q':
                manager.setmWormsCurrentCount(manager.getmWormsCurrentCount() + 1);
                break;
            case 'A':
            case 'a':
                manager.setmWormsCurrentCount(manager.getmWormsCurrentCount() - 1);
                break;
            case 'W':
            case 'w':
                manager.setmWormSegmentCount(manager.getmWormSegmentCount() + 1);
                break;
            case 'S':
            case 's':
                manager.setmWormSegmentCount(manager.getmWormSegmentCount() - 1);
                break;
            case 'E':
            case 'e':
                manager.setmWormSpeed(manager.getmWormSpeed() + 1.0 / 2048.0);
                break;
            case 'D':
            case 'd':
                manager.setmWormSpeed(manager.getmWormSpeed() - 1.0 / 2048.0);
                break;
            case 'R':
            case 'r':
                manager.setmWormLateralSpeed(manager.getmWormLateralSpeed() + 1.0 / 8192.0);
                break;
            case 'F':
            case 'f':
                manager.setmWormLateralSpeed(manager.getmWormLateralSpeed() - 1.0 / 8192.0);
                break;
            case 'T':
            case 't':
                manager.setmWormThickness(manager.getmWormThickness() + 1.0 / 256.0);
                break;
            case 'G':
            case 'g':
                manager.setmWormThickness(manager.getmWormThickness() - 1.0 / 256.0);
                break;
            case 'Y':
            case 'y':
                manager.setmWormTwistiness(manager.getmWormTwistiness() + 1.0 / 256.0);
                break;
            case 'H':
            case 'h':
                manager.setmWormTwistiness(manager.getmWormTwistiness() - 1.0 / 256.0);
                break;

            case 27:
                Gdx.app.exit();

            default:
                System.out.println("Unknown keycode: " + character);
        }

        return true;
    }
}
