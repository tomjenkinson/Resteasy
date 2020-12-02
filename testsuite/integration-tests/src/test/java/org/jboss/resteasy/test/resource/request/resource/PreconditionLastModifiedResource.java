package org.jboss.resteasy.test.resource.request.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import java.util.GregorianCalendar;

@Path("/")
public class PreconditionLastModifiedResource {

   @GET
   public Response doGet(@Context Request request) {
      GregorianCalendar lastModified = new GregorianCalendar(2007, 0, 0, 0, 0, 0);
      Response.ResponseBuilder rb = request.evaluatePreconditions(lastModified.getTime());
      if (rb != null) {
         return rb.build();
      }

      return Response.ok("foo", "text/plain").build();
   }
}
