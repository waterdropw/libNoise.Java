/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.xbwee.libnoise.module;

import net.xbwee.libnoise.noise.NoiseQuality;

public class ScalePoint extends Module {
  private transient long swigCPtr;

  public ScalePoint(long cPtr, boolean cMemoryOwn) {
    super(NoiseModuleJNI.ScalePoint_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(ScalePoint obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        NoiseModuleJNI.delete_ScalePoint(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ScalePoint() {
    this(NoiseModuleJNI.new_ScalePoint(), true);
  }

  public int GetSourceModuleCount() {
    return NoiseModuleJNI.ScalePoint_GetSourceModuleCount(swigCPtr, this);
  }

  public double GetValue(double x, double y, double z) {
    return NoiseModuleJNI.ScalePoint_GetValue(swigCPtr, this, x, y, z);
  }

  public double GetXScale() {
    return NoiseModuleJNI.ScalePoint_GetXScale(swigCPtr, this);
  }

  public double GetYScale() {
    return NoiseModuleJNI.ScalePoint_GetYScale(swigCPtr, this);
  }

  public double GetZScale() {
    return NoiseModuleJNI.ScalePoint_GetZScale(swigCPtr, this);
  }

  public void SetScale(double scale) {
    NoiseModuleJNI.ScalePoint_SetScale__SWIG_0(swigCPtr, this, scale);
  }

  public void SetScale(double xScale, double yScale, double zScale) {
    NoiseModuleJNI.ScalePoint_SetScale__SWIG_1(swigCPtr, this, xScale, yScale, zScale);
  }

  public void SetXScale(double xScale) {
    NoiseModuleJNI.ScalePoint_SetXScale(swigCPtr, this, xScale);
  }

  public void SetYScale(double yScale) {
    NoiseModuleJNI.ScalePoint_SetYScale(swigCPtr, this, yScale);
  }

  public void SetZScale(double zScale) {
    NoiseModuleJNI.ScalePoint_SetZScale(swigCPtr, this, zScale);
  }

}
