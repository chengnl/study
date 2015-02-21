package netty.lesson6.Serializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *@author chengnl
 *@date 2015年2月8日 下午4:10:46
 *@version 1.0
 *@Description:服务处理
 */
public class SubReqServerHandler extends ChannelHandlerAdapter{
	@Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
    	SubscribeReq req = (SubscribeReq)msg;
    	if(100006==req.getUserID()){
    		System.out.println("req ="+req.toString());
    		SubscribeResp resp = new SubscribeResp();
    		resp.setUserID(req.getUserID());
    		resp.setRespCode(200);
    		resp.setDesc("chengnl id");
    		ctx.writeAndFlush(resp);
    	}
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
