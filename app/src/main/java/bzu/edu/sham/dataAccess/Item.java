package bzu.edu.sham.dataAccess;

public class Item {
    private int ID;
    private String ItemName;
    private String Category;
    private String Description;
    private ItemType adult;
    private ItemType child;
    private int image;
    private int altImage;

    public Item(int id, String itemName, String category, String description, ItemType adult, ItemType child, int image, int altImage) {
        ID=id;
        ItemName = itemName;
        Category = category;
        Description = description;
        this.adult=adult;
        this.child=child;
        this.image = image;
        this.altImage=altImage;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getAltImage() {
        return altImage;
    }

    public void setAltImage(int altImage) {
        this.altImage = altImage;
    }



    public ItemType getAdult() {
        return adult;
    }



    public void setAdult(ItemType adult) {
        this.adult = adult;
    }

    public ItemType getChild() {
        return child;
    }

    public void setChild(ItemType child) {
        this.child = child;
    }



    @Override
    public String toString() {
        return ItemName +" "+ adult.getPrice() +"$"+" - "+child.getPrice()+"$";
    }
}
