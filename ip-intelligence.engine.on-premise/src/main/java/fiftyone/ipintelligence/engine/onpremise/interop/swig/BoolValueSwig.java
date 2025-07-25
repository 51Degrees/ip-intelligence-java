/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class BoolValueSwig implements AutoCloseable {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected BoolValueSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BoolValueSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(BoolValueSwig obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        IpIntelligenceOnPremiseEngineModuleJNI.delete_BoolValueSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void close() {
    this.delete();
  }

  public BoolValueSwig() {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_BoolValueSwig(), true);
  }

  public boolean hasValue() {
    return IpIntelligenceOnPremiseEngineModuleJNI.BoolValueSwig_hasValue(swigCPtr, this);
  }

  public String getNoValueMessage() {
    return IpIntelligenceOnPremiseEngineModuleJNI.BoolValueSwig_getNoValueMessage(swigCPtr, this);
  }

  public boolean getValue() {
    return IpIntelligenceOnPremiseEngineModuleJNI.BoolValueSwig_getValue(swigCPtr, this);
  }

}
