package com.mag.lab2.model.entity;

import com.mag.lab2.model.message.EntityTypes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ENTITY_TYPES_TABLE", schema = "PUBLIC")
public class EntityTypeTableEntity {
    private Long id;
    private String entityTypeName;
    private Set<EmailNotificationsTable> notificationReceivers;

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
    @Column(name = "entity_type_name")
    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    @OneToMany(mappedBy = "entityType")
    public Set<EmailNotificationsTable> getNotificationreceivers() {
        return notificationReceivers;
    }

    public void setNotificationreceivers(Set<EmailNotificationsTable> notificationReceivers) {
        this.notificationReceivers = notificationReceivers;
    }

    @Transient
    public EntityTypes getTypeEnum() {
        switch (this.entityTypeName.toLowerCase()) {
            case "client":
                return EntityTypes.CLIENT;
            case "order":
                return EntityTypes.ORDER;
            case "machinist":
                return EntityTypes.MACHINIST;
            default:
                return EntityTypes.CLIENT;
        }
    }
}
