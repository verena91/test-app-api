package py.edu.upa.test.ws;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.edu.upa.test.business.TaskBC;
import py.edu.upa.test.entity.Task;

@Path("productos")
@RequestScoped
public class TaskService {

	@Inject
	private TaskBC bc;

//  http://localhost:8080/rest/tasks
	@GET
    @Produces("application/json")
	public Response getAll() {
		try {
			return Response.ok().entity(bc.find()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
	}
	
//  http://localhost:8080/rest/tasks
	@POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
	public Response add(Task t) {
		try {
			bc.insert(t);
			return Response.ok().entity(t).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
	}

//	http://localhost:8080/rest/tasks/1
    @GET
    @Path("/{id: \\d+}")
    @Produces({"application/json"})
    public Response get(@PathParam("id") Integer id) {
    	try {
			return Response.ok().entity(bc.findById(id)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }

//    http://localhost:8080/rest/tasks/1
    @PUT
    @Path("/{id: \\d+}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response update(@PathParam("id") Integer id, Task t) {
    	try {
    		bc.update(id,t);
			return Response.ok().entity("OK").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }

    @DELETE
    @Path("/{id: \\d+}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
    	try {
    		bc.delete(id);
			return Response.ok().entity("OK").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }
    
//    http://localhost:8080/rest/tasks/prueba?filter=xx
    @GET
    @Path("/pruebas")
    @Produces({"application/json"})
    public Response update(@QueryParam("filter") String filter) {
    	try {
			return Response.ok().entity(bc.getWithFilter(filter)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }
    
    @GET
    @Path("/filtered")
    @Produces({"application/json"})
    public Response getFiltered(@QueryParam("type") int type) {
    	try {
			return Response.ok().entity(bc.getFilteredByType(type)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }
    
//  	http://localhost:8080/ws/rest/tasks/prueba?pageSize=1&first=1&sortField=a&sortOrder=ASC
	  @GET
	  @Path("/paginated")
	  @Produces({"application/json"})
	  public Response getPaginated(@QueryParam("pageSize") int pageSize,  
			  @QueryParam("page") int page, 
			  @QueryParam("sortField") String sortField, 
			  @QueryParam("sortOrder") String sortOrder) {
	  	try {
	  			List<Task> tasks = bc.getPaginated(pageSize, page, sortField, sortOrder);
	  			PaginatedTask paginatedList = new PaginatedTask();
	  			paginatedList.setTasks(tasks);
	  			List<Task> todos = bc.find(); // bc.getCount();
	  			paginatedList.setCount(todos != null ? todos.size() : 0);
				return Response.ok().entity(paginatedList).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("ERROR_GENERICO")
						.build();
			}
	  }

}

class PaginatedTask {
	
	List<Task> tasks;
	int count;
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
