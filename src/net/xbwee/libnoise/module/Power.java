/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.xbwee.libnoise.module;

import net.xbwee.libnoise.noise.NoiseQuality;

public class Power extends Module {
  private transient long swigCPtr;

  public Power(long cPtr, boolean cMemoryOwn) {
    super(NoiseModuleJNI.Power_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(Power obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        NoiseModuleJNI.delete_Power(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public Power() {
    this(NoiseModuleJNI.new_Power(), true);
  }

  public int GetSourceModuleCount() {
    return NoiseModuleJNI.Power_GetSourceModuleCount(swigCPtr, this);
  }

  public double GetValue(double x, double y, double z) {
    return NoiseModuleJNI.Power_GetValue(swigCPtr, this, x, y, z);
  }

}
