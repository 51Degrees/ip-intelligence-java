/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class ConfigIpiSwig extends ConfigBaseSwig {
  private transient long swigCPtr;

  protected ConfigIpiSwig(long cPtr, boolean cMemoryOwn) {
    super(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ConfigIpiSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ConfigIpiSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_ConfigIpiSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ConfigIpiSwig() {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_ConfigIpiSwig(), true);
  }

  public void setHighPerformance() {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setHighPerformance(swigCPtr, this);
  }

  public void setBalanced() {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setBalanced(swigCPtr, this);
  }

  public void setBalancedTemp() {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setBalancedTemp(swigCPtr, this);
  }

  public void setLowMemory() {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setLowMemory(swigCPtr, this);
  }

  public void setMaxPerformance() {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setMaxPerformance(swigCPtr, this);
  }

  public void setConcurrency(int concurrency) {
    IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_setConcurrency(swigCPtr, this, concurrency);
  }

  public CollectionConfigSwig getStrings() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getStrings(swigCPtr, this), false);
  }

  public CollectionConfigSwig getComponents() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getComponents(swigCPtr, this), true);
  }

  public CollectionConfigSwig getMaps() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getMaps(swigCPtr, this), false);
  }

  public CollectionConfigSwig getProperties() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getProperties(swigCPtr, this), true);
  }

  public CollectionConfigSwig getValues() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getValues(swigCPtr, this), true);
  }

  public CollectionConfigSwig getProfiles() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getProfiles(swigCPtr, this), true);
  }

  public CollectionConfigSwig getGraphs() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getGraphs(swigCPtr, this), false);
  }

  public CollectionConfigSwig getProfileGroups() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getProfileGroups(swigCPtr, this), false);
  }

  public CollectionConfigSwig getProfileOffsets() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getProfileOffsets(swigCPtr, this), false);
  }

  public CollectionConfigSwig getPropertyTypes() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getPropertyTypes(swigCPtr, this), false);
  }

  public CollectionConfigSwig getGraph() {
    return new CollectionConfigSwig(IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getGraph(swigCPtr, this), false);
  }

  public int getConcurrency() {
    return IpIntelligenceOnPremiseEngineModuleJNI.ConfigIpiSwig_getConcurrency(swigCPtr, this);
  }

}
