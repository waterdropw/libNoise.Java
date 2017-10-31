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
        Select finalTerrain = new Select();

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

        // mountain terrain map
        heightMapBuilder.SetSourceModule (mountainTerrain);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-mountain.bmp");
        writer.WriteDestFile();

        // base flat terrain map
        baseFlatTerrain.SetFrequency (2.0);
        heightMapBuilder.SetSourceModule (baseFlatTerrain);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-baseflat.bmp");
        writer.WriteDestFile();

        // flatten the base terrain map
        flatTerrain.SetSourceModule (0, baseFlatTerrain);
        flatTerrain.SetScale (0.125);
        flatTerrain.SetBias (-0.75);
        heightMapBuilder.SetSourceModule (flatTerrain);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-flatten.bmp");
        writer.WriteDestFile();


        // terrain type map
        terrainType.SetFrequency (0.5);
        terrainType.SetPersistence (0.25);
        heightMapBuilder.SetSourceModule (terrainType);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-terraintype.bmp");
        writer.WriteDestFile();

        // final map
        finalTerrain.SetSourceModule (0, flatTerrain);
        finalTerrain.SetSourceModule (1, mountainTerrain);
        finalTerrain.SetControlModule (terrainType);
        finalTerrain.SetBounds (0.0, 1000.0);
        heightMapBuilder.SetSourceModule (finalTerrain);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-final.bmp");
        writer.WriteDestFile();

        // more smooth the edge
        finalTerrain.SetEdgeFalloff (0.125);
        heightMapBuilder.Build ();
        renderer.Render ();
        writer.SetDestFilename("tut05-finalsmooth.bmp");
        writer.WriteDestFile();
    }
}
