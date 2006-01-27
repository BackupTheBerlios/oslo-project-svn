package org.oslo.ocl20.bridge4emf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.semantics.SemanticsVisitor;
import org.oslo.ocl20.semantics.bridge.Classifier;
import org.oslo.ocl20.semantics.bridge.Namespace;
import org.oslo.ocl20.semantics.bridge.OclModelElementType;
import org.oslo.ocl20.semantics.bridge.Operation;
import org.oslo.ocl20.semantics.bridge.Property;
import org.oslo.ocl20.semantics.model.types.OclAnyType;
import org.oslo.ocl20.standard.types.OclAnyTypeImpl;


/**
 * @author dha
 *
 */
public class OclModelElementTypeImpl extends OclAnyTypeImpl implements OclModelElementType {
    public OclModelElementTypeImpl(EClassifier impl, OclProcessor proc) {
        super(proc);
        _impl = impl;
        List oper = super.getOperations();
        oper.add(proc.getBridgeFactory().buildOperation(proc.getTypeFactory().buildBooleanType(), "=", new Classifier[] { this }));
        oper.add(proc.getBridgeFactory().buildOperation(proc.getTypeFactory().buildBooleanType(), "<>", new Classifier[] { this }));
        List oclAnyOper = proc.getTypeFactory().buildOclAnyType().getOperations();
        oper.addAll(oclAnyOper);
    }

    EClassifier _impl;
    public EClassifier getImplementation() {
    	return _impl;
    }

    Map _properties = new HashMap();
    public Property lookupProperty(String name) {
        Property prop = (Property) _properties.get(name);
        if (prop == null) {
            if (_impl instanceof EClass) {
                //String emf_name = Ocl4Emf.adaptor.getGetterName(name);
                // getEStructuralFeature uses names from the model.
                EStructuralFeature sf = ((EClass) _impl).getEStructuralFeature(name);
                if (sf == null) {
                    String n2 = name.substring(0, 1).toUpperCase() + name.substring(1);
                    sf = ((EClass) _impl).getEStructuralFeature(n2);
                }
                if (sf != null) {
                	prop = super.processor.getBridgeFactory().buildProperty(sf);
                	_properties.put(name, prop);
                }
            } else if (_impl instanceof EEnum) {} else if (_impl instanceof EDataType) {}
        }
        //if still not found check for property in 'Classifier' for superclasses of _impl
        if (prop == null) {
        	if (_impl instanceof EClass) {
        		List l = ((EClass)_impl).getESuperTypes();
        		Iterator i = l.iterator();
        		while (i.hasNext()) {
					Classifier spCls = processor.getBridgeFactory().buildClassifier(i.next());
					prop = spCls.lookupProperty(name);
					if (prop != null) {
						_properties.put(name, prop);
						break;
					} 
        		}
        	}
        }
        return prop;
    }

    public List getProperties() {
        return new Vector(_properties.values());
    }

    public void addProperty(Property p) {
        _properties.put(p.getName(), p);
    }
/*
    class P{
        public P(String name, List types) {
            this.name=name;
            this.types=types;
        }
        String name;
        List types;
        public int hashCode() {
            return name.hashCode();//*types.hashCode();
        }
        public boolean equals(Object o) {
            if (!(o instanceof P)) return false;
            P p = (P)o;
            return ( name.equals(p.name)
                     //&& types.equals(p.types)
                     );
        }
    }
    Map _operations = new HashMap();*/
    public Operation lookupOperation(String name, List types) {
        Operation o = super.lookupOperation(name, types);
        //if (o==null) {
	      //  o = (Operation)_operations.get( new P(name,types) );
	        if (o==null) {
		        if (_impl instanceof EClass) {
		        	EClass impCls = (EClass)_impl;
		            if (o == null) {
		                Iterator i = impCls.getEAllOperations().iterator();
		                while (i.hasNext()) {
		                    EOperation op = (EOperation) i.next();
		                    if (op.getName().equals(name)) {
		                        o = new OperationImpl(op, super.processor);
		                        break;
		                    }
		                    
		                }
		            }
		        }
	        }
	        //if still not found check for operation in 'Classifier' for superclasses of _impl
	        if (o == null) {
	        	if (_impl instanceof EClass) {
	        		List l = ((EClass)_impl).getESuperTypes();
	        		Iterator i = l.iterator();
	        		while (i.hasNext()) {
						Classifier spCls = processor.getBridgeFactory().buildClassifier(i.next());
						o = spCls.lookupOperation(name, types);
						if (o != null) {
							break;
						} 
	        		}
	        	}
	        }
        //}
        //if (o!=null)
        //    super.addOperation(o);
        return o;
    }
/*
    public List getOperation() {
        return new Vector(_operations.values());
    }

    public void addOperation(Operation o) {
        _operations.put(new P(o.getName(),o.getParameterTypes()), o);
    }
*/

    
    public String getName() {
        return _impl.getName();
    }

    public Namespace getNamespace() {
        Namespace ns = super.getNamespace();
        if (ns == null) {
            ns = processor.getBridgeFactory().buildNamespace(_impl.getEPackage());
            super.setNamespace(ns);
        }
        return ns;
    }

    public Boolean conformsTo(Classifier c) {
        if (c.equals(processor.getTypeFactory().buildOclAnyType()))
            return Boolean.TRUE;
        if (_impl instanceof EClass) {
            if (c instanceof OclModelElementTypeImpl) {
                EClassifier cc = ((OclModelElementTypeImpl) c)._impl;
                EClass self = ((EClass) _impl);
                return new Boolean(cc.equals(self) || self.getEAllSuperTypes().contains(cc));
            }
        } else if (_impl instanceof EEnum) {} else if (_impl instanceof EDataType) {}
        return Boolean.FALSE;
    }

    public Object accept(SemanticsVisitor v, Object obj) {
        return v.visit(this, obj);
    }

    public String toString() {
        return "OclModelElementType(" + _impl.getName() + ")";
    }
    public Object getDelegate() {
        return _impl.getInstanceClass();
    }
}
