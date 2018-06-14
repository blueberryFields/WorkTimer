
import java.io.File;
import java.time.format.DateTimeFormatter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WorkTimerModel {
	private int workingTime;
	private int pausingTime;
	private Boolean workOrNot = false;
	private int sek;
	private int min;

	// DateTimeFormatter sekFormat = DateTimeFormatter.ofPattern("s");
	// DateTimeFormatter minFormat = DateTimeFormatter.ofPattern("m");
	// DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("H");
	File alarmSound = new File("src/resources/198841__bone666138__analog-alarm-clock.wav");

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
			try {
				Clip clip = AudioSystem.getClip();
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
	
	public void initMinAndSec(){
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

	

}
