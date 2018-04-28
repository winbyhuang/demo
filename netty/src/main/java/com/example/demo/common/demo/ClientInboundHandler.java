package com.example.demo.common.demo;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof HttpResponse) {
			HttpResponse response = (HttpResponse) msg;
			System.out.println("CONTENT_TYPE:"
					+ response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
		}
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
			buf.release();
		}
		if (msg instanceof ByteBuf) {
			ByteBuf messageData = (ByteBuf) msg;
			int commandId = messageData.readInt();
			int length = messageData.readInt();
			byte[] c = new byte[length];
			messageData.readBytes(c);
			System.out.println("commandId:"+commandId+"\tmessage:"+new String(c));
		}
	}
}

