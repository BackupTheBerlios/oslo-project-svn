package org.oslo.ocl20.test;
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

import junit.framework.TestCase;


public class OCLTest extends TestCase {

	private ILog log = new OutputStreamLog(System.err);
	private OclProcessor processor = new EmfOclProcessorImpl(log);
	private List packages = new ArrayList();
	private Environment env;
	private RuntimeEnvironment renv;
	private Resource resource;
	
	public void testProcessor()
	{
		System.out.print("Test Processor with: 1+1: ");
		List l = processor.evaluate("1+1");
		Object o = l.get(0);
		int i=2;
		assertTrue("Test Processor with: 1+1=2",(String.valueOf(o).equals("2")));
		System.out.println(o);
	}
	
	public void testStandardTypes()
	{
		System.out.print("Test StandardTypes Boolean: true - must be true and is ");
		List l = processor.evaluate("true");
		Object o = l.get(0);
		assertTrue(String.valueOf(o).equals(String.valueOf(true)));
		System.out.println(l);
		
		
		System.out.print("Test StandardTypes Boolean: false - must be false and is ");
		l = processor.evaluate("false");
		o = l.get(0);
		assertTrue(String.valueOf(o).equals(String.valueOf(false)));
		System.out.println(l);
		
		System.out.print("Test StandardTypes Boolean: not false - must be true and is ");
		l = processor.evaluate("not false");
		o = l.get(0);
		assertTrue(String.valueOf(o).equals(String.valueOf(true)));
		System.out.println(l);
		
		System.out.print("Test StandardTypes Set: Set{} - must be empty and is  ");
		l = processor.evaluate("Set{}");
		o = l.get(0);
		assertTrue(o.toString().equals("[]"));
		System.out.println(o);
		
		System.out.print("Test StandardTypes OrderedSet: OrderedSet{} - must be empty and is  ");
		l = processor.evaluate("OrderedSet{}");
		o = l.get(0);
		assertTrue(o.toString().equals("[]"));
		System.out.println(o);
		
		System.out.print("Test StandardTypes Bag: Bag{} - must be empty and is  ");
		l = processor.evaluate("Bag{}");
		o = l.get(0);
		assertTrue(o.toString().equals("[]"));
		System.out.println(o);
		
		System.out.print("Test StandardTypes Sequence: Sequence{} - must be empty and is  ");
		l = processor.evaluate("Sequence{}");
		o = l.get(0);
		assertTrue(o.toString().equals("[]"));
		System.out.println(o);
		
		System.out.print("Test StandardTypes Collection: Collection{} - must be empty and is  ");
		l = processor.evaluate("Collection{}");
		o = l.get(0);
		assertTrue(o.toString().equals("[]"));
		System.out.println(o);
		
		System.out.print("Test StandardTypes String: '' - must be empty and is  ");
		l = processor.evaluate("''");
		o = l.get(0);
		assertTrue(o.toString().equals(""));
		System.out.println(o);
		
		System.out.print("Test StandardTypes Real: 1.234 - must be 1.234 and is  ");
		l = processor.evaluate("1.234");
		o = l.get(0);
		assertTrue(o.toString().equals("1.234"));
		System.out.println(o);
	}
	
	public void testStandardConstructs()
	{
		System.out.println();
		System.out.print("Test StandardConstructs let ... in ...: let a:Integer=2 in a*3 - must be 6 and is ");
		List l = processor.evaluate("let a:Integer=2 in a*3");
		Object o = l.get(0);
		assertTrue(o.toString().equals("6"));
		System.out.println(l);
		
		System.out.print("Test StandardConstructs if ... then ... else... endif : if true then 1 else 2 endif - must be 1 and is ");
		l = processor.evaluate("if true then 1 else 2 endif");
		o = l.get(0);
		assertTrue(o.toString().equals("1"));
		System.out.println(l);
		
		System.out.print("Test StandardConstructs if ... then ... else... endif : if false then 1 else 2 endif - must be 2 and is ");
		l = processor.evaluate("if false then 1 else 2 endif");
		o = l.get(0);
		assertTrue(o.toString().equals("2"));
		System.out.println(l);
		
		System.out.print("Test StandardConstructs let ... in ... if ... then ... else... endif : let: a:Integer=2 in if a=2 then 2 else 1 endif - must be 2 and is ");
		l = processor.evaluate("let a:Integer=2 in if a=2 then 2 else 1 endif");
		o = l.get(0);
		assertTrue(o.toString().equals("2"));
		System.out.println(l);
		
		System.out.print("Test StandardConstructs let ... in ... if ... then ... else... endif : let: a:Integer=4 in if a=2 then 2 else 1 endif - must be 1 and is ");
		l = processor.evaluate("let a:Integer=4 in if a=2 then 2 else 1 endif");
		o = l.get(0);
		assertTrue(o.toString().equals("1"));
		System.out.println(l);
	}
	
	
	
	public void testReflectiveModel_oclIsTypeOf()
	{
		loadsources();
		System.out.print("Test ReflectiveModel: context SLSystem inv: self.oclIsTypeOf(SLSystem) - must be true and is ");
		List l = processor.evaluate_2("context SLSystem inv: self.oclIsTypeOf(SLSystem)",env,renv,log);
		Object o = l.get(0);
		assertTrue(o.toString().equals("true"));
		System.out.println(l);
		
		System.out.print("Test ReflectiveModel: context SLSystem inv: self.oclIsTypeOf(ASDElement) - must be false and is ");
		l = processor.evaluate_2("context SLSystem inv: self.oclIsTypeOf(ASDElement)",env,renv,log);
		o = l.get(0);
		assertTrue(o.toString().equals("false"));
		System.out.println(l);
	}
	
	public void testReflectiveModel_string()
	{
		loadsources();
		System.out.println();
		System.out.print("Test ReflectiveModel: context SLSystem inv: identifier - must be OCLDemo1 and is ");
		List l = processor.evaluate_2("context SLSystem inv: identifier",env,renv,log);
		Object o = l.get(0);
		assertTrue(o.toString().equals("OCLDemo1"));
		System.out.println(l);
	}
	
	public void testReflectiveModel_oclIsKindOf()
	{
		loadsources();
		System.out.print("Test ReflectiveModel: context SLSystem inv: self.oclIsKindOf(ASDElement) - must be true and is ");
		List l = processor.evaluate_2("context SLSystem inv: self.oclIsKindOf(ASDElement)",env,renv,log);
		Object o = l.get(0);
		assertTrue(o.toString().equals("true"));
		System.out.println(l);
	}
		
	public void testReflectiveModel_size()
	{
		loadsources();
		System.out.print("Test ReflectiveModel: context SLSystem inv: theBlockToSystem->size() - must be 5 and is ");
		List l = processor.evaluate_2("context SLSystem inv: theBlockToSystem->size()",env,renv,log);
		Object o = l.get(0);
		assertTrue(o.toString().equals("5"));
		System.out.println(l);
		
		System.out.print("Test ReflectiveModel: context SLSystem inv: SLBlock.allInstances()->size() - must be 5 and is ");
		l = processor.evaluate_2("context SLSystem inv: SLBlock.allInstances()->size()",env,renv,log);
		o = l.get(0);
		assertTrue(o.toString().equals("5"));
		System.out.println(l);
	}
		
	public void testReflectiveModel_forAll()	
	{
		loadsources();
		System.out.print("Test ReflectiveModel: context SLSystem inv: SLBlock.allInstances()->forAll(x|x.oclIsTypeOf(SLBlock)) - must be false and is ");
		List l = processor.evaluate_2("context SLSystem inv: SLBlock.allInstances()->forAll(x|x.oclIsTypeOf(SLBlock))",env,renv,log);
		Object o = l.get(0);
		assertTrue(o.toString().equals("false"));
		System.out.println(l);
	
		
		System.out.print("Test ReflectiveModel: context SLSystem inv: SLBlock.allInstances()->forAll(x|x.oclIsKindOf(SLBlock)) - must be true and is ");
		l = processor.evaluate_2("context SLSystem inv: SLBlock.allInstances()->forAll(x|x.oclIsKindOf(SLBlock))",env,renv,log);
		o = l.get(0);
		assertTrue(o.toString().equals("true"));
		System.out.println(l);
	}
	
	public void testReflectiveModel_devConstruct()
	{
		loadsources();
		System.out.println("Test ReflectiveModel: ");
		System.out.println("context SLBlock def: DummyValue: Integer = 2 AND");
		System.out.print("context SLBlock inv: self.DummyValue - must be [2, 2, 2, 2, 2] and is ");
		String constraint1="context SLBlock def: DummyValue: Integer = 2 ";
		String constraint2="context SLBlock inv: self.DummyValue";
		List l = processor.evaluate_2(constraint1+constraint2, env, renv, log);
		boolean value = true; 
		for (int i=0; i<l.size();i++)
		{
			Object o = l.get(i);
			if (!(o.toString().equals("2"))) 
			{
				value=false;
			}
		}
		System.out.println(l);
		assertTrue(value);
		
	}
	
	protected void loadsources()
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		//	System.out.println(resourceSet);
//			URI fileURI = URI.createFileURI("E:\\Models\\company.ecore");
			
			
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  "ecore", new EcoreResourceFactoryImpl()); 
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  "xml", new XMLResourceFactoryImpl());
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					  Resource.Factory.Registry.DEFAULT_EXTENSION,
					  new XMIResourceFactoryImpl());
			
			URI InstancefileURI = URI.createFileURI("./resources/asd.ecore");
			//URI InstancefileURI2 = URI.createFileURI("d:\\Models\\my.baseidl");
			Resource ecore_resource = resourceSet.getResource(InstancefileURI,true);
			
			packages.clear();
			
			for (int p=0; p<ecore_resource.getContents().size();p++){
				
				EPackage epackage = (EPackage)ecore_resource.getContents().get(p);
				   
				
				register_package(epackage);
				
			}
			 	URI Instancefile2URI = URI.createFileURI("./resources/ocldemo1.simulink");
			 	//URI Instancefile2URI = URI.createFileURI("d:\\Models\\testcase1.uml2");
			resource = resourceSet.getResource(Instancefile2URI, true);
			
			testModeladd();
			EObject eobject = (EObject)resource.getContents().get(0);
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
	
	public void testModeladd()
	{
		 for (int p=0; p<packages.size();p++){
	    	assertTrue(processor.getModels().add(packages.get(p)));
		}
	}
}
