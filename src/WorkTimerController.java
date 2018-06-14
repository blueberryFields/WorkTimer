import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class WorkTimerController implements ActionListener {

	private WorkTimerModel model;
	private WorkTimerView view;
	private Timer t;

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
		if (view.getRunning() == false) {
			setWorkTime();
			setPausTime();
			model.setTime();
			model.setWorkOrNot(true);
			view.setTimer(model.toString());
			view.start();
			t.start();
		} else {
			view.stop();
			t.stop();
			model.setWorkOrNot(false);
			model.initMinAndSec();
		}
	}

	public int getWorkMin() {
		int workMin;
		return workMin = Integer.parseInt(view.getWorkMin());
	}

	public int getPausMin() {
		int pausMin;
		return pausMin = Integer.parseInt(view.getPausMin());
	}

	public void setWorkTime() {
		model.setWorkingTime(getWorkMin());
	}

	public void setPausTime() {
		model.setPausingTime(getPausMin());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.tick();
		view.setTimer(model.toString());
		model.checkTimer();
		if (model.checkWorkOrNot() == true) {
			view.setWorkLabel();
		} else {
			view.setPausLabel();
		}
	}

}
