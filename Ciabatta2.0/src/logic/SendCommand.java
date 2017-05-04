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
			if (!serialPort.isOpened()) {
				serialPort.openPort();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

			serialPort.writeString(command);




			serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

			// serialPort.closePort();

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

	public void resetSerialPort() {
		serialPort = null;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	private class PortReader implements SerialPortEventListener {

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
				}
				try {
					String receivedData = serialPort.readString(event.getEventValue());
					System.out.println("Received response: " + receivedData);
				} catch (SerialPortException ex) {
					System.out.println("Error in receiving string from COM-port: " + ex);
				}
			}
		}
	}
}
