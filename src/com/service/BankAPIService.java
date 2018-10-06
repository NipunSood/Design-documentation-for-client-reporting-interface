package com.service;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bean.ClientDetails;
import com.dbservices.DbServices;



@Path("/client")
public class BankAPIService {
	DbServices ds = new DbServices();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String startURL(){
		return "Hello welcome to SCB API BANKING";
	}
	@Path("/consolidate-report")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDetails> getClientDetails(){
		return ds.getDetails();
	}
	
	@Path("/all-clients")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDetails> getAllClient(){
		return ds.getAllClient();
	}
	
	@Path("/consolidate-report/from/{i}/to/{j}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDetails> getClientsDetailDatewise(@PathParam("i") String from, @PathParam("j") String to){
		return ds.getDetailDatewise(from, to);
	}
	
	@Path("/report/clientname/{i}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDetails> getClientDetail(@PathParam("i") String id){
		return ds.getDetail(id);
	}
	
	@Path("/report/clientname/{i}/from/{j}/to/{k}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDetails> getClientDetailDatewise(@PathParam("i") String client, @PathParam("j") String from, @PathParam("k") String to){
		return ds.getClientDetailDatewise(client, from, to);
	}
	
}
