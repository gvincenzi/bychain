package org.byochain.api.serializer;

import java.io.IOException;

import org.byochain.api.exception.ByoChainApiException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * ByoChainApiExceptionSerializer
 * 
 * @author Giuseppe Vincenzi
 *
 */
public class ByoChainApiExceptionSerializer extends StdSerializer<ByoChainApiException> {

	public ByoChainApiExceptionSerializer() {
		this(null);
	}

	public ByoChainApiExceptionSerializer(Class<ByoChainApiException> t) {
		super(t);
	}

	@Override
	public void serialize(ByoChainApiException value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("code", value.getCode());
		jgen.writeStringField("message", value.getMessage());
		jgen.writeEndObject();
	}

}
