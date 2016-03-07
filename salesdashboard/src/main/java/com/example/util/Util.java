package com.example.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.RegistryType;
import org.wso2.carbon.registry.core.Registry;
import org.wso2.carbon.registry.core.Resource;
import org.wso2.carbon.registry.core.exceptions.RegistryException;

/**
 * Created by samith on 3/4/16.
 */
public class Util {

    private static Log log = LogFactory.getLog(Util.class);

    public static String getResourceFromRegistry(String resourcePath) throws Exception {
        String propValue = null;
        CarbonContext cCtx = CarbonContext.getThreadLocalCarbonContext();
        Registry registry = (Registry) cCtx.getRegistry(RegistryType.SYSTEM_GOVERNANCE);
        try{
            if (registry.resourceExists(resourcePath)) {
                Resource resource = registry.get(resourcePath);
                if (resource.getContent() != null) {
                    if (resource.getContent() instanceof String) {
                        propValue = (String) resource.getContent();
                    } else if (resource.getContent() instanceof byte[]) {
                        propValue = new String((byte[]) resource.getContent());
                    }
                }
            } else {
                log.warn("Resource does not exists in registry path : "+resourcePath);
            }
        }catch(RegistryException e) {
            log.error("Error while getting resource: "+resourcePath , e);
            throw new Exception("Error while getting resource: "+resourcePath,e);
        }
        return propValue;
    }

    public static String getExternalApiValue(String propName) throws Exception {
        String resourcePath = "/dependencies/" +
                              ConfigUtil.getPropertyValue(Constants.CONFIG_APP_KEY) + "/" +
                              ConfigUtil.getPropertyValue(Constants.CONFIG_EXTERNAL_API_NAME) + "/" + propName;
        return getResourceFromRegistry(resourcePath);
    }

    public static String getSubscribedApiValue(String propName) throws Exception {
        String resourcePath = "/dependencies/" +
                              ConfigUtil.getPropertyValue(Constants.CONFIG_APP_KEY) + "/" + propName;
        return getResourceFromRegistry(resourcePath);
    }

}
