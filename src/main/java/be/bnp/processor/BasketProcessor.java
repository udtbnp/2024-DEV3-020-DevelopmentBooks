package be.bnp.processor;

import be.bnp.demo.exceptions.BasketException;
import be.bnp.demo.models.Basket;
import be.bnp.demo.models.Book;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.math.BigDecimal;

@Service
public class BasketProcessor {
    public static final String BasketProcessor = "basketProcessor";

    private static List<Book> bookList;

    public List<Book> getBookList(){
		return bookList;
    }

    public void initEmptyBookList(){
        bookList = new ArrayList<Book>();
    }

    public void initBookList(){
        bookList = new ArrayList<Book>();
        Book book1 = new Book("book1", "Book name 1", new BigDecimal(50));
        Book book2 = new Book("book2", "Book name 2", new BigDecimal(50));
        Book book3 = new Book("book3", "Book name 3", new BigDecimal(50));
        Book book4 = new Book("book4", "Book name 4", new BigDecimal(50));
        Book book5 = new Book("book5", "Book name 5", new BigDecimal(50));
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);

    }


    public Basket validateBasket(String basketString) throws BasketException {

        return null;
    }

    public Basket fillBuckets(Basket basket){

        return null;
    }

    public Basket calculatBasketPrice(Basket basket){

        return null;
    }
    
}
