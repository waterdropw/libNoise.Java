package net.xbwee.libnoise.tutorial;

import net.xbwee.libnoise.module.Perlin;
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
        NoiseMapBuilderPlane heightMapBuilder = new NoiseMapBuilderPlane();
        RendererImage renderer = new RendererImage();
        Image image = new Image();
        WriterBMP writer = new WriterBMP();

        writer.SetSourceImage(image);

        heightMapBuilder.SetSourceModule(perlin);
        heightMapBuilder.SetDestNoiseMap(heightMap);
        heightMapBuilder.SetDestSize(256, 256);
        heightMapBuilder.SetBounds(2.0, 6.0, 1.0, 5.0);

        renderer.SetSourceNoiseMap(heightMap);
        renderer.SetDestImage(image);
        // color image
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.0000, new Color (  (short)0,   (short)0, (short)128, (short)255)); // deeps
        renderer.AddGradientPoint (-0.2500, new Color (  (short)0,   (short)0, (short)255, (short)255)); // shallow
        renderer.AddGradientPoint ( 0.0000, new Color (  (short)0, (short)128, (short)255, (short)255)); // shore
        renderer.AddGradientPoint ( 0.0625, new Color ((short)240, (short)240,  (short)64, (short)255)); // sand
        renderer.AddGradientPoint ( 0.1250, new Color ( (short)32, (short)160,   (short)0, (short)255)); // grass
        renderer.AddGradientPoint ( 0.3750, new Color ((short)224, (short)224,   (short)0, (short)255)); // dirt
        renderer.AddGradientPoint ( 0.7500, new Color ((short)128, (short)128, (short)128, (short)255)); // rock
        renderer.AddGradientPoint ( 1.0000, new Color ((short)255, (short)255, (short)255, (short)255)); // snow

        renderer.EnableLight ();
        renderer.SetLightContrast (3.0); // Triple the contrast
        renderer.SetLightBrightness (2.0); // Double the brightness

        // height map generated with different octaves:1,2,3,4
        perlin.SetOctaveCount (1);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-octave1.bmp");
        writer.WriteDestFile();

        perlin.SetOctaveCount (2);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-octave2.bmp");
        writer.WriteDestFile();

        perlin.SetOctaveCount (3);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-octave3.bmp");
        writer.WriteDestFile();

        perlin.SetOctaveCount (4);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-octave4.bmp");
        writer.WriteDestFile();


        // height map generated with different frequency: 1,2,4,8

        // reset to default octaves
        perlin.SetOctaveCount (6);

        perlin.SetFrequency(1.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-freq1.bmp");
        writer.WriteDestFile();

        perlin.SetFrequency(2.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-freq2.bmp");
        writer.WriteDestFile();

        perlin.SetFrequency(4.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-freq4.bmp");
        writer.WriteDestFile();

        perlin.SetFrequency(8.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-freq8.bmp");
        writer.WriteDestFile();

        // height map generated with different persistence: 1/4,1/2,3/4

        // reset to default frequency
        perlin.SetFrequency(1.0);

        perlin.SetPersistence(0.25);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-pers0.25.bmp");
        writer.WriteDestFile();

        perlin.SetPersistence(0.5);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-pers0.50.bmp");
        writer.WriteDestFile();

        perlin.SetPersistence(0.75);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut04-pers0.75.bmp");
        writer.WriteDestFile();
    }
}
