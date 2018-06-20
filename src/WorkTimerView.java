
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	GridBagConstraints gbc = new GridBagConstraints();
	private Font timerFont = new Font("Helvetica", Font.PLAIN, 42);
	private Font buttonFont = new Font("Helvetica", Font.PLAIN, 36);
	private Font editorFont = new Font("Helvetica", Font.PLAIN, 12);
	private Font settingsFont = new Font("Helvetica", Font.PLAIN, 13);
	//private Dimension frameDimension = new Dimension(550, 100);
	
	//Create stuff for frontpanel
	private JButton startStop = new JButton("START");
	private JButton settingsButton = new JButton("...");
	private JTextArea settingsText = new JTextArea("Settings:");
	private JTextField workPaus = new JTextField("PAUS");
	private JComboBox<String> workMin = new JComboBox<String>(minArr);
	private JComboBox<String> pausMin = new JComboBox<String>(minArr);
	private JTextField timer = new JTextField("");
	private JTextArea workEditor = new JTextArea("Working time:");
	private JTextArea pausEditor = new JTextArea("Pausing time:");
	private Dimension buttonDimension = new Dimension(140, 50);
	private boolean running = false;

	//private JPanel frontPanel = new JPanel();
	private JPanel frontPanel = new JPanel();

	//Create stuff for settingpanel
	private boolean settingsPanelShowing = false;
	private String[] ringToneChoises = new String[] {"Analog Ringtone", "Digital Ringtone", "Spacey Ringtone"};
	private JPanel settingsPanel = new JPanel();
	private JButton returnButton = new JButton("Return");
	private JPanel ringTonePanel = new JPanel();
	private JTextArea ringTone = new JTextArea("Ringtone:");
	private JComboBox<String> ringToneChoice = new JComboBox<String>(ringToneChoises);
	private JPanel popUpPanel = new JPanel();
	private JTextArea popUp = new JTextArea("Use pop-up and loop ringtone:");	
	private JCheckBox popUpCheckBox = new JCheckBox();
	
	
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
		
		settingsButton.setFont(editorFont);
		settingsText.setFont(editorFont);

		workEditor.setFont(editorFont);
		pausEditor.setFont(editorFont);

		timer.setFont(timerFont);
		timer.setEditable(false);
		timer.setHorizontalAlignment(JTextField.CENTER);

		// Set layout and color and add Stuff to frontPanel
		workEditor.setBackground(Color.WHITE);
		pausEditor.setBackground(Color.WHITE);
		workMin.setEditable(false);
		pausMin.setEditable(false);
		frontPanel.setLayout(new GridBagLayout());
		frontPanel.setBackground(Color.WHITE);		
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
		gbc.gridx = 1;
		gbc.gridy = 3;
		frontPanel.add(settingsText,gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		frontPanel.add(settingsButton, gbc);
		
		// Set layout and color and add stuff to settingspanel
		ringTone.setBackground(Color.WHITE);
		ringTone.setFont(settingsFont);
		ringTone.setEditable(false);
		ringToneChoice.setBackground(Color.WHITE);
		ringToneChoice.setOpaque(true);
		popUp.setBackground(Color.WHITE);
		popUp.setFont(settingsFont);
		popUp.setEditable(false);
		popUpCheckBox.setBackground(Color.WHITE);
		popUpCheckBox.setOpaque(true);
			
		ringTonePanel.setLayout(new BorderLayout());
		popUpPanel.setLayout(new BorderLayout());
		
		ringTonePanel.add(ringTone, BorderLayout.LINE_START);
		ringTonePanel.add(ringToneChoice, BorderLayout.LINE_END);
		popUpPanel.add(popUp, BorderLayout.LINE_START);
		popUpPanel.add(popUpCheckBox, BorderLayout.LINE_END);
		
		settingsPanel.setBackground(Color.WHITE);
		settingsPanel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		settingsPanel.add(ringTonePanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		settingsPanel.add(popUpPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		settingsPanel.add(returnButton, gbc);
		

		// Set layout and color and add stuff to Frame
		setTitle("Work Timer");

		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		add(startStop, BorderLayout.LINE_START);
		add(frontPanel, BorderLayout.CENTER);
		add(workPaus, BorderLayout.LINE_END);
		
		add(settingsPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		//setSize(frameDimension);
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
	
	public void paintSettingsPanel() {
		//remove(startStop);
		remove(frontPanel);
		//remove(workPaus);
		//initSettingsPanel();
		add(settingsPanel);
		repaint();
	}
	
	public void paintFrontPanel() {
		remove(settingsPanel);
		add(startStop, BorderLayout.LINE_START);
		add(frontPanel, BorderLayout.CENTER);
		add(workPaus, BorderLayout.LINE_END);
		repaint();
		//pack();
	}
	
	public JButton getSettingsButton() {
		return settingsButton;
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
	
	public Boolean isRunning() {
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

	public boolean isSettingsPanelShowing() {
		return settingsPanelShowing;
	}

	public void setSettingsPanelShowing(boolean showSettingsPanel) {
		this.settingsPanelShowing = showSettingsPanel;
	}
	
	public JButton getReturnButton() {
		return returnButton;
	}
}
