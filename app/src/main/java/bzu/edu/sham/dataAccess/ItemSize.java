package bzu.edu.sham.dataAccess;

public class ItemSize {

    String itemSize;
    int quantity;

    public ItemSize(String itemSize, int quantity) {
        this.itemSize = itemSize;
        this.quantity = quantity;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return itemSize +" "+quantity;
    }
}
