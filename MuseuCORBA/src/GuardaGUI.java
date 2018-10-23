import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.omg.CosNaming.*; 
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import GuardaIDL.*;
import PortaoIDL.*;
import SinoIDL.*; 

public class GuardaGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	GuardaIDL.Guarda guarda;
	PortaoIDL.Portao portao;
	SinoIDL.Sino sino;
	private boolean portaoOn = false;
	private boolean guardaOn = false;
	private boolean sinoOn = false;
	private int numVisitantes = 0;
	private boolean isDia = true;
	private JLabel lblNumVisitantes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuardaGUI frame = new GuardaGUI();
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
	public GuardaGUI() {
		super("Guarda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGuarda = new JLabel("");
		lblGuarda.setIcon(new ImageIcon(GuardaGUI.class.getResource("/img/guarda.png")));
		lblGuarda.setBounds(0, 105, 167, 206);
		contentPane.add(lblGuarda);
		
		JLabel lblNome = new JLabel("NATIONAL MUSEUM");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(334, 50, 221, 25);
		contentPane.add(lblNome);
		
		JLabel lblCams = new JLabel("C\u00E2meras :");
		lblCams.setForeground(Color.RED);
		lblCams.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCams.setBounds(204, 80, 74, 14);
		contentPane.add(lblCams);
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setForeground(new Color(255, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setBounds(204, 191, 46, 14);
		contentPane.add(lblInfo);
		
		JLabel lblCam = new JLabel("New label");
		lblCam.setIcon(new ImageIcon(GuardaGUI.class.getResource("/img/camerasMonitor.png")));
		lblCam.setBounds(199, 105, 447, 62);
		contentPane.add(lblCam);
		
		JLabel lblVisitantes = new JLabel("N\u00FAmero de visitantes:");
		lblVisitantes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVisitantes.setBounds(204, 216, 167, 14);
		contentPane.add(lblVisitantes);
		
		lblNumVisitantes = new JLabel("0");
		lblNumVisitantes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumVisitantes.setBounds(366, 216, 46, 14);
		contentPane.add(lblNumVisitantes);
		
		JButton btnDia = new JButton("DIA");
		btnDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDia();
			}
		});
		btnDia.setFocusable(false);
		btnDia.setBackground(new Color(255, 255, 255));
		btnDia.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDia.setBounds(469, 256, 82, 23);
		contentPane.add(btnDia);
		
		JButton btnNoite = new JButton("NOITE");
		btnNoite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNoite();
			}
		});
		btnNoite.setFocusable(false);
		btnNoite.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNoite.setBackground(Color.WHITE);
		btnNoite.setBounds(559, 256, 82, 23);
		contentPane.add(btnNoite);
		
		JLabel lblMonitor = new JLabel("New label");
		lblMonitor.setIcon(new ImageIcon(GuardaGUI.class.getResource("/img/monitor.png")));
		lblMonitor.setBounds(173, 11, 500, 369);
		contentPane.add(lblMonitor);
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

	public void entrar() {
		numVisitantes++;
		lblNumVisitantes.setText(Integer.toString(numVisitantes));
	}
	
	public void sair() {
		numVisitantes--;
		lblNumVisitantes.setText(Integer.toString(numVisitantes));
		if(!isDia && numVisitantes == 0) {
			config();
			if (sinoOn) {
				sino.pararSino();
			}
		}
	}
	
	public void setNoite() {
		if (isDia) {
			config();
			if (sinoOn && numVisitantes > 0) {
				sino.tocarSino();
			}
			if (portaoOn) {
				portao.anoitecer();
			} 
			isDia = false;
		}
	}
	
	public void setDia() {
		if (!isDia) {
			config();
			if (sinoOn) {
				sino.pararSino();
			}
			if (portaoOn) {
				portao.amanhecer();
			} 
			isDia = true;
		}
	}

}
