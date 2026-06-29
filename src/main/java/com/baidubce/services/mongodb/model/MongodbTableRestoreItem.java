package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Table/collection level restore item used in database/table recovery.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbTableRestoreItem {
    private String type;
    private String name;
    private String newName;
    private List<SubObjectItem> subObjects;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public List<SubObjectItem> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(List<SubObjectItem> subObjects) {
        this.subObjects = subObjects;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubObjectItem {
        private String type;
        private String name;
        private String newName;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
