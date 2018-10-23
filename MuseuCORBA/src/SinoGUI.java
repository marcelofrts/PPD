import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import GuardaIDL.*;
import PortaoIDL.*;
import SinoIDL.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class SinoGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isNoite = false;
	private int numVisitantes = 0;
	private JLabel lblSino;
	GuardaIDL.Guarda guarda;
	PortaoIDL.Portao portao;
	SinoIDL.Sino sino;
	private boolean portaoOn = false;
	private boolean guardaOn = false;
	private boolean sinoOn = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinoGUI frame = new SinoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SinoGUI() {
		super("Sino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSino = new JLabel("");
		lblSino.setIcon(new ImageIcon(SinoGUI.class.getResource("/img/alarmeOff.png")));
		lblSino.setHorizontalAlignment(SwingConstants.CENTER);
		lblSino.setBounds(0, 0, 350, 350);
		contentPane.add(lblSino);
		
	}
	
	private void resolveGuarda() {
		try {			 
		 	ORB orb = ORB.init(new String[0], null);
			org.omg.CORBA.Object obj = null;
			obj = orb.resolve_initial_references("NameService");
		 	NamingContext naming = NamingContextHelper.narrow(obj);			
			NameComponent[] name = {new NameComponent("Guarda","TipoGuarda")};
			org.omg.CORBA.Object objGuarda = null;
			objGuarda = naming.resolve(name);
			guarda = GuardaHelper.narrow(objGuarda);
			guardaOn = true;
			System.out.println("Guarda resolvido.");
		    }catch (Exception e) {
		    	guardaOn = false;
		    	System.out.println("[Guarda]Erro sino: " + e) ;}
	}
	
	private void resolvePortao() {
		try {			 
		 	ORB orb = ORB.init(new String[0], null);
			org.omg.CORBA.Object obj = null;
			obj = orb.resolve_initial_references("NameService");
		 	NamingContext naming = NamingContextHelper.narrow(obj);			
			NameComponent[] name = {new NameComponent("Portao","TipoPortao")};
			org.omg.CORBA.Object objPortao = null;
			objPortao = naming.resolve(name);
			portao = PortaoHelper.narrow(objPortao);
			portaoOn = true;
			System.out.println("Portao resolvido.");
		    }catch (Exception e) {
		    	portaoOn = false;
		    	System.out.println("[Guarda]Erro portao: " + e) ;}
	}
	
	private void resolveSino() {
		try {			 
		 	ORB orb = ORB.init(new String[0], null);
			org.omg.CORBA.Object obj = null;
			obj = orb.resolve_initial_references("NameService");
		 	NamingContext naming = NamingContextHelper.narrow(obj);			
			NameComponent[] name = {new NameComponent("Sino","TipoSino")};
			org.omg.CORBA.Object objSino = null;
			objSino = naming.resolve(name);
			sino = SinoHelper.narrow(objSino);
			sinoOn = true;
			System.out.println("Sino resolvido.");
		    }catch (Exception e) {
		    	sinoOn = false;
		    	System.out.println("[Guarda]Erro sino: " + e) ;}
	}
	
	private void config() {
		if(!guardaOn) {
			resolveGuarda();
		}
		if(!portaoOn) {
			resolvePortao();
		}
		if(!sinoOn) {
			resolveSino();
		}
	}

	public void tocarSino() {
		lblSino.setIcon(new ImageIcon(SinoGUI.class.getResource("/img/alarmeOn.gif")));
	}
	
	public void pararSino() {
		lblSino.setIcon(new ImageIcon(SinoGUI.class.getResource("/img/alarmeOff.png")));
	}
	
}
