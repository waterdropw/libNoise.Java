package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.noise.NoiseQuality;
import net.xbwee.libnoise.utils.*;



public class WoodTextureGenerator extends TextureGenerator {
    private Module mMainModule;


    public WoodTextureGenerator() {
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
        // Create a dark-stained wood palette (oak?)
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.00, new Color((short)189, (short)94, (short)4, (short)255));
        renderer.AddGradientPoint ( 0.50, new Color ((short)144, (short)48, (short)6, (short)255));
        renderer.AddGradientPoint ( 1.00, new Color ((short)60, (short)0, (short)8, (short)255));

        renderer.EnableLight (false);
    }


    private void InitializeTexture() {
        // Base wood texture.  The base texture uses concentric cylinders aligned
        // on the z axis, like a log.
        Cylinders baseWood = new Cylinders();
        baseWood.SetFrequency (16.0);

        // Perlin noise to use for the wood grain.
        Perlin woodGrainNoise = new Perlin();
        woodGrainNoise.SetSeed (0);
        woodGrainNoise.SetFrequency (48.0);
        woodGrainNoise.SetPersistence (0.5);
        woodGrainNoise.SetLacunarity (2.20703125);
        woodGrainNoise.SetOctaveCount (3);
        woodGrainNoise.SetNoiseQuality (NoiseQuality.QUALITY_STD);

        // Stretch the Perlin noise in the same direction as the center of the
        // log.  This produces a nice wood-grain texture.
        ScalePoint scaledBaseWoodGrain = new ScalePoint();
        scaledBaseWoodGrain.SetSourceModule (0, woodGrainNoise);
        scaledBaseWoodGrain.SetYScale (0.25);

        // Scale the wood-grain values so that they may be added to the base wood
        // texture.
        ScaleBias woodGrain = new ScaleBias();
        woodGrain.SetSourceModule (0, scaledBaseWoodGrain);
        woodGrain.SetScale (0.25);
        woodGrain.SetBias (0.125);

        // Add the wood grain texture to the base wood texture.
        Add combinedWood = new Add();
        combinedWood.SetSourceModule (0, baseWood);
        combinedWood.SetSourceModule (1, woodGrain);

        // Slightly perturb the wood texture for more realism.
        Turbulence perturbedWood = new Turbulence();
        perturbedWood.SetSourceModule (0, combinedWood);
        perturbedWood.SetSeed (1);
        perturbedWood.SetFrequency (4.0);
        perturbedWood.SetPower (1.0 / 256.0);
        perturbedWood.SetRoughness (4);

        // Cut the wood texture a small distance from the center of the "log".
        TranslatePoint translatedWood = new TranslatePoint();
        translatedWood.SetSourceModule (0, perturbedWood);
        translatedWood.SetZTranslation (1.48);

        // Cut the wood texture on an angle to produce a more interesting wood
        // texture.
        RotatePoint rotatedWood = new RotatePoint();
        rotatedWood.SetSourceModule (0, translatedWood);
        rotatedWood.SetAngles (84.0, 0.0, 0.0);

        // Finally, perturb the wood texture to produce the final texture.
        Turbulence finalWood = new Turbulence();
        finalWood.SetSourceModule (0, rotatedWood);
        finalWood.SetSeed (2);
        finalWood.SetFrequency (2.0);
        finalWood.SetPower (1.0 / 64.0);
        finalWood.SetRoughness (4);

        mMainModule = finalWood;
    }
}
