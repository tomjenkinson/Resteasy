package org.jboss.resteasy.microprofile.client;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

public class DefaultResponseExceptionMapper implements ResponseExceptionMapper {

    @Override
    public Throwable toThrowable(Response response) {
        try {
            response.bufferEntity();
        } catch (Exception ignored) {}
        return new WebApplicationException("Unknown error, status code " + response.getStatus(), response);
    }

    @Override
    public boolean handles(int status, MultivaluedMap headers) {
        return status >= 400;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
