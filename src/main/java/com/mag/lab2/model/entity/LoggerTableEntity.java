package com.mag.lab2.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LOGGER_TABLE", schema = "PUBLIC")
public class LoggerTableEntity {
    private Long id;
    private String tableName;
    private String action;
    private Long objectId;
    private Set<ChangesTableEntity> changesTableEntities;

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
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @OneToMany(mappedBy = "logEntity")
    public Set<ChangesTableEntity> getChangesTableEntities() {
        return changesTableEntities;
    }

    public void setChangesTableEntities(Set<ChangesTableEntity> changesTableEntities) {
        this.changesTableEntities = changesTableEntities;
    }

    @Basic
    @Column(name = "object_id")
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}
