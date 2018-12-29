package Threads;
import GUI.Myframe;
public class MyThread extends Thread {



	Myframe myF = new Myframe(); 

	public void run() {

		myF.setVisible(true);

	}
}
