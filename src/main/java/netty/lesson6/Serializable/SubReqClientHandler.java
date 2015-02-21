package netty.lesson6.Serializable;

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
public class SubReqClientHandler extends ChannelHandlerAdapter{
   
   public SubReqClientHandler() {
   }
   @Override
   public void channelActive(ChannelHandlerContext ctx){
	   for(int i=0;i<10;i++){
		   SubscribeReq  req = new SubscribeReq();
		   req.setUserID(100006);
		   req.setUserName("chengnl");
		   req.setPhoneNumber("18618");
		   ctx.write(req);
	   }
	   ctx.flush();
	  
   }
   
   @Override
   public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException{
	   System.out.println("receive server: ["+msg+"]");
   }
   @Override
   public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
	   ctx.close();
	   
   }
}
