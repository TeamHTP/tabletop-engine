package com.teamhtp.tabletopengine;

import com.teamhtp.tabletopengine.net.HttpInitializer;
import com.teamhtp.tabletopengine.script.ScriptLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerLauncher {

    private static final int PORT = 58290;

    private static ScriptLoader scriptLoader;

    public static void main(String[] args) {
        initScripts(Paths.get("scripts/"));
        // Do this last
        initNetty(PORT);
    }

    private static void initScripts(Path path) {
        scriptLoader = new ScriptLoader();
        try {
            scriptLoader.loadScripts(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initNetty(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer());

            Channel ch = serverBootstrap.bind(port).sync().channel();

            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
