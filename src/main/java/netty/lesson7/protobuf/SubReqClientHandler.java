package netty.lesson7.protobuf;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
		   SubscribeReqProto.SubscribeReq.Builder  req = SubscribeReqProto.SubscribeReq.newBuilder();
		   req.setSubReqID(i);
		   req.setUserName("chengnl");
		   req.setProductName("mac");
		   List<String>  address = new ArrayList<>(); 
		   address.add("beijing");
		   address.add("wuhan");
		   req.addAllAddress(address);
		   ctx.write(req.build());
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
