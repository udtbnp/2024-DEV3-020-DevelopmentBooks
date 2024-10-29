package be.bnp.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
		basketProcessor.initEmptyBookList();
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
	void testFillBucketsBucketBasePrice(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);

		Basket result = basketProcessor.fillBuckets(basket);

		assertEquals(new BigDecimal(50), result.getBuckets().get(0).getBucketBasePrice());
	}

	@Test
	void testFillBucketsOneBookNoCheck(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);

		Bucket bucket = new Bucket();
		bucket.addBookToBucket(basketProcessor.getBookList().get(0));

		Basket result = basketProcessor.fillBuckets(basket);

		// check one bucket in basket
		assertEquals(1, result.getBuckets().size());
		assertEquals(bucket.getBookList().get(0), result.getBuckets().get(0).getBookList().get(0));
	}

	@Test
	void testFillBucketsTwoSameBooks(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);
		basket.addBookToBasket("book1", 1);
		
		Basket result = basketProcessor.fillBuckets(basket);

		assertEquals(2, result.getBuckets().size());
	}

	@Test
	void testFillBucketsTwoDifferentBooks(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);
		basket.addBookToBasket("book2", 1);

		Bucket bucket = new Bucket();
		bucket.addBookToBucket(basketProcessor.getBookList().get(0));

		Basket result = basketProcessor.fillBuckets(basket);

		assertEquals(1, result.getBuckets().size());
	}

	@Test
	void testFillBucketsBucketPriceWithDiscountBasic(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 1);
		
		Basket result = basketProcessor.fillBuckets(basket);
		assertEquals(1, result.getBuckets().size());
		assertEquals(new BigDecimal(50), result.getBuckets().get(0).getBucketBasePrice());
		assertEquals(0, result.getBuckets().get(0).getDiscount());
		assertEquals(new BigDecimal("50"), result.getBuckets().get(0).getBucketPrice());
		assertEquals(new BigDecimal("50"), basket.getTotal());

		basket.addBookToBasket("book2", 1);
		Basket result2 = basketProcessor.fillBuckets(basket);
		assertEquals(1, result2.getBuckets().size());
		assertEquals(new BigDecimal(100), result2.getBuckets().get(0).getBucketBasePrice());
		assertEquals(5, result2.getBuckets().get(0).getDiscount());
		assertEquals(new BigDecimal("95.00"), result2.getBuckets().get(0).getBucketPrice());
		assertEquals(new BigDecimal("95.00"), basket.getTotal());

		basket.addBookToBasket("book3", 1);
		Basket result3 = basketProcessor.fillBuckets(basket);
		assertEquals(1, result3.getBuckets().size());
		assertEquals(new BigDecimal(150), result3.getBuckets().get(0).getBucketBasePrice());
		assertEquals(10, result3.getBuckets().get(0).getDiscount());
		assertEquals(new BigDecimal("135.0"), result3.getBuckets().get(0).getBucketPrice());
		assertEquals(new BigDecimal("135.0"), basket.getTotal());

		basket.addBookToBasket("book4", 1);
		Basket result4 = basketProcessor.fillBuckets(basket);
		assertEquals(1, result4.getBuckets().size());
		assertEquals(new BigDecimal(200), result4.getBuckets().get(0).getBucketBasePrice());
		assertEquals(20, result4.getBuckets().get(0).getDiscount());
		assertEquals(new BigDecimal("160.0"), result4.getBuckets().get(0).getBucketPrice());
		assertEquals(new BigDecimal("160.0"), basket.getTotal());

		basket.addBookToBasket("book5", 1);
		Basket result5 = basketProcessor.fillBuckets(basket);
		assertEquals(1, result5.getBuckets().size());
		assertEquals(new BigDecimal(250), result5.getBuckets().get(0).getBucketBasePrice());
		assertEquals(25, result5.getBuckets().get(0).getDiscount());
		assertEquals(new BigDecimal("187.50"), result5.getBuckets().get(0).getBucketPrice());
		assertEquals(new BigDecimal("187.50"), basket.getTotal());

	}

	@Test
	void testComplexBucketsPriority0(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 2);
		basket.addBookToBasket("book2", 1);
		basket.addBookToBasket("book3", 1);
		

		basketProcessor.fillBuckets(basket);
		assertEquals(new BigDecimal("185.0"), basket.getTotal());
	}

	@Test
	void testComplexBucketsPriority(){
		Basket basket = new Basket();
		basket.addBookToBasket("book1", 2);
		basket.addBookToBasket("book2", 2);
		basket.addBookToBasket("book3", 2);
		basket.addBookToBasket("book4", 1);
		basket.addBookToBasket("book5", 1);

		basketProcessor.fillBuckets(basket);
		assertEquals(new BigDecimal("320.0"), basket.getTotal());
	}
	
}
