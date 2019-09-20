package py.edu.upa.test.ws;

import javax.inject.Inject;
//import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.edu.upa.test.business.TypeBC;
import py.edu.upa.test.entity.Type;

@Path("type")
public class TypeService {

	@Inject
	private TypeBC bc;
//a. Obtener todos los tipos
	@GET
	@Produces("application/json")
	public Response getAll() {
		try {
			return Response.ok().entity(bc.find()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}
//b. Obtener un tipo por id
	@GET
	@Path("/{id: \\\\d+}")
	@Produces({"application/json"})
	public Response getId(@PathParam("id") Integer id) {
		try {
			return Response.ok().entity(bc.findById(id)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}
//c. Crear un nuevo tipo
	@POST
	@Produces("application/json")
	public Response postType(Type t) {
		try {
			bc.insert(t);
			return Response.ok().entity(t).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}
//d. Actualizar un tipo
	@PUT
	@Path("/pruebas")
	@Produces({"application/json"})
	public Response updateType(@QueryParam("filter") String filter) {
		System.out.println(filter);
		try {
			return Response.ok().entity(bc.getWithFilter(filter)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}
//e. Borrar un tipo
	@DELETE
	@Path("/{id: \\d+}")
	@Produces({"application/json"})
	public Response deleteType (@PathParam("id") Integer id) {
		try {
		bc.delete(id);
		return Response.ok().entity("OK").build();
	} catch (Exception e) {
		e.printStackTrace();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

}
