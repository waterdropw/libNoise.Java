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
        writer.SetDestFilename("tutorial03-blackwhite.bmp");
        writer.WriteDestFile();

        // color image
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.0000, new Color (  0,   0, 128, 255)); // deeps
        renderer.AddGradientPoint (-0.2500, new Color (  0,   0, 255, 255)); // shallow
        renderer.AddGradientPoint ( 0.0000, new Color (  0, 128, 255, 255)); // shore
        renderer.AddGradientPoint ( 0.0625, new Color (240, 240,  64, 255)); // sand
        renderer.AddGradientPoint ( 0.1250, new Color ( 32, 160,   0, 255)); // grass
        renderer.AddGradientPoint ( 0.3750, new Color (224, 224,   0, 255)); // dirt
        renderer.AddGradientPoint ( 0.7500, new Color (128, 128, 128, 255)); // rock
        renderer.AddGradientPoint ( 1.0000, new Color (255, 255, 255, 255)); // snow

        renderer.Render ();
        writer.SetDestFilename("tutorial03-colorful.bmp");
        writer.WriteDestFile();

        // light contrast
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0); // Triple the contrast
        renderer.Render ();
        writer.SetDestFilename("tutorial03-contrast.bmp");
        writer.WriteDestFile();

        // light brightness
        renderer.EnableLight ();
        renderer.SetLightContrast (3.0); // Triple the contrast
        renderer.SetLightBrightness (2.0); // Double the brightness
        renderer.Render ();
        writer.SetDestFilename("tutorial03-brightness.bmp");
        writer.WriteDestFile();

        // two height-map 'tiles' can then be seamlessly joined together.
        heightMapBuilder.SetBounds (6.0, 10.0, 1.0, 5.0);
        heightMapBuilder.Build();
        renderer.Render ();
        writer.SetDestFilename("tutorial03-tiling.bmp");
        writer.WriteDestFile();
    }
}
