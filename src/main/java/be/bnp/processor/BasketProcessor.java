package be.bnp.processor;

import be.bnp.demo.exceptions.BasketException;
import be.bnp.demo.models.Basket;
import be.bnp.demo.models.Book;
import be.bnp.demo.models.BookQuantity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;

@Service
public class BasketProcessor {
    public static final String BasketProcessor = "basketProcessor";

    private static List<Book> bookList;
    private static Map<String, Book> bookMap;

    public List<Book> getBookList(){
        if (bookList == null){
            initBookList();
        }
		return bookList;
    }
    public Map<String, Book> getBookMap(){
		return bookMap;
    }

    public void initEmptyBookList(){
        bookList = new ArrayList<Book>();
        bookMap = new HashMap<String, Book>();
    }

    public void initBookList(){
        Book book1 = new Book("book1", "Book name 1", new BigDecimal(50));
        Book book2 = new Book("book2", "Book name 2", new BigDecimal(50));
        Book book3 = new Book("book3", "Book name 3", new BigDecimal(50));
        Book book4 = new Book("book4", "Book name 4", new BigDecimal(50));
        Book book5 = new Book("book5", "Book name 5", new BigDecimal(50));
        
        bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);

        bookMap = new HashMap<String, Book>();
        bookMap.put(book1.getId(), book1);
        bookMap.put(book2.getId(), book2);
        bookMap.put(book3.getId(), book3);
        bookMap.put(book4.getId(), book4);
        bookMap.put(book5.getId(), book5);

    }


    public Basket validateBasket(String basketString) throws BasketException {
        Gson gson = new Gson();
        Basket basket = null;
        try {
            basket = gson.fromJson(basketString, Basket.class);
        }
        catch (JsonSyntaxException jse){
            System.err.println(jse.getMessage());
            throw new BasketException("invalid json", jse);
        }

        if (bookList == null){
            initBookList();
        }
        for (BookQuantity bq : basket.getQuantities()) {
            if (bookMap.get(bq.getBookId()) == null){
                throw new BasketException("invalid book in basket");
            }            
        }

        return basket;
    }

    public Basket fillBuckets(Basket basket){
        
        return null;
    }

    public Basket calculatBasketPrice(Basket basket){

        return null;
    }
    
}
