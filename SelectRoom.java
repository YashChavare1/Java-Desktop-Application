import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

class SelectRoom extends JFrame {

	Container c;
	JLabel labTitle, labRoom;
	JTextArea ta;
	JTextField txtRoom;
	JButton btnSubmit, btnBack;

	SelectRoom(String id) {	
		c = getContentPane();
		c.setLayout(null);
		
		labTitle = new JLabel("Select Room");
		ta = new JTextArea(20, 30);
		labRoom = new JLabel("Select Room Class");
		txtRoom = new JTextField(20);
		btnSubmit = new JButton("Select Room");
		btnBack = new JButton("Back");
		ta.setEditable(false);
	

		Font f = new Font("Arial", Font.BOLD, 20);
			
		labTitle.setFont(f);
		ta.setFont(f);
		labRoom.setFont(f);
		txtRoom.setFont(f);
		btnSubmit.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(175, 30, 150, 40);
		ta.setBounds(10, 95, 450, 110);
		labRoom.setBounds(30, 220, 200, 40);
		txtRoom.setBounds(240, 220, 200, 40);
		btnSubmit.setBounds(150, 320, 200, 40);
		btnBack.setBounds(150, 380, 200, 40);

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/kc_project";
			Connection con = DriverManager.getConnection(url, "root", "abc456");
			String sql = "select * from rooms";
			PreparedStatement pst = con.prepareStatement(sql);
			StringBuffer data = new StringBuffer("");
			try {
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					do {
						String res = "  Class: " + rs.getString(5) + "  |  Type: " + rs.getString(1) + "  |  Cost: " + rs.getInt(2) + "\n";
						data.append(res);
					} while(rs.next());
					String showData = data.toString();
					ta.setText(showData);
				}
				else {
					JOptionPane.showMessageDialog(c, "No Data Found!");
				}
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
			catch(NumberFormatException er) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + er);
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
		}

		ActionListener a1 = (ae) -> {
			DateTimeFormatter y = DateTimeFormatter.ofPattern("yyyy");
			DateTimeFormatter mm = DateTimeFormatter.ofPattern("MM");	
			DateTimeFormatter d = DateTimeFormatter.ofPattern("dd");	
			DateTimeFormatter h = DateTimeFormatter.ofPattern("hh");	
			DateTimeFormatter m = DateTimeFormatter.ofPattern("mm");	
			LocalDateTime now = LocalDateTime.now();	
		
			String year = y.format(now);
			String month = mm.format(now);
			String days = d.format(now);
			String hour = h.format(now);
			String min = m.format(now);
	
			String roomClass = txtRoom.getText();

			if(roomClass.trim().length() == 0) {
				JOptionPane.showMessageDialog(c, "Select Room Class");
			}
			else if(!roomClass.trim().matches("^[0-9]*$")) {
				JOptionPane.showMessageDialog(c, "Enter Digits Only");
			}
			else {
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/kc_project";
					Connection con = DriverManager.getConnection(url, "root", "abc456");
					String sql = "insert into admitPatient values(?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(sql);
					StringBuffer cost = new StringBuffer("");

					try {
						String query = "select * from rooms WHERE roomClass=?";
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1, roomClass);
						ResultSet rst = ps.executeQuery();
						if(rst.next()) {
							cost.append(rst.getInt(2));
						}
						else {
							JOptionPane.showMessageDialog(c, "Result Not Found");
						}

					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
					}

					String rCost = cost.toString();

					if(!(rCost.length() == 0)) {
						pst.setString(1, id);
						pst.setString(2, roomClass);
						pst.setString(3, year);
						pst.setString(4, month);
						pst.setString(5, days);
						pst.setString(6, hour);
						pst.setString(7, min);
						pst.setString(8, rCost);
				
						try {
							pst.executeUpdate();
							JOptionPane.showMessageDialog(c, "Room Allotted");
							txtRoom.setText("");
							AdmitPatient ap = new AdmitPatient();
							dispose();
						}
						catch(SQLException e) {
							JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
						}	
					}
					else {
						JOptionPane.showMessageDialog(c, "Does Not Get Cost | Please Contact the Coder");
					}
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
				}
			}
		};
		btnSubmit.addActionListener(a1);
		
		ActionListener a2 = (ae) -> {
			AdmitPatient ap = new AdmitPatient();
			dispose();
		};
		btnBack.addActionListener(a2);

		c.add(labTitle);
		c.add(ta);
		c.add(labRoom);
		c.add(txtRoom);
		c.add(btnSubmit);
		c.add(btnBack);

		setTitle("Select Rooms");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

