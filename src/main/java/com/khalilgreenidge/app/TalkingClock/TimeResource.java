package com.khalilgreenidge.app.TalkingClock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Component
@Path("/time")
public class TimeResource {

    private TimeService service;

    @Autowired
    public TimeResource(TimeService service) {
        this.service = service;
    }

    @GET
    @Path("/{time}")
    @Produces("application/json")
    public ResponseEntity translation(@PathParam("time") String payload){

        try {
            return ResponseEntity.ok(service.translate(payload));
        } catch (Exception e) {
            throw new BadRequestException(Response.status(BAD_REQUEST)
                    .entity(e.getMessage()).build());
        }
    }


}
