package net.xbwee.libnoise.tutorial;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.utils.*;


public class Main {

    static {
        System.loadLibrary("libNoiseBase");
        System.loadLibrary("libNoiseModel");
        System.loadLibrary("libNoiseModule");
        System.loadLibrary("libNoiseUtils");
    }

    public static void main(String[] args) {
        Perlin perlin = new Perlin();
        NoiseMap heightMap = new NoiseMap();
        NoiseMapBuilderSphere heightMapBuilder = new NoiseMapBuilderSphere();
        RendererImage renderer = new RendererImage();
        Image image = new Image();
        WriterBMP writer = new WriterBMP();


        heightMapBuilder.SetDestNoiseMap(heightMap);
        heightMapBuilder.SetSourceModule(perlin);
        heightMapBuilder.SetDestSize (512, 256);
        heightMapBuilder.SetBounds (-90.0, 90.0, -180.0, 180.0);
        heightMapBuilder.Build ();

        renderer.SetSourceNoiseMap (heightMap);
        renderer.SetDestImage (image);
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.0000, new Color ((short)0, (short)0, (short)128, (short)255)); // deeps
        renderer.AddGradientPoint (-0.2500, new Color ((short)0, (short)0, (short)255, (short)255)); // shallow
        renderer.AddGradientPoint ( 0.0000, new Color ((short)0, (short)128, (short)255, (short)255)); // shore
        renderer.AddGradientPoint ( 0.0625, new Color ((short)240, (short)240, (short)64, (short)255)); // sand
        renderer.AddGradientPoint ( 0.1250, new Color ((short)32, (short)160, (short)0, (short)255)); // grass
        renderer.AddGradientPoint ( 0.3750, new Color ((short)224, (short)224, (short)0, (short)255)); // dirt
        renderer.AddGradientPoint ( 0.7500, new Color ((short)128, (short)128, (short)128, (short)255)); // rock
        renderer.AddGradientPoint ( 1.0000, new Color ((short)255, (short)255, (short)255, (short)255)); // snow
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0);
        renderer.SetLightBrightness (2.0);
        renderer.Render ();

        writer.SetSourceImage(image);
        writer.SetDestFilename("tut08-planet.bmp");
        writer.WriteDestFile();

        perlin.SetOctaveCount(10);
        heightMapBuilder.SetDestSize (256, 256);
        heightMapBuilder.SetBounds (0.0, 30.0, 50.0, 80.0);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut08-zoomin.bmp");
        writer.WriteDestFile();
    }
}
