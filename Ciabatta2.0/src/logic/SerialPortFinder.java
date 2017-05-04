package logic;

import jssc.SerialPortList;

public class SerialPortFinder {
	public String find() {
		// getting serial ports list into the array
		String[] portNames = SerialPortList.getPortNames();

		// if (portNames.length == 0) {
		// System.out
		// .println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
		// System.out.println("Press Enter to exit...");
		// try {
		// System.in.read();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// for (int i = 0; i < portNames.length; i++) {
		// System.out.println(portNames[i]);
		// }
		String realPort = "";
		for (int i = 0; i < portNames.length; i++) {
			if (!portNames[i].equals("/dev/ttyAMA0")) {
				System.out.println("porta -> " + portNames[i]);
				realPort = portNames[i];
			}
		}
		return realPort;

	}
}