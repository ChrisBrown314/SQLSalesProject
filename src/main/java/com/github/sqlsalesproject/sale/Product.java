package com.github.sqlsalesproject.sale;


import java.util.HashMap;

//TODO - Scalable product list with more accurate supplies

//TODO - Supply counter

//TODO - initialize from file!
// -> chicken & hamburger

/**The enum Product.
 * Contains a list of all possible products, their sale price and production cost, as well as a
 * toString method.
 * @author Christopher Brown
 */
public class Product {
    private static final HashMap<String, Product> allProducts = new HashMap<>();
    private final HashMap<Supply, Double> supplyList;
    private final Double salePrice;
    private Double productionCost;
    private final String nameOfProduct;

    //~ Constructors ~//

    public Product (String nameOfProduct, Double salePrice, HashMap<Supply, Double> supplyList) {
        allProducts.put(nameOfProduct, this);
        this.nameOfProduct = nameOfProduct;
        this.salePrice = salePrice;
        this.supplyList = supplyList;
        // Adds all supply cost to the productionCost of this product
        supplyList.forEach((supply, unitReq) -> productionCost += supply.getCost(unitReq));
    }

    public Product (String nameOfProduct, Double salePrice) {
        allProducts.put(nameOfProduct, this);
        this.nameOfProduct = nameOfProduct;
        this.salePrice = salePrice;
        this.supplyList = new HashMap<Supply, Double>();
        productionCost = 0.0;
    }


    //~ Tools ~//


    public void addSupply(Supply supplyToAdd, Double unitReq) {
        supplyList.put(supplyToAdd, unitReq);  // Adds the supply to the supply requirement
        productionCost += supplyToAdd.getCost(unitReq);  // Updates the production cost
    }

    @Override
    public String toString() {
        return nameOfProduct;
    }

    //~ Getters and Setters ~//


    public Double getCostToProduce() {
        return this.productionCost;
    }

    public Double getSalePrice() {
        return this.salePrice;
    }

    public static Double getCostToProduce(String nameOfProduct) {
        return allProducts.get(nameOfProduct).productionCost;
    }

    public static Double getSalePrice(String nameOfProduct) {
        return allProducts.get(nameOfProduct).salePrice;
    }

    public static Product getProduct(String nameOfProduct) {
        return allProducts.get(nameOfProduct);
    }

    public HashMap<Supply, Double> getSuppliesRequired() {
        return supplyList;
    }
}
