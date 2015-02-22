package netty.lesson7.protobuf;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 *@author chengnl
 *@date 2015年2月22日 下午7:44:02
 *@version 1.0
 *@Description:测试protobuf 编解码
 */
public class TestSubscribeReqProto {
	
	private static byte[] encode(SubscribeReqProto.SubscribeReq req){
		return req.toByteArray();
	}
	
	private static SubscribeReqProto.SubscribeReq decode(byte[] data) 
			throws InvalidProtocolBufferException{
		return SubscribeReqProto.SubscribeReq.parseFrom(data);
	}
	
	private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("chengnl");
		builder.setProductName("mac");
		List<String>  address = new ArrayList<>(); 
		address.add("beijing");
		address.add("wuhan");
		builder.addAllAddress(address);
		return builder.build();
	}

	public static void main(String[] args) throws InvalidProtocolBufferException {
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.println("before encode:"+req.toString());
		SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
		System.out.println("encode : "+encode(req));
		
		System.out.println("After decode : "+req2.toString());
		System.out.println("Assert equal : --->"+req2.equals(req));
		
		
		System.out.println("encode string:"+req.toByteString());
	}

}
