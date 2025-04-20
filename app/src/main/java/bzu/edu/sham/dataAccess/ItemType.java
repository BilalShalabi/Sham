package bzu.edu.sham.dataAccess;

public class ItemType {

    private String itemType;
    private ItemSize small;
    private ItemSize medium;
    private ItemSize large;
    private double price;

    public ItemType(String itemType, double price, int smallQuantity, int mediumQuantity, int largeQuatity) {
        this.itemType = itemType;
        this.small = new ItemSize("Small",smallQuantity);
        this.large = new ItemSize("Medium",mediumQuantity);
        this.medium = new ItemSize("Large",largeQuatity);
        this.price=price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public ItemSize getSmall() {
        return small;
    }

    public void setSmall(ItemSize small) {
        this.small = small;
    }

    public ItemSize getMedium() {
        return medium;
    }

    public void setMedium(ItemSize medium) {
        this.medium = medium;
    }

    public ItemSize getLarge() {
        return large;
    }

    public void setLarge(ItemSize large) {
        this.large = large;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return itemType +" "+ small+" " + medium+" " + large+" "+price+"$";
    }
}
