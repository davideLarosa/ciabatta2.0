package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import logic.SendCommand;
import logic.SerialPortFinder;
import logic.Status;

//@WebServlet("/DataSend")
public class DataSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SendCommand sendCommand;
	private static String comPort;
	private Status status;

	public DataSend() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameterNames().hasMoreElements()) {

			if (sendCommand == null) {
				sendCommand = new SendCommand();
			}
			if (comPort == null) {
				SerialPortFinder serialPortFinder = new SerialPortFinder();
				comPort = serialPortFinder.find();
			} else {
				SerialPortFinder serialPortFinder = new SerialPortFinder();
				String checkComPort = serialPortFinder.find();
				if (checkComPort == null) {
					comPort = "";
					sendCommand.resetSerialPort();
					return;
				}
				if (!comPort.equals(checkComPort)) {
					sendCommand.resetSerialPort();
					comPort = checkComPort;
				}

			}
			if (this.status == null) {
				this.status = new Status();
			}

			String jsonString = request.getParameter("dataSent");

			if (jsonString != null) {
				ObjectMapper mapper = new ObjectMapper();
				String receivedData = mapper.readValue(jsonString, String.class);

				sendCommand.send(comPort, this.status.get(receivedData));

				// request.getRequestDispatcher("index.html").forward(request,
				// response);
			}
		} else {
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}

}
