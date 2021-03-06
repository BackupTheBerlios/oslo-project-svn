/**
 *
 *  Class LoopExp.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.model.expressions;

import java.util.Set;

import org.oslo.ocl20.semantics.SemanticsElement;


public interface LoopExp
extends
    SemanticsElement,
    CallExp,
    OclExpression
{

	/** Get the 'iterators' from 'LoopExp' */
	public Set getIterators();
	/** Set the 'iterators' from 'LoopExp' */
	public void setIterators(Set iterators);

	/** Get the 'body' from 'LoopExp' */
	public OclExpression getBody();
	/** Set the 'body' from 'LoopExp' */
	public void setBody(OclExpression body);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
