/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public enum NoiseQuality {
  QUALITY_FAST(0),
  QUALITY_STD(1),
  QUALITY_BEST(2);

  public final int swigValue() {
    return swigValue;
  }

  public static NoiseQuality swigToEnum(int swigValue) {
    NoiseQuality[] swigValues = NoiseQuality.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (NoiseQuality swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + NoiseQuality.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private NoiseQuality() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private NoiseQuality(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private NoiseQuality(NoiseQuality swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}
