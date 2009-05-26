package org.oslo.ocl20.examples;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.bridge4emf.EmfOclProcessorImpl;
import org.oslo.ocl20.semantics.bridge.Environment;
import org.oslo.ocl20.synthesis.RuntimeEnvironment;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

public class ReflectiveExample {

	private ILog log = new OutputStreamLog(System.err);
	public OclProcessor processor = new EmfOclProcessorImpl(log);
	private List packages = new ArrayList();
	private Environment env;
	private RuntimeEnvironment renv;
	private Resource resource;
	private EObject eobject;
	
	public ReflectiveExample()
	{
		loadsources();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ReflectiveExample reflectiveExample = new ReflectiveExample();
		String defconstraint = "context CompanyModel::Company def: numberOfDepartments: Integer = departments.size()";
		String queryconstraint = "context CompanyModel::Company inv: numberOfDepartments";
		
		System.out.println(reflectiveExample.evalute(defconstraint+queryconstraint));		
	}
	
	public Object evalute(String constraint)
	{
		return processor.evaluate_2(constraint, env, renv, log);
	}
	
	private void loadsources()
	{
		ResourceSet resourceSet = new ResourceSetImpl();
			
			
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  "ecore", new EcoreResourceFactoryImpl()); 
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  "xml", new XMLResourceFactoryImpl());
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  Resource.Factory.Registry.DEFAULT_EXTENSION,
					  new XMIResourceFactoryImpl());
			
			URI metaModelfileURI = URI.createFileURI("./models/Company.ecore");
			Resource ecore_resource = resourceSet.getResource(metaModelfileURI,true);
			
			packages.clear();
			
			for (int p=0; p<ecore_resource.getContents().size();p++){
				
				EPackage epackage = (EPackage)ecore_resource.getContents().get(p);
				register_package(epackage);
				processor.addModel(epackage);
			}
			
			URI InstancefileURI = URI.createFileURI("./models/My.company");
			resource = resourceSet.getResource(InstancefileURI, true);
			
			eobject = (EObject)resource.getContents().get(0);
			System.out.println(eobject);
			addModels();
			env = processor.environment("self", eobject.getClass());
			renv = processor.runtimeEnvironment("self", eobject);
	}
	
	
	private void register_package(EPackage epackage)
	{
		
		EPackage.Registry.INSTANCE.put(epackage.getNsURI(),epackage);
	    	    
	    if (!(packages.contains(epackage))){
	    	packages.add(epackage);
	    	for (int p=0; p<epackage.getESubpackages().size();p++)
	    	{
	    		EPackage e_package = (EPackage)epackage.getESubpackages().get(p);
	    		register_package(e_package);
	    	}
	   
	    
	    }
	}
	
	public void addModels()
	{
		 for (int p=0; p<packages.size();p++){
	    	processor.getModels().add(packages.get(p));
		}
	}

}
