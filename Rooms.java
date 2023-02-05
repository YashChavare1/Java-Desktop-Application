import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Rooms extends JFrame {
	Container c;
	JLabel labTitle, labRoomClass, labType, labCost, labQuantity;
	JTextField txtRoomClass, txtType, txtCost, txtQuantity;
	JButton btnAdd, btnBack; 

	Rooms() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Add Rooms");
		labRoomClass = new JLabel("Room Class");
		labType = new JLabel("Room Type");
		labCost = new JLabel("Room Cost");
		labQuantity = new JLabel("No Of Beds");
		txtRoomClass = new JTextField(20);
		txtType = new JTextField(20);
		txtCost = new JTextField(20);
		txtQuantity = new JTextField(20);
		btnAdd = new JButton("Add Rooms");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);

		labTitle.setFont(f);
		labRoomClass.setFont(f);
		labType.setFont(f);
		labCost.setFont(f);
		labQuantity.setFont(f);
		txtRoomClass.setFont(f);
		txtType.setFont(f);
		txtCost.setFont(f);
		txtQuantity.setFont(f);
		btnAdd.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(180, 10, 200, 40);
		labRoomClass.setBounds(30, 90, 200, 40);
		txtRoomClass.setBounds(230, 90, 200, 40);
		labType.setBounds(30, 150, 200, 40);
		txtType.setBounds(230, 150, 200, 40);
		labCost.setBounds(30, 210, 200, 40);
		txtCost.setBounds(230, 210, 200, 40);
		labQuantity.setBounds(30, 270, 200, 40);
		txtQuantity.setBounds(230, 270, 200, 40);
		btnAdd.setBounds(150, 350, 200, 40);
		btnBack.setBounds(150, 410, 200, 40);

		ActionListener a1 = (ae) -> {
			try {	
				String roomClass = txtRoomClass.getText();
				String type = txtType.getText();
				String cost = txtCost.getText();
				String quantity = txtQuantity.getText();

				if(roomClass.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Room Class");
					txtRoomClass.setText("");
				}
				else if(!roomClass.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Room-Class show be Digits Only");
				}				
				else if(type.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Room Type");
					txtType.setText("");
				}
				else if(!type.trim().matches("^[a-zA-z ]*$")) {
					JOptionPane.showMessageDialog(c, "Room Type Should be Character Only");
				}
				else if(cost.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Cost");
					txtCost.setText("");
				}
				else if(!cost.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Cost Should Only Be in Digits");
				}
				else if(quantity.trim().length() == 0) {
					JOptionPane.showMessageDialog(c, "Enter Room's Quantity");
					txtQuantity.setText("");
				}
				else if(!quantity.trim().matches("^[0-9]*$")) {
					JOptionPane.showMessageDialog(c, "Quantity Should be Digits Only");
				}
				else {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "insert into rooms values(?, ?, ?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(sql);

					pst.setString(1, type);
					pst.setString(2, cost);
					pst.setString(3, quantity);
					pst.setString(4, quantity);
					pst.setString(5, roomClass);

					try {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(c, "Room Added");
						con.close();

						txtType.setText("");
						txtCost.setText("");
						txtQuantity.setText("");
						txtRoomClass.setText("");
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
		btnAdd.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Hospital h = new Hospital();
			dispose();
		};
		btnBack.addActionListener(a2);

		c.add(labTitle);
		c.add(labRoomClass);
		c.add(labType);
		c.add(labCost);
		c.add(labQuantity);
		c.add(txtRoomClass);
		c.add(txtType);
		c.add(txtCost);
		c.add(txtQuantity);
		c.add(btnAdd);
		c.add(btnBack);

		setTitle("Add Rooms");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}