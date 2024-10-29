package be.bnp.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.bnp.demo.models.Book;
import be.bnp.demo.models.Bucket;
import be.bnp.processor.BasketProcessor;
import be.bnp.demo.exceptions.BasketException;
import be.bnp.demo.models.Basket;
import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
    private BasketProcessor basketProcessor;

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
		basketProcessor.initBookList();
		assertEquals(0, basketProcessor.getBookList().size());
	}
	@Test
	void testGetBookListInit(){
		basketProcessor.initBookList();
		assertEquals(5, basketProcessor.getBookList().size());
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

	@Test
	void testValidateBasket(){
		

		String basketString1 = """
				{
					\"quantities\":[
						{
							\"bookId\" : \"book1\",
							\"quantity\" : 1
						}
					]
				}
				""";
		String basketString2 = """
			{
				\"quantities\":[
						{
							\"bookId\" : \"book2\",
							\"quantity\" : 1
						}
					]
			}
			""";
		String basketString15 = """
			{
				\"quantities\":[
						{
							\"bookId\" : \"book15\",
							\"quantity\" : 1
						}
					]
			}
			""";

		assertDoesNotThrow(()->{
			Basket b1 = basketProcessor.validateBasket(basketString1);
		});
		assertDoesNotThrow(()->{
			Basket b2 = basketProcessor.validateBasket(basketString2);
		});
		assertThrows(BasketException.class,()->{
			Basket b15 = basketProcessor.validateBasket(basketString15);
		}
		);
	}

	@Test
	void testFillBuckets(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);

		Bucket bucket = new Bucket();
		bucket.addBookToBucket(basketProcessor.getBookList().get(0));

		Basket result = basketProcessor.fillBuckets(basket);

		assertEquals(bucket, result.getBuckets().get(0));
	}

}
