package pers.krpc.core.protocol.codec;


import com.fasterxml.jackson.databind.json.JsonMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import pers.krpc.core.protocol.KrpcMsg;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

public class KrpcDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final JsonMapper JSON_MAPPER;

    private static final Charset CHARSET;

    static {
        JSON_MAPPER = new JsonMapper();
        CHARSET = ObjectUtil.checkNotNull(Charset.defaultCharset(), "charset");
    }

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String msg = in.toString(CHARSET);
        KrpcMsg krpcMsg = JSON_MAPPER.readValue(msg, KrpcMsg.class);
        Method method =
        krpcMsg.setObject(JSON_MAPPER.convertValue(krpcMsg.getObject(),krpcMsg.getMethod().getReturnType()));
        out.add(krpcMsg);
    }
}
