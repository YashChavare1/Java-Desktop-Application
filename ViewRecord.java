import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewRecord extends JFrame {

	Container c;
	JLabel labTitle;
	JTextArea ta;
	JButton btnBack;

	ViewRecord(String tableName, String text) {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("View " + tableName);
		ta = new JTextArea();
		btnBack = new JButton("Back");

		ta.setEditable(false);
		ta.setWrapStyleWord(true);
		labTitle.setHorizontalAlignment(SwingConstants.CENTER);

		Font f = new Font("Arial", Font.BOLD, 20);
		
		labTitle.setFont(f);
		ta.setFont(f);
		btnBack.setFont(f);

		labTitle.setBounds(100, 10, 300, 40);
		ta.setBounds(50, 70, 400, 310);
		btnBack.setBounds(150, 410, 200, 40);
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/kc_project";
			Connection con = DriverManager.getConnection(url, "root", "abc456");
			String sql = "select * from " + tableName;
			PreparedStatement pst = con.prepareStatement(sql);
			StringBuffer str = new StringBuffer("");
			try {
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					if(tableName.equals("doctor")) {
						String data = "Username: " + rs.getString(8) + " | Name: " + rs.getString(1) + " | Specialization: " + rs.getString(3) + "\n";
						str.append(data);
					}
					else if(tableName.equals("patient")) {
						String data = "Id: " + rs.getString(1) + " | Name: " + rs.getString(2) + " | Phone: " + rs.getString(4) + "\n";
						str.append(data);
					}
					else if(tableName.equals("admitpatient")) {
						String data = "Id: " + rs.getString(1) + " | RoomClass: " + rs.getString(2) + " | Cost: " + rs.getString(8) + "\n";
						str.append(data);
					}
					else if(tableName.equals("medicalemployee")) {
						String data = "Username: " + rs.getString(5) + " | Name: " + rs.getString(1) + " | Phone: " + rs.getString(3) + "\n";
						str.append(data);
					}
					else {
						String data = "Type: " + rs.getString(1) + " | Cost: " + rs.getString(2) + " | Available Rooms: " + rs.getString(4) + "\n";
						str.append(data);					
					}
				}
				ta.setText(str.toString());
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(c, "ISSUE ==> " + e);
		}

		ActionListener a2 = (ae) -> {
			View v = new View();
			dispose();
		};
		btnBack.addActionListener(a2);

		c.add(labTitle);
		c.add(ta);
		c.add(btnBack);

		setTitle("View Record");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
}