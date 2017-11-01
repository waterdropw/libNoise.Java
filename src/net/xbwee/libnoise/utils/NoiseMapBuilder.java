/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.xbwee.libnoise.utils;

import net.xbwee.libnoise.module.Module;

public class NoiseMapBuilder {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public NoiseMapBuilder(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(NoiseMapBuilder obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        NoiseUtilsJNI.delete_NoiseMapBuilder(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void Build() {
    NoiseUtilsJNI.NoiseMapBuilder_Build(swigCPtr, this);
  }

  public double GetDestHeight() {
    return NoiseUtilsJNI.NoiseMapBuilder_GetDestHeight(swigCPtr, this);
  }

  public double GetDestWidth() {
    return NoiseUtilsJNI.NoiseMapBuilder_GetDestWidth(swigCPtr, this);
  }

  public void SetCallback(SWIGTYPE_p_f_int__void pCallback) {
    NoiseUtilsJNI.NoiseMapBuilder_SetCallback(swigCPtr, this, SWIGTYPE_p_f_int__void.getCPtr(pCallback));
  }

  public void SetDestNoiseMap(NoiseMap destNoiseMap) {
    NoiseUtilsJNI.NoiseMapBuilder_SetDestNoiseMap(swigCPtr, this, NoiseMap.getCPtr(destNoiseMap), destNoiseMap);
  }

  public void SetSourceModule(Module sourceModule) {
    NoiseUtilsJNI.NoiseMapBuilder_SetSourceModule(swigCPtr, this, Module.getCPtr(sourceModule), sourceModule);
  }

  public void SetDestSize(int destWidth, int destHeight) {
    NoiseUtilsJNI.NoiseMapBuilder_SetDestSize(swigCPtr, this, destWidth, destHeight);
  }

}
