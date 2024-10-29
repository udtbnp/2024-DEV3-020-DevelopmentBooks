package be.bnp.demo.models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.math.BigDecimal;

public class Bucket {

    @SerializedName(value="bookList")
	@Expose
    private List<Book> bookList;

    @SerializedName(value="discount")
	@Expose
    private int discount;

    @SerializedName(value="bucketPrice")
	@Expose
    private BigDecimal bucketPrice;

    public Bucket(){
        this.bookList = new ArrayList<Book>();
        this.discount = 0;
        this.bucketPrice = new BigDecimal(0);
    }

    public List<Book> getBookList(){
        return bookList;
    }

    public int getDiscount(){
        return discount;
    }

    public void setDisckout(int discount){
        this.discount = discount;
    }

    public BigDecimal bucketPrice(){
        return bucketPrice;
    }

    public void setBucketPrice(BigDecimal buckPrice){
        this.bucketPrice = buckPrice;
    }

    public String toString(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(this);
		return gsonString;
    }
}
