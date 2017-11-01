package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.noise.NoiseQuality;
import net.xbwee.libnoise.utils.Color;
import net.xbwee.libnoise.utils.RendererImage;


public class SlimeTextureGenerator extends TextureGenerator {
    private Module mMainModule;


    public SlimeTextureGenerator() {
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
        // Create a green slime palette.  A dirt brown color is used for very low
        // values while green is used for the rest of the values.
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.0, new Color((short)160, (short)64, (short)42, (short)255));
        renderer.AddGradientPoint ( 0.0, new Color ((short)64, (short)192, (short)64, (short)255));
        renderer.AddGradientPoint ( 1.0, new Color ((short)128, (short)255, (short)128, (short)255));

        renderer.EnableLight (true);
        renderer.SetLightAzimuth (135.0);
        renderer.SetLightElev (60.0);
        renderer.SetLightContrast (2.0);
        renderer.SetLightColor (new Color ((short)255, (short)255, (short)255, (short)0));

    }

    private void InitializeTexture() {
        // Large slime bubble texture.
        Billow largeSlime = new Billow();
        largeSlime.SetSeed (0);
        largeSlime.SetFrequency (4.0);
        largeSlime.SetLacunarity (2.12109375);
        largeSlime.SetOctaveCount (1);
        largeSlime.SetNoiseQuality (NoiseQuality.QUALITY_BEST);

        // Base of the small slime bubble texture.  This texture will eventually
        // appear inside cracks in the large slime bubble texture.
        Billow smallSlimeBase = new Billow();
        smallSlimeBase.SetSeed (1);
        smallSlimeBase.SetFrequency (24.0);
        smallSlimeBase.SetLacunarity (2.14453125);
        smallSlimeBase.SetOctaveCount (1);
        smallSlimeBase.SetNoiseQuality (NoiseQuality.QUALITY_BEST);

        // Scale and lower the small slime bubble values.
        ScaleBias smallSlime = new ScaleBias();
        smallSlime.SetSourceModule (0, smallSlimeBase);
        smallSlime.SetScale (0.5);
        smallSlime.SetBias (-0.5);

        // Create a map that specifies where the large and small slime bubble
        // textures will appear in the final texture map.
        RidgedMulti slimeMap = new RidgedMulti();
        slimeMap.SetSeed (0);
        slimeMap.SetFrequency (2.0);
        slimeMap.SetLacunarity (2.20703125);
        slimeMap.SetOctaveCount (3);
        slimeMap.SetNoiseQuality (NoiseQuality.QUALITY_STD);

        // Choose between the large or small slime bubble textures depending on the
        // corresponding value from the slime map.  Choose the small slime bubble
        // texture if the slime map value is within a narrow range of values,
        // otherwise choose the large slime bubble texture.  The edge falloff is
        // non-zero so that there is a smooth transition between the two textures.
        Select slimeChooser = new Select();
        slimeChooser.SetSourceModule (0, largeSlime);
        slimeChooser.SetSourceModule (1, smallSlime);
        slimeChooser.SetControlModule (slimeMap);
        slimeChooser.SetBounds (-0.375, 0.375);
        slimeChooser.SetEdgeFalloff (0.5);

        // Finally, perturb the slime texture to add realism.
        Turbulence finalSlime = new Turbulence();
        finalSlime.SetSourceModule (0, slimeChooser);
        finalSlime.SetSeed (2);
        finalSlime.SetFrequency (8.0);
        finalSlime.SetPower (1.0 / 32.0);
        finalSlime.SetRoughness (2);

        mMainModule = finalSlime;
    }
}
