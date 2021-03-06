/**
 *
 *  Class Operation.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:06
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.bridge;

import java.util.List;

import org.oslo.ocl20.semantics.SemanticsElement;


public interface Operation
extends
    SemanticsElement,
    ModelElement
{
	/** Get the 'returnType' from 'Operation' */
	public Classifier getReturnType();
	/** Set the 'returnType' from 'Operation' */
	public void setReturnType(Classifier returnType);

	/** Get the 'parameterTypes' from 'Operation' */
	public List getParameterTypes();
	/** Set the 'parameterTypes' from 'Operation' */
	public void setParameterTypes(List parameterTypes);

	/** Get the 'parameterNames' from 'Operation' */
	public List getParameterNames();
	/** Set the 'parameterNames' from 'Operation' */
	public void setParameterNames(List parameterNames);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
