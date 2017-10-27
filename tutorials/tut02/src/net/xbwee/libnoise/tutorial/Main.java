package net.xbwee.libnoise.tutorial;

import net.xbwee.libnoise.module.Perlin;



public class Main {

    static {
        System.loadLibrary("libNoiseBase");
        System.loadLibrary("libNoiseModel");
        System.loadLibrary("libNoiseModule");
    }

    public static void main(String[] args) {
        Perlin perlin = new Perlin();
        double x = 1.25;
        double y = 0.75;
        double z = 0.50;
        double value = perlin.GetValue(x, y, z);

        System.out.println("Perlin (" + x + "," + y + "," + z + ") -> " + value);

        x = 1.2501;
        y = 0.7501;
        z = 0.5001;
        value = perlin.GetValue (x, y, z);
        System.out.println("Perlin (" + x + "," + y + "," + z + ") -> " + value);

        x = 14.50;
        y = 20.25;
        z = 75.75;
        value = perlin.GetValue (x, y, z);
        System.out.println("Perlin (" + x + "," + y + "," + z + ") -> " + value);
    }
}
