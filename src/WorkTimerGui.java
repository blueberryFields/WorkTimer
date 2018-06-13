
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class WorkTimerGui extends JFrame implements ActionListener {

	private String[] minArr = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

	private Font timerFont = new Font("Helvetica", Font.PLAIN, 42);
	private Font buttonFont = new Font("Helvetica", Font.PLAIN, 36);
	private Font editorFont = new Font("Helvetica", Font.PLAIN, 12);
	private JButton startStop = new JButton("START");
	private JTextField workPaus = new JTextField("PAUS");
	private JComboBox<String> workMin = new JComboBox<String>(minArr);
	private JComboBox<String> pausMin = new JComboBox<String>(minArr);
	private JTextField timer = new JTextField("00:00");
	private JTextArea workEditor = new JTextArea("Working time");
	private JTextArea pausEditor = new JTextArea("Pausing time");
	private Dimension buttonDimension = new Dimension(140, 50);

	private JPanel panel = new JPanel();
	private JPanel editorPanel = new JPanel();

	private Clock c = new Clock();

	private Boolean running = false;

	Timer t = new Timer(1000, this);

	Scanner in = new Scanner(System.in);

	// Konstruktor
	public WorkTimerGui() {

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
		editorPanel.setLayout(new GridBagLayout());
		editorPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		editorPanel.add(workEditor, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		editorPanel.add(pausEditor, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		editorPanel.add(workMin, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		editorPanel.add(pausMin, gbc);

		// Set layout and color and add stuff to Frame
		setTitle("Work Timer");
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		add(startStop, BorderLayout.LINE_START);
		add(editorPanel, BorderLayout.CENTER);
		add(workPaus, BorderLayout.LINE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

		// Add actionlistener to Start/Stop button
		startStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (running == false) {
					t.start();
					running = true;
					remove(editorPanel);
					add(timer, BorderLayout.CENTER);
					startStop.setText("STOP");
					workPaus.setText("WORK");
					repaint();
					workPaus.setBackground(Color.GREEN);
				} else {
					t.stop();
					running = false;
					remove(timer);
					add(editorPanel);
					startStop.setText("START");
					workPaus.setText("PAUS");
					repaint();
					workPaus.setBackground(Color.RED);
				}
			}
		});

		// System.out.println("Hur många minuter vill du arbeta?");
		// c.setWorkingTime(in.nextInt());
		// System.out.println("Hur många minuter vill du pausa?");
		// c.setPausingTime(in.nextInt());
		// c.setTime();
		// t.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.tick();
		System.out.println(c.toString());
		c.checkTimer();
	}
}
