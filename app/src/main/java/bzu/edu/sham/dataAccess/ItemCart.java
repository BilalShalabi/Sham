package bzu.edu.sham.dataAccess;

public class ItemCart {
    private int ID;
    private String Name;
    private String Type;
    private String Size;
    private int Quantity;
    private double TotalPrice;
    private int Image;

    public ItemCart(int id, String name, String type, String size, int quantity, double totalPrice, int image) {
        ID=id;
        Name = name;
        Type = type;
        Size = size;
        Quantity = quantity;
        TotalPrice = totalPrice;
        Image=image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    @Override
    public String toString() {
        return Name +" for "+ Type +" "+ Size +" "+ Quantity +"pcs "+TotalPrice+"$" ;
    }
}
