import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class View extends JFrame {
	Container c;
	JButton btnViewDoctor, btnViewPatient, btnViewAdmitPatient, btnViewMedEmp, btnViewRooms, btnBack;
	
	View() {
		c = getContentPane();
		c.setLayout(null);

		btnViewDoctor = new JButton("View Doctor");
		btnViewPatient = new JButton("View Patient");
		btnViewAdmitPatient = new JButton("View Admit Patient");
		btnViewMedEmp = new JButton("View Medical Employee");
		btnViewRooms = new JButton("View Rooms");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);	
		btnViewDoctor.setFont(f);
		btnViewPatient.setFont(f);
		btnViewAdmitPatient.setFont(f);
		btnViewMedEmp.setFont(f);
		btnViewRooms.setFont(f);
		btnBack.setFont(f);

		btnViewDoctor.setBounds(100, 30, 300, 40);
		btnViewPatient.setBounds(100, 90, 300, 40);
		btnViewAdmitPatient.setBounds(100, 150, 300, 40);
		btnViewMedEmp.setBounds(100, 210, 300, 40);
		btnViewRooms.setBounds(100, 270, 300, 40);
		btnBack.setBounds(100, 360, 300, 40);

		ActionListener a1 = (ae) -> {
			ViewRecord vr = new ViewRecord("doctor", "Doctor");
			dispose();
		};
		btnViewDoctor.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			ViewRecord vr = new ViewRecord("patient", "Patient");
			dispose();
		};
		btnViewPatient.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			ViewRecord vr = new ViewRecord("admitpatient", "Admit Patient");
			dispose();	
		};
		btnViewAdmitPatient.addActionListener(a3);

		ActionListener a4 = (ae) -> {
			ViewRecord vr = new ViewRecord("medicalemployee", "Medical Employee");
			dispose();
		};
		btnViewMedEmp.addActionListener(a4);

		ActionListener a5 = (ae) -> {
			ViewRecord vr = new ViewRecord("rooms", "Rooms");
			dispose();
		};
		btnViewRooms.addActionListener(a5);

		ActionListener a6 = (ae) -> {
			Hospital h = new Hospital();
			dispose();
		};
		btnBack.addActionListener(a6);

		c.add(btnViewDoctor);
		c.add(btnViewPatient);
		c.add(btnViewAdmitPatient);
		c.add(btnViewMedEmp);
		c.add(btnViewRooms);
		c.add(btnBack);

		setTitle("View Page");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
}