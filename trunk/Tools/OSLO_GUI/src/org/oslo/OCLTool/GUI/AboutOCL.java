package org.oslo.OCLTool.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;


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
public class AboutOCL extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel About;
	private JLabel Kent;
	private JButton ViewLicense;
	private JLabel KentLogo;
	private JSeparator jSeparator3;
	private JLabel Fhlogo;
	private JSeparator jSeparator1;
	private JButton EndDialog;
	private JSeparator jSeparator2;
	private JLabel License;
	private JLabel Author;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AboutOCL inst = new AboutOCL(frame);
		inst.setVisible(true);
	}
	
	public AboutOCL(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.getContentPane().setLayout(thisLayout);
			this.setTitle("About Fraunhofer OCL Tool");
			{
				KentLogo = new JLabel();
				this.getContentPane().add(
					KentLogo,
					new AnchorConstraint(
						213,
						919,
						388,
						568,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				KentLogo.setText("KentLogo");
				KentLogo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("kent.jpg")));
				KentLogo.setPreferredSize(new java.awt.Dimension(125, 30));
			}
			{
				jSeparator3 = new JSeparator();
				this.getContentPane().add(
					jSeparator3,
					new AnchorConstraint(
						506,
						992,
						536,
						1,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jSeparator3.setPreferredSize(new java.awt.Dimension(353, 5));
			}
			{
				Fhlogo = new JLabel();
				this.getContentPane().add(
					Fhlogo,
					new AnchorConstraint(
						119,
						290,
						511,
						110,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				Fhlogo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("fokuslogo.gif")));
				Fhlogo.setPreferredSize(new java.awt.Dimension(64, 67));
			}
			{
				jSeparator1 = new JSeparator();
				this.getContentPane().add(
					jSeparator1,
					new AnchorConstraint(
						120,
						1007,
						138,
						7,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jSeparator1.setPreferredSize(new java.awt.Dimension(356, 3));
			}
			{
				EndDialog = new JButton();
				this.getContentPane().add(
					EndDialog,
					new AnchorConstraint(
						831,
						610,
						978,
						442,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				EndDialog.setText("OK");
				EndDialog.setPreferredSize(new java.awt.Dimension(60, 24));
				EndDialog.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						EndDialogActionPerformed(evt);
					}
				});
			}
			{
				jSeparator2 = new JSeparator();
				this.getContentPane().add(
					jSeparator2,
					new AnchorConstraint(
						806,
						1001,
						861,
						4,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jSeparator2.setPreferredSize(new java.awt.Dimension(355, 9));
			}
			{
				ViewLicense = new JButton();
				this.getContentPane().add(
					ViewLicense,
					new AnchorConstraint(
						677,
						976,
						782,
						681,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				ViewLicense.setText("View License");
				ViewLicense.setPreferredSize(new java.awt.Dimension(105, 17));
			}
			{
				License = new JLabel();
				this.getContentPane().add(
					License,
					new AnchorConstraint(
						696,
						644,
						782,
						15,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				License.setText("License: Common Public License Version 1.0");
				License.setPreferredSize(new java.awt.Dimension(224, 14));
			}
			{
				Author = new JLabel();
				this.getContentPane().add(
					Author,
					new AnchorConstraint(
						518,
						265,
						610,
						12,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				Author.setText("Author: MOTION");
				Author.setPreferredSize(new java.awt.Dimension(90, 15));
			}
			{
				About = new JLabel();
				this.getContentPane().add(
					About,
					new AnchorConstraint(
						25,
						1012,
						103,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				About.setText("About OSLOTool - Version 1");
				About.setFont(new java.awt.Font("Dialog",1,16));
				About.setPreferredSize(new java.awt.Dimension(349, 14));
			}
			{
				Kent = new JLabel();
				this.getContentPane().add(
					Kent,
					new AnchorConstraint(
						610,
						681,
						684,
						12,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				Kent.setText("with use of Kent OCL Library");
				Kent.setPreferredSize(new java.awt.Dimension(238, 12));
			}
			this.setSize(364, 205);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void EndDialogActionPerformed(ActionEvent evt) {
		this.dispose();
	}
	
	/**
	* Auto-generated method for setting the popup menu for a component
	*/
	private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
parent.addMouseListener(new java.awt.event.MouseAdapter() {
public void mousePressed(java.awt.event.MouseEvent e) {
if(e.isPopupTrigger())
menu.show(parent, e.getX(), e.getY());
}
public void mouseReleased(java.awt.event.MouseEvent e) {
if(e.isPopupTrigger())
menu.show(parent, e.getX(), e.getY());
}
});
	}

}
