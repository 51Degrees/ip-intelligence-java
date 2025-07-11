/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class EvidenceIpiSwig extends EvidenceBaseSwig {
  private transient long swigCPtr;

  protected EvidenceIpiSwig(long cPtr, boolean cMemoryOwn) {
    super(IpIntelligenceOnPremiseEngineModuleJNI.EvidenceIpiSwig_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EvidenceIpiSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(EvidenceIpiSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_EvidenceIpiSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public EvidenceIpiSwig() {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_EvidenceIpiSwig(), true);
  }

}
