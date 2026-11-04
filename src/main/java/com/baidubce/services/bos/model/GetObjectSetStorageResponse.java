package com.baidubce.services.bos.model;

/** Storage statistics for an ObjectSet, grouped by storage class. */
public class GetObjectSetStorageResponse extends BosResponse {
    private ObjectSetStorageStat totalStorageStat;
    private ObjectSetStorageStat standardStorageStat;
    private ObjectSetStorageStat standardIAStorageStat;
    private ObjectSetStorageStat coldStorageStat;
    private ObjectSetStorageStat archiveStorageStat;
    private ObjectSetStorageStat mazStandardStorageStat;
    private ObjectSetStorageStat mazStandardIAStorageStat;
    private ObjectSetStorageStat mazColdStorageStat;

    public ObjectSetStorageStat getTotalStorageStat() {
        return totalStorageStat;
    }

    public void setTotalStorageStat(ObjectSetStorageStat value) {
        this.totalStorageStat = value;
    }

    public ObjectSetStorageStat getStandardStorageStat() {
        return standardStorageStat;
    }

    public void setStandardStorageStat(ObjectSetStorageStat value) {
        this.standardStorageStat = value;
    }

    public ObjectSetStorageStat getStandardIAStorageStat() {
        return standardIAStorageStat;
    }

    public void setStandardIAStorageStat(ObjectSetStorageStat value) {
        this.standardIAStorageStat = value;
    }

    public ObjectSetStorageStat getColdStorageStat() {
        return coldStorageStat;
    }

    public void setColdStorageStat(ObjectSetStorageStat value) {
        this.coldStorageStat = value;
    }

    public ObjectSetStorageStat getArchiveStorageStat() {
        return archiveStorageStat;
    }

    public void setArchiveStorageStat(ObjectSetStorageStat value) {
        this.archiveStorageStat = value;
    }

    public ObjectSetStorageStat getMazStandardStorageStat() {
        return mazStandardStorageStat;
    }

    public void setMazStandardStorageStat(ObjectSetStorageStat value) {
        this.mazStandardStorageStat = value;
    }

    public ObjectSetStorageStat getMazStandardIAStorageStat() {
        return mazStandardIAStorageStat;
    }

    public void setMazStandardIAStorageStat(ObjectSetStorageStat value) {
        this.mazStandardIAStorageStat = value;
    }

    public ObjectSetStorageStat getMazColdStorageStat() {
        return mazColdStorageStat;
    }

    public void setMazColdStorageStat(ObjectSetStorageStat value) {
        this.mazColdStorageStat = value;
    }
}
