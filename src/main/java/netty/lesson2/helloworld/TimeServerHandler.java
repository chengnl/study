package netty.lesson2.helloworld;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *@author chengnl
 *@date 2015年2月8日 下午4:10:46
 *@version 1.0
 *@Description:时间服务处理
 */
public class TimeServerHandler extends ChannelHandlerAdapter{
	private int counter;
	@Override
    public void channelRead(ChannelHandlerContext ctx,Object map) throws Exception{
    	ByteBuf buf = (ByteBuf)map;
    	byte[] req = new byte[buf.readableBytes()];
    	buf.readBytes(req);
    	String body = new String(req,"UTF-8").substring(0, req.length-System.getProperty("line.separator").length());
    	System.out.println("the time server receiver order :" +body+"; the counter is : "+ ++counter);
    	
    	String currentTime ="QUERY TIME ORDER".equalsIgnoreCase(body)?
    			new Date(System.currentTimeMillis()).toString():"BAD ORDER";
    	currentTime=currentTime+System.getProperty("line.separator");		
    	ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
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
