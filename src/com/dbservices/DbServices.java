package com.dbservices;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bean.ClientDetails;
import com.sqllite.SqLite;

public class DbServices {
	
	private static Set<ClientDetails> clientSet = new HashSet<>();
	
	public List<ClientDetails> getDetails(){
	return new SqLite().fetchDetails();
	}
	
	public List<ClientDetails> getAllClient(){
		return new SqLite().fetchAllClient();
	}
	
	public List<ClientDetails> getDetail(String name){
		return new SqLite().fetchDetail(name);
	}
	
	public List<ClientDetails> getClientDetailDatewise(String client, String from, String to){
	return new SqLite().fetchClientDetailsDatewise(client, from, to);
	}
	
	public List<ClientDetails> getDetailDatewise(String from, String to){
	return new SqLite().fetchDetailsDatewise(from, to);
	}
}
