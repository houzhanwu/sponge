package com.apr7.sponge.protocol.server.hjt212;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class HJ_T212Server {

	private static HJ_T212Server server;

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	private HJ_T212Server() {

	}

	public static HJ_T212Server getInstance() {
		if (server == null) {
			server = new HJ_T212Server();
		}
		return server;
	}

	public void start() {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup(16);
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline().addLast("frameDecoder", new LineBasedFrameDecoder(10 * 1024));
				socketChannel.pipeline().addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
				socketChannel.pipeline().addLast("lineEncoder", new LineEncoder(LineSeparator.WINDOWS, CharsetUtil.UTF_8));
				socketChannel.pipeline().addLast(new ServerHandler());
			}
		}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.bind(9001);
	}

	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
}
