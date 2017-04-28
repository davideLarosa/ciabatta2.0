package main;

import logic.SendCommand;
import logic.SerialPortFinder;

public class Main {

	public static void main(String[] args) {
		SendCommand sc = new SendCommand();
		SerialPortFinder sPortFinder = new SerialPortFinder();
		String comPort = sPortFinder.find();
		if (!comPort.equals("")) {
			sc.send(comPort, "1_1");

			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			sc.send(comPort, "2_1");
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			sc.send(comPort, "1_0");

			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			sc.send(comPort, "2_0");
		} else {
			System.out.println("No com port found!");
		}

	}
}
