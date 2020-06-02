package org.arp.humanresources.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEPARTMENTS")
@NamedQuery(name = Department.FIND_ALL, query = "SELECT d FROM Department d")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Department.findAll";

	@Id
	@Column(name = "DEPARTMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 32, name = "NAME")
	@NotNull(message = "{department.name.notNull}")
	@Size(min = 2, max = 32, message = "{department.name.size}")
	private String name;

	@Column(name = "BASE_SALARY")
	@NotNull(message = "{department.baseSalary.notNull}")
	@Min(value = 12000, message = "{department.baseSalary.min}")
	@Max(value = 200000, message = "{department.baseSalary.max}")
	private Double baseSalary;

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}