package org.oslo.ocl20.bridge4emf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.semantics.SemanticsVisitor;
import org.oslo.ocl20.semantics.bridge.BridgeFactory;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.Operation;
import org.oslo.ocl20.semantics.bridge.Parameter;

/**
 * @author dha
 * 
 */
public class OperationImpl extends org.oslo.ocl20.semantics.bridge.impl.OperationImpl implements Operation {

	EOperation _impl;

	OclProcessor processor;

	/**
	 * Constructor for Operation$Impl.
	 */
	public OperationImpl(EOperation op, OclProcessor proc) {
		_impl = op;
		this.processor = proc;
	}

	// --- Operation ---
	/**
	 * @see ocl20.bridge.Operation#getRetrunType()
	 */
	Classifier _returnType = null;

	public Classifier getReturnType() {
		if (_impl == null)
			return _returnType;
		return this.processor.getBridgeFactory().buildClassifier(_impl.getEType());
	}

	public void setReturnType(Classifier cl) {
		_returnType = cl;
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
		return v.visit(this, obj);
	}

	public Object getDelegate() {
		return _impl;
	}

	public String toString() {
		String s = "";
		s += getName() + "(";
		 Iterator i = getOwnedParameter().iterator();
		while (i.hasNext()) {
			Parameter parameter = (Parameter)i.next();
			Classifier c = parameter.getType();
			s += c.getName();
			if (i.hasNext())
				s += ", ";
		}
		s += ")";
		return s;
	}

	List _ownedParameters = null;

	public EList getOwnedParameter() {
		if (_ownedParameters == null) {
			_ownedParameters = new ArrayList();
			if (_impl != null) {
				Iterator i = _impl.getEParameters().iterator();
				while (i.hasNext()) {
					EParameter p = (EParameter) i.next();
					// TODO use Bridge factory to build parameters
                	Parameter parameter = BridgeFactory.eINSTANCE.createParameter();
                	parameter.setType(this.processor.getBridgeFactory().buildClassifier(p.getEType()));
                	parameter.setName(p.getName());
                	_ownedParameters.add(parameter);
				}
			}
			super.getOwnedParameter().addAll(_ownedParameters);
		}
		return super.getOwnedParameter();
	}

}
