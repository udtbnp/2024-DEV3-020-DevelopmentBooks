package be.bnp.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.bnp.demo.models.Book;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@RestController
@RequestMapping("/books")
public class BookApi {

    private static List<Book> bookList;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public static String getJsonBookList(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(getBookList());
		return gsonString;
    }
    public static List<Book> getBookList(){
		return bookList;
    }
    public static void initEmptyBookList(){
        bookList = new ArrayList<Book>();
    }
    public static void initBookList(){
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


}
