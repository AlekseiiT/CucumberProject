package org.example.api;

import io.restassured.response.Response;

public final class ApiManager {
    private ApiManager() {
    }

    private static final String GET_SUBUNIT = ApiRoutes.getGetSubunit();

    public static Response getSubunit() {
        return BaseRequestSpecification.getBaseRequestSpecification()
                .get(GET_SUBUNIT);
    }
}
