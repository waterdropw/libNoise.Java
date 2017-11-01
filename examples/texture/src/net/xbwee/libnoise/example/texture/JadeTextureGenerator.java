package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.noise.NoiseQuality;
import net.xbwee.libnoise.utils.*;



public class JadeTextureGenerator extends TextureGenerator {
    private Module mMainModule;


    public JadeTextureGenerator() {
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
        // Create a nice jade palette.
        renderer.ClearGradient ();
        renderer.AddGradientPoint (-1.000, new Color ((short) 24, (short)146, (short)102, (short)255));
        renderer.AddGradientPoint ( 0.000, new Color ((short) 78, (short)154, (short)115, (short)255));
        renderer.AddGradientPoint ( 0.250, new Color ((short)128, (short)204, (short)165, (short)255));
        renderer.AddGradientPoint ( 0.375, new Color ((short) 78, (short)154, (short)115, (short)255));
        renderer.AddGradientPoint ( 1.000, new Color ((short) 29, (short)135, (short)102, (short)255));

        renderer.EnableLight (false);
    }


    private void InitializeTexture() {
        // Primary jade texture.  The ridges from the ridged-multifractal module
        // produces the veins.
        RidgedMulti primaryJade = new RidgedMulti();
        primaryJade.SetSeed (0);
        primaryJade.SetFrequency (2.0);
        primaryJade.SetLacunarity (2.20703125);
        primaryJade.SetOctaveCount (6);
        primaryJade.SetNoiseQuality (NoiseQuality.QUALITY_STD);

        // Base of the secondary jade texture.  The base texture uses concentric
        // cylinders aligned on the z axis, which will eventually be perturbed.
        Cylinders baseSecondaryJade = new Cylinders();
        baseSecondaryJade.SetFrequency (2.0);

        // Rotate the base secondary jade texture so that the cylinders are not
        // aligned with any axis.  This produces more variation in the secondary
        // jade texture since the texture is parallel to the y-axis.
        RotatePoint rotatedBaseSecondaryJade = new RotatePoint();
        rotatedBaseSecondaryJade.SetSourceModule (0, baseSecondaryJade);
        rotatedBaseSecondaryJade.SetAngles (90.0, 25.0, 5.0);

        // Slightly perturb the secondary jade texture for more realism.
        Turbulence perturbedBaseSecondaryJade = new Turbulence();
        perturbedBaseSecondaryJade.SetSourceModule (0, rotatedBaseSecondaryJade);
        perturbedBaseSecondaryJade.SetSeed (1);
        perturbedBaseSecondaryJade.SetFrequency (4.0);
        perturbedBaseSecondaryJade.SetPower (1.0 / 4.0);
        perturbedBaseSecondaryJade.SetRoughness (4);

        // Scale the secondary jade texture so it contributes a small part to the
        // final jade texture.
        ScaleBias secondaryJade = new ScaleBias();
        secondaryJade.SetSourceModule (0, perturbedBaseSecondaryJade);
        secondaryJade.SetScale (0.25);
        secondaryJade.SetBias (0.0);

        // Add the two jade textures together.  These two textures were produced
        // using different combinations of coherent noise, so the final texture will
        // have a lot of variation.
        Add combinedJade = new Add();
        combinedJade.SetSourceModule (0, primaryJade);
        combinedJade.SetSourceModule (1, secondaryJade);

        // Finally, perturb the combined jade textures to produce the final jade
        // texture.  A low roughness produces nice veins.
        Turbulence finalJade = new Turbulence();
        finalJade.SetSourceModule (0, combinedJade);
        finalJade.SetSeed (2);
        finalJade.SetFrequency (4.0);
        finalJade.SetPower (1.0 / 16.0);
        finalJade.SetRoughness (2);

        mMainModule = finalJade;
    }
}
