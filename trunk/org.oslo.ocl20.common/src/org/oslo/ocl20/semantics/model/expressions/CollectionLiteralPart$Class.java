/**
 *
 *  Class CollectionLiteralPart$Class.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.model.expressions;

import org.oslo.ocl20.semantics.SemanticsVisitable;
import org.oslo.ocl20.semantics.SemanticsVisitor;

public class CollectionLiteralPart$Class
implements
    CollectionLiteralPart,
    SemanticsVisitable
{
	/** Default constructor */
	public CollectionLiteralPart$Class() {
		//--- Set property 'type' from 'CollectionLiteralPart' ---
		this.type = null;
		//--- Set property 'collectionLiteralExp' from 'CollectionLiteralPart' ---
		this.collectionLiteralExp = null;
	}


	/** Property 'type' from 'CollectionLiteralPart' */
	protected org.oslo.ocl20.semantics.bridge.Classifier type;
	/** Get property 'type' from 'CollectionLiteralPart' */
	public org.oslo.ocl20.semantics.bridge.Classifier getType() {
		return type;
	}
	/** Set property 'type' from 'CollectionLiteralPart' */
	public void setType(org.oslo.ocl20.semantics.bridge.Classifier type) { 
		this.type = type;
	}

	/** Property 'collectionLiteralExp' from 'CollectionLiteralPart' */
	protected CollectionLiteralExp collectionLiteralExp;
	/** Get property 'collectionLiteralExp' from 'CollectionLiteralPart' */
	public CollectionLiteralExp getCollectionLiteralExp() {
		return collectionLiteralExp;
	}
	/** Set property 'collectionLiteralExp' from 'CollectionLiteralPart' */
	public void setCollectionLiteralExp(CollectionLiteralExp collectionLiteralExp) { 
		this.collectionLiteralExp = collectionLiteralExp;
	}

	/** Override toString */
	public String toString() {
		String strId = "CollectionLiteralPart";
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

	/** Accept 'uk.ac.kent.cs.ocl20.semantics.model.expressions.CollectionLiteralPart$Visitor' */
	public Object accept(SemanticsVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		CollectionLiteralPart$Class obj = new CollectionLiteralPart$Class();
		obj.type = type==null ? null : this.type;
		obj.collectionLiteralExp = collectionLiteralExp==null ? null : this.collectionLiteralExp;
		return obj;
	}
}
