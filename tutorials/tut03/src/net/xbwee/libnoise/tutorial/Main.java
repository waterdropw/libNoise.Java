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

        heightMapBuilder.SetSourceModule(perlin);
        heightMapBuilder.SetDestNoiseMap(heightMap);
        heightMapBuilder.SetDestSize(256, 256);
        heightMapBuilder.SetBounds(2.0, 6.0, 1.0, 5.0);
        heightMapBuilder.Build();

        renderer.SetSourceNoiseMap(heightMap);
        renderer.SetDestImage(image);
        renderer.Render();

        // white/black image
        writer.SetSourceImage(image);
        writer.SetDestFilename("tut03-blackwhite.bmp");
        writer.WriteDestFile();

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

        renderer.Render ();
        writer.SetDestFilename("tut03-colorful.bmp");
        writer.WriteDestFile();

        // light contrast
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0); // Triple the contrast
        renderer.Render ();
        writer.SetDestFilename("tut03-contrast.bmp");
        writer.WriteDestFile();

        // light brightness
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0); // Triple the contrast
        renderer.SetLightBrightness (2.0); // Double the brightness
        renderer.Render ();
        writer.SetDestFilename("tut03-brightness.bmp");
        writer.WriteDestFile();

        // two height-map 'tiles' can then be seamlessly joined together.
        heightMapBuilder.SetBounds (6.0, 10.0, 1.0, 5.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tut03-tiling.bmp");
        writer.WriteDestFile();
    }
}
