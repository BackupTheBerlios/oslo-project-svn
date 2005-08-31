package org.oslo.ocl20.bridge4emf;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.EnumLiteral;
import org.oslo.ocl20.semantics.bridge.ModelElement;
import org.oslo.ocl20.semantics.bridge.OclModelElementType;
import org.oslo.ocl20.semantics.bridge.Property;
import org.oslo.ocl20.semantics.model.types.BooleanType;
import org.oslo.ocl20.standard.lib.OclAny;
import org.oslo.ocl20.standard.lib.OclAnyModelElement;
import org.oslo.ocl20.standard.lib.OclEnumeration;
import org.oslo.ocl20.standard.lib.OclSet;
import org.oslo.ocl20.standard.lib.OclType;
import org.oslo.ocl20.standard.lib.OclUndefined;
import org.oslo.ocl20.synthesis.ModelEvaluationAdapter;


/**
 * @author dha
 *
 */
public class EmfEvaluationAdapter
	implements ModelEvaluationAdapter
{
	protected OclProcessor processor;
	public EmfEvaluationAdapter(OclProcessor proc) {
		this.processor = proc;
	}

	// -- For Code Generation ---
	
	public String getGetterName(Property property) {
		String name = property.getName();
		Classifier type = property.getType();
		String upperName = name.substring(0,1).toUpperCase()+name.substring(1,name.length());
		//Class x = (Class)type.getDelegate();
		if (type instanceof BooleanType)
			return "is"+upperName;
		else
			return "get"+upperName;
			
	}

	public String getSetterName(Property property) {
		return null;
	}
	/*
	public String getModelPropertyName(String getter) {
		String result = getter.substring(3, getter.length());
		return result.substring(0, 1).toLowerCase() + result.substring(1, result.length());
	}

	public String getEnumLiteralReference(EnumLiteral enumLit) {
		EnumerationType enum = enumLit.getEnumeration();
		Class enumClass = (Class)enum.getDelegate();
		EEnumLiteralImpl eLit = (EEnumLiteralImpl)enumLit.getDelegate();
		String enumName = enumClass.getName();
		return enumName+".get(\""+enumLit.getName()+"\")";
	}

	public String getDefinedPropertyReference(OclAny source, DefinedProperty p) {
		OclAnyImpl src = (OclAnyImpl)source;
		String clsName = p.getDefinition().getContext().getReferredNamespace().getName();
		String pName = p.getName();
		String s = clsName+"."+pName+"("+source+")";
		return s;
	}
*/
	// --- For Evaluation ---

	public Object getEnumLiteralValue(EnumLiteral enumLit) {
		return ((EnumLiteralImpl)enumLit)._eenumLit.getInstance();
	}

	// Model element
	public boolean OclModelElement_equalTo(OclAny o1, OclAny o2) {
		Object obj1 = o1.asJavaObject();
		Object obj2 = o2.asJavaObject();
		return obj1.equals(obj2);
	}
	public boolean OclModelElement_oclIsNew(OclAny o1) {
		return false;
	}
	public boolean OclModelElement_oclIsUndefined(OclAny o1) {
		return o1 == null || o1 instanceof OclUndefined;
	}
	public Object OclModelElement_oclAsType(OclAny o1, OclType o2) {
		return o1;
	}
	public boolean OclModelElement_oclIsTypeOf(OclAny o1, OclType type) {
		EClass cObj = ((EObject)o1.asJavaObject()).eClass();
		Class cType = (Class)((OclModelElementType)type.asJavaObject()).getDelegate();
		return cObj.getInstanceClass() == cType;
	}
	public boolean OclModelElement_oclIsKindOf(OclAny o1, OclType type) {
		EClass cObj = ((EObject)o1.asJavaObject()).eClass();
		Class cType = (Class)((OclModelElementType)type.asJavaObject()).getDelegate();
		return cType.isAssignableFrom(cObj.getInstanceClass());
	}
	public OclSet OclType_allInstances(OclType self) {
		return null;
	}

	public OclType OclModelElement_oclType(OclAnyModelElement self) {
		EClass cObj = ((EObject)self.asJavaObject()).eClass();
		Classifier type = processor.getBridgeFactory().buildClassifier(cObj);
		return processor.getStdLibAdapter().Type(type);
	}

	//--- Enumeration
	public boolean EnumLiteral_equalTo(OclEnumeration e1, OclAny e2) {
		return e1.asJavaObject().equals(e2.asJavaObject());
	}	
	public boolean EnumLiteral_oclIsNew(Object o1) {
		return false;
	}
	public boolean EnumLiteral_oclIsUndefined(Object o1) {
		return o1 == null;
	}
	public Object EnumLiteral_oclAsType(Object o1, Object o2) {
		return o1;
	}
	public boolean EnumLiteral_oclIsTypeOf(Object o1, Object o2) {
		return o1.getClass() == o2.getClass();
	}
	public boolean EnumLiteral_oclIsKindOf(Object o1, Object o2) {
		Class c1 = o1.getClass();
		Class c2 = o2.getClass();
		return c1.isAssignableFrom(c2);
	}
	public Set EnumLiteral_allInstances(Object o1) {
		return new LinkedHashSet();
	}

	public Object getImpl(ModelElement me) {
		return me.getDelegate();
	}
	
	public Class getImplClass(Classifier cls) {
		EClass ecls = (EClass)cls.getDelegate();
		return ecls.getInstanceClass();
	}
}
