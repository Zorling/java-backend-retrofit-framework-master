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

public class ProductsTestGetID extends BaseTest{
    Product product;
    Integer id;
    Integer code;

    void createsProducts() throws IOException {
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        product.setId(id);

    }

    @BeforeEach
    void setUp() throws IOException {
        product=  new Product()
                .withTitle(faker.food().dish())
                .withCategoryTitle(Category.FOOD.getName())
                .withPrice(1000);
        createsProducts();
    }
    @AfterEach
    void tearDown() throws IOException {
        productService.deleteProduct(id).execute();
    }
    //Тест получения информации о продукте по id code200

    @Test
    void GetProductWithIntPriceTest() throws IOException {

        Response<Product> response = productService
                .getProduct(product.getId())
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    //Тест получения информации о несуществующем продукте по id code404

    @Test
    void GetProductWithIntPriceTest404() throws IOException {

        Response<Product> response = productService
                .getProduct(0000)
                .execute();
        code = response.code();
        assert code.equals(404);

    }


}
