/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class ValueMetaDataSwig {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ValueMetaDataSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ValueMetaDataSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ValueMetaDataSwig obj) {
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

  @SuppressWarnings({"deprecation", "removal"})
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        IpIntelligenceOnPremiseEngineModuleJNI.delete_ValueMetaDataSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public String getName() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ValueMetaDataSwig_getName(swigCPtr, this);
  }

  public String getDescription() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ValueMetaDataSwig_getDescription(swigCPtr, this);
  }

  public String getUrl() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ValueMetaDataSwig_getUrl(swigCPtr, this);
  }

}
