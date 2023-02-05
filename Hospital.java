import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Hospital extends JFrame {
	Container c;
	JButton btnAddDoctor, btnAddPatient, btnAdmitPatient, btnAddMedEmp, btnAddRooms, btnView, btnBack;

	Hospital() {
		c = getContentPane();
		c.setLayout(null);

		btnAddDoctor = new JButton("Add Doctor");
		btnAddPatient = new JButton("Add Patient");
		btnAdmitPatient = new JButton("Admit Patient");
		btnAddMedEmp = new JButton("Add Medical Employee");
		btnAddRooms = new JButton("Add Rooms");
		btnView = new JButton("View");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 20);
		btnAddDoctor.setFont(f);
		btnAddPatient.setFont(f);
		btnAdmitPatient.setFont(f);
		btnAddMedEmp.setFont(f);
		btnAddRooms.setFont(f);
		btnView.setFont(f);
		btnBack.setFont(f);
	
		btnAddDoctor.setBounds(100, 30, 300, 40);
		btnAddPatient.setBounds(100, 90, 300, 40);
		btnAddMedEmp.setBounds(100, 150, 300, 40);
		btnAddRooms.setBounds(100, 210, 300, 40);
		btnAdmitPatient.setBounds(100, 270, 300, 40);
		btnView.setBounds(100, 330, 300, 40);
		btnBack.setBounds(100, 390, 300, 40);

		c.add(btnAddDoctor);
		c.add(btnAddPatient);
		c.add(btnAdmitPatient);
		c.add(btnAddMedEmp);
		c.add(btnAddRooms);
		c.add(btnView);
		c.add(btnBack);

		ActionListener a1 = (ae) -> {
			AddDoctor ad = new AddDoctor();
			dispose();
		};
		btnAddDoctor.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			AddPatient ap = new AddPatient();
			dispose();
		};
		btnAddPatient.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			AddMedEmp ame = new AddMedEmp();
			dispose();
		};
		btnAddMedEmp.addActionListener(a3);

		ActionListener a4 = (ae) -> {
			Rooms r = new Rooms();
			dispose();
		};
		btnAddRooms.addActionListener(a4);

		ActionListener a5 = (ae) -> {
			Hms h = new Hms();
			dispose();
		};
		btnBack.addActionListener(a5);
	
		ActionListener a6 = (ae) -> {
			View v = new View();
			dispose();
		};
		btnView.addActionListener(a6);

		ActionListener a7 = (ae) -> {
			AdmitPatient ap = new AdmitPatient();
			dispose();
		};
		btnAdmitPatient.addActionListener(a7);

		setTitle("Hospital Page");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}