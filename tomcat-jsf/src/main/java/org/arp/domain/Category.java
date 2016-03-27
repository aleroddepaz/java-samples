package org.arp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CATEGORIES")
@NamedQueries({
    @NamedQuery(name = Category.FIND_ROOT, query = "SELECT c FROM Category c WHERE c.parent IS NULL"),
    @NamedQuery(name = Category.FIND_CHILDREN, query = "SELECT c FROM Category c WHERE c.parent.id = :parentId")
})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ROOT = "Category.findRoot";
    public static final String FIND_CHILDREN = "Category.findChildren";

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "PARENT_ID", nullable = true)
    private Category parent;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(min = 5, max = 250)
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @PrePersist
    public void prePersist() {
        setCreationDate(new Date());
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }

}