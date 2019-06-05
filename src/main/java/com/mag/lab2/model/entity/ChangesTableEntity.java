package com.mag.lab2.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHANGES_TABLE", schema = "PUBLIC")
public class ChangesTableEntity {
    private Long id;
    private String field, oldValue, newValue;
    private LoggerTableEntity logEntity;

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
    @Column(name = "field")
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Basic
    @Column(name = "old_value")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "new_value")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    public LoggerTableEntity getLogEntity() {
        return logEntity;
    }

    public void setLogEntity(LoggerTableEntity logEntity) {
        this.logEntity = logEntity;
    }
}
