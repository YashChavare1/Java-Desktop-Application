import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

class AddPatient extends JFrame {
	
	Container c;

	JLabel labTitle , labName, labAge, labAddress, labPhone, labEmail, labPreIssue;
	JTextField txtName, txtAge, txtAddress, txtPhone, txtEmail, txtPreIssue;
	JButton btnAdd, btnBack;

	AddPatient() {
	
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Add Patient");
		labName = new JLabel("Name");
		txtName = new JTextField(20);
		labAge = new JLabel("Age");
		txtAge = new JTextField(3);
		labAddress = new JLabel("Address");
		txtAddress = new JTextField(50);
		labPhone = new JLabel("Phone");
		txtPhone = new JTextField(10);
		labPreIssue = new JLabel("Previous Issue");
		txtPreIssue = new JTextField(50);
		labEmail = new JLabel("Email");
		txtEmail = new JTextField(30);
		btnAdd = new JButton("Add Patient");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);
		Font fTitle = new Font("Arial", Font.BOLD, 35);

		labTitle.setFont(fTitle);
		labName.setFont(f);
		txtName.setFont(f);
		labAge.setFont(f);
		txtAge.setFont(f);
		labAddress.setFont(f);
		txtAddress.setFont(f);
		labPhone.setFont(f);
		txtPhone.setFont(f);
		labPreIssue.setFont(f);
		txtPreIssue.setFont(f);
		labEmail.setFont(f);
		txtEmail.setFont(f);
		btnAdd.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(160, 30, 300, 40);
		labName.setBounds(30, 120, 180, 40);
		txtName.setBounds(220, 120, 250, 40);
		labAge.setBounds(30, 180, 180, 40);
		txtAge.setBounds(220, 180, 250, 40);
		labAddress.setBounds(30, 240, 180, 40);
		txtAddress.setBounds(220, 240, 250, 40);
		labPhone.setBounds(30, 300, 180, 40);
		txtPhone.setBounds(220, 300, 250, 40);
		labPreIssue.setBounds(30, 360, 180, 40);
		txtPreIssue.setBounds(220, 360, 250, 40);
		labEmail.setBounds(30, 420, 180, 40);
		txtEmail.setBounds(220, 420, 250, 40);
		btnAdd.setBounds(70, 500, 200, 40);
		btnBack.setBounds(300, 500, 200, 40);

		ActionListener a1 = (ae) -> {
			try {
				String mail = "";		// Enter the Gmail Id through which you want to send the Gmail ==> eg: abc@gmail.com
				String id = random();
				String name = txtName.getText();
				String age = txtAge.getText();
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				String preIssue = txtPreIssue.getText();
				String email = txtEmail.getText();			

				if(name.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Name");
				}
				else if(!name.trim().matches("^[a-zA-Z ]*$")) {
					JOptionPane.showMessageDialog(c, "Name Contains Only Characters");
				}
				else if(age.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Valid Age");
				}
				else if(!age.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Age Contains only Digits");
				}
				else if(address.trim().length() == 0){
					JOptionPane.showMessageDialog(c, "Enter Address");
				}		
				else if(phone.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Phone");
				}								
				else if(phone.trim().length() < 10 || phone.trim().length() > 10){
					JOptionPane.showMessageDialog(c, "Phone Shoud Be 10 Digits");
				}
				else if(!phone.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Phone Should Only Contian Digits");
				}
				else if(preIssue.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Previous Issue");
				}
				else if(email.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Email");
				}
				else if(!email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
					JOptionPane.showMessageDialog(c, "Email Format abc@gmail.com");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "insert into Patient values(?, ?, ?, ?, ?, ?, ?, NULL)";
					PreparedStatement pst = con.prepareStatement(sql);
													
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, age);
					pst.setString(4, phone);
					pst.setString(5, preIssue);
					pst.setString(6, email);
					pst.setString(7, address);

					try {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(c, name + " Added");
						con.close();
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
					}

					txtName.setText("");
					txtAge.setText("");
					txtAddress.setText("");
					txtPhone.setText("");
					txtPreIssue.setText("");
					txtEmail.setText("");

					// Sending mail to the user
					// mail kahan se jayega
					Properties p = System.getProperties();
					p.put("mail.smtp.host", "smtp.gmail.com");
					p.put("mail.smtp.port", 587);
					p.put("mail.smtp.auth", true);
					p.put("mail.smtp.starttls.enable", true);
						
					// appka un and pw ka authentication
					Session ms = Session.getInstance(p, new Authenticator() {
						public PasswordAuthentication getPasswordAuthentication() {
							String un = "yashchavare1@gmail.com";
							String pw = "ldujsciszsxxgaok";
							return new PasswordAuthentication(un, pw);
						}
					});
					
					try {
						// mail ko draft karke bhejo
						MimeMessage msg = new MimeMessage(ms);
						String subject = "Message from => " + "Hospital Management System";
						msg.setSubject(subject);
						String txt = "Hello " + name + "; \n" + "Your Registration in HMS is Done. \n" + "Patient Id: " + id;
						msg.setText(txt);
						msg.setFrom(new InternetAddress(mail));
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
						Transport.send(msg);
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(c, "Email Issue => " + e);
					}
					con.close();	


				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
		};
		btnAdd.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Hospital h = new Hospital();
			dispose();
		};
		btnBack.addActionListener(a2);
	
		c.add(labTitle);
		c.add(labName);
		c.add(txtName);
		c.add(labAge);
		c.add(txtAge);
		c.add(labAddress);
		c.add(txtAddress);
		c.add(labPhone);
		c.add(txtPhone);
		c.add(labPreIssue);
		c.add(txtPreIssue);
		c.add(labEmail);
		c.add(txtEmail);
		c.add(btnAdd);
		c.add(btnBack);

		setTitle("Add Patient");
		setSize(600, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static String random() {
		int n1 = (int)(Math.random()*10);
		int n2 = (int)(Math.random()*10);
		int n3 = (int)(Math.random()*10);
		int n4 = (int)(Math.random()*10);
		String ran = n1 + "" + n2 + "" + n3 + "" + n4;
		return ran;
	}
}
