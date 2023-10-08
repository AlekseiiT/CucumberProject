package org.example.api;

import io.restassured.response.Response;

/**
 * Api manager class is used for sending REST API requests and return response object
 */
public final class ApiManager {
    /**
     * Private constructor
     */
    private ApiManager() {
    }

    /**
     * URL for request
     */
    private static final String GET_SUBUNIT = ApiRoutes.getGetSubunit();

    public static Response getSubunit() {
        return BaseRequestSpecification.getBaseRequestSpecification()
                .get(GET_SUBUNIT);
    }
}
