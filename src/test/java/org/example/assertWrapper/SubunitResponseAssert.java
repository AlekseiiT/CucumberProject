package org.example.assertWrapper;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;
import org.example.api.PojoWrapper;
import org.example.api.pojos.subunit.RootSubunit;
import org.example.enums.LogType;

import java.util.Objects;

import static org.example.reports.FrameworkLogger.log;

public class SubunitResponseAssert extends AbstractAssert<SubunitResponseAssert, Response> {

    RootSubunit pojo;
    protected final SoftAssertions softAssertions;

    protected SubunitResponseAssert(Response response) {
        super(response, SubunitResponseAssert.class);
        softAssertions = new SoftAssertions();
    }

    public static SubunitResponseAssert assertThat(Response response) {
        return new SubunitResponseAssert(response);
    }

    public void assertAll() {
        softAssertions.assertAll();
    }


    private RootSubunit getPojo() {
        if (Objects.isNull(pojo))
            pojo = (RootSubunit) PojoWrapper.getPojoFromJsonString(actual.asString(), RootSubunit.class);
        return pojo;
    }

    public SubunitResponseAssert statusCodeIs(int expectedStatusCode) {
        int statusCode = actual.statusCode();
        softAssertions.assertThat(statusCode)
                .isEqualTo(expectedStatusCode);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка статус кода. Ожидаем: " + expectedStatusCode + " . Факт: " + statusCode);
        return this;
    }

    public SubunitResponseAssert metaSubunitCount(int value) {
        int totalSubunitCount = getPojo().getMeta().getTotalSubunitCount();
        softAssertions.assertThat(totalSubunitCount)
                .as("metaSubunitCount")
                .isEqualTo(value);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка metaSubunitCount. Ожидаем: " + value + " . Факт: " + totalSubunitCount);
        return this;
    }

    public SubunitResponseAssert metaOtherEmployeeCount(int value) {
        int otherEmployeeCount = getPojo().getMeta().getOtherEmployeeCount();
        softAssertions.assertThat(otherEmployeeCount)
                .as("otherEmployeeCount")
                .isEqualTo(value);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка otherEmployeeCount. Ожидаем: " + value + " . Факт: " + otherEmployeeCount);
        return this;
    }

    public SubunitResponseAssert metaUnassignedEmployeeCount(int value) {
        int unassignedEmployeeCount = getPojo().getMeta().getUnassignedEmployeeCount();
        softAssertions.assertThat(unassignedEmployeeCount)
                .as("metaUnassignedEmployeeCount")
                .isEqualTo(value);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка unassignedEmployeeCount. Ожидаем: " + value + " . Факт: " + unassignedEmployeeCount);
        return this;
    }

    public SubunitResponseAssert dataCount(int value) {
        int totalSubunitCount = getPojo().getData().size();
        softAssertions.assertThat(totalSubunitCount)
                .as("dataCount")
                .isEqualTo(value);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка totalSubunitCount. Ожидаем: " + value + " . Факт: " + totalSubunitCount);
        return this;
    }

    public SubunitResponseAssert subunitPresent(String subunitName) {
        long count = getPojo().getData().stream().filter(e -> e.getSubunit().getName().contains(subunitName)).count();
        softAssertions.assertThat(count)
                .as("subunitName " + subunitName + " in data")
                .isEqualTo(1);
        log(LogType.LOG_REPORT_CONSOLE, "Проверка кол-ва " + subunitName + " в списке. Ожидаем: 1. " + " . Факт: " + count);
        return this;
    }
}
