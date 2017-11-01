/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.xbwee.libnoise.model;

import net.xbwee.libnoise.module.Module;

public class Sphere {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public Sphere(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(Sphere obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        NoiseModelJNI.delete_Sphere(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public Sphere() {
    this(NoiseModelJNI.new_Sphere__SWIG_0(), true);
  }

  public Sphere(Module module) {
    this(NoiseModelJNI.new_Sphere__SWIG_1(Module.getCPtr(module), module), true);
  }

  public Module GetModule() {
    return new Module(NoiseModelJNI.Sphere_GetModule(swigCPtr, this), false);
  }

  public double GetValue(double lat, double lon) {
    return NoiseModelJNI.Sphere_GetValue(swigCPtr, this, lat, lon);
  }

  public void SetModule(Module module) {
    NoiseModelJNI.Sphere_SetModule(swigCPtr, this, Module.getCPtr(module), module);
  }

}
