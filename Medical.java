import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;

class Medical extends JFrame {

	Container c;
	JLabel labTitle;
	JTextField txtId;
	JTextArea taPres;
	JButton btnId, btnBack;

	Medical() {
		c = getContentPane();
		c.setLayout(null);
	
		labTitle = new JLabel("Medical Panel");
		txtId = new JTextField(20);
		taPres = new JTextArea(20, 15);
		btnId = new JButton("Find Patient");
		btnBack = new JButton("Back");		
		taPres.setEditable(false);
		taPres.setLineWrap(true);		
		taPres.setWrapStyleWord(true);

		Border bd = BorderFactory.createLineBorder(Color.BLACK, 3);
		Font f = new Font("Arial", Font.BOLD, 20);
			
		taPres.setBorder(bd);

		labTitle.setFont(f);
		txtId.setFont(f);
		taPres.setFont(f);
		btnId.setFont(f);
		btnBack.setFont(f);
		
		labTitle.setBounds(170, 10, 200, 50);
		txtId.setBounds(100, 80, 300, 50);
		btnId.setBounds(20, 160, 200, 40);
		btnBack.setBounds(240, 160, 200, 40);
		taPres.setBounds(50, 280, 400, 170);

		ActionListener a1 = (ae) -> {
			String id = txtId.getText();
			if(id.trim().length() == 0) {
				JOptionPane.showMessageDialog(c, "Enter Id");
				txtId.setText("");
			}
			else if(!id.trim().matches("^[0-9]*$")) {
				JOptionPane.showMessageDialog(c, "Only 4 DIGITS Allowed");
				txtId.setText("");
			}
			else {
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "Select * from Patient WHERE patientId=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, id);
					try {
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							String res = "  Id: " + rs.getInt(1) + "\n" + "  Name: " + rs.getString(2) + "\n" + "  Prescription: " + rs.getString(8);
							taPres.setText(res);
						}
						else {
							JOptionPane.showMessageDialog(c, "No Patient Found");
							txtId.setText("");
							taPres.setText("");
						}
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
					}
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
				}
			}
			
		};
		btnId.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Hms h = new Hms();
			dispose();
		};
		btnBack.addActionListener(a2);

		c.add(labTitle);
		c.add(txtId);
		c.add(btnId);
		c.add(taPres);
		c.add(btnBack);

		setTitle("Medical Panel");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}