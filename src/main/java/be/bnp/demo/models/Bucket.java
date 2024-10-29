package be.bnp.demo.models;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;

public class Bucket {

    @SerializedName(value="bookList")
	@Expose
    private List<Book> bookList;

    private Map<String, Book> bookMap;

    @SerializedName(value="discount")
	@Expose
    private int discount;

    @SerializedName(value="bucketBasePrice")
	@Expose
    private BigDecimal bucketBasePrice;

    @SerializedName(value="bucketPrice")
	@Expose
    private BigDecimal bucketPrice;

    public Bucket(){
        this.bookList = new ArrayList<Book>();
        this.bookMap = new HashMap<String, Book>();
        this.discount = 0;
        this.bucketPrice = new BigDecimal(0);
        this.bucketBasePrice = new BigDecimal(0);
    }

    public List<Book> getBookList(){
        return bookList;
    }

    public int getDiscount(){
        return discount;
    }

    public void setDiscount(int discount){
        this.discount = discount;
    }

    public BigDecimal getBucketPrice(){
        return bucketPrice;
    }

    public void setBucketPrice(BigDecimal buckPrice){
        this.bucketPrice = buckPrice;
    }

    public BigDecimal getBucketBasePrice(){
        return bucketBasePrice;
    }

    public void setBucketBasePrice(BigDecimal buckBasePrice){
        this.bucketBasePrice = buckBasePrice;
    }

    public boolean addBookToBucket(Book book) {
        if (bookMap.get(book.getId()) != null){
            return false;
        }
        bookMap.put(book.getId(), book);
        bookList = new ArrayList<Book>();
        
        BigDecimal basePrice = new BigDecimal(0);
        for (Book b : bookMap.values()) {
            bookList.add(b);
            basePrice = basePrice.add(b.getPrice());
        }
        bucketBasePrice = basePrice;

        return true;
    }

    public boolean hasBook(Book book){
        return bookMap.get(book.getId()) != null;
    }

    public String toString(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(this);
		return gsonString;
    }
}
