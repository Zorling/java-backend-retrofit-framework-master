package ru.geekbrains.tests;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import ru.geekbrains.dto.Delete;
import ru.geekbrains.dto.Product;
import ru.geekbrains.enums.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static ru.geekbrains.tests.BaseTest.faker;
import static ru.geekbrains.tests.BaseTest.productService;
public class ProductsTestGet extends BaseTest{
    Integer code;

    //Проверка запроса всех продуктов ошибка 500
    @Test
    void GetProductWithIntPriceTest() throws IOException {

        Response<Product> response = productService
                .getAllProduct()
                .execute();
        code = response.code();
        assert code.equals(500);
    }
}
