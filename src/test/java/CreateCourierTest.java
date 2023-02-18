import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.Courier;
import org.example.CourierClient;
import org.example.RandomCourier;
import org.junit.After;
import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();
    Courier courier = new RandomCourier();

    @Test
    @DisplayName("Проверяем код ответа в случае такого же запроса")
    @Description("Ожидаемый результат: код 201, курьер создан")

    public void checkStatusForCreateSameCourier() {
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    @DisplayName("Проверяем тело ответа")
    @Description("Ожидаемый результат: соответсвие тела (ok, true)")

    public void checkBodyForCreateSameCourier() {
        courierClient.createCourier(courier)
                .then()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Проверяем код ответа в случае запроса без логина")
    @Description("Ожидаемый результат: запрос без логина ведет к коду клиентской ошибки 400")

    public void checkStatusForCreateCourierWithoutLogin() {
        courier = new Courier("", randomPassword,randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверяем сообщения в случае запроса без логина")
    @Description("Ожидаемый результат: выводится сообщение \"Недостаточно данных для создания учетной записи\"")

    public void checkCreateCourierWithoutLogin() {
        courier = new Courier("",randomPassword, randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Проверяем код ответа в случае запроса без пароля")
    @Description("Ожидаемый результат: запрос без пароля ведет к коду клиентской ошибки 400")

    public void checkStatusForCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверяем сообщения в случае запроса без логина")
    @Description("Ожидаемый результат: выводится сообщение \"Недостаточно данных для создания учетной записи\"")

    public void checkCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().
                body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Проверяем код ответа в случае запроса с таким же логином")
    @Description("Ожидаемый результат: повторный запрос с уже существующего логина ведет к коду клиентской ошибки 409.")

    public void checkStatusForDuplicationCreateCourier() {
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(409);
    }

    @Test
    @DisplayName("Проверяем сообщение в случае запроса с таким же логином")
    @Description("Ожидаемый результат: выводится сообщение \"Этот логин уже используется. Попробуйте другой.\"")

    public void checkDuplicationCreateCourier() {
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteCourier(){
        courierClient.deleteCourier(courier);
    }

}