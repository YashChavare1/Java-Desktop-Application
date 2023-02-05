import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DoctorLogin extends JFrame{

	Container c;
	JLabel labTitle, labUser, labPass;
	JTextField txtUser, txtPass;
	JButton btnLogin, btnBack;

	DoctorLogin() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Doctor Login");
		labUser = new JLabel("Username");
		txtUser = new JTextField(20);
		labPass = new JLabel("Password");
		txtPass = new JTextField(20);
		btnLogin = new JButton("Login");
		btnBack = new JButton("Back");

		Font f1 = new Font("Arial", Font.BOLD, 20);
		Font f2 = new Font("Arial", Font.BOLD, 30);

		labTitle.setFont(f2);
		labUser.setFont(f1);
		txtUser.setFont(f1);
		labPass.setFont(f1);
		txtPass.setFont(f1);
		btnLogin.setFont(f1);
		btnBack.setFont(f1);

		labTitle.setBounds(160, 30, 300, 50);
		labUser.setBounds(30, 120, 200, 30);
		txtUser.setBounds(200, 120, 200, 30);
		labPass.setBounds(30, 180, 200, 50);
		txtPass.setBounds(200, 190, 200, 30);
		btnLogin.setBounds(150, 300, 200, 50);
		btnBack.setBounds(150, 370, 200, 50);

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
					String sql = "select * from Doctor where username=? AND password=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, user);
					pst.setString(2, pass);
					try {
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							Doctor d = new Doctor();
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(c, "Invalid Credentials !");
						}
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
					}
				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ERROR  ==> " + e);
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
		add(txtUser);
		add(labPass);
		add(txtPass);
		add(btnLogin);
		add(btnBack);

		setTitle("Doctor Login");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}