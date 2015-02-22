package netty.lesson7.protobuf;

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
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
    	if("chengnl".equals(req.getUserName())){
    		System.out.println("req ="+req.toString());
    		SubscribeRespProto.SubscribeResp.Builder resp =SubscribeRespProto.SubscribeResp.newBuilder();
    		resp.setSubReqID(req.getSubReqID());
    		resp.setRespCode(200);
    		resp.setDesc("chengnl id");
    		ctx.writeAndFlush(resp.build());
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
