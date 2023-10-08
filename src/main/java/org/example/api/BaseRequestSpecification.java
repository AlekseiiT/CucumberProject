package org.example.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.example.driver.Driver;
import org.example.driver.ProxyManager;
import org.example.enums.ConfigProperties;
import org.example.pages.LoginPage;
import org.example.utils.PropertyUtils;

import java.util.List;
import java.util.stream.Collectors;

public final class BaseRequestSpecification {

    private BaseRequestSpecification() {
    }

    private static final String BASE_URL = ApiRoutes.getBaseUrl();

    public static RequestSpecification getBaseRequestSpecification() {
        String token = BaseRequestSpecification.getToken();

        return RestAssured.given()
                .header("Cookie", token)
                .baseUri(BASE_URL);
    }

    public static String getToken() {
        Driver.initDriver();
        new LoginPage()
                .enterLogin("Admin")
                .enterPassword("admin123")
                .clickLoginBtn();

        if (!PropertyUtils.hasProperty(ConfigProperties.APITOKEN)){
            Har har = ProxyManager.getProxy().getHar();
            List<HarNameValuePair> messages = har.getLog().getEntries().stream().filter(e -> e.getRequest().getUrl().contains("messages")).collect(Collectors.toList()).get(0).getRequest().getHeaders();

            for (HarNameValuePair pair : messages) {
                if (pair.getName().contains("Cookie"))
                    PropertyUtils.setProperty(ConfigProperties.APITOKEN, pair.getValue());
            }
            Driver.quitDriver();
        }
        System.out.println(PropertyUtils.getProperty(ConfigProperties.APITOKEN));
        return PropertyUtils.getProperty(ConfigProperties.APITOKEN);
    }

}