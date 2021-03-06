/**
 *
 *  Class OrderedSetType.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:13
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.model.types;

import org.oslo.ocl20.semantics.SemanticsElement;

public interface OrderedSetType
extends
    SemanticsElement,
    SetType,
    org.oslo.ocl20.semantics.bridge.DataType,
    org.oslo.ocl20.semantics.bridge.Classifier,
    org.oslo.ocl20.semantics.bridge.Namespace,
    org.oslo.ocl20.semantics.bridge.ModelElement
{

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
