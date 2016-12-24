package org.arp.chat.websocket;

import java.io.IOException;
import java.io.Writer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonEncoder implements Encoder.TextStream<Object> {

    private ThreadLocal<ObjectMapper> mapper = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            return new ObjectMapper();
        }
    };

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void encode(Object object, Writer writer) throws EncodeException, IOException {
        mapper.get().writeValue(writer, object);
    }

}
