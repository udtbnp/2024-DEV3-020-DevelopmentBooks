package be.bnp.demo.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookQuantity {
    @SerializedName(value="bookId")
	@Expose
    private String bookId;

    @SerializedName(value="quantity")
	@Expose
    private int quantity;

    public String getBookId(){
        return bookId;
    }
    public void setBookId(String bookId){
        this.bookId = bookId;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public BookQuantity (String bookId, int quantity){
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String toString(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(this);
		return gsonString;
    }
}
