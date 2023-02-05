import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

class AddDoctor extends JFrame {
	Container c;
	JLabel labTitle, labName, labPhone, labSpecialization, labDegree, labManageableDisease, labEmail; 
	JTextField txtName, txtPhone, txtSpecialization, txtDegree, txtManageableDisease, txtEmail;
	JButton btnAdd, btnBack;

	AddDoctor() {
		c = getContentPane();
		c.setLayout(null);
		
		labTitle = new JLabel("Add Doctor");
		labName = new JLabel("Name");
		txtName = new JTextField(20);
		labPhone = new JLabel("Phone");
		txtPhone = new JTextField(10);
		labSpecialization = new JLabel("Specialization");
		txtSpecialization = new JTextField(40);
		labDegree = new JLabel("Degree");
		txtDegree = new JTextField(20);
		labManageableDisease = new JLabel("Manageable Disease");
		txtManageableDisease = new JTextField(40);
		labEmail = new JLabel("Email");
		txtEmail = new JTextField(30);	
		btnAdd = new JButton("Add Doctor");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);
		Font fTitle = new Font("Arial", Font.BOLD, 35);
		labTitle.setFont(fTitle);
		labName.setFont(f);
		txtName.setFont(f);
		labPhone.setFont(f);
		txtPhone.setFont(f);
		labSpecialization.setFont(f);
		txtSpecialization.setFont(f);
		labDegree.setFont(f);
		txtDegree.setFont(f);
		labManageableDisease.setFont(f);
		txtManageableDisease.setFont(f);
		labEmail.setFont(f);
		txtEmail.setFont(f);
		btnAdd.setFont(f);
		btnBack.setFont(f);

		ActionListener a1 = (ae) -> {
			try {
				String name = txtName.getText();
				String phone = txtPhone.getText();
				String spec = txtSpecialization.getText();
				String degree = txtDegree.getText();
				String manageableDisease = txtManageableDisease.getText();
				String email = txtEmail.getText();
				String pass = random();
				String user = username(name);

				if(name.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Name");
				}
				else if(!name.trim().matches("^[a-zA-Z]*$")){
					JOptionPane.showMessageDialog(c, "Name Should Not Contain Numbers/ Special Character's");
				}
				else if(phone.trim().length() < 10 || phone.trim().length() > 10) {
					JOptionPane.showMessageDialog(c, "Phone Should be 10 Digit");
				}
				else if(!phone.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Phone can only contain Digits");					
				}
				else if(spec.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Specialization");						
				}
				else if(!spec.trim().matches("^[a-zA-z]*$")) {
					JOptionPane.showMessageDialog(c, "Specialization can contain Only Character");					
				}
				else if(degree.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Degree");				
				}
				else if(!degree.trim().matches("^[a-zA-z]*$")) {
					JOptionPane.showMessageDialog(c, "Degree can contain Only Character");					
				}
				else if(manageableDisease.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Manageable Disease");
				}
				else if(!manageableDisease.trim().matches("^[a-zA-Z]*$")) {
					JOptionPane.showMessageDialog(c, "Digits & Special Character Not Allowed");					
				}
				else if(email.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Email");					
				}
				else if(!email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
					JOptionPane.showMessageDialog(c, "Enter Valid Email");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "insert into Doctor values(?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(sql);

					pst.setString(1, name);
					pst.setString(2, phone);
					pst.setString(3, spec);
					pst.setString(4, degree);
					pst.setString(5, manageableDisease);
					pst.setString(6, email);
					pst.setString(7, pass);
					pst.setString(8, user);
					try {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(c, name + " Added");
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " +  e);
					}		

					txtName.setText("");
					txtPhone.setText("");
					txtSpecialization.setText("");
					txtDegree.setText("");
					txtManageableDisease.setText("");
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
						String txt = "Hello " + name + "\n" + "Your have been Registered in HMS as an Doctor \n" + "USERNAME: " + user + "\n PASSWORD: " + pass;
						msg.setText(txt);
						msg.setFrom(new InternetAddress("yashchavare1@gmail.com"));
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

		labTitle.setBounds(160, 30, 300, 40);
		labName.setBounds(30, 120, 200, 40);
		txtName.setBounds(270, 120, 250, 40);
		labPhone.setBounds(30, 180, 200, 40);
		txtPhone.setBounds(270, 180, 250, 40);
		labSpecialization.setBounds(30, 240, 200, 40);
		txtSpecialization.setBounds(270, 240, 250, 40);
		labDegree.setBounds(30, 300, 200, 40);
		txtDegree.setBounds(270, 300, 250, 40);
		labManageableDisease.setBounds(30, 360, 200, 40);
		txtManageableDisease.setBounds(270, 360, 250, 40);
		labEmail.setBounds(30, 420, 200, 40);
		txtEmail.setBounds(270, 420, 250, 40);
		btnAdd.setBounds(60, 500, 200, 40);
		btnBack.setBounds(320, 500, 200, 40);

		c.add(labTitle);
		c.add(labName);
		c.add(txtName);
		c.add(labPhone);
		c.add(txtPhone);
		c.add(labSpecialization);
		c.add(txtSpecialization);
		c.add(labDegree);
		c.add(txtDegree);
		c.add(labManageableDisease);
		c.add(txtManageableDisease);
		c.add(labEmail);
		c.add(txtEmail);
		c.add(btnAdd);
		c.add(btnBack);

		setTitle("Add Doctor");
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
		int n5 = (int)(Math.random()*10);
		String ran = n1 + "" + n2 + "" + n3 + "" + n4 + "" + n5;
		return ran;
	}
		
	public static String username(String name) {
		int n1 = (int)(Math.random()*10);
		int n2 = (int)(Math.random()*10);
		int n3 = (int)(Math.random()*10);
		String users = name + "" + n1 + "" + n2 + "" + n3;
		return users;		
	}
	public static void main(String [] args) {
		AddDoctor ad = new AddDoctor();
	}
}