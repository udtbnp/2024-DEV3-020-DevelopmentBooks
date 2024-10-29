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
    private Map<String, Integer> quantities;
    
    @SerializedName(value="buckets")
	@Expose
    private List<Bucket> buckets;

    @SerializedName(value="total")
	@Expose
    private BigDecimal total;

    public Basket(){
        this.quantities = new HashMap<String, Integer>();
        this.buckets = new ArrayList<Bucket>();
        this.total = new BigDecimal(0);
    }


    public Map<String, Integer> getQuantities() {
        return quantities;
    }

    public void addBookToBasket(String bookId, int quantity) {
        if (quantities == null) {
            quantities = new HashMap<String, Integer>();
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
