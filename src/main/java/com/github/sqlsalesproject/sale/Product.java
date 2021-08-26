package com.github.sqlsalesproject.sale;

import com.github.sqlsalesproject.tools.PropertyReader;
import static com.github.sqlsalesproject.tools.PropertyReader.fetchProductionCost;
import static com.github.sqlsalesproject.tools.PropertyReader.fetchSalePrice;

/**The enum Product.
 * Contains a list of all possible products, their sale price and production cost, as well as a
 * toString method.
 * @author Christopher Brown
 */
public enum Product {
    /**The chicken sandwich.
     * Contains information about the chicken sandwich product.
     * Fetches the production cost and sale price using the fetch methods
     * of {@link PropertyReader}.
     */
    CHICKEN_SANDWICH (fetchProductionCost("chickensandwich"), fetchSalePrice("chickensandwich"),
            "chicken sandwich"),
    /**Chicken Strips.
     * Contains information about the chicken strip product.
     * Fetches the production cost and sale price using the fetch methods
     * of {@link PropertyReader}.
     */
    CHICKEN_STRIPS (fetchProductionCost("chickenstrips"), fetchSalePrice("chickenstrips"),
            "chicken strips"),
    /**The hamburger.
     * Contains information about the hamburger product.
     * Fetches the production cost and sale price using the fetch methods
     * of {@link PropertyReader}.
     */
    HAMBURGER( fetchProductionCost("hamburger"), fetchSalePrice("hamburger"),"hamburger");

    /**Cost to produce. The production cost of a product.*/
    private final double COST_TO_PRODUCE;
    /**Sell price. The price at which the product sells for.*/
    private final double SALE_PRICE;
    /**Product name. A string holding the name of the product, used within the toString method.*/
    private final String NAME;

    /**Product Constructor.
     * Constructs a product with a production cost, sale price, and name.
     * @param costToProduce The cost to produce the product.
     * @param salePrice The sale price of the product.
     * @param name The name of the product.
     */
    Product ( double costToProduce, double salePrice, String name)
    {
        COST_TO_PRODUCE = costToProduce;
        SALE_PRICE = salePrice;
        NAME = name;
    }


    /**Returns cost to produce.
     * @return The cost to produce the product.
     */
    double getCostToProduce() {
        return COST_TO_PRODUCE;
    }

    /**Returns product's sale price
     * @return  The sale price of the product.
     */
    double getSalePrice() {
        return SALE_PRICE;
    }

    /**Returns the product's name.
     * @return The name of the product.
     */
    @Override
    public String toString() {
        return NAME;
    }
}
