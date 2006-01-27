package org.oslo.ocl20.bridge4emf;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.semantics.SemanticsVisitor;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.Operation;


/**
 * @author dha
 *
 */
public class OperationImpl implements Operation {

	EOperation _impl;
	OclProcessor processor;

	/**
	 * Constructor for Operation$Impl.
	 */
	public OperationImpl(EOperation op, OclProcessor proc) {
		_impl = op;
		this.processor = proc;
	}

	//--- Operation ---
	/**
	 * @see ocl20.bridge.Operation#getRetrunType()
	 */
	Classifier _returnType=null;
	public Classifier getReturnType() {
		if (_impl==null) return _returnType;
		return this.processor.getBridgeFactory().buildClassifier(_impl.getEType());
	}

	public void setReturnType(Classifier cl) {
		_returnType = cl;
	}

	/**
	 * @see ocl20.bridge.Operation#getArgumentTypes()
	 */
	List _parameterTypes = null;
	public List getParameterTypes() {
		if (_parameterTypes == null) {
			_parameterTypes = new Vector();
			if (_impl != null) {
				Iterator i = _impl.getEParameters().iterator();
				while (i.hasNext()) {
					EParameter p = (EParameter) i.next();
					_parameterTypes.add(this.processor.getBridgeFactory().buildClassifier(p.getEType()));
				}
			}
		}
		return _parameterTypes;
	}
	public void setParameterTypes(List l) {
		_parameterTypes = l;
	}

	public void setParameterNames(List l) {
	}

	List _parameterNames = null;
	public List getParameterNames() {
		if (_parameterNames == null) {
			_parameterNames = new Vector();
			if (_impl != null) {
				Iterator i = _impl.getEParameters().iterator();
				while (i.hasNext()) {
					EParameter p = (EParameter) i.next();
					_parameterNames.add(this.processor.getBridgeFactory().buildClassifier(p.getEType()));
				}
			}
		}
		return _parameterNames;
	}

	/**
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		return null;
	}

	/**
	 * @see ocl20.bridge.ModelElement#getName()
	 */
	String _name = null;
	public String getName() {
		if (_impl == null)
			return _name;
		return _impl.getName();
	}

	/**
	 * @see ocl20.bridge.ModelElement#setName(String)
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * @see ocl20.ocl20Visitable#accept(ocl20Visitor, Object)
	 */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	public Object getDelegate() {
		return _impl;
	}
	
	public String toString() {
	    String s="";
	    s+=getName()+"(";
	    Iterator i = getParameterTypes().iterator();
	    while (i.hasNext()) {
	        Classifier c = (Classifier)i.next();
	        s+=c.getName();
	        if (i.hasNext()) s+= ", ";
	    }
	    s+=")";
	    return s;
	}

}
