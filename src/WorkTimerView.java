
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WorkTimerView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4709189419252856844L;

	//Create allround stuff
	private String[] minArr = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

	private Font timerFont = new Font("Helvetica", Font.PLAIN, 42);
	private Font buttonFont = new Font("Helvetica", Font.PLAIN, 36);
	private Font editorFont = new Font("Helvetica", Font.PLAIN, 12);
	
	//Create stuff for frontpanel
	private JButton startStop = new JButton("START");
	private JTextField workPaus = new JTextField("PAUS");
	private JComboBox<String> workMin = new JComboBox<String>(minArr);
	private JComboBox<String> pausMin = new JComboBox<String>(minArr);
	private JTextField timer = new JTextField("");
	private JTextArea workEditor = new JTextArea("Working time");
	private JTextArea pausEditor = new JTextArea("Pausing time");
	private Dimension buttonDimension = new Dimension(140, 50);
	private Boolean running = false;

	//private JPanel frontPanel = new JPanel();
	private JPanel frontPanel = new JPanel();

	//private WorkTimerModel c = new WorkTimerModel();

	

	//Scanner in = new Scanner(System.in);

	// Konstruktor
	public WorkTimerView() {

		// Set font and edit buttons and textareas
		startStop.setFont(buttonFont);
		startStop.setPreferredSize(buttonDimension);
		workPaus.setFont(buttonFont);
		workPaus.setPreferredSize(buttonDimension);
		workPaus.setEditable(false);
		workPaus.setBackground(Color.RED);
		workPaus.setHorizontalAlignment(JTextField.CENTER);

		workEditor.setFont(editorFont);
		pausEditor.setFont(editorFont);

		timer.setFont(timerFont);
		timer.setEditable(false);
		timer.setHorizontalAlignment(JTextField.CENTER);

		// Set layout and color and add Stuff to editorPanel

		workEditor.setBackground(Color.WHITE);
		pausEditor.setBackground(Color.WHITE);
		frontPanel.setLayout(new GridBagLayout());
		frontPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		frontPanel.add(workEditor, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		frontPanel.add(pausEditor, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		frontPanel.add(workMin, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		frontPanel.add(pausMin, gbc);

		// Set layout and color and add stuff to Frame
		setTitle("Work Timer");

		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		add(startStop, BorderLayout.LINE_START);
		add(frontPanel, BorderLayout.CENTER);
		add(workPaus, BorderLayout.LINE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void start() {
		running = true;
		remove(frontPanel);
		add(timer, BorderLayout.CENTER);
		startStop.setText("STOP");
		workPaus.setText("WORK");
		workPaus.setBackground(Color.GREEN);
		repaint();	
	}
	
	public void stop() {
		running = false;
		remove(timer);
		add(frontPanel);
		startStop.setText("START");
		workPaus.setText("PAUS");
		workPaus.setBackground(Color.RED);
		repaint();		
	}
	
	public JButton getStartStopButton() {
		return startStop;
	}
	
	public String getWorkMin() {
		return workMin.getSelectedItem().toString();
	}
	
	public String getPausMin() {
		return pausMin.getSelectedItem().toString();
	}
	
	public Boolean getRunning() {
		return running;
	}
	
	public void setRunning(Boolean running) {
		this.running = running;
	}
	
	public void setTimer(String time) {
		timer.setText(time);
	}
	
	public void setWorkLabel() {
		workPaus.setText("WORK");
		workPaus.setBackground(Color.GREEN);
	}
	
	public void setPausLabel() {
		workPaus.setText("PAUS");
		workPaus.setBackground(Color.RED);
	}
}
