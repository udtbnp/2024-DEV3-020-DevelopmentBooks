package be.bnp.demo.models;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Basket {
    @SerializedName(value="quantities")
	@Expose
    private List<BookQuantity> quantities;

    private Map<String, BookQuantity> mapQuantities;
    
    @SerializedName(value="buckets")
	@Expose
    private List<Bucket> buckets;

    @SerializedName(value="total")
	@Expose
    private BigDecimal total;

    public Basket(){
        this.mapQuantities = new HashMap<String, BookQuantity>();
        this.quantities = new ArrayList<BookQuantity>();
        this.buckets = new ArrayList<Bucket>();
        this.total = new BigDecimal(0);
    }


    public List<BookQuantity> getQuantities() {
        return quantities;
    }

    public void addBookToBasket(String bookId, int quantity) {
        if (mapQuantities == null) {
            mapQuantities = new HashMap<String, BookQuantity>();
        }
        BookQuantity bq = mapQuantities.get(bookId) ;
        if ( bq == null ){
            mapQuantities.put(bookId, new BookQuantity(bookId, quantity));
        }
        else {
            mapQuantities.put(bookId, new BookQuantity(bookId, bq.getQuantity()+ quantity));
        }

        quantities = new ArrayList<BookQuantity>();
        for (BookQuantity bqi : mapQuantities.values()) {
            quantities.add(bqi);
        }
    }

    public List<Bucket> getBuckets(){
        return buckets;
    }

    public void setBuckets(List<Bucket> buckets){
        this.buckets = buckets;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }

    public String toString(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(this);
		return gsonString;
    }
}
