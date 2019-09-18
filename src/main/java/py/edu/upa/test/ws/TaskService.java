package py.edu.upa.test.ws;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


import py.edu.upa.test.business.TaskBC;
import py.edu.upa.test.entity.Task;

@Path("tasks")
public class TaskService {

	@PersistenceContext
	EntityManager entityManager;

	@Inject
	private TaskBC bc;

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

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response add(Task t) {
		try {
			bc.insert(t);
			return Response.ok().entity(t).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

//	http://localhost:8080/rest/taks/1
	@GET
	@Path("/{id: \\d+}")
	@Produces({ "application/json" })
	public Response get(@PathParam("id") Integer id) {
		try {
			return Response.ok().entity(bc.findById(id)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

//    http://localhost:8080/rest/taks/1?filter=xx
	@PUT
	@Path("/{id: \\d+}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(@PathParam("id") Integer id, Task t) {
		try {
			bc.update(id, t);
			return Response.ok().entity("OK").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

	@DELETE
	@Path("/{id: \\d+}")
	@Produces({ "application/json" })
	public Response delete(@PathParam("id") Integer id) {
		try {
			bc.delete(id);
			return Response.ok().entity("OK").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

//    http://localhost:8080/rest/tasks/prueba?filter=xx
	@GET
	@Path("/pruebas")
	@Produces({ "application/json" })
	public Response update(@QueryParam("filter") String filter) {
		System.out.println("=================================");
		System.out.println(filter);
		try {
			return Response.ok().entity(bc.getWithFilter(filter)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();
		}
	}

//  http://localhost:8080/rest/tasks/?findByType=xx
	@GET
	@Path("/findByType")
	@Produces({ "application/json" })
	public Response update(@QueryParam("id_type") int findByType) {
		System.out.println("=================================");
		System.out.println(findByType);
		try {
			return Response.ok().entity(bc.getfindByType(findByType)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR_GENERICO").build();

		}
	}
}
//  http://localhost:8080/rest/tasks/paginated?filter=xx
// @GET
// @Path("/paginated")
// @Produces({"application/json"})
// public Response getPaginated(@QueryParam("PageSize") int PagSize,
// @QueryParam("first") int first,
// @QueryParam("sortField") String sortField,
// @QueryParam("sortOrder") String sortOrder) {
// try {
// return Response.ok().entity(bc.getPaginated(PagSize, first, sortField,
// sortOrder)).build;
// } catch (Exception e) {
// e.printStackTrace();
// return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
// .entity("ERROR_GENERICO")
// .build();

// }
// }
//}
