package be.bnp.demo.models;

import java.math.BigDecimal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName(value="id", alternate={"ID","Id"})
	@Expose
    private String id;

    @SerializedName(value="name")
	@Expose
    private String name;
    
    @SerializedName(value="price")
	@Expose
    private BigDecimal price;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    protected Book(){}
    
    public Book(String id, String name, BigDecimal price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public String toString(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(this);
		return gsonString;
    }
}
