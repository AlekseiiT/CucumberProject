package org.example.api;

import lombok.Getter;
import org.example.enums.ConfigProperties;
import org.example.utils.PropertyUtils;

public final class ApiRoutes {

    private ApiRoutes() {
    }

    @Getter
    private static final String baseUrl = PropertyUtils.getProperty(ConfigProperties.URL);
    @Getter
    private static final String getSubunit = "/web/index.php/api/v2/dashboard/employees/subunit";

}
