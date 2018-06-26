
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class WorkTimerModel {
	private int workingTime;
	private int pausingTime;
	private Boolean workOrNot = false;
	private int sek;
	private int min;
	// private boolean loopAndPopUp;
	private File alarmSound;
	private boolean popUp = false;
	private int loop = Clip.LOOP_CONTINUOUSLY;
	private Clip clip;

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
			if (popUp == false) {
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(alarmSound));
					clip.start();
				} catch (Exception e) {
					System.out.println("Error when loading audioclip");
				}
				if (workOrNot == false) {
					workOrNot = true;
					setTime();
				} else {
					workOrNot = false;
					setTime();
				}
			} else {
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(alarmSound));
					clip.loop(loop);
				} catch (Exception e) {
					System.out.println("Error when loading audioclip");
				}
				if (workOrNot == false) {
					workOrNot = true;
					setTime();
				} else {
					workOrNot = false;
					setTime();
				}
				if (workOrNot == true) {
					JOptionPane.showMessageDialog(null, "Time for work!");
				} else {
					JOptionPane.showMessageDialog(null, "Time for paus!");
				}
				clip.stop();
			}
		}
	}

	public void setRingTone(int index) {
		switch (index) {
		case 0:
			alarmSound = new File("src/resources/analog_ringtone.wav");
			break;
		case 1:
			alarmSound = new File("src/resources/digital_ringtone.wav");
			break;
		case 2:
			alarmSound = new File("src/resources/spacey_ringtone.aiff");
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
		if (workOrNot == false) {
			min = workingTime;
		} else
			min = pausingTime;
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

}
