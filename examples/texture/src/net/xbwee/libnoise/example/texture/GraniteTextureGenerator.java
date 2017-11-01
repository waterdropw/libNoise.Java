package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.noise.NoiseQuality;
import net.xbwee.libnoise.utils.*;



public class GraniteTextureGenerator extends TextureGenerator {
    private Module mMainModule;


    public GraniteTextureGenerator() {
        super();
        InitializeTexture();
    }

    @Override
    public Module GetMainSourceModule() {
        return mMainModule;
    }

    @Override
    public Module GetSubSourceModule() {
        return null;
    }

    @Override
    public void ConfigureSubTexture(RendererImage renderer) {

    }

    @Override
    public void ConfigureMainTexture (RendererImage renderer) {
        // Create a gray granite palette.  Black and pink appear at either ends of
        // the palette; those colors provide the charactistic black and pink flecks
        // in granite.
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.0000, new Color ((short)  0, (short)  0, (short)  0, (short)255));
        renderer.AddGradientPoint (-0.9375, new Color ((short)  0, (short)  0, (short)  0, (short)255));
        renderer.AddGradientPoint (-0.8750, new Color ((short)216, (short)216, (short)242, (short)255));
        renderer.AddGradientPoint ( 0.0000, new Color ((short)191, (short)191, (short)191, (short)255));
        renderer.AddGradientPoint ( 0.5000, new Color ((short)210, (short)116, (short)125, (short)255));
        renderer.AddGradientPoint ( 0.7500, new Color ((short)210, (short)113, (short) 98, (short)255));
        renderer.AddGradientPoint ( 1.0000, new Color ((short)255, (short)176, (short)192, (short)255));

        renderer.EnableLight (true);
        renderer.SetLightAzimuth (135.0);
        renderer.SetLightElev (60.0);
        renderer.SetLightContrast (2.0);
        renderer.SetLightColor (new Color ((short)255, (short)255, (short)255, (short)0));

    }


    private void InitializeTexture() {
        // Primary granite texture.  This generates the "roughness" of the texture
        // when lit by a light source.
        Billow primaryGranite = new Billow();
        primaryGranite.SetSeed (0);
        primaryGranite.SetFrequency (8.0);
        primaryGranite.SetPersistence (0.625);
        primaryGranite.SetLacunarity (2.18359375);
        primaryGranite.SetOctaveCount (6);
        primaryGranite.SetNoiseQuality (NoiseQuality.QUALITY_STD);

        // Use Voronoi polygons to produce the small grains for the granite texture.
        Voronoi baseGrains = new Voronoi();
        baseGrains.SetSeed (1);
        baseGrains.SetFrequency (16.0);
        baseGrains.EnableDistance (true);

        // Scale the small grain values so that they may be added to the base
        // granite texture.  Voronoi polygons normally generate pits, so apply a
        // negative scaling factor to produce bumps instead.
        ScaleBias scaledGrains = new ScaleBias();
        scaledGrains.SetSourceModule (0, baseGrains);
        scaledGrains.SetScale (-0.5);
        scaledGrains.SetBias (0.0);

        // Combine the primary granite texture with the small grain texture.
        Add combinedGranite = new Add();
        combinedGranite.SetSourceModule (0, primaryGranite);
        combinedGranite.SetSourceModule (1, scaledGrains);

        // Finally, perturb the granite texture to add realism.
        Turbulence finalGranite = new Turbulence();
        finalGranite.SetSourceModule (0, combinedGranite);
        finalGranite.SetSeed (2);
        finalGranite.SetFrequency (4.0);
        finalGranite.SetPower (1.0 / 8.0);
        finalGranite.SetRoughness (6);

        mMainModule = finalGranite;
    }
}
