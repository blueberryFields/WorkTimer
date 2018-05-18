
import java.io.File;
import java.time.format.DateTimeFormatter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Clock {
    private int workingTime;
    private int pausingTime;
    private Boolean workOrNot = true;
    private int sek;
    private int min;

    // private String alarmTime;
    // private boolean alarmOn = false;
    // private static LocalDateTime dateTime;
    DateTimeFormatter sekFormat = DateTimeFormatter.ofPattern("s");
    DateTimeFormatter minFormat = DateTimeFormatter.ofPattern("m");
    // DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("H");
    File alarmSound = new File("src/resources/198841__bone666138__analog-alarm-clock.wav");

    public Clock() {
	sek = 0;
	min = 0;

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
	    if (workOrNot == true) {
		workOrNot = false;
		setTime();
	    } else {
		workOrNot = true;
		setTime();
	    }
	}

    }

    public void setTime() {
	if (workOrNot == true) {
	    min = workingTime;
	} else
	    min = pausingTime;
    }

    public void setWorkingTime(int min) {
	workingTime = min;
    }

    public void setPausingTime(int min) {
	pausingTime = min;
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

    // public void checkAlarm(String currentTime) {
    // if(alarmOn == true) {
    // if(currentTime.equals(this.alarmTime)) {
    // try {
    // Clip clip = AudioSystem.getClip();
    // clip.open(AudioSystem.getAudioInputStream(alarmSound));
    // clip.start();
    // }catch(Exception e) {
    // System.out.println("Error when loading audioclip");
    // }
    // }
    // }

    // public boolean isAlarmOn() {
    // return alarmOn;
    // }
    //
    // public void setAlarmOn(boolean alarmOn) {
    // this.alarmOn = alarmOn;
    // }

}
