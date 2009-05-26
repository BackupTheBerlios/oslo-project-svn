package org.oslo.ocl20.examples;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.bridge4emf.EmfOclProcessorImpl;
import org.oslo.ocl20.semantics.bridge.Environment;
import org.oslo.ocl20.synthesis.RuntimeEnvironment;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;



public class UMLExample {

	
	private ILog log = new OutputStreamLog(System.err);
	private OclProcessor processor = new EmfOclProcessorImpl(log);
	private List packages = new ArrayList();
	private Environment env;
	private RuntimeEnvironment renv;
	private Resource resource;
	private UMLPackage umlPackage = UMLPackage.eINSTANCE;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UMLExample umlExample = new UMLExample();
		
		
		String defconstraint = "context Model def: numberOfClasses: Integer = Class.allInstances().size()";
		String queryconstriant ="context Model inv: self.numberOfClasses";
		System.out.println(umlExample.evaluate(defconstraint+queryconstriant));
	}

	public UMLExample()
	{
		loadresources();
	}
	
	
	private Object evaluate(String constraint)
	{
		return processor.evaluate(constraint, env, renv, log);
	}
	private void loadresources()
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  "ecore", new EcoreResourceFactoryImpl()); 
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  "xml", new XMLResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  Resource.Factory.Registry.DEFAULT_EXTENSION,
				  new XMIResourceFactoryImpl());
		
		URI umlInstancefileURI = URI.createFileURI("./models/SimpleUMLModel.uml");
		Resource umlResource = resourceSet.getResource(umlInstancefileURI,true);
		
		packages.clear();
		
		Model umlModel = (Model)umlResource.getContents().get(0);
		processor.addModel(umlPackage);
		env = processor.environment("self", umlModel.getClass());
		renv = processor.runtimeEnvironment("self", umlModel);
		
	}
	
}
