package logic;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SendCommand {
	private static SerialPort serialPort;

	public void send(String port, String command) {

		if (serialPort == null) {
			serialPort = new SerialPort(port);
		}

		try {
			serialPort.openPort();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			serialPort.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
					| SerialPort.FLOWCONTROL_RTSCTS_OUT);

			serialPort.writeString(command);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			serialPort.addEventListener(new PortReader(),
					SerialPort.MASK_RXCHAR);

			 serialPort.closePort();

		} catch (SerialPortException ex) {
			// System.out
			// .println("There are an error on writing string to port т: "
			// + ex);

			if (ex.getMessage().contains("Port busy")) {
				System.out.println("Port is busy retry!");
				try {
					serialPort.closePort();
				} catch (Exception e) {

				}

			}
		}
	}

	private class PortReader implements SerialPortEventListener {

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
