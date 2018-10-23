import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MuseuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuseuGUI frame = new MuseuGUI();
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
	public MuseuGUI() {
		super("Museu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 634);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMuseu = new JLabel("");
		lblMuseu.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/museuFechadoDia.png")));
		lblMuseu.setBounds(0, 0, 512, 325);
		contentPane.add(lblMuseu);
		
		JLabel lblGuarda = new JLabel("");
		lblGuarda.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/guarda.png")));
		lblGuarda.setBounds(10, 336, 217, 206);
		contentPane.add(lblGuarda);
		
		JLabel lblAlarme = new JLabel("");
		lblAlarme.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarme.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/alarmeOff.png")));
		lblAlarme.setBounds(182, 324, 330, 260);
		contentPane.add(lblAlarme);
		
		JButton btnDia = new JButton("DIA");
		btnDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMuseu.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/museuAbertoDia.png")));
				lblAlarme.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/alarmeOff.png")));
			}
		});
		btnDia.setBounds(10, 553, 56, 31);
		contentPane.add(btnDia);
		
		JButton btnNoite = new JButton("NOITE");
		btnNoite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMuseu.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/museuAbertoNoite.png")));
				lblAlarme.setIcon(new ImageIcon(MuseuGUI.class.getResource("/img/alarmeOn.gif")));
			}
		});
		btnNoite.setBounds(68, 553, 85, 31);
		contentPane.add(btnNoite);
		
	}
}
