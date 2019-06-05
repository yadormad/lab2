package com.mag.lab2.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMAIL_NOTIFICATIONS_TABLE", schema = "PUBLIC")
public class EmailNotificationsTable {
    private Long id;
    private String email;
    private EntityTypeTableEntity entityType;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "changed_table_condition", nullable = false)
    public EntityTypeTableEntity getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeTableEntity entityType) {
        this.entityType = entityType;
    }
}
