/*
 * Created on 02-Jun-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.oslo.ocl20.bridge4emf;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.oslo.ocl20.semantics.SemanticsVisitor;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.EnumLiteral;
import org.oslo.ocl20.semantics.bridge.EnumerationType;


public class EnumLiteralImpl implements EnumLiteral {

	protected EEnumLiteral _eenumLit;
	public EnumLiteralImpl(EEnumLiteral eenumLit, EnumerationType parent) {
		_eenumLit = eenumLit;
		_enum=parent;
	}

	EnumerationType _enum=null;
	public EnumerationType getEnumeration() {
		return _enum;
	}

	public void setEnumeration(EnumerationType enumerationType) {
		_enum = enumerationType;
	}

	String _name = null;
	public String getName() {
		if (_name == null) _name = _eenumLit.getName();
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	/**
	 * @see ocl20.bridge.Property#getType()
	 */
	Classifier type = null;
	public Classifier getType() {
		return getEnumeration(); 
	}

	/**
	 * @see ocl20.bridge.Property#setType(Classifier)
	 */
	public void setType(Classifier type) {
	}
	
	public Object accept(SemanticsVisitor v, Object data) {
		return  v.visit(this, data);
	}

	public Object clone() {
		return this;
	}
	public Object getDelegate() {
		return _eenumLit;
	}
}
