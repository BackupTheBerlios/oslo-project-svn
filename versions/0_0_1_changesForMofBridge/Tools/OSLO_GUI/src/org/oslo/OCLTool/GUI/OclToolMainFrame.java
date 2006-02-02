package org.oslo.OCLTool.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.uml2.UML2Package;
//import org.modelware.modelbus.adapter.impl.deploy.AdapterContainer;
//import org.modelware.modelbus.adapter.impl.stub.AdapterStub;
import org.oslo.ocl20.OclProcessor;
import org.oslo.ocl20.bridge4emf.EmfOclProcessorImpl;
import org.oslo.ocl20.semantics.bridge.Environment;
import org.oslo.ocl20.synthesis.RuntimeEnvironment;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;


/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
public class OclToolMainFrame extends javax.swing.JFrame {
	private JMenuBar MenuBar;
	private JMenuItem ExitMItem;
	private JMenuItem AboutMItem;
	private JMenu Help;
	private JLabel ResultLabel;
	private JLabel LogViewLabel;
	private JSeparator jSeparator3;
	private JLabel ModelDescriptionLabel;
	private JButton evaluateButton;
	private JSeparator jSeparator2;
	private JTextField OCLConstraintTextField;
	private JSeparator jSeparator1;
	private JLabel ModelLabel;
	private JLabel OCLConstraintLabel;
	private JInternalFrame jInternalFrame1;
	private JSeparator FileSeperator1;
	private JMenuItem LoadUML2ModelMItem;
	private JMenu File;
	private JFileChooser m_fileChooser = new JFileChooser();
	
	private static ByteArrayOutputStream ops= new ByteArrayOutputStream();
	private static PrintStream pt = new PrintStream(ops);
	
	private static UML2Package uml2package = UML2Package.eINSTANCE;
	private static ILog log = new OutputStreamLog(pt);
	private static OclProcessor processor = new EmfOclProcessorImpl(log);
	private static Environment env;
    private static RuntimeEnvironment renv;
    private JLabel FokusImage;
    private JTextArea LogViewTextArea;
    private JTextArea ResultTextArea;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane1;
    private Resource resource;
    //private AdapterStub stub;
   

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  "ecore", new EcoreResourceFactoryImpl()); 
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  "xml", new XMLResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				  Resource.Factory.Registry.DEFAULT_EXTENSION,
				  new XMIResourceFactoryImpl());
		
		processor.getModels().add(uml2package);
		OclToolMainFrame inst = new OclToolMainFrame();
		inst.setTitle("Fraunhofer OCLTool - V0.a");
		inst.setVisible(true);
		
		
	}
	
	public OclToolMainFrame() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				MenuBar = new JMenuBar();
				setJMenuBar(MenuBar);
				{
					File = new JMenu();
					MenuBar.add(File);
					File.setText("File");
					{
						LoadUML2ModelMItem = new JMenuItem();
						File.add(LoadUML2ModelMItem);
						LoadUML2ModelMItem.setText("Load UML2 Model");
						LoadUML2ModelMItem.setName("LoadUML2ModelMItem");
						LoadUML2ModelMItem
							.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								LoadUML2ModelMItemActionPerformed(evt);
							}
							});
					}
					{
						FileSeperator1 = new JSeparator();
						File.add(FileSeperator1);
					}
					{
						ExitMItem = new JMenuItem();
						File.add(ExitMItem);
						ExitMItem.setText("Exit");
						ExitMItem.setName("ExitMItem");
						ExitMItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								ExitMItemActionPerformed(evt);
							}
						});
					}
				}
				{
					Help = new JMenu();
					MenuBar.add(Help);
					Help.setText("Help");
					{
						AboutMItem = new JMenuItem();
						Help.add(AboutMItem);
						AboutMItem.setText("About Fraunhofer OCLTool");
						AboutMItem.setName("AboutMItem");
						AboutMItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AboutMItemActionPerformed(evt);
							}
						});
					}
				}
			}
			this.getContentPane().setLayout(null);
			this.setTitle("Fraunhofer OCLTool - V0.a");
			{
				jInternalFrame1 = new JInternalFrame();
				{
					OCLConstraintLabel = new JLabel();
					this.getContentPane().add(OCLConstraintLabel);
					OCLConstraintLabel.setText("OCLConstraint:");
					OCLConstraintLabel.setBounds(7, 48, 73, 13);
				}
				{
					ModelLabel = new JLabel();
					this.getContentPane().add(ModelLabel);
					ModelLabel.setText("Model:");
					ModelLabel.setBounds(7, 17, 31, 15);
				}
				{
					OCLConstraintTextField = new JTextField();
					this.getContentPane().add(OCLConstraintTextField);
					OCLConstraintTextField.setBounds(4, 70, 379, 23);
					OCLConstraintTextField.setPreferredSize(new java.awt.Dimension(437, 44));
				}
				{
					jSeparator2 = new JSeparator();
					this.getContentPane().add(jSeparator2);
					jSeparator2.setBounds(-11, 101, 547, 17);
				}
				{
					evaluateButton = new JButton();
					this.getContentPane().add(evaluateButton);
					evaluateButton.setText("evaluate");
					evaluateButton.setBounds(392, 73, 133, 19);
					evaluateButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							evaluateButtonActionPerformed(evt);
						}
					});
				}
				this.getContentPane().add(jInternalFrame1);
				jInternalFrame1.setBounds(16, 431, 0, 0);
			}
			{
				ResultLabel = new JLabel();
				this.getContentPane().add(ResultLabel);
				ResultLabel.setText("Result:");
				ResultLabel.setBounds(6, 107, 60, 15);
			}
			{
				jSeparator3 = new JSeparator();
				this.getContentPane().add(jSeparator3);
				jSeparator3.setBounds(4, 278, 531, 12);
			}
			{
				LogViewLabel = new JLabel();
				this.getContentPane().add(LogViewLabel);
				LogViewLabel.setText("LogView:");
				LogViewLabel.setBounds(5, 286, 60, 16);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(8, 306, 515, 160);
				{
					LogViewTextArea = new JTextArea();
					jScrollPane1.setViewportView(LogViewTextArea);
					LogViewTextArea.setText("OSLO GUI - V1");
					LogViewTextArea.setPreferredSize(new java.awt.Dimension(511, 156));
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				this.getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(9, 128, 515, 138);
				{
					ResultTextArea = new JTextArea();
					jScrollPane2.setViewportView(ResultTextArea);
				}
			}
			{
				FokusImage = new JLabel();
				this.getContentPane().add(FokusImage);
				FokusImage.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("fokuslogo.gif")));
				FokusImage.setBounds(459, 5, 66, 56);
			}
			{
				jSeparator1 = new JSeparator();
				this.getContentPane().add(jSeparator1);
				jSeparator1.setBounds(0, -21, 535, 520);
			}
			{
				ModelDescriptionLabel = new JLabel();
				this.getContentPane().add(ModelDescriptionLabel);
				ModelDescriptionLabel.setText("no Model is loaded");
				ModelDescriptionLabel.setBounds(42, 13, 177, 23);
			}
			this.setSize(543, 528);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void AboutMItemActionPerformed(ActionEvent evt) {
		AboutOCL aboutDialog = new AboutOCL(this);
		aboutDialog.show();
		
		
	}
	
	private void ExitMItemActionPerformed(ActionEvent evt) {

		System.exit(0);
	}
	
	private void LoadUML2ModelMItemActionPerformed(ActionEvent evt) {
		 int retval = m_fileChooser.showOpenDialog(this);
         if (retval == JFileChooser.APPROVE_OPTION) {
             //... The user selected a file, process it.
             File file = m_fileChooser.getSelectedFile();
             ResourceSet resourceSet = new ResourceSetImpl();
             URI InstancefileURI = URI.createFileURI(file.getAbsolutePath());
             resource = null;
     		 resource = resourceSet.getResource(InstancefileURI,true);
     		 
     		String temp = LogViewTextArea.getText();
    		temp=temp+"\n"+"load: "+resource.toString();
    		LogViewTextArea.setText(temp);
            ModelDescriptionLabel.setText(file.getName());
         }
	}
	
	private void evaluateButtonActionPerformed(ActionEvent evt) {
		evaluate();
	}

	private void evaluate()
	{
		
		String temp = LogViewTextArea.getText();
		temp=temp+"\n"+"evaluate: "+OCLConstraintTextField.getText();
		LogViewTextArea.setText(temp);
		
		ResultTextArea.setText("Result of "+OCLConstraintTextField.getText()+"\n");
		// Get Contents of resource
		processor = new EmfOclProcessorImpl(log);
		processor.getModels().add(uml2package);
		
		if (resource != null)
		{
		EList list = resource.getContents();
		
		for(int i=0;i<list.size();i++){
			EObject eobject = (EObject)list.get(i);
			
			env = processor.environment("self", eobject.getClass());
			renv = processor.runtimeEnvironment("self", eobject);
			String expr = OCLConstraintTextField.getText();
			String context=getContext(expr);
			List result=new ArrayList(); 
			//boolean dummy=true;
			/*if (context.equals("Model"))
		    { */
				
				List resultlist = processor.evaluate_2(expr,env,renv,log);
				
				if (resultlist!=null) {
				
		    	result.addAll(resultlist);
				} else {
					result=resultlist;
				}
					
		    	//dummy=false;
		   // } 
		   /*if (context.equals("Package"))
		    {
		    	List packagelist=getAllPackages(eobject);
		    	
		    	for (int c=0;c<packagelist.size();c++){
		    		EObject eo = (EObject)packagelist.get(c);
		    		renv = processor.runtimeEnvironment("self", eo);
		    		List resultlist = processor.evaluate(expr,env,renv,log);
					if (resultlist!=null) {
					
			    	result.addAll(resultlist);
					} else {
						result=resultlist;
						break;
					}
		    		
		    		
		    	}
		    	dummy=false;
		    }
		    
		    if (context.equals("Class"))
		    {
		    	List classlist=getAllClasses(eobject);	 
		    	for (int c=0;c<classlist.size();c++){
		    		EObject eo = (EObject)classlist.get(c);
		    		renv = processor.runtimeEnvironment("self", eo);
		    		List resultlist = processor.evaluate(expr,env,renv,log);
					if (resultlist!=null) {
					
			    	result.addAll(resultlist);
					} else {
						result=resultlist;
						break;
					}
		    		
		    		//System.out.println(eo);
		    		
		    	}
		    	dummy=false;
		    } 
		    
		    if (dummy)  
			{
		    	result=processor.evaluate(expr,env,renv,log);
			}
			*/
			if (result!=null) {
				
			for (int a=0;a<result.size();a++) {
				String ResultText=ResultTextArea.getText();
				ResultText = ResultText.concat("\n"+result.get(a).toString());
				ResultTextArea.setText(ResultText);
			}
			} else {
				String ResultText=ResultTextArea.getText();
		        
				ResultText = ResultText.concat("\n"+ops);
				temp = LogViewTextArea.getText();
				temp=temp+" WITH ERROR !";	
				LogViewTextArea.setText(temp);
				try 
				{
				ops.reset();
				} catch (Exception e)
				{
				  System.out.println(ops);	
				}
				ResultTextArea.setText(ResultText);
				
			} 
			
		}
		} else {
			temp = LogViewTextArea.getText();
			temp=temp+" ERROR: no Model loaded";	
			LogViewTextArea.setText(temp);
			String ResultText=ResultTextArea.getText();
	        
			ResultText = ResultText.concat("\n"+temp);
			ResultTextArea.setText(ResultText);
		}
	}
	
	
	private static String getClassName(EObject obj) 
	{
		String classname=obj.getClass().getName();
		String packname=obj.getClass().getPackage().getName();
		String clsname=classname.replaceAll(packname+".","");
		
		return clsname;
	}
	
	private String getContext(String expression)
	{
		String value="";
		try {
		value=expression.substring(8,expression.indexOf("inv:")-1);
		} catch (StringIndexOutOfBoundsException e)
		{
		}
		return value;
	}
	
	private static List getAllPackages(EObject obj)
	{
		List list = new ArrayList();
		// Auf Model Ebene
		EStructuralFeature structuralFeature = obj.eClass().getEStructuralFeature("ownedMember");
		Object result = obj.eGet(structuralFeature);
				
		EList elist = (EList)result;
		if (elist.size()==0) {
			return null;
		} 
		for (int i=0; i<elist.size();i++){
			EObject eo = (EObject)elist.get(i);
			
			String contextcls="PackageImpl";
			if (getClassName(eo).equals(contextcls))
			{
				list.add(eo);
				List helplist = getAllPackages(eo);
				if (helplist!=null) {
				list.addAll(getAllPackages(eo));
				}
			}
		}
		
		return list;
	}
	
	private static List getAllClasses(EObject obj)
	{
		List list=new ArrayList();
		EStructuralFeature structuralFeature = obj.eClass().getEStructuralFeature("ownedMember");
		Object result = obj.eGet(structuralFeature);
				
		EList elist = (EList)result;
		if (elist.size()==0) {
			return null;
		} 
		
		for (int i=0; i<elist.size();i++){
			EObject eo = (EObject)elist.get(i);
			
			String contextcls="ClassImpl";
			if (getClassName(eo).equals(contextcls))
			{
				list.add(eo);
			}					
		}
		
		
		// get all calls within packages
		List helplist=getAllClassesWithinPackage(obj);
		if (helplist!=null) {
		list.addAll(helplist);
		}
		
		
		return list;
	}
	
	private static List getAllClassesHelp(EObject obj)
	{
		List list = new ArrayList();
		EStructuralFeature structuralFeature = obj.eClass().getEStructuralFeature("ownedMember");
		Object result = obj.eGet(structuralFeature);
				
		EList elist = (EList)result;
		if (elist.size()==0) {
			return null;
		} 
		
		for (int i=0; i<elist.size();i++){
			EObject eo = (EObject)elist.get(i);
			
			String contextcls="ClassImpl";
			if (getClassName(eo).equals(contextcls))
			{
				list.add(eo);
			}					
		}
		
		return list;
	}
	
	private static List getAllClassesWithinPackage(EObject obj)
	{
		List list = new ArrayList();
		List plist = getAllPackages(obj);
		for (int c=0;c<plist.size();c++){
			EObject eoj = (EObject)plist.get(c);
			
			List helplist=getAllClassesHelp(eoj);
			if (helplist!=null) 
			{
			list.addAll(helplist);
			}
		
		}
		return list;
	}
	
	
}
