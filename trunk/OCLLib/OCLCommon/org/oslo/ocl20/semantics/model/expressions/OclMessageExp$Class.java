/**
 *
 *  Class OclMessageExp$Class.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.model.expressions;

import java.util.LinkedHashSet;
import java.util.Set;

import org.oslo.ocl20.semantics.SemanticsVisitable;
import org.oslo.ocl20.semantics.SemanticsVisitor;


public class OclMessageExp$Class
implements
    OclMessageExp,
    SemanticsVisitable
{
	/** Default constructor */
	public OclMessageExp$Class() {
		//--- Set property 'name' from 'ModelElement' ---
		this.name = null;
		//--- Set property 'isMarkedPre' from 'OclExpression' ---
		this.isMarkedPre = null;
		//--- Set property 'appliedProperty' from 'OclExpression' ---
		this.appliedProperty = null;
		//--- Set property 'operationCallExp' from 'OclExpression' ---
		this.operationCallExp = null;
		//--- Set property 'initialisedVariable' from 'OclExpression' ---
		this.initialisedVariable = null;
		//--- Set property 'type' from 'OclExpression' ---
		this.type = null;
		//--- Set property 'propertyCallExp' from 'OclExpression' ---
		this.propertyCallExp = null;
		//--- Set property 'loopExp' from 'OclExpression' ---
		this.loopExp = null;
		//--- Set property 'target' from 'OclMessageExp' ---
		this.target = null;
		//--- Set property 'arguments' from 'OclMessageExp' ---
		this.arguments = new LinkedHashSet();
	}
	/** Specialized constructor */
	public OclMessageExp$Class(String name, Boolean isMarkedPre) {
		//--- Set property 'name' from 'ModelElement' ---
		this.name = name;
		//--- Set property 'isMarkedPre' from 'OclExpression' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'appliedProperty' from 'OclExpression' ---
		this.appliedProperty = null;
		//--- Set property 'operationCallExp' from 'OclExpression' ---
		this.operationCallExp = null;
		//--- Set property 'initialisedVariable' from 'OclExpression' ---
		this.initialisedVariable = null;
		//--- Set property 'type' from 'OclExpression' ---
		this.type = null;
		//--- Set property 'propertyCallExp' from 'OclExpression' ---
		this.propertyCallExp = null;
		//--- Set property 'loopExp' from 'OclExpression' ---
		this.loopExp = null;
		//--- Set property 'target' from 'OclMessageExp' ---
		this.target = null;
		//--- Set property 'arguments' from 'OclMessageExp' ---
		this.arguments = new LinkedHashSet();
	}


	/** Property 'name' from 'ModelElement' */
	protected String name;
	/** Get property 'name' from 'ModelElement' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'ModelElement' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'isMarkedPre' from 'OclExpression' */
	protected Boolean isMarkedPre;
	/** Get property 'isMarkedPre' from 'OclExpression' */
	public Boolean getIsMarkedPre() {
		return isMarkedPre;
	}
	/** Set property 'isMarkedPre' from 'OclExpression' */
	public void setIsMarkedPre(Boolean isMarkedPre) {
		this.isMarkedPre = isMarkedPre;
	}

	/** Property 'appliedProperty' from 'OclExpression' */
	protected CallExp appliedProperty;
	/** Get property 'appliedProperty' from 'OclExpression' */
	public CallExp getAppliedProperty() {
		return appliedProperty;
	}
	/** Set property 'appliedProperty' from 'OclExpression' */
	public void setAppliedProperty(CallExp appliedProperty) { 
		this.appliedProperty = appliedProperty;
	}

	/** Property 'operationCallExp' from 'OclExpression' */
	protected OperationCallExp operationCallExp;
	/** Get property 'operationCallExp' from 'OclExpression' */
	public OperationCallExp getOperationCallExp() {
		return operationCallExp;
	}
	/** Set property 'operationCallExp' from 'OclExpression' */
	public void setOperationCallExp(OperationCallExp operationCallExp) { 
		this.operationCallExp = operationCallExp;
	}

	/** Property 'initialisedVariable' from 'OclExpression' */
	protected VariableDeclaration initialisedVariable;
	/** Get property 'initialisedVariable' from 'OclExpression' */
	public VariableDeclaration getInitialisedVariable() {
		return initialisedVariable;
	}
	/** Set property 'initialisedVariable' from 'OclExpression' */
	public void setInitialisedVariable(VariableDeclaration initialisedVariable) { 
		this.initialisedVariable = initialisedVariable;
	}

	/** Property 'type' from 'OclExpression' */
	protected org.oslo.ocl20.semantics.bridge.Classifier type;
	/** Get property 'type' from 'OclExpression' */
	public org.oslo.ocl20.semantics.bridge.Classifier getType() {
		return type;
	}
	/** Set property 'type' from 'OclExpression' */
	public void setType(org.oslo.ocl20.semantics.bridge.Classifier type) { 
		this.type = type;
	}

	/** Property 'propertyCallExp' from 'OclExpression' */
	protected PropertyCallExp propertyCallExp;
	/** Get property 'propertyCallExp' from 'OclExpression' */
	public PropertyCallExp getPropertyCallExp() {
		return propertyCallExp;
	}
	/** Set property 'propertyCallExp' from 'OclExpression' */
	public void setPropertyCallExp(PropertyCallExp propertyCallExp) { 
		this.propertyCallExp = propertyCallExp;
	}

	/** Property 'loopExp' from 'OclExpression' */
	protected LoopExp loopExp;
	/** Get property 'loopExp' from 'OclExpression' */
	public LoopExp getLoopExp() {
		return loopExp;
	}
	/** Set property 'loopExp' from 'OclExpression' */
	public void setLoopExp(LoopExp loopExp) { 
		this.loopExp = loopExp;
	}

	/** Property 'target' from 'OclMessageExp' */
	protected OclExpression target;
	/** Get property 'target' from 'OclMessageExp' */
	public OclExpression getTarget() {
		return target;
	}
	/** Set property 'target' from 'OclMessageExp' */
	public void setTarget(OclExpression target) { 
		this.target = target;
	}

	/** Property 'arguments' from 'OclMessageExp' */
	protected Set arguments;
	/** Get property 'arguments' from 'OclMessageExp' */
	public Set getArguments() {
		return arguments;
	}
	/** Set property 'arguments' from 'OclMessageExp' */
	public void setArguments(Set arguments) { 
		this.arguments = arguments;
	}

	/** Override toString */
	public String toString() {
		String strId = "OclMessageExp";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId;
		else
			return strId+" '"+name+"'";
	}

	/** Accept 'uk.ac.kent.cs.ocl20.semantics.model.expressions.OclMessageExp$Visitor' */
	public Object accept(SemanticsVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		OclMessageExp$Class obj = new OclMessageExp$Class();
		obj.name = name==null ? null : this.name;
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.appliedProperty = appliedProperty==null ? null : this.appliedProperty;
		obj.operationCallExp = operationCallExp==null ? null : this.operationCallExp;
		obj.initialisedVariable = initialisedVariable==null ? null : this.initialisedVariable;
		obj.type = type==null ? null : this.type;
		obj.propertyCallExp = propertyCallExp==null ? null : this.propertyCallExp;
		obj.loopExp = loopExp==null ? null : this.loopExp;
		obj.target = target==null ? null : this.target;
		obj.arguments = arguments==null ? null : (Set)((LinkedHashSet)this.arguments).clone();
		return obj;
	}
}
