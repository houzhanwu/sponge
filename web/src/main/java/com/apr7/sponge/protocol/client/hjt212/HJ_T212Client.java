package com.apr7.sponge.protocol.client.hjt212;

import com.apr7.sponge.protocol.server.hjt212.ServerHandler;
import com.apr7.sponge.protocol.server.hjt212.command.CN2021Command;
import com.apr7.sponge.protocol.server.hjt212.command.Hjt212Command;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HJ_T212Client {

	public void sendCommand(Hjt212Command command) throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
//			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new CommandEncoder());
					ch.pipeline().addLast(new ServerHandler());
				}
			});
			ChannelFuture f = b.connect("localhost", 9001).sync(); // (5)
			Channel channel = f.channel();
			channel.writeAndFlush(command);
			channel.closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Hjt212Command command = new CN2021Command();
		new HJ_T212Client().sendCommand(command);
	}
}
