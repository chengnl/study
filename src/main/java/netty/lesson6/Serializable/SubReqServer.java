package netty.lesson6.Serializable;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *@author chengnl
 *@date 2015年2月8日 下午3:56:18
 *@version 1.0
 *@Description:netty  server
 */
public class SubReqServer {
	
	public void bind(int port) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup= new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .option(ChannelOption.SO_BACKLOG, 100)
			 .handler(new LoggingHandler(LogLevel.INFO))
			 .childHandler(new ChildChannelHandler());
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		}finally{
			//优雅推出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			//这里使用weakCachingConcurrentResolver创建线程安全的WeakReferenceMap对类加载器进行缓存，
			//支持对线程并发访问，当虚拟机内存不足时，会释放缓存中得内存，防止内存泄漏，为了防止异常码流和解码错
			//位导致的内存溢出，这里将单个对象序列化后的字节数组长设置为1M。
			arg0.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.
					weakCachingConcurrentResolver(this.getClass().getClassLoader())));
			arg0.pipeline().addLast(new ObjectEncoder());
			arg0.pipeline().addLast(new SubReqServerHandler());
			
		}
    	
    }
    
    public static void main(String[] args) throws Exception {
		int port =8080;
		if(args!=null && args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				
			}
		}
		new SubReqServer().bind(port);
	}

}
