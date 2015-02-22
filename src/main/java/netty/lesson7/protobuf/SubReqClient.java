package netty.lesson7.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 *@author chengnl
 *@date 2015年2月8日 下午4:59:03
 *@version 1.0
 *@Description: 客户端
 */
public class SubReqClient {
    public  void connect(int port ,String host) throws Exception{
    	EventLoopGroup group = new NioEventLoopGroup();
    	try{
    		Bootstrap b = new Bootstrap();
    		b.group(group).channel(NioSocketChannel.class)
    		 .option(ChannelOption.TCP_NODELAY, true)
    		 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					//用于半包处理 解码去掉长度信息
					arg0.pipeline().addLast(new ProtobufVarint32FrameDecoder());
					//告知解码的目标类是什么
					arg0.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
					//编码，添加长度信息
					arg0.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					//编码
					arg0.pipeline().addLast(new ProtobufEncoder());
					arg0.pipeline().addLast(new SubReqClientHandler());
					
				}
			});
    		//发起异步连接操作
    		ChannelFuture f = b.connect(host,port).sync();
    		//等待客户端链路关闭
    		f.channel().closeFuture().sync();
    	}finally{
    		group.shutdownGracefully();
    	}
    }
    
    public static void main(String[] args) throws Exception {
		int port =8080;
		if(args!=null&&args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				
			}
		}
		new SubReqClient().connect(port, "127.0.0.1");
	}
}
