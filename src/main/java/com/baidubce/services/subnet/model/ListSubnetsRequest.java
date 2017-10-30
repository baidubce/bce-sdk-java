package com.baidubce.services.subnet.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request model to query subnet list
 */
public class ListSubnetsRequest extends ListRequest {

    /**
     * The id of vpc which this subnet belong.
     */
    private String vpcId;

    /**
     * the name of available zone which the subnet belong
     * through listZones, we can get all available zone info at current region
     * ee.g. "cn-gz-a"  "cn-gz-b"
     */
    private String zoneName;

    /**
     * The option param to describe the type of subnet create
     * See more detail on
     * <a href = "https://cloud.baidu.com/doc/VPC/API.html#.E5.88.9B.E5.BB.BA.E5.AD.90.E7.BD.91">
     *     Subnet API doc</a>
     */
    private String subnetType;

    /**
     * Configure zoneName for the request.
     *
     * @param  zoneName the name of available zone
     * @return ListSubnetsRequest with zone name
     */
    public ListSubnetsRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    /**
     * Configure vpc id for the request.
     *
     * @param  vpcId the vpcId of subnet
     * @return ListSubnetsRequest with vpcId
     */
    public ListSubnetsRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    /**
     * Configure subnet type for the request.
     *
     * @param  subnetType the type of subnet
     * @return ListSubnetsRequest with subnet type
     */
    public ListSubnetsRequest withSubnetType(String subnetType) {
        this.subnetType = subnetType;
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getSubnetType() {
        return subnetType;
    }

    public void setSubnetType(String subnetType) {
        this.subnetType = subnetType;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListSubnetsRequest with specified marker.
     */
    @Override
    public ListSubnetsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListSubnetsRequest with specified maxKeys.
     */
    @Override
    public ListSubnetsRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListSubnetsRequest with credentials.
     */
    @Override
    public ListSubnetsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
