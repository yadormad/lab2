package com.mag.lab2.model.message;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LogMessage implements Serializable {
    private EntityTypes entityType;
    private ActionTypes action;
    private Set<ChangesMessage> changes = new HashSet<>();
    private Long objectId;

    public LogMessage(Long objectId, EntityTypes entityType, ActionTypes action) {
        this.entityType = entityType;
        this.action = action;
        this.objectId = objectId;
    }

    public LogMessage() {
    }

    public EntityTypes getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypes entityType) {
        this.entityType = entityType;
    }

    public ActionTypes getAction() {
        return action;
    }

    public void setAction(ActionTypes action) {
        this.action = action;
    }

    public Set<ChangesMessage> getChanges() {
        return changes;
    }

    public void setChanges(Set<ChangesMessage> changes) {
        this.changes = changes;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        final String[] changeMessageString = {""};
        changes.forEach(changesMessage -> changeMessageString[0] += "\n\t" + changesMessage.toString());
        return "LogMessage{\n" +
                "entityType=" + entityType +
                ",\naction=" + action +
                ",\nchanges=" + changeMessageString[0] +
                ",\nobjectId=" + objectId +
                "\n}";
    }
}
