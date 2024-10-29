package be.bnp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import be.bnp.demo.models.Book;
import be.bnp.demo.models.Basket;
import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testUpper() {
		String h = "hello";

		assertEquals("HELLO", DemoApplication.upper(h));
	}

	@Test
	void testGetEmptyBookListInit(){
		BookApi.initEmptyBookList();
		assertEquals(0, BookApi.getBookList().size());
	}
	@Test
	void testGetBookListInit(){
		BookApi.initBookList();
		assertEquals(5, BookApi.getBookList().size());
	}

	@Test
	void testBookToString(){
		Book book = new Book("book1", "Book name 1", new BigDecimal(50));
		String expected = "{\"id\":\"book1\",\"name\":\"Book name 1\",\"price\":50}";
		assertEquals(expected, book.toString());

	}

	@Test
	void testEmptyBasket(){
		Basket basket = new Basket();
		BigDecimal priceExpected = new BigDecimal(0);

		assertEquals(priceExpected, basket.getTotal());
		assertEquals(0, basket.getBuckets().size());
	}

}
