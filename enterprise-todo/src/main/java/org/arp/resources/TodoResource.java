package org.arp.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.arp.model.Todo;
import org.arp.services.TodoService;

@Path("/todos")
public class TodoResource {

	@Inject
	private TodoService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTodos() {
		List<Todo> todos = service.findAllTodos();
		return Response.ok(todos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTodo(@Valid final Todo todo) {
		Todo newTodo = service.createTodo(todo);
		return Response.ok(newTodo).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTodo(@PathParam("id") Long id) {
		boolean removed = service.removeTodo(id);
		Status status = removed ? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}
	
}