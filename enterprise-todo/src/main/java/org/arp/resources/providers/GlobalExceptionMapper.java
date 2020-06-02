package org.arp.resources.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionMapper.class.getName());
	
	@Override
	public Response toResponse(RuntimeException e) {
		LOGGER.log(Level.SEVERE, "An uncaught error ocurred!", e);
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}