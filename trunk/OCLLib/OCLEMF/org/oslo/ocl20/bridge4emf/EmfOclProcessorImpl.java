package org.oslo.ocl20.bridge4emf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.OclProcessorImpl;
import org.oslo.ocl20.generation.lib.StdLibGenerationAdapterImpl;
import org.oslo.ocl20.semantics.analyser.OclDebugVisitorImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyser;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserVisitorImpl;
import org.oslo.ocl20.semantics.bridge.BridgeFactory;
import org.oslo.ocl20.semantics.bridge.Environment;
import org.oslo.ocl20.semantics.model.types.TypeFactory;
import org.oslo.ocl20.standard.lib.StdLibAdapter;
import org.oslo.ocl20.standard.lib.StdLibAdapterImpl;
import org.oslo.ocl20.standard.types.TypeFactoryImpl;
import org.oslo.ocl20.syntax.ast.contexts.ClassifierContextDeclAS;
import org.oslo.ocl20.syntax.ast.contexts.PackageDeclarationAS;
import org.oslo.ocl20.syntax.ast.types.ClassifierAS;
import org.oslo.ocl20.syntax.parser.OclParser;
import org.oslo.ocl20.syntax.parser.OclParserImpl;
import org.oslo.ocl20.synthesis.ModelEvaluationAdapter;
import org.oslo.ocl20.synthesis.ModelGenerationAdapter;
import org.oslo.ocl20.synthesis.OclCodeGenerator;
import org.oslo.ocl20.synthesis.OclCodeGeneratorImpl;
import org.oslo.ocl20.synthesis.OclCodeGeneratorVisitorImpl;
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
	
    public BridgeFactory getBridgeFactory() {
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

    ModelGenerationAdapter modelGenAdapter = new EmfGenerationAdapter(this);
    public ModelGenerationAdapter getModelGenerationAdapter() {
        return modelGenAdapter;
    }

    StdLibAdapterImpl stdLibAdapter = new StdLibAdapterImpl(this);
    public StdLibAdapter getStdLibAdapter() {
        return stdLibAdapter;
    }

    StdLibAdapter stdLibGenerationAdapter = new StdLibGenerationAdapterImpl(this);
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

	public OclCodeGeneratorImpl generator = new OclCodeGeneratorImpl( this, new OclCodeGeneratorVisitorImpl(null,this) );
    public OclCodeGenerator getGenerator() {
        return generator;
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
    	// parse constraints
    	List<String> contextpathnames = getContextPathNames(str);
    	    	
    	
    	List InstancesList = new ArrayList();
    	
    	EObject eobject = (EObject)renv.getValue("self");
    	
    	for (int i=0; i<contextpathnames.size(); i++)
    	{
    		String context = contextpathnames.get(i);
			TreeIterator iter = eobject.eResource().getAllContents();
			
			while (iter.hasNext())
			{
				EObject content = (EObject)iter.next();
				if (content.eClass().getName().toString().equals(context))
				{
					if (!(InstancesList.contains(content)))
					{
						InstancesList.add(content);
						
					}
				}
				
				List supertypes = content.eClass().getEAllSuperTypes();
				
				for (int s=0; s<supertypes.size(); s++)
				{
					EClass eclass = (EClass)supertypes.get(s);
					if (eclass.getName().toString().equals(context))
					{
						if (!(InstancesList.contains(content)))
						{
							InstancesList.add(content);
							
						}
					}
				}
				
			}
    	}
    	//list_.clear();
    	//visit_list_.clear();
    	
    //	findInstances(context,eobject);
    	
    	List wholelist = new ArrayList();
    	
    	for (int i=0; i<InstancesList.size(); i++)
    	{
    		RuntimeEnvironment renv_ = runtimeEnvironment("self", (EObject)InstancesList.get(i));  
    		String name = getNodename((EObject)InstancesList.get(i));
    		List list=evaluate(str,env,renv_,log);
    		
    		/*if (list!=null){
    			for (Iterator it = list.iterator(); it.hasNext(); ) {
					Object obj = (Object) it.next();
					EvaluateResult er = new EvaluateResult(name, obj);
//					System.err.println(er);
					wholelist.add(obj);
				}    		
    		}*/
    		wholelist.addAll(list);
    	}
    	return wholelist;
    }
    
    private List<String> getContextPathNames(String constraints)
    {
    	PackageDeclarationAS packageDeclarationAS = parse(constraints);
    	List<String> contextpathnames = new ArrayList<String>();
    	List contextDecls = packageDeclarationAS.getContextDecls();
    	for (int i=0; i<contextDecls.size();i++)
    	{
    		if (contextDecls.get(i) instanceof ClassifierContextDeclAS)
    		{
    			ClassifierContextDeclAS classifierContextDeclAS = 
    				(ClassifierContextDeclAS)contextDecls.get(i);
    			if (classifierContextDeclAS.getType() instanceof ClassifierAS)
    			{
    				ClassifierAS classifierAS = 
    					(ClassifierAS)classifierContextDeclAS.getType();
    				List pathnames = classifierAS.getPathName();
    				String name = (String)pathnames.get(pathnames.size()-1);
    				if (!(contextpathnames.contains(name)))
    				{
    					contextpathnames.add(name);
    				}
    			}
    		}
    	}
    	
    	return contextpathnames;
    }
    
    
    private String getNodename(EObject object)
    {
        List list = object.eClass().getEAllStructuralFeatures();
        EStructuralFeature name = null;
        for (Iterator it = list.iterator(); it.hasNext(); ) {
			name = (EStructuralFeature) it.next();
			if (name.getEType().getName().matches(".*(?i)string.*") && object.eGet(name) != null && object.eGet(name).toString().trim().length() != 0) {
				break;
			}
		}
        
        String nodename = "";
        if(name == null)
            name = object.eClass().getEStructuralFeature("Name");
        if(name != null)
        {
            if(object.eGet(name) != null)
                nodename = object.eGet(name).toString();
        } else
        {
            nodename = object.eClass().getName();
        }
        return nodename;
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
}
