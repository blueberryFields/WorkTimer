
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

public class WorkTimerGui implements ActionListener {

//    private String[] minArr = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
//	    "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
//	    "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
//	    "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };
//
//    private String[] secArr = new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
//	    "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
//	    "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
//	    "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };
//
//    private Font timeFont = new Font("Helvetica", Font.PLAIN, 36);

    private Clock c = new Clock();

    Timer t = new Timer(1000, this);

    Scanner in = new Scanner(System.in);

    public WorkTimerGui() {
	System.out.println("Hur många minuter vill du arbeta?");
	c.setWorkingTime(in.nextInt());
	System.out.println("Hur många minuter vill du pausa?");
	c.setPausingTime(in.nextInt());
	c.setTime();
	t.start();

    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // c.tick();
    //// time.setText(dateAndTime.toString());
    // dateAndTime.checkAlarm(dateAndTime.toString());
    // }
    // }

    @Override
    public void actionPerformed(ActionEvent e) {	
	System.out.println(c.toString());
	c.tick();
	c.checkTimer();
    }

}
