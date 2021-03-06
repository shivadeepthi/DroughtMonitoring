package com.sjsu.cmpe272.api;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.vz.mongodb.jackson.JacksonDBCollection;

import com.sjsu.cmpe272.domain.Reservoir;
import com.sjsu.cmpe272.repository.ReservoirRepositoryInterface;
import com.sjsu.cmpe272.repository.fetcher.ReservoirFetcher;
import com.yammer.metrics.annotation.Timed;

@Path("/v1/reservoirs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservoirResource {
	private final JacksonDBCollection<Reservoir, String> reservoirs;
	

	/**
	 * Reservoir resource
	 * @param reservoirs
	 */
	public ReservoirResource(JacksonDBCollection<Reservoir, String> reservoirs) {
		this.reservoirs = reservoirs;
	}
	
	/**
	 *  Rest API to insert all the parsed data to the DB
	 */
	@POST
	@Timed(name= "create-reservoirs")
	public Response insertReservoirs() throws Exception
	{
//        reservoirs.save(request);
		ReservoirFetcher reservoirFetcher = new ReservoirFetcher();
        List<Reservoir> allReservoirs = reservoirFetcher.getAllReservoirs();
        for(int i=0; i<allReservoirs.size() ; i++)
        {
        	reservoirs.insert(allReservoirs.get(i));
        }
		
        return Response.noContent().build();
	}


}
