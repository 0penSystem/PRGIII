package Lab3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.*;

public class Lab3 extends JFrame implements ItemListener, ActionListener {
	public static void main(String[] args) {
		new Lab3();
	}

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();
	
	private JLabel titleLabel = new JLabel("City Viewer");

	private JPanel lpanel = new JPanel();
	private JPanel rpanel = new JPanel();
		
	private JTextField fileField = new JTextField("CityData3.csv");
	private JLabel fileLabel = new JLabel("Input File");
	private JButton fileButton = new JButton("Read File");
	private JLabel successLabel = new JLabel();

	private JLabel cityLabel = new JLabel("Select City");
	private JComboBox<City> cityCombo = new JComboBox<City>();
	private JComboBox<State> stateCombo = new JComboBox<State>();

	private JLabel stateLabel = new JLabel("Select State"),  zipLabel = new JLabel("Zipcode:"),
			zipLabelOut = new JLabel(), timezoneLabel = new JLabel("Timezone:"), timezoneLabelOut = new JLabel();

	private OrderedLinkedList<State> states = new OrderedLinkedList<State>();

	public Lab3() {
		setup();
	}

	private void setup() {
		setTitle("Lab 3");
		setSize(350, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a panel with an empty border to improve appearance
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BorderLayout(10,10));

		
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		successLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		fileButton.addActionListener(this);
		cityCombo.addItemListener(this);
		stateCombo.addItemListener(new StateListener());
		
		//fileField.setMinimumSize(new Dimension(150,30));
		fileField.setPreferredSize(new Dimension(150,30));

		
		fileButton.setPreferredSize(new Dimension(150,30));

		
		lpanel.setLayout(new GridLayout(0,1));
		rpanel.setLayout(new GridLayout(0,1));
		
		lpanel.add(fileField);
		rpanel.add(fileButton);
		lpanel.add(fileLabel);
		rpanel.add(successLabel);
		
		lpanel.add(new JLabel());
		rpanel.add(new JLabel());

		lpanel.add(stateLabel);
		lpanel.add(stateCombo);

		rpanel.add(cityLabel);
		rpanel.add(cityCombo);
		
		lpanel.add(zipLabel);
		rpanel.add(zipLabelOut);
		
		lpanel.add(timezoneLabel);
		rpanel.add(timezoneLabelOut);



		panel.add(titleLabel, BorderLayout.NORTH);
		panel.add(lpanel, BorderLayout.WEST);
		panel.add(rpanel, BorderLayout.EAST);

		add(panel);
		setVisible(true);
	}

	private void openFile(String filename) throws FileNotFoundException {
		try {
			states = new OrderedLinkedList<State>();
			stateCombo.removeAllItems();
			cityCombo.removeAllItems();
			zipLabelOut.setText("");
			timezoneLabelOut.setText("");
			
			
			Scanner inFile = new Scanner(new FileReader(filename));

			inFile.useDelimiter(", *|\n|\r\n");

			// get rid of the useless header data
			for (int i = 0; i < 7; i++) {
				inFile.next();
			}

			while (inFile.hasNext()) {
				int zipcodeT = inFile.nextInt();
				String cityNameT = inFile.next(), stateT = inFile.next();
				double latitudeT = inFile.nextDouble(), longitudeT = inFile.nextDouble();
				int timezoneT = inFile.nextInt();
				boolean yesDaylightT = (inFile.next().charAt(0) == '1');

				City temp = new City(zipcodeT, cityNameT, latitudeT, longitudeT, timezoneT, yesDaylightT);
				//State tempState = new State(stateT);
				State tempState = states.findOrAdd(new State(stateT));
				tempState.getCityList().add(temp);
			}

			inFile.close();
			
			for(State s : states){
				stateCombo.addItem(s);
			}

			successLabel.setForeground(Color.GREEN);
			successLabel.setText("File Successfully loaded!");
		} catch (FileNotFoundException e) {
			successLabel.setForeground(Color.RED);
			successLabel.setText("File Not Found!");

		}
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// button pressed, load file.
		try {
			openFile(fileField.getText());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// city picked!
			City selected = (City) arg0.getItem();

			zipLabelOut.setText("" + selected.zipcode);
			timezoneLabelOut.setText(selected.getTimezone());
		
	}
	
	private class StateListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			State selected = (State) e.getItem();
			System.out.println();

			cityCombo.removeAllItems();
			OrderedLinkedList<City> cityList = selected.getCityList();
//			
//			for(int i = 0; i < cityList.count; i++){
//				cityCombo.addItem(cityList.at(i));
//			}
			
			for(City c : cityList){
				cityCombo.addItem(c);
			}
		}
		
	}

	

}
