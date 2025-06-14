/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class EngineIpiSwig extends EngineBaseSwig {
  private transient long swigCPtr;

  protected EngineIpiSwig(long cPtr, boolean cMemoryOwn) {
    super(IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineIpiSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(EngineIpiSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_EngineIpiSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public EngineIpiSwig(String fileName, ConfigIpiSwig config, RequiredPropertiesConfigSwig properties) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_EngineIpiSwig__SWIG_0(fileName, ConfigIpiSwig.getCPtr(config), config, RequiredPropertiesConfigSwig.getCPtr(properties), properties), true);
  }

  public EngineIpiSwig(byte[] data, ConfigIpiSwig config, RequiredPropertiesConfigSwig properties) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_EngineIpiSwig__SWIG_1(data, ConfigIpiSwig.getCPtr(config), config, RequiredPropertiesConfigSwig.getCPtr(properties), properties), true);
  }

  public Date getPublishedTime() {
    return new Date(IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_getPublishedTime(swigCPtr, this), true);
  }

  public Date getUpdateAvailableTime() {
    return new Date(IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_getUpdateAvailableTime(swigCPtr, this), true);
  }

  public String getDataFilePath() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_getDataFilePath(swigCPtr, this);
  }

  public String getDataFileTempPath() {
    return IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_getDataFileTempPath(swigCPtr, this);
  }

  public void refreshData() {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_refreshData__SWIG_0(swigCPtr, this);
  }

  public void refreshData(String fileName) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_refreshData__SWIG_1(swigCPtr, this, fileName);
  }

  public void refreshData(byte[] data) {
    IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_refreshData__SWIG_2(swigCPtr, this, data);
  }

  public ResultsIpiSwig process(EvidenceIpiSwig evidence) {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_process__SWIG_0(swigCPtr, this, EvidenceIpiSwig.getCPtr(evidence), evidence);
    return (cPtr == 0) ? null : new ResultsIpiSwig(cPtr, true);
  }

  public ResultsIpiSwig process(String ipAddress) {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_process__SWIG_1(swigCPtr, this, ipAddress);
    return (cPtr == 0) ? null : new ResultsIpiSwig(cPtr, true);
  }

  public ResultsIpiSwig process(byte[] ipAddress, IpTypeSwig type) {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_process__SWIG_2(swigCPtr, this, ipAddress, type.swigValue());
    return (cPtr == 0) ? null : new ResultsIpiSwig(cPtr, true);
  }

  public ResultsBaseSwig processBase(EvidenceBaseSwig evidence) {
    long cPtr = IpIntelligenceOnPremiseEngineModuleJNI.EngineIpiSwig_processBase(swigCPtr, this, EvidenceBaseSwig.getCPtr(evidence), evidence);
    return (cPtr == 0) ? null : new ResultsBaseSwig(cPtr, true);
  }

}
