//package main.java;
public interface P2PBuyerInterface {

    /** This method would search the network for the buyers
     * @param productName - Contains the product name that the buyer needs to buy
     * @param hopCount - Decrements after every call
     */
    public void lookup(String productName, int hopCount);

    /**
     * This method will be used by the buyers to directly connect to a seller and buy a product
     * @param sellerID - Contains the ID of the seller
     */
    public void buy(int sellerID);
    /**
     * This method will be used by the sellers to respond to a buy request
     * @param sellerID - Contains the ID of the seller
     */
    public void reply(int sellerID);

}
