import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

class AddMedEmp extends JFrame{
	
	Container c;
	JLabel labTitle, labName, labAddress, labPhone, labEmail;
	JTextField txtName, txtAddress, txtPhone, txtEmail;
	JButton btnAdd, btnBack;

	AddMedEmp() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Add Employee");
		labName = new JLabel("Name");
		txtName = new JTextField(20);
		labAddress = new JLabel("Address");
		txtAddress = new JTextField(20);
		labPhone = new JLabel("Phone");
		txtPhone = new JTextField(10);
		labEmail = new JLabel("Email");
		txtEmail = new JTextField(20);
		btnAdd = new JButton("Add Employee");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);
		labTitle.setFont(f);
		labName.setFont(f);
		txtName.setFont(f);
		labAddress.setFont(f);
		txtAddress.setFont(f);
		labPhone.setFont(f);
		txtPhone.setFont(f);
		labEmail.setFont(f);
		txtEmail.setFont(f);
		btnAdd.setFont(f);
		btnBack.setFont(f);
	
		ActionListener a1 = (ae) -> {
			try {
				String mail = ""		// Enter the Gmail Id through which you want to send the Gmail ==> eg: abc@gmail.com
				String name = txtName.getText();
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				String username = username(name);
				String password = password(name);

				if(name.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Name");
					txtName.setText("");
				}
				else if(!name.trim().matches("^[a-zA-Z]*$")) {
					JOptionPane.showMessageDialog(c, "Name Should Only Contain Characters");
				}
				else if(address.trim().length() == 0) {
					txtAddress.setText("");
					JOptionPane.showMessageDialog(c, "Enter Address");
				}
				else if(phone.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Phone Number");
					txtPhone.setText("");
				}
				else if(!phone.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Phone Should only Contain Digits");
				}
				else if(phone.trim().length() > 10 || phone.trim().length() < 10) {
					JOptionPane.showMessageDialog(c, "Phone Should be 10 Digits");
				}
				else if(email.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Email");
					txtEmail.setText("");
				}
				else if(!email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
					JOptionPane.showMessageDialog(c, "Email format abc@gmail.com");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "insert into MedicalEmployee values(?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(sql);

					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phone);
					pst.setString(4, email);
					pst.setString(5, username);
					pst.setString(6, password);

					try {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(c, name + " Added.");
						txtName.setText("");
						txtAddress.setText("");
						txtPhone.setText("");
						txtEmail.setText("");			
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e); 
					}
					
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
						String txt = "Hello " + name + ", \n" + "You have been appointed as an Medical Employee in HMS." + "\n Username: " + username + "\n Password: " + password;
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


		labTitle.setBounds(150, 20, 300, 40);
		labName.setBounds(30, 110, 250, 40);
		txtName.setBounds(220, 110, 200, 40);
		labAddress.setBounds(30, 170, 250, 40);
		txtAddress.setBounds(220, 170, 200, 40);
		labPhone.setBounds(30, 230, 250, 40);
		txtPhone.setBounds(220, 230, 200, 40);
		labEmail.setBounds(30, 290, 200, 40);
		txtEmail.setBounds(220, 290, 200, 40);
		btnAdd.setBounds(30, 380, 200, 40);
		btnBack.setBounds(250, 380, 200, 40);

		c.add(labTitle);
		c.add(labName);
		c.add(txtName);
		c.add(labAddress);
		c.add(txtAddress);
		c.add(labPhone);
		c.add(txtPhone);
		c.add(labEmail);
		c.add(txtEmail);
		c.add(btnAdd);
		c.add(btnBack);

		setTitle("Add Medical Employee");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static String username(String name) {
		int n1 = (int)(Math.random()*10);
		int n2 = (int)(Math.random()*10);
		int n3 = (int)(Math.random()*10);
		int n4 = (int)(Math.random()*10);
		String user = name + "" + n1 + "" + n2 + "" + n3;
		return user;
	}

	public static String password(String name) {
		int n1 = (int)(Math.random()*10);
		int n2 = (int)(Math.random()*10);
		String pass = name + "" + n1 + "" + n2;		
		return pass;
	}
}	
