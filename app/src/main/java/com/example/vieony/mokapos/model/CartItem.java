package com.example.vieony.mokapos.model;

public class CartItem {
    private Item item;
    private int quantity = 1;
    private Discount discount;

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem(){
        return item;
    }

    public Discount getDiscount(){
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceWithoutDiscount(){
        return quantity * item.price();
    }

    public String getFormattedPriceWithoutDiscount(){
        return String.format("$%.0f", getPriceWithoutDiscount());
    }
}
