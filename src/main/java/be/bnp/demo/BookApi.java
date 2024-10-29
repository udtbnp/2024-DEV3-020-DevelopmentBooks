package be.bnp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.bnp.demo.exceptions.BasketException;
import be.bnp.demo.models.Basket;
import be.bnp.processor.BasketProcessor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/books")
public class BookApi {

    @Autowired
    private BasketProcessor basketProcessor;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getJsonBookList(){
        basketProcessor.initBookList();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonString = gson.toJson(basketProcessor.getBookList());
		return gsonString;
    }

    @RequestMapping(
        value = "/submitBasket", 
        method = RequestMethod.POST, 
        consumes = "application/json;charset=UTF-8", 
        produces = "application/json;charset=UTF-8"
        )
    public String submitBasket(
        @RequestBody String basketString,
        HttpServletRequest request,
		HttpServletResponse response
    ) throws Exception {
        Basket basket = null;
        try {
            basket = basketProcessor.validateBasket(basketString);
        } catch (BasketException be) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "invalid basket");
            return null;
        }

        basket = basketProcessor.fillBuckets(basket);

        return basket.toString();
    }
}
