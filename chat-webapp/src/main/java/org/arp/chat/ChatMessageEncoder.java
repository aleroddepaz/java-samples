package org.arp.chat;

import java.io.Writer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.TextStream<ChatMessage> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void encode(ChatMessage message, Writer writer) throws EncodeException {
        JsonObject event = Json.createObjectBuilder()
                .add("username", message.getUsername())
                .add("text", message.getText())
                .add("date", message.getDate().getTime())
                .build();
        try (JsonWriter jsonWriter = Json.createWriter(writer)) {
            jsonWriter.writeObject(event);
        }
    }

}
