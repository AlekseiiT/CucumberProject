package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * PojoWrapper class is used to create and work with structural data in form os POJO class
 * Mainly used for API test scenarios
 */
public class PojoWrapper {

    /**
     * Private constructor
     */
    private PojoWrapper() {
    }

    /**
     * Generates pojo class out of jsonString
     *
     * @param jsonString input value for mapping
     * @param clazz      pojo root class
     * @return Object class should be cast manually after returning
     */
    @SneakyThrows
    public static Object getPojoFromJsonString(String jsonString, Class clazz) {
        return new ObjectMapper()
                .readValue(jsonString, clazz);
    }
}
