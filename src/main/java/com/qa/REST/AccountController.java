package com.qa.REST;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.AccountService;

@Path("account")
public class AccountController {

	@Inject
	private AccountService service;

	@Path("getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/cycleAccounts/{aName}")
	@GET
	@Produces({ "application/json" })
	public long cycleAccounts(@PathParam("aName") String aName) {
		return service.cycleAccounts(aName);
	}

	@Path("/getAnAccount/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAnAccount(@PathParam("id") int id) {
		return service.getAnAccount(id);
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") int id) {
		return service.deleteAccount(id);
	}
	
	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") int id, String account) {
		return service.updateAccount(id, account);
	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
