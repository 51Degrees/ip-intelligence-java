/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class EngineBaseSwig implements AutoCloseable {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected EngineBaseSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineBaseSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(EngineBaseSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_EngineBaseSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void close() {
    this.delete();
  }

  public void setLicenseKey(String licenceKey) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_setLicenseKey(swigCPtr, this, licenceKey);
  }

  public void setDataUpdateUrl(String updateUrl) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_setDataUpdateUrl(swigCPtr, this, updateUrl);
  }

  public MetaDataSwig getMetaData() {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getMetaData(swigCPtr, this);
    return (cPtr == 0) ? null : new MetaDataSwig(cPtr, false);
  }

  public boolean getAutomaticUpdatesEnabled() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getAutomaticUpdatesEnabled(swigCPtr, this);
  }

  public ResultsBaseSwig processBase(EvidenceBaseSwig evidence) {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_processBase(swigCPtr, this, EvidenceBaseSwig.getCPtr(evidence), evidence);
    return (cPtr == 0) ? null : new ResultsBaseSwig(cPtr, true);
  }

  public void refreshData() {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_refreshData__SWIG_0(swigCPtr, this);
  }

  public void refreshData(String fileName) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_refreshData__SWIG_1(swigCPtr, this, fileName);
  }

  public void refreshData(byte[] data) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_refreshData__SWIG_2(swigCPtr, this, data);
  }

  public String getDataUpdateUrl() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getDataUpdateUrl(swigCPtr, this);
  }

  public Date getPublishedTime() {
    return new Date(IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getPublishedTime(swigCPtr, this), true);
  }

  public Date getUpdateAvailableTime() {
    return new Date(IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getUpdateAvailableTime(swigCPtr, this), true);
  }

  public String getDataFilePath() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getDataFilePath(swigCPtr, this);
  }

  public String getDataFileTempPath() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getDataFileTempPath(swigCPtr, this);
  }

  public String getProduct() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getProduct(swigCPtr, this);
  }

  public String getType() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getType(swigCPtr, this);
  }

  public VectorStringSwig getKeys() {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getKeys(swigCPtr, this);
    return (cPtr == 0) ? null : new VectorStringSwig(cPtr, false);
  }

  public boolean getIsThreadSafe() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineBaseSwig_getIsThreadSafe(swigCPtr, this);
  }

}
