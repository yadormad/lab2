package com.mag.lab2.service.impl.logger;

import com.mag.lab2.model.message.ChangesMessage;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;

import java.util.HashSet;
import java.util.Set;

public abstract class Logger {
    Set<ChangesMessage> findDiscrepancy(Object oldObject, Object newObject) {
        System.out.println(oldObject.toString());
        System.out.println(newObject.toString());
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(oldObject, newObject);
        if (diff.hasChanges()) {
            Set<ChangesMessage> changesSet = new HashSet<>();
            for(ValueChange valueChange: diff.getChangesByType(ValueChange.class)) {
                if(valueChange.getRight() != null) {
                    ChangesMessage changeEntity = new ChangesMessage();
                    changeEntity.setField(valueChange.getPropertyNameWithPath());
                    changeEntity.setOldValue(valueChange.getLeft().toString());
                    changeEntity.setNewValue(valueChange.getRight().toString());
                    changesSet.add(changeEntity);
                }
            }
            return changesSet;
        }
        return null;
    }
}
