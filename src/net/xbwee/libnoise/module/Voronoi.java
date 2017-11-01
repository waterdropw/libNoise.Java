/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.xbwee.libnoise.module;

import net.xbwee.libnoise.noise.NoiseQuality;

public class Voronoi extends Module {
  private transient long swigCPtr;

  public Voronoi(long cPtr, boolean cMemoryOwn) {
    super(NoiseModuleJNI.Voronoi_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(Voronoi obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        NoiseModuleJNI.delete_Voronoi(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public Voronoi() {
    this(NoiseModuleJNI.new_Voronoi(), true);
  }

  public void EnableDistance(boolean enable) {
    NoiseModuleJNI.Voronoi_EnableDistance__SWIG_0(swigCPtr, this, enable);
  }

  public void EnableDistance() {
    NoiseModuleJNI.Voronoi_EnableDistance__SWIG_1(swigCPtr, this);
  }

  public double GetDisplacement() {
    return NoiseModuleJNI.Voronoi_GetDisplacement(swigCPtr, this);
  }

  public double GetFrequency() {
    return NoiseModuleJNI.Voronoi_GetFrequency(swigCPtr, this);
  }

  public int GetSourceModuleCount() {
    return NoiseModuleJNI.Voronoi_GetSourceModuleCount(swigCPtr, this);
  }

  public int GetSeed() {
    return NoiseModuleJNI.Voronoi_GetSeed(swigCPtr, this);
  }

  public boolean IsDistanceEnabled() {
    return NoiseModuleJNI.Voronoi_IsDistanceEnabled(swigCPtr, this);
  }

  public double GetValue(double x, double y, double z) {
    return NoiseModuleJNI.Voronoi_GetValue(swigCPtr, this, x, y, z);
  }

  public void SetDisplacement(double displacement) {
    NoiseModuleJNI.Voronoi_SetDisplacement(swigCPtr, this, displacement);
  }

  public void SetFrequency(double frequency) {
    NoiseModuleJNI.Voronoi_SetFrequency(swigCPtr, this, frequency);
  }

  public void SetSeed(int seed) {
    NoiseModuleJNI.Voronoi_SetSeed(swigCPtr, this, seed);
  }

}
