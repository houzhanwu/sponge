package com.apr7.sponge.protocol.hjt212.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.apr7.sponge.protocol.hjt212.client.command.CommandModel;

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
		writer.write(cmd.toString());
		writer.flush();
	}

	public CommandModel readCommand() throws IOException {
		return CommandModel.create(reader.readLine());
	}

	public void close() {
		IOUtils.closeQuietly(socket);
	}
}
