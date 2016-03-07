package com.example.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by samith on 3/4/16.
 */
public class ConfigUtil {

    private static String propFile = "config.properties";
    private static Properties properties = new Properties();
    private static final Log log = LogFactory.getLog(ConfigUtil.class);

    static {
        try {
            loadAppCloudConfig();
        } catch (Exception e) {
            String message = "Unable to load configuration file : " + propFile;
            log.error(message, e);
        }
    }


    private static void loadAppCloudConfig() throws Exception {
        InputStream inputStream = null;
        try {

            inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(propFile);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            String message = "The "+propFile+" file not found";
            throw new Exception(message, e);
        } catch (IOException e) {
            String message = "Unable to read"+propFile+ " file";
            throw new Exception(message, e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                String message = "Unable to close file input stream while reading configuration file : "+propFile;
                log.error(message, e);
            }
        }
    }

    public static String getPropertyValue(String property) {
        String value = properties.getProperty(property);
        if (value == null) {
            String message = "The given property: " + property + " is not found from the :"+propFile;
            log.warn(message);
        }
        return value;
    }
}
