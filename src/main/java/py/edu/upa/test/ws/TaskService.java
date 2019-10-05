package py.edu.upa.test.ws;

import java.util.List;
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

@Path("tasks")
public class TaskService {

	@Inject
	private TaskBC bc;

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

//	http://localhost:8080/rest/taks/1
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
    
    /**
     * Obtener taks's por id_type (de type)
     * @param id_type
     * @return
     */
//	http://localhost:8080/rest/taks?id_type=1
    @GET
    @Path("/bytype")
    @Produces({"application/json"})
    public Response getByType(@QueryParam("id_type") Integer id_type) {
    	try {
			return Response.ok().entity(bc.getByType(id_type)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }
    
    
//    http://localhost:8080/rest/taks/1?filter=xx
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
    	System.out.println("=================================");
    	System.out.println(filter);
    	try {
			return Response.ok().entity(bc.getWithFilter(filter)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }

   /**
    * obtener tasks paginando 
    * @param page
    * @param size
    * @return
    */
//	http://localhost:8080/rest/taks/pagination?page=2&size=1
    @GET
    @Path("/pagination")
    @Produces({"application/json"})
    public Response getWithPagination(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
    	try {
			return Response.ok().entity(bc.getWithPagination(page,size)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ERROR_GENERICO")
					.build();
		}
    }

    /**
     * obtener tasks paginando y con cantidad de tasks
     * @param page
     * @param size
     * @return
     */
// 	http://localhost:8080/rest/taks/pagination?page=2&size=1
     @GET
     @Path("/paginationAndQuantity")
     @Produces({"application/json"})
     public Response getWithPaginationAndQuantity(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
     
    	 try {
//    		 return Response.ok().entity(bc.getWithPagination(page,size)).build();
    		TasksAndQuantity tareasYcantidad = new TasksAndQuantity(); 
    		 // se reciben todas las tareas
    		List<Task> tareas;
    		tareas = bc.getWithPagination(page,size);	
    		// obtener todas 
    		tareasYcantidad.setTasks(tareas);
    		
    		// se guarda la cantidad total de tareas, si es que hay
    		int cantidadTotalTareas = 0;
    		cantidadTotalTareas = bc.find().size();
    		
    		if (tareas.size()>0) {
				tareasYcantidad.setQuantity(cantidadTotalTareas);
			}
    		else {
    			tareasYcantidad.setQuantity(0);
			}
    		
    		// se retorna toda la entidad que tiene la lista de tareas y la cantidad de tareas
 			return Response.ok().entity(tareasYcantidad).build();
 		} catch (Exception e) {
 			e.printStackTrace();
 			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
 					.entity("ERROR_GENERICO")
 					.build();
 		}
     }
    
     /*
      * esta clase contiene una lista de tareas y la cantidad de tareas
      */
     class TasksAndQuantity{
    	 private List<Task> tasks;
    	 private int quantity;
		public List<Task> getTasks() {
			return tasks;
		}
		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
    			
     }
     
}
