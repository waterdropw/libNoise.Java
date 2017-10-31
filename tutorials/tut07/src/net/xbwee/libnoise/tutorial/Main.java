package net.xbwee.libnoise.tutorial;

import net.xbwee.libnoise.module.*;
import net.xbwee.libnoise.utils.*;


public class Main {

    static {
        System.loadLibrary("libNoiseBase");
        System.loadLibrary("libNoiseModel");
        System.loadLibrary("libNoiseModule");
        System.loadLibrary("libNoiseUtils");
    }

    public static void main(String[] args) {
        RidgedMulti mountainTerrain = new RidgedMulti();
        Billow baseFlatTerrain = new Billow();
        ScaleBias flatTerrain = new ScaleBias();
        ScaleBias terrainScaler = new ScaleBias();
        Perlin terrainType = new Perlin();
        Select terrainSelector = new Select();
        Turbulence finalTerrain = new Turbulence();

        NoiseMap heightMap = new NoiseMap();
        NoiseMapBuilderPlane heightMapBuilder = new NoiseMapBuilderPlane();

        WriterTER writer = new WriterTER();


        heightMapBuilder.SetDestNoiseMap (heightMap);
        heightMapBuilder.SetDestSize (513, 513);
        heightMapBuilder.SetBounds (6.0, 10.0, 1.0, 5.0);

        baseFlatTerrain.SetFrequency (2.0);

        flatTerrain.SetSourceModule (0, baseFlatTerrain);
        flatTerrain.SetScale (0.125);
        flatTerrain.SetBias (-0.75);

        terrainType.SetFrequency (0.5);
        terrainType.SetPersistence (0.25);

        terrainSelector.SetSourceModule (0, flatTerrain);
        terrainSelector.SetSourceModule (1, mountainTerrain);
        terrainSelector.SetControlModule (terrainType);
        terrainSelector.SetBounds (0.0, 1000.0);
        terrainSelector.SetEdgeFalloff (0.125);

        terrainScaler.SetSourceModule (0, terrainSelector);
        terrainScaler.SetScale (375.0);
        terrainScaler.SetBias (375.0);

        finalTerrain.SetSourceModule(0, terrainScaler);
        finalTerrain.SetFrequency (4.0);
        finalTerrain.SetPower (0.125);

        heightMapBuilder.SetSourceModule (finalTerrain);

        writer.SetSourceNoiseMap(heightMap);
        writer.SetDestFilename("terrain.ter");
        writer.SetMetersPerPoint(15);
        writer.WriteDestFile();
    }
}
