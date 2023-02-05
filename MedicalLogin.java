import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MedicalLogin extends JFrame {

	Container c;
	JLabel labTitle, labUser, labPass;
	JTextField txtUser, txtPass;
	JButton btnLogin, btnBack;

	MedicalLogin() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Medical Login");
		labUser = new JLabel("Username");
		labPass = new JLabel("Password");
		txtUser = new JTextField(20);
		txtPass = new JTextField(20);
		btnLogin = new JButton("Login");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);

		labTitle.setFont(f);
		labUser.setFont(f);
		labPass.setFont(f);
		txtUser.setFont(f);
		txtPass.setFont(f);
		btnLogin.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(150, 30, 300, 30);
		labUser.setBounds(30, 90, 200, 50);
		txtUser.setBounds(210, 100, 200, 30);
		labPass.setBounds(30, 160, 200, 50);
		txtPass.setBounds(210, 170, 200, 30);
		btnLogin.setBounds(150, 250, 200, 50);
		btnBack.setBounds(150, 330, 200, 50);

		ActionListener a1 = (ae) -> {
			try {
				String user = txtUser.getText();
				String pass = txtPass.getText();

				if(user.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Username");
				}
				else if(pass.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Password");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "select * from MedicalEmployee where username=? AND password=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, user);
					pst.setString(2, pass);
					try {
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							Medical m = new Medical();
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(c, "Invalid Credentail !");
						}
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
					}
				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
		};
		btnLogin.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Hms h = new Hms();
			dispose();
		};
		btnBack.addActionListener(a2);

		add(labTitle);
		add(labUser);
		add(labPass);
		add(txtUser);
		add(txtPass);
		add(btnLogin);
		add(btnBack);

		setTitle("Medical Login");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}