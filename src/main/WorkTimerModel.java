package main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class WorkTimerModel {
	private int workingTime;
	private int pausingTime;
	private Boolean workOrNot = false;
	private int sek;
	private int min;
	private String alarmSound;
	private boolean popUp = false;
	private Clip clip;
	private int cycleCounter = 0;

	// Konstruktor
	public WorkTimerModel() {
		initMinAndSec();
	}

	public void tick() {
		sek--;
		if (sek < 0) {
			min--;
			sek = 59;
		}
	}

	public void checkTimer() {
		if (min == 0 && sek == 0) {
			if (popUp) {
				setOffAlarmWhitPopUp();
			} else {
				setOffAlarm();
			}
		}
	}

	private void setOffAlarm() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(alarmSound)));
			clip.start();
		} catch (Exception e) {
			System.out.println("Error when loading audioclip");
		}
		if (workOrNot) {
			setTime();
			workOrNot = false;
		} else {
			setTime();
			workOrNot = true;
			cycleCounter++;
		}
	}

	private void setOffAlarmWhitPopUp() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(alarmSound)));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("Error when loading audioclip");
		}
		if (workOrNot) {
			setTime();
			workOrNot = false;
		} else {
			setTime();
			workOrNot = true;
		}
		showMessage(workOrNot);
		clip.stop();
	}

	public void showMessage(boolean workOrNot) {
		if (workOrNot) {
			JOptionPane.showMessageDialog(null, "Time for work!");
		} else {
			JOptionPane.showMessageDialog(null, "Time for paus!");
		}
	}

	public void setRingTone(int index) {
		switch (index) {
		case 0:
			alarmSound = "/resources/analog_ringtone.wav";
			break;
		case 1:
			alarmSound = "/resources/digital_ringtone.wav";
			break;
		case 2:
			alarmSound = "/resources/spacey_ringtone.aiff";
			break;
		}
	}

	public boolean checkWorkOrNot() {
		return workOrNot;
	}

	public void setWorkOrNot(boolean workOrNot) {
		this.workOrNot = workOrNot;
	}

	public void setTime() {
		if (workOrNot) {
			min = pausingTime;
		} else
			min = workingTime;
	}

	public void setWorkingTime(int workTime) {
		workingTime = workTime;
	}

	public void setPausingTime(int pausTime) {
		pausingTime = pausTime;
	}

	public void initMinAndSec() {
		sek = 0;
		min = 0;
	}

	@Override
	public String toString() {
		String s = "";
		if (min < 10) {
			s = "0" + min;
		} else {
			s += min;
		}
		if (sek < 10) {
			s += ":0" + sek;
		} else {
			s += ":" + sek;
		}
		return s;
	}

	public void setPopUp(boolean popUp) {
		this.popUp = popUp;
	}
	
	public int getCycleCounter() {
		return cycleCounter;
	}

}
