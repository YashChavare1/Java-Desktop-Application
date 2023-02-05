import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Doctor extends JFrame{

	Container c;
	JLabel labTitle, labId;
	JTextField txtId;
	JButton btnId, btnBack;

	Doctor() {
		c = getContentPane();
		c.setLayout(null);
			
		labTitle = new JLabel("Doctor Panel");
		labId = new JLabel("Patient Id");
		txtId = new JTextField(20);
		btnId = new JButton("Find Patient");
		btnBack = new JButton("Back");

		Font f1 = new Font("Arial", Font.BOLD, 35);		
		Font f2 = new Font("Arial", Font.BOLD, 25);

		labTitle.setFont(f1);
		labId.setFont(f2);
		txtId.setFont(f2);
		btnId.setFont(f2);
		btnBack.setFont(f2);
			
		labTitle.setBounds(130, 30, 400, 50);
		labId.setBounds(190, 110, 200, 50);
		txtId.setBounds(150, 200, 200, 35);
		btnId.setBounds(150, 280, 200, 35);
		btnBack.setBounds(150, 360, 200, 35);
		
		ActionListener a1 = (ae) -> {
			String id = txtId.getText();
			if(id.trim().length() == 0) {
				JOptionPane.showMessageDialog(c, "Enter Patient Id");
				txtId.setText("");
			}
			else if(!id.trim().matches("^[0-9]*$")) {
				JOptionPane.showMessageDialog(c, "Only Digits Allowed");
				txtId.setText("");
			}
			else if(id.trim().length() > 4) {
				JOptionPane.showMessageDialog(c, "Id Should Be 4 Digits");	
				txtId.setText("");
			}
			else {	
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "select * from Patient where patientId=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, id);
					try {
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							Prescription p = new Prescription(id);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(c, "No Id Found.");
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
		c.add(labId);
		c.add(txtId);
		c.add(btnId);
		c.add(btnBack);	
		
		setTitle("Doctor Panel");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String [] args) {
		Doctor d = new Doctor();
	}
}