package org.oslo.ocl20.bridge4emf;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.OclProcessorImpl;
import org.oslo.ocl20.semantics.analyser.OclDebugVisitorImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyser;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserVisitorImpl;
import org.oslo.ocl20.semantics.bridge.Environment;
import org.oslo.ocl20.semantics.factories.TypeFactory;
import org.oslo.ocl20.standard.lib.StdLibAdapter;
import org.oslo.ocl20.standard.lib.StdLibAdapterImpl;
import org.oslo.ocl20.standard.types.TypeFactoryImpl;
import org.oslo.ocl20.syntax.parser.OclParser;
import org.oslo.ocl20.syntax.parser.OclParserImpl;
import org.oslo.ocl20.synthesis.ModelEvaluationAdapter;
import org.oslo.ocl20.synthesis.ModelGenerationAdapter;
import org.oslo.ocl20.synthesis.OclCodeGenerator;
import org.oslo.ocl20.synthesis.OclEvaluator;
import org.oslo.ocl20.synthesis.OclEvaluatorImpl;
import org.oslo.ocl20.synthesis.OclEvaluatorVisitorImpl;
import org.oslo.ocl20.synthesis.RuntimeEnvironment;

import uk.ac.kent.cs.kmf.util.ILog;

/**
 * @author dha
 *
 */
public class EmfOclProcessorImpl extends OclProcessorImpl implements OclProcessor {
    EmfBridgeFactory bridgeFactory = new EmfBridgeFactory(this);
    private static List list_ = new ArrayList();
	private static List visit_list_ = new ArrayList();
	
    public org.oslo.ocl20.semantics.factories.BridgeFactory getBridgeFactory() {
        return bridgeFactory;
    }

    TypeFactory typeFactory = new TypeFactoryImpl(this);
    public TypeFactory getTypeFactory() {
        return typeFactory;
    }

    ModelEvaluationAdapter modelImplAdapter = new EmfEvaluationAdapter(this);
    public ModelEvaluationAdapter getModelEvaluationAdapter() {
        return modelImplAdapter;
    }

    ModelGenerationAdapter modelGenAdapter = null;//= new EmfGenerationAdapter(this);
    public ModelGenerationAdapter getModelGenerationAdapter() {
        return modelGenAdapter;
    }

    StdLibAdapterImpl stdLibAdapter = new StdLibAdapterImpl(this);
    public StdLibAdapter getStdLibAdapter() {
        return stdLibAdapter;
    }

    StdLibAdapter stdLibGenerationAdapter = null;//= new StdLibGenerationAdapterImpl(this);
    public StdLibAdapter getStdLibGenerationAdapter() {
        return stdLibGenerationAdapter;
    }

    public String getStdLibAdapterName() {
        return "Ocl4Emf.processor.getStdLibAdapter()";
    }
    public String getModelImplAdapterName() {
        return "Ocl4Emf.processor.getModelImplAdapter()";
    }

    OclParser parser = new OclParserImpl();
    public OclParser getParser() {
        return parser;
    }

    OclSemanticAnalyser analyser = new OclSemanticAnalyserImpl(this, new OclSemanticAnalyserVisitorImpl(this), new OclDebugVisitorImpl(), null);
    public OclSemanticAnalyser getAnalyser() {
        return analyser;
    }

    OclEvaluatorImpl evaluator = new OclEvaluatorImpl(this, new OclEvaluatorVisitorImpl(this));
    public OclEvaluator getEvaluator() {
        return evaluator;
    }

//	public OclCodeGeneratorImpl generator = new OclCodeGeneratorImpl( this, new OclCodeGeneratorVisitorImpl(null,this) );
    public OclCodeGenerator getGenerator() {
        return null;//generator;
    }

    public void addModel(Object mdl) {
        if (mdl instanceof EPackage) {
            EPackage pkg = (EPackage) mdl;
            getModels().add(pkg);
        } else {
            getLog().reportError("Models added to the namespace must be of type EPackage for an EMF OclProcessor.");
        }
    }

    public EmfOclProcessorImpl(ILog log) {
        super(log);
    }

    public List evaluate_2(String str, Environment env, RuntimeEnvironment renv, ILog log)
    {
    	String context;
    	
    	int p=str.indexOf("inv:");
    	context=str.substring(8,p);
    	context=context.replaceAll(" ","");
    	
    	EObject eobject = (EObject)renv.getValue("self");
    	list_.clear();
    	visit_list_.clear();
    	
    	findInstances(context,eobject);
    	
    	List wholelist = new ArrayList();
    	
    	
    	for (int i=0; i<list_.size(); i++)
    	{
    		RuntimeEnvironment renv_ = runtimeEnvironment("self", (EObject)list_.get(i));
    		List list=evaluate(str,env,renv_,log);
    		if (list!=null){
    		wholelist.addAll(list);
    		}
    	}
    	return wholelist;
    }
    
    private static void findInstances(String InstanceName, EObject object)
	{
    	if (object.eClass().getName().toString().equals(InstanceName))
    	{
    		if (!(list_.contains(object)))
			{
				list_.add(object);
				
				//System.out.println(list_.size()+":"+eo);
			}
    		
    	}
		EList elist = object.eClass().getEAllStructuralFeatures();
		
		for (int l=0; l<elist.size();l++)
		{
			EStructuralFeature sf = (EStructuralFeature)elist.get(l);
						
			
			if (sf.eClass().getName()=="EReference")
			{
				
				//System.out.println(sf);
				if (sf!=null) {
					Object obj = object.eGet(sf);
					
					if (obj instanceof EList) {
						EList li_ = (EList)obj;
						for (int a=0; a<li_.size();a++)
						{
							EObject eo = (EObject)li_.get(a);
							
							
							if (eo.eClass().getName().toString().equals(InstanceName))
							{
								//System.out.println(eo);
								if (!(list_.contains(eo)))
								{
									list_.add(eo);
									
									//System.out.println(list_.size()+":"+eo);
								}
						
							}
							
							if (!visit_list_.contains(eo)) {
								visit_list_.add(eo);
								findInstances(InstanceName,eo);
							}
						 }
					}
					
					if (obj instanceof EObject) {
						EObject eo = (EObject)obj;
						
						if (eo!=null) {
							
							if (eo.eClass().getName().toString().equals(InstanceName))
							{
								//System.out.println(eo);
								if (!(list_.contains(eo)))
								{
									list_.add(eo);
									//System.out.println(list_.size()+":"+eo);
								}																			
							}
							
							if (!visit_list_.contains(eo)) {
								//System.out.println(eo);
								visit_list_.add(eo);
								findInstances(InstanceName,eo);
								//visit_list_.add(eo);
							}
							
						}
						
						visit_list_.add(eo);
					}
				}
			}
			
		}
		
	}

	public void generate(Reader input, PrintWriter output, String pkgName, String className, ILog log) {
		// TODO Auto-generated method stub
		
	}
}
