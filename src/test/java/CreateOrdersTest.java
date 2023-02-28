import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.OrderClient;
import org.example.Orders;
import org.example.RandomOrders;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreateOrdersTest {

    private final Orders orders;

    OrderClient orderClient = new OrderClient();

    public CreateOrdersTest(Orders orders) {
        this.orders = orders;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                //выбран самокат черного цвета
                {new RandomOrders(new String[]{"BLACK"})},
                //выбран самокат серого цвета
                {new RandomOrders(new String[]{"GREY"})},
                //выбраны одновременно оба цвета самоката
                {new RandomOrders(new String[]{"BLACK", "GREY"})},
                //цвет самоката не выбран
                {new RandomOrders(null)},
        };
    }

    @Test
    @DisplayName("Проверяем код и тело ответа в случае валидного запроса")
    @Description("Ожидаемый результат: код 201, заказ создан, тело запроса содержит track")

    public void checkCreateValidOrder() {
        orderClient.createOrder(orders)
                .then()
                    .assertThat()
                        .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}