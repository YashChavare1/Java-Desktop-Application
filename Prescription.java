import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Prescription extends JFrame {

	Container c;
	JLabel labTitle, labId, labName, labAge, labPhone, labPreviousIssue, labEmail, labAddress, labPrescription, labIdRes, labNameRes, labAgeRes, 	labPhoneRes, labPreviousIssueRes, labEmailRes, labAddressRes;
	JTextField txtPrescription;
	JButton btnSubmit, btnBack;

	Prescription(String id) {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Prescription");
		labId = new JLabel("Id: ");
		labName = new JLabel("Name: ");
		labAge = new JLabel("Age: ");
		labPhone = new JLabel("Phone: ");
		labPreviousIssue = new JLabel("Previous Issue: ");
		labEmail = new JLabel("Email: ");
		labAddress = new JLabel("Address: ");
		labPrescription = new JLabel("Prescription: ");
		labIdRes = new JLabel("");
		labNameRes = new JLabel("");
		labAgeRes = new JLabel("");
		labPhoneRes = new JLabel("");
		labPreviousIssueRes = new JLabel("");
		labEmailRes = new JLabel("");
		labAddressRes = new JLabel("");
		txtPrescription = new JTextField(30);
		btnSubmit = new JButton("Submit");
		btnBack = new JButton("Back");
		
		Font f = new Font("Arial", Font.BOLD, 25);
		Font f2 = new Font("Arial", Font.PLAIN, 23);

		labTitle.setFont(f);
		labId.setFont(f);
		labName.setFont(f);
		labAge.setFont(f);
		labPhone.setFont(f);
		labPreviousIssue.setFont(f);
		labEmail.setFont(f);
		labAddress.setFont(f);
		labPrescription.setFont(f);
		labIdRes.setFont(f2);
		labNameRes.setFont(f2);
		labAgeRes.setFont(f2);
		labPhoneRes.setFont(f2);
		labPreviousIssueRes.setFont(f2);
		labEmailRes.setFont(f2);
		labAddressRes.setFont(f2);
		txtPrescription.setFont(f2);
		btnSubmit.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(200, 20, 150, 40);
		labId.setBounds(30, 80, 200, 40);
		labIdRes.setBounds(240, 80, 400, 40);
		labName.setBounds(30, 130, 200, 40);
		labNameRes.setBounds(240, 130, 400, 40);		
		labAge.setBounds(30, 180, 200, 40);
		labAgeRes.setBounds(240, 180, 400, 40);
		labPhone.setBounds(30, 230, 200, 40);
		labPhoneRes.setBounds(240, 230, 400, 40);
		labPreviousIssue.setBounds(30, 280, 200, 40);
		labPreviousIssueRes.setBounds(240, 280, 400, 40);
		labEmail.setBounds(30, 330, 200, 40);
		labEmailRes.setBounds(240, 330, 400, 40);
		labAddress.setBounds(30, 380, 400, 40);
		labAddressRes.setBounds(240, 380, 400, 40);
		labPrescription.setBounds(30, 430, 300, 40);
		txtPrescription.setBounds(200, 430, 350, 40);
		btnSubmit.setBounds(200, 530, 200, 40);
		btnBack.setBounds(200, 590, 200, 40);

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
					labIdRes.setText(rs.getString(1));
					labNameRes.setText(rs.getString(2));
					labAgeRes.setText(rs.getString(3));
					labPhoneRes.setText(rs.getString(4));
					labPreviousIssueRes.setText(rs.getString(5));
					labEmailRes.setText(rs.getString(6));
					labAddressRes.setText(rs.getString(7));
					txtPrescription.setText(rs.getString(8));
				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
			con.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
		}

		ActionListener a1 = (ae) -> {
			try {
				String pres = txtPrescription.getText();
				if(pres.trim().length() > 0) {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String update = "update Patient SET prescription=? WHERE patientId=?";
					PreparedStatement pst = con.prepareStatement(update);
					pst.setString(1, pres);
					pst.setString(2, id);
					try {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(c, "Done");
						Doctor d = new Doctor();
						dispose();
						con.close();
					}
					catch(SQLException e) {	
						JOptionPane.showMessageDialog(c, "ISSUE1 ==> " + e);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(c, "Enter Prescription.");
				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
		};
		btnSubmit.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Doctor d = new Doctor();
			dispose();
		};
		btnBack.addActionListener(a2);

		c.add(labTitle);
		c.add(labId);
		c.add(labName);
		c.add(labAge);
		c.add(labPhone);
		c.add(labPreviousIssue);
		c.add(labEmail);
		c.add(labAddress);
		c.add(labIdRes);
		c.add(labNameRes);
		c.add(labAgeRes);
		c.add(labPhoneRes);
		c.add(labPreviousIssueRes);
		c.add(labEmailRes);
		c.add(labAddressRes);
		c.add(labPrescription);
		c.add(txtPrescription);
		c.add(btnSubmit);
		c.add(btnBack);

		setTitle("Prescription");
		setSize(600, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}