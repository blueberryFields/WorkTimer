package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class WorkTimerController implements ActionListener {

	private WorkTimerModel model;
	private WorkTimerView view;
	private Timer t;
	private int cycleCounter = 0;

	// Konstruktor
	public WorkTimerController() {
		model = new WorkTimerModel();
		view = new WorkTimerView();
		t = new Timer(1000, this);
		initWorkTimerView();
	}

	public void initWorkTimerView() {
		view.getStartStopButton().addActionListener(e -> start());
	}

	public void start() {
		if (view.isRunning()) {
			view.stop();
			view.enableSettingsStrip();
			t.stop();
			model.setWorkOrNot(false);
			model.initMinAndSec();
		} else {
			setWorkTime();
			setPausTime();
			model.setTime();
			model.setWorkOrNot(true);
			model.setRingTone(view.getRingToneChoice());
			setPopUp(view.getPopUpCheckBox());
			view.disableSettingStrip();
			view.setTimer(model.toString());
			view.start();
			t.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.tick();
		view.setTimer(model.toString());
		model.checkTimer();
		view.setCycles(model.getCycleCounter());
		if (model.checkWorkOrNot() == true) {
			view.setWorkLabel();
		} else {
			view.setPausLabel();
		}
	}

	public void setRingTone(int index) {
		model.setRingTone(index);
	}

	public int getWorkMin() {
		return Integer.parseInt(view.getWorkMin());
	}

	public int getPausMin() {
		return Integer.parseInt(view.getPausMin());
	}

	public void setWorkTime() {
		model.setWorkingTime(getWorkMin());
	}

	public void setPausTime() {
		model.setPausingTime(getPausMin());
	}

	public void setPopUp(boolean popUp) {
		model.setPopUp(popUp);
	}
}
