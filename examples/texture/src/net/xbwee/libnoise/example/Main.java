package net.xbwee.libnoise.example;


import net.xbwee.libnoise.example.texture.*;


public class Main {

    public static void main(String[] args) {

        ITextureGenerator generator = null;

	    System.out.println("Generate Stained wood Texture begin");
        generator = new WoodTextureGenerator();
        generator.CreatePlanarTexture(false, 256, "StainedWood-plane.bmp");
        generator.CreatePlanarTexture(true, 256, "StainedWood-seamless.bmp");
        generator.CreateSphericalTexture(256, "StainedWood-spherical.bmp");
        System.out.println("Generate Stained wood Texture end");

        System.out.println("Generate Green slime Texture begin");
        generator = new SlimeTextureGenerator();
        generator.CreatePlanarTexture(false, 256, "GreenSlime-plane.bmp");
        generator.CreatePlanarTexture(true, 256, "GreenSlime-seamless.bmp");
        generator.CreateSphericalTexture(256, "GreenSlime-spherical.bmp");
        System.out.println("Generate Green slime Texture end");

        System.out.println("Generate Clouds over water Texture begin");
        generator = new SkyTextureGenerator();
        generator.CreatePlanarTexture(false, 256, "CloudsOverWater-plane.bmp");
        generator.CreatePlanarTexture(true, 256, "CloudsOverWater-seamless.bmp");
        generator.CreateSphericalTexture(256, "CloudsOverWater-spherical.bmp");
        System.out.println("Generate Clouds over water Texture end");

        System.out.println("Generate African jade Texture begin");
        generator = new JadeTextureGenerator();
        generator.CreatePlanarTexture(false, 256, "AfricanJade-plane.bmp");
        generator.CreatePlanarTexture(true, 256, "AfricanJade-seamless.bmp");
        generator.CreateSphericalTexture(256, "AfricanJade-spherical.bmp");
        System.out.println("Generate African jade Texture end");

        System.out.println("Generate Granite Texture begin");
        generator = new GraniteTextureGenerator();
        generator.CreatePlanarTexture(false, 256, "Granite-plane.bmp");
        generator.CreatePlanarTexture(true, 256, "Granite-seamless.bmp");
        generator.CreateSphericalTexture(256, "Granite-spherical.bmp");
        System.out.println("Generate Granite Texture end");
    }

    static {
        System.loadLibrary("libNoiseBase");
        System.loadLibrary("libNoiseModel");
        System.loadLibrary("libNoiseModule");
        System.loadLibrary("libNoiseUtils");
    }
}