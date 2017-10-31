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
        RidgedMulti mountainTerrain = new RidgedMulti();
        Billow baseFlatTerrain = new Billow();
        ScaleBias flatTerrain = new ScaleBias();
        Perlin terrainType = new Perlin();
        Select terrainSelector = new Select();
        Turbulence finalTerrain = new Turbulence();

        NoiseMap heightMap = new NoiseMap();
        NoiseMapBuilderPlane heightMapBuilder = new NoiseMapBuilderPlane();
        RendererImage renderer = new RendererImage();
        Image image = new Image();
        WriterBMP writer = new WriterBMP();

        writer.SetSourceImage(image);
        heightMapBuilder.SetDestNoiseMap (heightMap);
        heightMapBuilder.SetDestSize (256, 256);
        heightMapBuilder.SetBounds (6.0, 10.0, 1.0, 5.0);
        renderer.SetSourceNoiseMap (heightMap);
        renderer.SetDestImage (image);


        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.00, new Color ( (short)32, (short)160,   (short)0, (short)255)); // grass
        renderer.AddGradientPoint (-0.25, new Color ((short)224, (short)224,   (short)0, (short)255)); // dirt
        renderer.AddGradientPoint ( 0.25, new Color ((short)128, (short)128, (short)128, (short)255)); // rock
        renderer.AddGradientPoint ( 1.00, new Color ((short)255, (short)255, (short)255, (short)255)); // snow
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0);
        renderer.SetLightBrightness (2.0);

        baseFlatTerrain.SetFrequency (2.0);

        flatTerrain.SetSourceModule (0, baseFlatTerrain);
        flatTerrain.SetScale (0.125);
        flatTerrain.SetBias (-0.75);

        terrainType.SetFrequency (0.5);
        terrainType.SetPersistence (0.25);

        terrainSelector.SetSourceModule (0, flatTerrain);
        terrainSelector.SetSourceModule (1, mountainTerrain);
        terrainSelector.SetControlModule (terrainType);
        terrainSelector.SetBounds (0.0, 1000.0);
        terrainSelector.SetEdgeFalloff (0.125);

        finalTerrain.SetSourceModule(0, terrainSelector);
        heightMapBuilder.SetSourceModule (finalTerrain);

        for (int i = 1; i <= 16; i *= 2) {
            for (int j = 1; j <= 16; j *= 2) {
                finalTerrain.SetFrequency (i);
                finalTerrain.SetPower (1 / (double)j);

                heightMapBuilder.Build ();
                renderer.Render ();
                writer.SetDestFilename("tut06-f" + i + "-p1_" + j + ".bmp");
                writer.WriteDestFile();
            }
        }
    }
}
