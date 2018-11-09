package com.apr7.sponge.protocol.knt2014.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import com.apr7.sponge.protocol.knt2014.CommandModel;

public class ClientSession implements Closeable {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	public ClientSession(String host, int port) throws IOException {
		socket = new Socket(host, port);
		socket.setSoTimeout(10 * 1000);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
	}

	public void send(CommandModel cmd) throws IOException {
		writer.write(CommandModel.encode(cmd));
		writer.write("\r\n");
		writer.flush();
	}

	public CommandModel readCommand() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		return CommandModel.decode(line);
	}

	public void close() {
		if (socket != null) {
			try {
				socket.close();
			} catch (final IOException ioe) {
				// ignored
			}
		}
	}
}
