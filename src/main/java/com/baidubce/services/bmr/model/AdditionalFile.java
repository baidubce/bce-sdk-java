package com.baidubce.services.bmr.model;

/**
 * Represent configuration for a BMR step additional file.
 * <p>
 * An additional file is a mapping of remote file and local file.
 * Remote file represents a file which we can not access by Instance local path, like a BOS file.
 * Local file represents a file which we can use in the arguments of a BMR step,
 * like the arguments of streaming step.
 */
public class AdditionalFile {
    private String remote;

    private String local;

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Configure the remote file of the additional file.
     *
     * @param remote The remote file of the additional file.
     *
     * @return AdditionalFile
     */
    public AdditionalFile withRemote(String remote) {
        this.setRemote(remote);
        return this;
    }

    /**
     * Configure the local file of the additional file.
     *
     * @param local The local file of the additional file.
     *
     * @return AdditionalFile
     */
    public AdditionalFile withLocal(String local) {
        this.setLocal(local);
        return this;
    }
}
