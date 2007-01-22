package org.oslo.ocl20.bridge4emf;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.oslo.ocl20.synthesis.RuntimeEnvironment;

import uk.ac.kent.cs.kmf.util.ILog;


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

	// --- For Evaluation ---

	public Object getEnumLiteralValue(EnumLiteral enumLit) {
		return ((EnumLiteralImpl)enumLit)._eenumLit.getInstance();
	}

	// Model element
	public boolean OclModelElement_equalTo(OclAny o1, OclAny o2) {
		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		
		Object obj1 = o1.asJavaObject();
		Object obj2 = o2.asJavaObject();
		if (obj1 instanceof EObject && obj2 instanceof EObject &&
				EcoreUtil.getID((EObject)obj1) != null &&
				EcoreUtil.getID((EObject)obj2) != null) {
			if (obj1 != null) {
				return EcoreUtil.getID((EObject)obj1).equals(EcoreUtil.getID((EObject)obj2));
			}
		}
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
        OclModelElementType InstanceName = 
(OclModelElementType)self.asJavaObject();
        EClass InstanceTyp = (EClass) 
((OclModelElementTypeImpl)self.asJavaObject()).getImplementation();
        List InstancesList = new ArrayList();
        
        // [HEINI]
        
        RuntimeEnvironment renv = this.processor.getRuntimeEnvironment();
        
        EObject eobject = (EObject)renv.getValue("self");
        TreeIterator iter = eobject.eResource().getAllContents();
        while (iter.hasNext())
        {
            EObject content = (EObject)iter.next();
            if (content.getClass().getName().equals(InstanceTyp.getName()))
            {
                if (!(InstancesList.contains(content)))
                {
                    InstancesList.add(content);
                    
                }
            }
            
            if (InstanceTyp.isSuperTypeOf(content.eClass()))
            {
                if (!(InstancesList.contains(content)))
                {
                    InstancesList.add(content);
                    
                }
            }
        }
        //findInstances(InstanceName.getName(),eobject);
        
        OclSet set=this.processor.getStdLibAdapter().Collection( 
(Collection) InstancesList).asSet();
        //list_.clear();
        //visit_list_.clear();
        return set;
        /*return null;*/
    }


	public OclType OclModelElement_oclType(OclAnyModelElement self) {
		EObject eObj = (EObject)self.asJavaObject();
		EClass cObj = eObj.eClass();
		Classifier type = processor.getBridgeFactory().buildClassifier(cObj);
		return processor.getStdLibAdapter().Type(type);
	}

	//--- Enumeration
	public boolean EnumLiteral_equalTo(OclEnumeration e1, OclAny e2) {
		
		Object o1=e1.asJavaObject();
		Object o2=e2.asJavaObject();
		
		if (e1.asJavaObject() instanceof EEnumLiteralImpl)
		{
			EEnumLiteralImpl enumliteral = (EEnumLiteralImpl) e1.asJavaObject();
			o1 = enumliteral.getInstance();
			
		}
		
		
		if (e2.asJavaObject() instanceof EEnumLiteralImpl)
		{
			EEnumLiteralImpl enumliteral = (EEnumLiteralImpl) e2.asJavaObject();
			o2 = enumliteral.getInstance();
			
			
		} 
		
		return o1.equals(o2);
		
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
	

    public Object getValueForFeauture(Object source, Property property) {
        if (source instanceof EObject) {
            EObject eo = (EObject)source;
            EStructuralFeature structuralFeature = 
                eo.eClass().getEStructuralFeature(property.getName());
            return eo.eGet(structuralFeature);
        }
        throw new RuntimeException("Expected EObject bud found " + source);
    }

    public Object invokeModelOperation(Classifier sourceType, Classifier resultType, Object source, String operName, List javaTypes, List args, ILog log) {
        Object result = null;
        try {
            Method oper = getMethod(source, operName, (Class[]) javaTypes.toArray(new Class[] {}));
            if (source != null) {
                if (oper != null) {
                    result = oper.invoke(source, args.toArray());
                } else {
                    //try converting source into an OclAnyModelElement ?
                    log.reportError("eval: Operation " + operName + javaTypes + " not found on object " + source);
                }
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return this.processor.getStdLibAdapter().OclAny(result);
    }
    
    /** Get a method using reflection */
    private Method getMethod(Object source, String operName, Class[] types) throws Exception {
        if (source == null)
            return null;
        Method method = null;
        try {
            //--- Search foe exact match ---
            try {
                method = source.getClass().getMethod(operName, types);
            } catch (Exception e) {}
            //--- Search compatible methods ---
            if (method == null) {
                Method methods[] = source.getClass().getMethods();
                for (int i = 0; i < methods.length; i++) {
                    String methodName = methods[i].getName();
                    Class argTypes[] = methods[i].getParameterTypes();
                    if (methodName.equals(operName) && types.length == argTypes.length) {
                        boolean found = true;
                        for (int j = 0; j < types.length; j++) {
                            if (!argTypes[j].isAssignableFrom(types[j])) {
                                found = false;
                                break;
                            }
                        }
                        if (found)
                            return methods[i];
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return method;
    }
}
