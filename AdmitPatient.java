import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AdmitPatient extends JFrame{

	Container c;
	JLabel labTitle, labId;
	JTextField txtId;
	JButton btnRoom, btnBack;

	AdmitPatient() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Admit Patient");
		labId = new JLabel("Patient Id");
		txtId = new JTextField(20);
		btnRoom = new JButton("Select Beds");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 25);

		labTitle.setFont(f);
		labId.setFont(f);
		txtId.setFont(f);
		btnRoom.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(150, 30, 200, 50);
		labId.setBounds(185, 120, 150, 40);
		txtId.setBounds(125, 170, 250, 40);
		btnRoom.setBounds(125, 280, 250, 40);
		btnBack.setBounds(125, 340, 250, 40);

		ActionListener a1 = (ae) -> {
			String id = txtId.getText();
			try {
				if(id.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Id");
					txtId.setText("");
				}
				else if(!id.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Id Only Contain Digits");
					txtId.setText("");
				}
				else if(!(id.trim().length() == 4)) {
					JOptionPane.showMessageDialog(c, "Only Digits Allowed");
					txtId.setText("");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "select * from Patient where PatientId=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, id);
					try {
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							String query = "select * from admitPatient WHERE id=?";
							PreparedStatement prep = con.prepareStatement(query);
							int pId = Integer.parseInt(id);
							prep.setInt(1, pId);		
							ResultSet rst = prep.executeQuery();	
							if(rst.next()) {
								JOptionPane.showMessageDialog(c, "Patient Id: " + id + " Already Admitted");
								txtId.setText("");
							}
							else {
								SelectRoom sr = new SelectRoom(id);
								dispose();
							}
						}	
						else {
							txtId.setText("");
							JOptionPane.showMessageDialog(c, id + " Patient Not Found!");
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
		btnRoom.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Hospital h = new Hospital();
			dispose();
		};
		btnBack.addActionListener(a2); 

		c.add(labTitle);
		c.add(labId);
		c.add(txtId);
		c.add(btnRoom);
		c.add(btnBack);

		setTitle("Admit Patient");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}