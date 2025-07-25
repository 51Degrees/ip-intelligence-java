/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class WeightedStringListSwig extends java.util.AbstractList<WeightedValueStringSwig> implements AutoCloseable, java.util.RandomAccess {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected WeightedStringListSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(WeightedStringListSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(WeightedStringListSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_WeightedStringListSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void close() {
    this.delete();
  }

  public WeightedStringListSwig(WeightedValueStringSwig[] initialElements) {
    this();
    reserve(initialElements.length);

    for (WeightedValueStringSwig element : initialElements) {
      add(element);
    }
  }

  public WeightedStringListSwig(Iterable<WeightedValueStringSwig> initialElements) {
    this();
    for (WeightedValueStringSwig element : initialElements) {
      add(element);
    }
  }

  public WeightedValueStringSwig get(int index) {
    return doGet(index);
  }

  public WeightedValueStringSwig set(int index, WeightedValueStringSwig e) {
    return doSet(index, e);
  }

  public boolean add(WeightedValueStringSwig e) {
    modCount++;
    doAdd(e);
    return true;
  }

  public void add(int index, WeightedValueStringSwig e) {
    modCount++;
    doAdd(index, e);
  }

  public WeightedValueStringSwig remove(int index) {
    modCount++;
    return doRemove(index);
  }

  protected void removeRange(int fromIndex, int toIndex) {
    modCount++;
    doRemoveRange(fromIndex, toIndex);
  }

  public int size() {
    return doSize();
  }

  public int capacity() {
    return doCapacity();
  }

  public void reserve(int n) {
    doReserve(n);
  }

  public WeightedStringListSwig() {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_WeightedStringListSwig__SWIG_0(), true);
  }

  public WeightedStringListSwig(WeightedStringListSwig other) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_WeightedStringListSwig__SWIG_1(WeightedStringListSwig.getCPtr(other), other), true);
  }

  public boolean isEmpty() {
    return IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_isEmpty(swigCPtr, this);
  }

  public void clear() {
    IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_clear(swigCPtr, this);
  }

  public WeightedStringListSwig(int count, WeightedValueStringSwig value) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_WeightedStringListSwig__SWIG_2(count, WeightedValueStringSwig.getCPtr(value), value), true);
  }

  private int doCapacity() {
    return IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doCapacity(swigCPtr, this);
  }

  private void doReserve(int n) {
    IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doReserve(swigCPtr, this, n);
  }

  private int doSize() {
    return IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doSize(swigCPtr, this);
  }

  private void doAdd(WeightedValueStringSwig x) {
    IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doAdd__SWIG_0(swigCPtr, this, WeightedValueStringSwig.getCPtr(x), x);
  }

  private void doAdd(int index, WeightedValueStringSwig x) {
    IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doAdd__SWIG_1(swigCPtr, this, index, WeightedValueStringSwig.getCPtr(x), x);
  }

  private WeightedValueStringSwig doRemove(int index) {
    return new WeightedValueStringSwig(IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doRemove(swigCPtr, this, index), true);
  }

  private WeightedValueStringSwig doGet(int index) {
    return new WeightedValueStringSwig(IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doGet(swigCPtr, this, index), false);
  }

  private WeightedValueStringSwig doSet(int index, WeightedValueStringSwig val) {
    return new WeightedValueStringSwig(IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doSet(swigCPtr, this, index, WeightedValueStringSwig.getCPtr(val), val), true);
  }

  private void doRemoveRange(int fromIndex, int toIndex) {
    IpIntelligenceOnPremiseEngineModuleJNI.WeightedStringListSwig_doRemoveRange(swigCPtr, this, fromIndex, toIndex);
  }

}
