package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jssc.*;

public class Clock {
	static SerialPort serialPort;

	public static void play(String port) throws Exception {
		try {
			
			 serialPort = new SerialPort(port);

			serialPort.openPort();

			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(System.in));
			// System.out.print("Enter String");
			// String s = br.readLine();
			// System.out.print("Enter Integer:");
			// int i = 0;
			// try {
			// i = Integer.parseInt(br.readLine());
			// } catch (NumberFormatException nfe) {
			// System.err.println("Invalid Format!");
			// }
			//
			// System.out.println("string is " + s);
			// System.out.println("integer is " + i);

			Thread.sleep(1000);

			serialPort.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
					| SerialPort.FLOWCONTROL_RTSCTS_OUT);

			serialPort.addEventListener(new PortReader(),
					SerialPort.MASK_RXCHAR);

			while (true) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss ");
				Date date = new Date();

				serialPort.writeString(dateFormat.format(date));
				Thread.sleep(1000);
			}

			// serialPort.closePort();

		} catch (SerialPortException ex) {
			System.out
					.println("There are an error on writing string to port т: "
							+ ex);
		}
	}

	private static class PortReader implements SerialPortEventListener {

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					String receivedData = serialPort.readString(event
							.getEventValue());
					System.out.println("Received response: " + receivedData);
				} catch (SerialPortException ex) {
					System.out
							.println("Error in receiving string from COM-port: "
									+ ex);
				}
			}
		}
	}
}
