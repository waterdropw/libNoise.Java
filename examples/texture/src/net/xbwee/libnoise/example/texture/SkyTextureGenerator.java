package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.noise.NoiseQuality;
import net.xbwee.libnoise.utils.Color;
import net.xbwee.libnoise.utils.RendererImage;

import static net.xbwee.libnoise.noise.NoiseQuality.QUALITY_BEST;


public class SkyTextureGenerator extends TextureGenerator {
    private Module mMainModule;
    private Module mSubModule;


    public SkyTextureGenerator() {
        super();
        InitializeTexture();
    }

    @Override
    public Module GetMainSourceModule() {
        return mMainModule;
    }

    @Override
    public Module GetSubSourceModule() {
        return mSubModule;
    }

    @Override
    public void ConfigureMainTexture (RendererImage renderer) {
        // Create a water palette with varying shades of blue.
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.00, new Color ((short) 48,(short)  64, (short)192, (short)255));
        renderer.AddGradientPoint ( 0.50, new Color ((short) 96,(short) 192, (short)255, (short)255));
        renderer.AddGradientPoint ( 1.00, new Color ((short)255,(short) 255, (short)255, (short)255));

        renderer.EnableLight (true);
        renderer.SetLightAzimuth (135.0);
        renderer.SetLightElev (60.0);
        renderer.SetLightContrast (2.0);
        renderer.SetLightColor (new Color ((short)255, (short)255, (short)255, (short)0));

    }

    @Override
    public void ConfigureSubTexture(RendererImage renderer) {
        // Create an entirely white palette with varying alpha (transparency) values
        // for the clouds.  These transparent values allows the water to show
        // through.
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.00, new Color ((short)255, (short)255, (short)255, (short)  0));
        renderer.AddGradientPoint (-0.50, new Color ((short)255, (short)255, (short)255, (short)  0));
        renderer.AddGradientPoint ( 1.00, new Color ((short)255, (short)255, (short)255, (short)255));

        renderer.EnableLight (false);
    }

    private void InitializeTexture() {
        // This texture map is made up two layers.  The bottom layer is a wavy water
        // texture.  The top layer is a cloud texture.  These two layers are
        // combined together to create the final texture map.

        // Lower layer: water texture
        // --------------------------

        // Base of the water texture.  The Voronoi polygons generate the waves.  At
        // the center of the polygons, the values are at their lowest.  At the edges
        // of the polygons, the values are at their highest.  The values smoothly
        // change between the center and the edges of the polygons, producing a
        // wave-like effect.
        Voronoi baseWater = new Voronoi();
        baseWater.SetSeed (0);
        baseWater.SetFrequency (8.0);
        baseWater.EnableDistance (true);
        baseWater.SetDisplacement (0.0);

        // Stretch the waves along the z axis.
        ScalePoint baseStretchedWater = new ScalePoint();
        baseStretchedWater.SetSourceModule (0, baseWater);
        baseStretchedWater.SetScale (1.0, 1.0, 3.0);

        // Smoothly perturb the water texture for more realism.
        Turbulence finalWater = new Turbulence();
        finalWater.SetSourceModule (0, baseStretchedWater);
        finalWater.SetSeed (1);
        finalWater.SetFrequency (8.0);
        finalWater.SetPower (1.0 / 32.0);
        finalWater.SetRoughness (1);

        // Upper layer: cloud texture
        // --------------------------

        // Base of the cloud texture.  The billowy noise produces the basic shape
        // of soft, fluffy clouds.
        Billow cloudBase = new Billow();
        cloudBase.SetSeed (2);
        cloudBase.SetFrequency (2.0);
        cloudBase.SetPersistence (0.375);
        cloudBase.SetLacunarity (2.12109375);
        cloudBase.SetOctaveCount (4);
        cloudBase.SetNoiseQuality (QUALITY_BEST);

        // Perturb the cloud texture for more realism.
        Turbulence finalClouds = new Turbulence();
        finalClouds.SetSourceModule (0, cloudBase);
        finalClouds.SetSeed (3);
        finalClouds.SetFrequency (16.0);
        finalClouds.SetPower (1.0 / 64.0);
        finalClouds.SetRoughness (2);

        mMainModule = finalWater;
        mSubModule = finalClouds;
    }
}
