package netty.lesson8.Marshalling;


import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 *@author chengnl
 *@date 2015年2月22日 下午10:48:21
 *@version 1.0
 *@Description:marshalling 编码工厂类
 */
public class MarshallingCodeCFactory {
	//解码器
	public static MarshallingDecoder buildMarshallingDecoder(){
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration= new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider);
		return decoder;
	}
	
	//编码器
	public static MarshallingEncoder buildMarshallingEncoder(){
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration= new MarshallingConfiguration();
		configuration.setVersion(5);
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
		MarshallingEncoder encoder = new MarshallingEncoder(provider);
		return encoder;
	}

}
