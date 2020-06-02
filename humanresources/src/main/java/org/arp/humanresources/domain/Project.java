package org.arp.humanresources.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.arp.humanresources.validation.TemporalEntity;

@Entity
@Table(name = "PROJECTS")
@NamedQuery(name = Project.FIND_BY_EMPLOYEE, query = "SELECT p FROM Project p WHERE p.employee.id = :empId")
public class Project implements TemporalEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_EMPLOYEE = "Project.findByEmployee";

	@Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE")
    @NotNull(message = "{project.name.notNull}")
    @Size(min = 2, max = 32, message = "{project.name.size}")
    private String title;

    @Column(name = "DESCRIPTION")
    @NotNull(message = "{project.description.notNull}")
    @Size(min = 10, max = 2000, message = "{project.description.size}")
    private String description;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "{project.startDate.notNull}")
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_EMPLOYEE_ID")
    private Employee assignedEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Project [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(title);
        builder.append("]");
        return builder.toString();
    }

}
