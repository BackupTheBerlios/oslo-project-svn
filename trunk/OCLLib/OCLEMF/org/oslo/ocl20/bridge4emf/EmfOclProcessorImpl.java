package org.oslo.ocl20.bridge4emf;

import org.eclipse.emf.ecore.EPackage;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.OclProcessorImpl;
import org.oslo.ocl20.generation.lib.StdLibGenerationAdapterImpl;
import org.oslo.ocl20.semantics.analyser.OclDebugVisitorImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyser;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserImpl;
import org.oslo.ocl20.semantics.analyser.OclSemanticAnalyserVisitorImpl;
import org.oslo.ocl20.semantics.bridge.BridgeFactory;
import org.oslo.ocl20.semantics.model.types.TypeFactory;
import org.oslo.ocl20.standard.lib.StdLibAdapter;
import org.oslo.ocl20.standard.lib.StdLibAdapterImpl;
import org.oslo.ocl20.standard.types.TypeFactoryImpl;
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

import uk.ac.kent.cs.kmf.util.ILog;

/**
 * @author dha
 *
 */
public class EmfOclProcessorImpl extends OclProcessorImpl implements OclProcessor {
    EmfBridgeFactory bridgeFactory = new EmfBridgeFactory(this);
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
}
