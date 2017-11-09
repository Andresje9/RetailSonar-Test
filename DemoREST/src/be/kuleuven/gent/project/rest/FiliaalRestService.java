package be.kuleuven.gent.project.rest;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import model.Filiaal;
import be.kuleuven.gent.project.ejb.FiliaalManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@javax.enterprise.context.RequestScoped
@Path("task_service")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class FiliaalRestService {

	@EJB
	private FiliaalManagementEJBLocal taskRepo;
	
	@EJB
	private UserManagementEJBLocal userRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;

	@POST
	@JWTTokenNeeded
	public Response createFiliaal(Filiaal filiaal) {
		if (filiaal == null)
			throw new BadRequestException();
		
		taskRepo.createFiliaal(filiaal);
		URI taskUri = uriInfo.getAbsolutePathBuilder().path(filiaal.getId().toString()).build();
		return Response.created(taskUri).build();
	}

	@GET
	@JWTTokenNeeded
	public Filiaal getFiliaal() {
		
		Principal p = ctx.getUserPrincipal();
		Filiaal filiaal = userRepo.findFiliaal(p.getName());
		return filiaal;
	}
	
}
