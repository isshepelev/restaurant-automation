package ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Basket implements Serializable{
    private List<Product> products = new ArrayList<>();
    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public void clearBasket(){
        products.clear();
    }

}
