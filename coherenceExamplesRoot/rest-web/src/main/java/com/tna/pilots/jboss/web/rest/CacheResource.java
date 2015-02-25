package com.tna.pilots.jboss.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/cache")
public class CacheResource {


    @GET
    @Path("/{key}/{value}")
    @Consumes({"text/plain"})
    @Produces({"text/plain"})
    public String get(@PathParam("key") String key, @PathParam("value") String value) {

        NamedCache cache = CacheFactory.getCache("mycache");
        cache.put(key, value);

        System.out.println(cache.get(key));

        return key;
    }
}
