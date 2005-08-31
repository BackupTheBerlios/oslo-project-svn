package org.oslo.ocl20.bridge4emf;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.generation.lib.Impl;
import org.oslo.ocl20.generation.lib.OclAnyImpl;
import org.oslo.ocl20.generation.lib.OclBooleanImpl;
import org.oslo.ocl20.generation.lib.OclTypeImpl;
import org.oslo.ocl20.generation.lib.StdLibGenerationAdapterImpl;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.EnumLiteral;
import org.oslo.ocl20.semantics.bridge.EnumerationType;
import org.oslo.ocl20.semantics.bridge.ModelElement;
import org.oslo.ocl20.semantics.model.contexts.DefinedOperation;
import org.oslo.ocl20.semantics.model.contexts.DefinedProperty;
import org.oslo.ocl20.standard.lib.OclAny;
import org.oslo.ocl20.standard.lib.OclBoolean;
import org.oslo.ocl20.standard.lib.OclType;
import org.oslo.ocl20.synthesis.ModelGenerationAdapter;


/**
 * @author dha
 *
 */
public class EmfGenerationAdapter
	implements ModelGenerationAdapter
{
	OclProcessor processor;
	public EmfGenerationAdapter(OclProcessor proc) {
		this.processor = proc;
	}

	// -- For Code Generation ---
	
	public String getGetterName(String property_name) {
		return "get"+property_name.substring(0,1).toUpperCase()+property_name.substring(1,property_name.length());
	}

	public String getSetterName(String property_name) {
		return null;
	}

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
		
		Classifier t = p.getType();
		Class c = (Class)t.getDelegate();

		String argStr = "new Object[]{"+source+"}";

		String s = "(("+c.getName()+")def(\""+pName+"\", "+argStr+"))";
		return s;
	}

	public String getDefinedOperationReference(OclAny source, DefinedOperation op, List arguments) {
		OclAnyImpl src = (OclAnyImpl)source;
		String clsName = op.getDefinition().getContext().getReferredNamespace().getName();
		String pName = op.getName();
		
		Classifier t = op.getReturnType();
		Class c = (Class)t.getDelegate();

		String argStr = "new Object[]{"+source;
		Iterator i = arguments.iterator();
		while (i.hasNext()) {
			argStr += ", ";
			argStr += i.next();
		}
		argStr+="}";

		String s = "(("+c.getName()+")def(\""+pName+"\", "+argStr+"))";
		return s;
	}

	public Object getEnumLiteralValue(EnumLiteral enumLit) {
		return ((EnumLiteralImpl)enumLit)._eenumLit.getInstance();
	}

	//--- OclModelElement
	public OclBoolean OclModelElement_equalTo(OclAny obj1, OclAny obj2) {
		if (obj1 instanceof OclAnyImpl && obj2 instanceof OclAnyImpl) {
			OclAnyImpl object1 = (OclAnyImpl)obj1;
			OclAnyImpl object2 = (OclAnyImpl)obj2;
			OclBooleanImpl result = (OclBooleanImpl)processor.getStdLibGenerationAdapter().Boolean("new Boolean(("+obj1.asJavaObject()+").equals("+object2.asJavaObject()+"))");
			result.setInitialisation( object1.getInitialisation() + object2.getInitialisation() + result.getInitialisation());
			return result;
		}
		return null;
	}
	public OclBoolean OclModelElement_oclIsNew(OclAny o1) {
		return processor.getStdLibGenerationAdapter().Boolean(false);
	}
	public OclBoolean OclModelElement_oclIsUndefined(OclAny o1) {
	    OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1.asJavaObject()+" == null"+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public Object OclModelElement_oclAsType(OclAny o1, OclType type) {
		Class cls = (Class)type.asJavaObject();
		Classifier clsfr = ((OclTypeImpl)type).cls;
		OclAnyImpl o = (OclAnyImpl)((StdLibGenerationAdapterImpl)processor.getStdLibGenerationAdapter()).OclAny(clsfr,"(("+cls.getName()+")"+o1+")");
		o.setInitialisation( ((OclAnyImpl)o1).getInitialisation() + o.getInitialisation());
		return o;
	}
	public OclBoolean OclModelElement_oclIsTypeOf(OclAny o1, OclType type) {
		Class cls = (Class) type.asJavaObject();
		//EClass cObj = ((EObject)o1.asJavaObject()).eClass();
		//EClass cType = (EClass)((OclModelElementType)type.asJavaObject()).getDelegate();
		OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1+".eClass().getInstanceClass() == "+cls.getName()+".class)");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)type).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public OclBoolean OclModelElement_oclIsKindOf(OclAny o1, OclType type) {
		//EClass cObj = ((EObject)o1.asJavaObject()).eClass();
		//EClass cType = (EClass)((OclModelElementType)type.asJavaObject()).getDelegate();
		Class cls = (Class)type.asJavaObject();
		OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1+" instanceof "+cls.getName()+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)type).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public Set OclModelElement_allInstances(OclAny o1) {
		return new LinkedHashSet();
	}

	//--- Enumeration
	public OclBoolean EnumLiteral_equalTo(Object obj1, Object obj2) {
		if (obj1 instanceof OclAnyImpl && obj2 instanceof OclAnyImpl) {
			OclAnyImpl object1 = (OclAnyImpl)obj1;
			OclAnyImpl object2 = (OclAnyImpl)obj2;
			OclBooleanImpl result = (OclBooleanImpl)processor.getStdLibGenerationAdapter().Boolean("("+object1.asJavaObject()+").equals("+object2.asJavaObject()+")");
			result.setInitialisation( object1.getInitialisation() + object2.getInitialisation() + result.getInitialisation());
			return result;
		}
		return null;
	}	
	public OclBoolean EnumLiteral_oclIsNew(Object o1) {
		return processor.getStdLibGenerationAdapter().Boolean(false);
	}
	public OclBoolean EnumLiteral_oclIsUndefined(Object o1) {
	    OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1+" == null"+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public Object EnumLiteral_oclAsType(Object o1, OclType type) {
		EClassifier cls = (EClassifier) ((Classifier)type.asJavaObject()).getDelegate();
		OclAny res = processor.getStdLibGenerationAdapter().OclAny( "(("+cls.getInstanceClassName()+")"+this+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)type).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public OclBoolean EnumLiteral_oclIsTypeOf(Object o1, OclType type) {
		EClassifier cls = (EClassifier) ((Classifier)type.asJavaObject()).getDelegate();
		OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1+" instanceof "+cls.getInstanceClassName()+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)type).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
	}
	public OclBoolean EnumLiteral_oclIsKindOf(Object o1, OclType type) {
		EClassifier cls = (EClassifier) ((Classifier)type.asJavaObject()).getDelegate();
		OclBoolean res = processor.getStdLibGenerationAdapter().Boolean("new Boolean("+o1+" instanceof "+cls.getInstanceClassName()+")");
	    ((Impl)res).setInitialisation( ((Impl)o1).getInitialisation() + ((Impl)type).getInitialisation() + ((Impl)res).getInitialisation() );
	    return res;
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
