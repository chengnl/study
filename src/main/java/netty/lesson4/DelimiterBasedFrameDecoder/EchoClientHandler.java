package netty.lesson4.DelimiterBasedFrameDecoder;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *@author chengnl
 *@date 2015年2月8日 下午5:06:08
 *@version 1.0
 *@Description:客户端处理类
 */
public class EchoClientHandler extends ChannelHandlerAdapter{
   private int counter;
   private byte[] req;
   
   public EchoClientHandler() {
	 req = ("hello chengnl. $_")	.getBytes();
   }
   @Override
   public void channelActive(ChannelHandlerContext ctx){
	   ByteBuf message = null;
	   for(int i=0;i<10;i++){
		   message=Unpooled.buffer(req.length);
		   message.writeBytes(req);
		   ctx.writeAndFlush(message);
	   }
	  
   }
   
   @Override
   public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException{
	   String body = (String)msg;
	   System.out.println("this is : "+ ++counter+" times receive server: ["+body+"]");
   }
   @Override
   public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
	   ctx.close();
	   
   }
}
