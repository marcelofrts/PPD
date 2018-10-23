import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import GuardaIDL.*;
import PortaoIDL.*;
import SinoIDL.*;

public class PortaoGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int numVisitantes = 0;
	private boolean primeiroVisitante = true;
	private boolean isDia = true;
	GuardaIDL.Guarda guarda;
	PortaoIDL.Portao portao;
	SinoIDL.Sino sino;
	private boolean portaoOn = false;
	private boolean guardaOn = false;
	private boolean sinoOn = false;
	private JLabel lblMuseu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PortaoGUI frame = new PortaoGUI();
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
	public PortaoGUI() {
		super("Portão");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 417);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMuseu = new JLabel("");
		lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuFechadoDia.png")));
		lblMuseu.setBounds(0, 0, 512, 325);
		contentPane.add(lblMuseu);
		
		JLabel lblVisitantes = new JLabel("Visitantes :");
		lblVisitantes.setForeground(new Color(255, 255, 255));
		lblVisitantes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVisitantes.setBounds(110, 336, 123, 27);
		contentPane.add(lblVisitantes);
		
		JLabel lblNumVisitantes = new JLabel("0");
		lblNumVisitantes.setForeground(new Color(255, 255, 255));
		lblNumVisitantes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNumVisitantes.setBounds(228, 336, 37, 27);
		contentPane.add(lblNumVisitantes);
		
		JButton btnEntrar = new JButton("+");
		btnEntrar.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		btnEntrar.setFocusable(false);
		btnEntrar.setBackground(new Color(0, 128, 0));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isDia) {
					numVisitantes++;
					if (primeiroVisitante) {
						primeiroVisitante = false;
						lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuAbertoDia.png")));
					}
					lblNumVisitantes.setText(Integer.toString(numVisitantes));
					entrarVisitante();
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEntrar.setBounds(302, 336, 49, 27);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("-");
		btnSair.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		btnSair.setFocusable(false);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numVisitantes > 0) {
					numVisitantes--;
					lblNumVisitantes.setText(Integer.toString(numVisitantes));
					sairVisitante();
				}
			}
		});
		btnSair.setBackground(new Color(0, 128, 0));
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSair.setBounds(352, 336, 49, 27);
		contentPane.add(btnSair);
				
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
	
	//Quando alguém entra pelo portão, o guarda é avisado
	private void entrarVisitante() {
		config();
		if(guardaOn) {
			guarda.entrarVisitante();
		}
	}
	
	//Quando alguém sai pelo portão, o guarda é avisado
	private void sairVisitante() {
		config();
		if(!isDia && numVisitantes == 0) {
			lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuFechadoNoite.png")));
		}
		if(guardaOn) {
			guarda.sairVisitante();
		}
	}
	
	public void isDia() {
		isDia = true;
		lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuFechadoDia.png")));
		if(numVisitantes > 0) {
			lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuAbertoDia.png")));
		}
		else {
			primeiroVisitante = true;
		}
	}
	
	public void isNoite() {
		isDia = false;
		lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuAbertoNoite.png")));
		if(numVisitantes == 0) {
			lblMuseu.setIcon(new ImageIcon(PortaoGUI.class.getResource("/img/museuFechadoNoite.png")));
		}
	}
	
}
