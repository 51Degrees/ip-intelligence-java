/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.3.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package fiftyone.ipintelligence.engine.onpremise.interop.swig;

public class UTF8StringSwig extends java.util.AbstractList<Short> implements AutoCloseable, java.util.RandomAccess {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected UTF8StringSwig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(UTF8StringSwig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(UTF8StringSwig obj) {
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
        IpIntelligenceOnPremiseEngineModuleJNI.delete_UTF8StringSwig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void close() {
    this.delete();
  }

  public UTF8StringSwig(short[] initialElements) {
    this();
    reserve(initialElements.length);

    for (short element : initialElements) {
      add(element);
    }
  }

  public UTF8StringSwig(Iterable<Short> initialElements) {
    this();
    for (short element : initialElements) {
      add(element);
    }
  }

  public Short get(int index) {
    return doGet(index);
  }

  public Short set(int index, Short e) {
    return doSet(index, e);
  }

  public boolean add(Short e) {
    modCount++;
    doAdd(e);
    return true;
  }

  public void add(int index, Short e) {
    modCount++;
    doAdd(index, e);
  }

  public Short remove(int index) {
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

  public UTF8StringSwig() {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_UTF8StringSwig__SWIG_0(), true);
  }

  public UTF8StringSwig(UTF8StringSwig other) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_UTF8StringSwig__SWIG_1(UTF8StringSwig.getCPtr(other), other), true);
  }

  public boolean isEmpty() {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_isEmpty(swigCPtr, this);
  }

  public void clear() {
    IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_clear(swigCPtr, this);
  }

  public UTF8StringSwig(int count, short value) {
    this(IpIntelligenceOnPremiseEngineModuleJNI.new_UTF8StringSwig__SWIG_2(count, value), true);
  }

  private int doCapacity() {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doCapacity(swigCPtr, this);
  }

  private void doReserve(int n) {
    IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doReserve(swigCPtr, this, n);
  }

  private int doSize() {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doSize(swigCPtr, this);
  }

  private void doAdd(short x) {
    IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doAdd__SWIG_0(swigCPtr, this, x);
  }

  private void doAdd(int index, short x) {
    IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doAdd__SWIG_1(swigCPtr, this, index, x);
  }

  private short doRemove(int index) {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doRemove(swigCPtr, this, index);
  }

  private short doGet(int index) {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doGet(swigCPtr, this, index);
  }

  private short doSet(int index, short val) {
    return IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doSet(swigCPtr, this, index, val);
  }

  private void doRemoveRange(int fromIndex, int toIndex) {
    IpIntelligenceOnPremiseEngineModuleJNI.UTF8StringSwig_doRemoveRange(swigCPtr, this, fromIndex, toIndex);
  }

}
