/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class ConfigBaseSwig {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ConfigBaseSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ConfigBaseSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ConfigBaseSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_ConfigBaseSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setUseUpperPrefixHeaders(boolean use) {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_setUseUpperPrefixHeaders(swigCPtr, this, use);
  }

  public void setUseTempFile(boolean use) {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_setUseTempFile(swigCPtr, this, use);
  }

  public void setReuseTempFile(boolean reuse) {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_setReuseTempFile(swigCPtr, this, reuse);
  }

  public void setTempDirectories(VectorStringSwig tempDirs) {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_setTempDirectories(swigCPtr, this, VectorStringSwig.getCPtr(tempDirs), tempDirs);
  }

  public boolean getUseUpperPrefixHeaders() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_getUseUpperPrefixHeaders(swigCPtr, this);
  }

  public boolean getUseTempFile() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_getUseTempFile(swigCPtr, this);
  }

  public boolean getReuseTempFile() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_getReuseTempFile(swigCPtr, this);
  }

  public VectorStringSwig getTempDirectories() {
    return new VectorStringSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_getTempDirectories(swigCPtr, this), true);
  }

  public int getConcurrency() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ConfigBaseSwig_getConcurrency(swigCPtr, this);
  }

}
