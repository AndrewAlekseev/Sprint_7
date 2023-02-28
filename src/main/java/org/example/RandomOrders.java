package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/*генерируем рандомные значения для:
 - Имени, Фамилии, Адреса, Телефона заказчика
 - Ближайшей к заказчику станции метро
 - Количеству дней аренды
 - Даты доставки
 - Комментария от заказчика
 - Предпочитаемого цвета самоката
 */
public class RandomOrders extends Orders  {

    //генераци рандомного имени
    private static final String RANDOM_FIRSTNAME = RandomStringUtils.random(10, true, false);

    //генерация рандомной фамилии
    private static  final String RANDOM_LASTNAME = RandomStringUtils.random(10, true, false);

    //генерация рандомного адреса
    private static final String RANDOM_ADDRESS = RandomStringUtils.random(10, true, false);

    //генерация рандомной станции метро
    private static final int RANDOM_METRO_STATION =  ThreadLocalRandom.current().nextInt(1, 11);

    //генерация рандомного номера телефона
    private static final String RANDOM_PHONE_NUMBER = RandomStringUtils.random(10, false, true);

    //генерация рандомного времени аренды
    private static final int RANDOM_RENT_TIME= ThreadLocalRandom.current().nextInt(1, 11);

    //генерация рандомной даты аренды
    private static final String RANDOM_DELIVERY_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    //генерация рандомного комментария от заказчика, а так же выбор  цвета самоката
    private static final String RANDOM_COMMENT = RandomStringUtils.random(10, true, false);
    private  String[] newColor;


    public RandomOrders(String[] newColour) {
        super(
                RANDOM_FIRSTNAME,
                RANDOM_LASTNAME,
                RANDOM_ADDRESS,
                RANDOM_METRO_STATION,
                RANDOM_PHONE_NUMBER,
                RANDOM_RENT_TIME,
                RANDOM_DELIVERY_DATE,
                RANDOM_COMMENT,
                newColour);
    }
}