package net.xbwee.libnoise.example.texture;

import net.xbwee.libnoise.module.Module;
import net.xbwee.libnoise.utils.*;



public abstract class TextureGenerator implements ITextureGenerator {


    public abstract Module GetMainSourceModule();
    public abstract Module GetSubSourceModule();
    public abstract void ConfigureMainTexture(RendererImage renderer);
    public abstract void ConfigureSubTexture(RendererImage renderer);


    @Override
    public void CreatePlanarTexture(boolean seamless, int height, String filename) {
        // Map the output values from the noise module onto a plane.  This will
        // create a two-dimensional noise map which can be rendered as a flat
        // texture map.
        NoiseMapBuilderPlane plane = new NoiseMapBuilderPlane();
        NoiseMap lowerNoiseMap = new NoiseMap();
        NoiseMap upperNoiseMap = new NoiseMap();
        plane.SetBounds (-1.0, 1.0, -1.0, 1.0);
        plane.SetDestSize (height, height);
        plane.EnableSeamless (seamless);

        // Generate the lower noise map.
        plane.SetSourceModule (GetMainSourceModule());
        plane.SetDestNoiseMap (lowerNoiseMap);
        plane.Build ();

        // Generate the upper noise map.
        if (HasOverlay()) {
            plane.SetSourceModule(GetSubSourceModule());
            plane.SetDestNoiseMap(upperNoiseMap);
            plane.Build();
        }

        RenderTexture (lowerNoiseMap, upperNoiseMap, filename);
    }

    @Override
    public void CreateSphericalTexture(int height, String filename) {
        // Map the output values from the noise module onto a sphere.  This will
        // create a two-dimensional noise map which can be rendered as a spherical
        // texture map.
        NoiseMapBuilderSphere sphere = new NoiseMapBuilderSphere();
        NoiseMap lowerNoiseMap = new NoiseMap();
        NoiseMap upperNoiseMap = new NoiseMap();
        sphere.SetBounds (-90.0, 90.0, -180.0, 180.0); // degrees
        sphere.SetDestSize (height * 2, height);

        // Generate the lower noise map.
        sphere.SetSourceModule (GetMainSourceModule());
        sphere.SetDestNoiseMap (lowerNoiseMap);
        sphere.Build ();

        // Generate the upper noise map.
        if (HasOverlay()) {
            sphere.SetSourceModule(GetSubSourceModule());
            sphere.SetDestNoiseMap(upperNoiseMap);
            sphere.Build();
        }

        RenderTexture (lowerNoiseMap, upperNoiseMap, filename);
    }

    private void RenderTexture(NoiseMap lowerNoiseMap, NoiseMap upperNoiseMap, String filename) {
        // Create the color gradients for the texture.
        RendererImage textureRenderer = new RendererImage();
        // Set up us the texture renderer and pass the noise map to it.
        Image destTexture = new Image();

        textureRenderer.SetSourceNoiseMap (lowerNoiseMap);
        textureRenderer.SetDestImage (destTexture);

        // lower texture.
        ConfigureMainTexture (textureRenderer);

        // Render the texture.
        textureRenderer.Render ();

        if (HasOverlay()) {
            // Set up us the texture renderer and pass the upper noise map to it.  Also
            // use the lower texture map as a background so that the upper texture map
            // can be rendered on top of the lower texture map.
            textureRenderer.SetSourceNoiseMap(upperNoiseMap);
            textureRenderer.SetBackgroundImage(destTexture);
            textureRenderer.SetDestImage(destTexture);

            // upper texture.
            ConfigureSubTexture(textureRenderer);

            // Render the texture.
            textureRenderer.Render();
        }
        // Write the texture as a Windows bitmap file (*.bmp).
        WriterBMP textureWriter = new WriterBMP();
        textureWriter.SetSourceImage (destTexture);
        textureWriter.SetDestFilename (filename);
        textureWriter.WriteDestFile ();
    }

    private boolean HasOverlay() {
        return GetSubSourceModule() != null;
    }
}
