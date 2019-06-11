package main;

import javax.swing.SwingUtilities;

public class WorkTimerMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				WorkTimerController wt = new WorkTimerController();
			}
		});
	}
}
