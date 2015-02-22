#Google Protobuf

[download](https://github.com/google/protobuf/releases)


##install
1、Extract the tar.gz file.

2、$cd ~/Downloads/protobuf-2.4.1

3、$./configure

4、$make

5、$make check

6、$sudo make install

7、$which protoc

8、$protoc --version


#生成

[proto](https://developers.google.com/protocol-buffers/docs/proto)

protoc --proto_path=IMPORT_PATH --cpp_out=DST_DIR --java_out=DST_DIR --python_out=DST_DIR path/to/file.proto


#ProtobufDecoder

ProtobufDecoder仅仅只负责解码，不支持读半包问题，在ProtobufDecoder前面一定要有能够处理读半包的解码器，有三种方式可以选择：

*  使用netty提供的ProtobufVarint32FrameDecoder，可以处理半包消息

*  继承netty提供的通用半包解码器LengthFieldBasedFrameDecoder；

*  继承ByteToMessageDecoder类，自己处理半包消息。


<code>

  ChannelPipeline pipeline = ...;

 // Decoders
 
 //解码，参数1，最大解码块，参数2，3表示内容长度字节（0到4个字节为内容长度），参数4，5表示读取内容去掉字节数（去掉0到4个字节，留下传送内容）
 
 pipeline.addLast("frameDecoder",
                  new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
                  
 pipeline.addLast("protobufDecoder",
                  new ProtobufDecoder(MyMessage.getDefaultInstance()));

 // Encoder

 //编码，前四个字节添加传输长度信息
 
 pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
 
 pipeline.addLast("protobufEncoder", new ProtobufEncoder());

</code>