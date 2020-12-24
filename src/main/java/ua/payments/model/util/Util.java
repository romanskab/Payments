package ua.payments.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    public String getPropertyValue(String propertyName){
        String propertyValue = "";
        Properties properties = new Properties();
        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        }catch (IOException e){
            e.printStackTrace();
        }
        return propertyValue;
    }
}
