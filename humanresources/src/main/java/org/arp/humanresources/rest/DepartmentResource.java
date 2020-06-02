package org.arp.humanresources.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.arp.humanresources.domain.Department;

@Path("/departments")
public class DepartmentResource {

	@PersistenceContext
	EntityManager em;

	@GET
	public List<Department> getDepartments() {
		return em.createNamedQuery(Department.FIND_ALL, Department.class).getResultList();
	}

	@GET
	@Path("{id}")
	public Department getDepartment(@PathParam("id") Long id) {
		return em.find(Department.class, id);
	}

	public Department postDepartment(@Valid Department department) {
		em.persist(department);
		return department;
	}

	public Department putDepartment(@PathParam("id") Long id, @Valid Department update) {
		Department department = em.find(Department.class, id);
		department.setName(update.getName());
		department.setBaseSalary(update.getBaseSalary());
		return department;
	}

}