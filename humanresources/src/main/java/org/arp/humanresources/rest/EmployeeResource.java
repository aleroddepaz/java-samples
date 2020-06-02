package org.arp.humanresources.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.arp.humanresources.domain.Employee;
import org.arp.humanresources.domain.Project;

@Path("/employees")
public class EmployeeResource {

	@PersistenceContext
	EntityManager em;

	@GET
	public List<Employee> getEmployees() {
		return em.createNamedQuery(Employee.FIND_ALL, Employee.class).getResultList();
	}

	@GET
	@Path("{id}")
	public Employee getEmployee(@PathParam("id") Long id) {
		return em.find(Employee.class, id);
	}

	@GET
	@Path("{id}/projects")
	public List<Project> getEmployeeProjects(@PathParam("id") Long id) {
		TypedQuery<Project> query = em.createNamedQuery(Project.FIND_BY_EMPLOYEE, Project.class);
		return query.setParameter("empId", id).getResultList();
	}

	@POST
	public Employee postEmployee(@Valid Employee employee) {
		em.persist(employee);
		return employee;
	}

	@PUT
	public Employee putEmployee(@PathParam("id") Long id, @Valid Employee employee) {
		Employee current = em.find(Employee.class, id);
		current.setFirstName(employee.getFirstName());
		current.setLastName(employee.getLastName());
		return current;
	}

}