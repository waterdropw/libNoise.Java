package net.xbwee.libnoise.example.texture;


/**
 * ITextureGenerator
 */
public interface ITextureGenerator {
    void CreatePlanarTexture(boolean seamless, int height, String filename);
    void CreateSphericalTexture(int height, String filename);
}