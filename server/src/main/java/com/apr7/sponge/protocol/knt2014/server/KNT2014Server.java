package com.apr7.sponge.protocol.knt2014.server;

import com.apr7.sponge.protocol.knt2014.netty.CommandModelDecoder;
import com.apr7.sponge.protocol.knt2014.netty.CommandModelEncoder;

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

public class KNT2014Server {

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	private ServerHandler serverHandler;

	public void setServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

	public void start() {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup(16);
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline().addLast("lineBasedFrameDecoder", new LineBasedFrameDecoder(10 * 1024));
				socketChannel.pipeline().addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
				socketChannel.pipeline().addLast("commandModelDecoder", new CommandModelDecoder());
				socketChannel.pipeline().addLast("lineEncoder", new LineEncoder(LineSeparator.WINDOWS, CharsetUtil.UTF_8));
				socketChannel.pipeline().addLast("commandModelEncoder", new CommandModelEncoder());
				socketChannel.pipeline().addLast(serverHandler);
			}
		}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.bind(9002);
	}

	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
}
