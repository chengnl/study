package netty.lesson4.DelimiterBasedFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *@author chengnl
 *@date 2015年2月8日 下午4:10:46
 *@version 1.0
 *@Description:echo服务处理
 */
public class EchoServerHandler extends ChannelHandlerAdapter{
	private int counter;
	@Override
    public void channelRead(ChannelHandlerContext ctx,Object map) throws Exception{
    	String body = (String)map;
    	System.out.println("this is "+ ++counter +"time receive client :["+body+"]");
    	body += "$_";		
    	ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
    	ctx.writeAndFlush(resp);
    }
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
		
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		ctx.close();
		
	}
}
