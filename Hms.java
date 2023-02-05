import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Hms extends JFrame {

	Container c;
	JLabel labTitle;
	JButton btnAdminLogin, btnDoctorLogin, btnMedicalLogin; 

	Hms() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Hospital Management System");
		btnAdminLogin = new JButton("Admin Login");
		btnDoctorLogin = new JButton("Doctor Login");
		btnMedicalLogin = new JButton("Medical Login");

		Font f = new Font("Arial", Font.BOLD, 20);
		
		labTitle.setFont(f);
		btnAdminLogin.setFont(f);
		btnDoctorLogin.setFont(f);
		btnMedicalLogin.setFont(f);
	
		labTitle.setBounds(100, 30, 300, 50);
		btnAdminLogin.setBounds(150, 130, 200, 50);
		btnDoctorLogin.setBounds(150, 210, 200, 50);
		btnMedicalLogin.setBounds(150, 290, 200, 50);

		ActionListener a1 = (ae) -> {
			AdminLogin al = new AdminLogin();
			dispose();
		};
		btnAdminLogin.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			MedicalLogin ml = new MedicalLogin();
			dispose();
		};
		btnMedicalLogin.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			DoctorLogin dl = new DoctorLogin();
			dispose();
		};
		btnDoctorLogin.addActionListener(a3);

		add(labTitle);
		add(btnAdminLogin);
		add(btnDoctorLogin);
		add(btnMedicalLogin);

		setTitle("HMS");	
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}	

	public static void main(String [] args) {
		Hms h = new Hms();
	}
}