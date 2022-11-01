import groovy.json.*
import com.amazonaws.util.EC2MetadataUtils;

class EC2MetaDataJSON {
    String privateIpAddress;
    String hostname;
    String instanceAction;
    String instanceId;
    String instanceType;

    EC2MetaDataJSON() {
        privateIpAddress = EC2MetadataUtils.getPrivateIpAddress();
        hostname = EC2MetadataUtils.getLocalHostName();
        instanceAction = EC2MetadataUtils.getInstanceAction();
        instanceId = EC2MetadataUtils.getInstanceId();
        instanceType = EC2MetadataUtils.getInstanceType();

    }

    public static void main(String[] arg) {
        println new JsonBuilder(new EC2MetaDataJSON()).toPrettyString()
    }
}
