# Cucumber Test Framework

____

### Основные моменты:

- Сборщик - Maven
- Test runner - TestNG
- UI - Selenium
- API - Rest Assured, Jackson
- Репорт - extend reports with cucumber adapter plugin
- Логирование - slf4j, Logback
- Assertins - AssertJ<br/>
  

### Основные концепты:

- OOP
- Page Objects, Fluent/Chain of invocations
```
  public class LoginPage extends BasePage {
      private final By loginInput = By.xpath("//input[@name='username']");
      private final By passwordInput = By.xpath("//input[@name='password']");
      private final By loginBtn = By.xpath("//button[@type='submit']");
  
      public LoginPage enterLogin(String login) {
          sendKeys(loginInput, login, WaitStrategy.PRESENCE, "Логин");
          return this;
      }

      public LoginPage enterPassword(String password) {
          sendKeys(passwordInput, password, WaitStrategy.PRESENCE, "Пароль");
          return this;
      }

      public void clickLoginBtn() {
          click(loginBtn, WaitStrategy.CLICKABLE, "Кнопка login");
      }
}
```
- Driver Factories
- BDD, Scenario, Scenario outline
```
Feature: Login functionality for OrangeHRM website

  As a user of the OrangeHRM website
  I want to be able to log in with provided account
  SO that I can access my features and manage my main tasks

  @UiScenario
  Scenario: Valid login
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully

  @UiScenario
  Scenario Outline: Invalid login with parameters
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username             | password    | error_message       |
      | invaliduser@mail.com | invalidPass | Invalid credentials |
      | adminInvalid         | validPass   | Invalid credentials |
      | validMail@mail.com   | invalidPass | Invalid credentials |
```
- Value Object, Asseert Object

```
//Test step for assert REST response with Assert Object
@Then("I check their values")
public void iCheckTheirValues() {
    SubunitResponseAssert.assertThat(response)
          .statusCodeIs(200)
          .subunitPresent("Engineering")
          .assertAll();
}

//AssertMethod
public SubunitResponseAssert statusCodeIs(int expectedStatusCode) {
    int statusCode = actual.statusCode();
    softAssertions.assertThat(statusCode)
          .isEqualTo(expectedStatusCode);
    log(LogType.LOG_REPORT_CONSOLE, "Проверка статус кода. Ожидаем: " + expectedStatusCode + " . Факт: " + statusCode);
    return this;
}
 
//AssertMethod   
public SubunitResponseAssert subunitPresent(String subunitName) {
    long count = getPojo().getData().stream().filter(e -> e.getSubunit().getName().contains(subunitName)).count();
    softAssertions.assertThat(count)
           .as("subunitName " + subunitName + " in data")
           .isEqualTo(1);
    log(LogType.LOG_REPORT_CONSOLE, "Проверка кол-ва " + subunitName + " в списке. Ожидаем: 1. " + " . Факт: " + count);
    return this;
}
```
- POJO classes
```
//POJO Wrapper
/**
* Generates pojo class out of jsonString
*
* @param jsonString input value for mapping
* @param clazz      pojo root class
* @return Object class should be cast manually after returning
*/
@SneakyThrows
public static Object getPojoFromJsonString(String jsonString, Class clazz) {
    return new ObjectMapper().readValue(jsonString, clazz);
}

//POJO Root class
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootSubunit {
    private List<Datum> data;
    private Meta meta;
    private List<Object> rels;
}

//POJO serialization
pojo = (RootSubunit) PojoWrapper.getPojoFromJsonString(actual.asString(), RootSubunit.class)
```
- Property utils
```
static {
    try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath())) {
        property.load(file);
        property.forEach((key, value) -> CONFIG_MAP.put(String.valueOf(key), String.valueOf(value)));
    } catch (IOException e) {
        e.printStackTrace();
        System.exit(0);
    }
}

public static String getProperty(ConfigProperties key) {
    if (Objects.isNull(CONFIG_MAP.get(key.name().toLowerCase()))) {
        throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
    }
    return CONFIG_MAP.get(key.name().toLowerCase());
}
```
- Enums classes for constants
```
/**
* Enum lists available framework properties
*/
public enum ConfigProperties {
    URL,
    HEADLESSMODE,
    PASSEDSTEPSCREENSHOTS,
    APITOKEN
}
```
- Proxy for Selenium driver

### Репорт скриншоты

Успешный сценарий
![Alt text](https://user-images.githubusercontent.com/53654093/273474149-da4662e9-cda0-42da-8909-4542fefc1b1f.png?raw=true "Success")

Упавший сценарий
![Alt text](https://user-images.githubusercontent.com/53654093/273474196-66b0805f-3ce6-43bf-83ab-d3751d0e9b63.png?raw=true "Fail")

Метрики со статистикой
![Alt text](https://user-images.githubusercontent.com/53654093/273474436-49a2f51f-f211-4fcf-b82f-cbf03245a859.png?raw=true "Fail")


