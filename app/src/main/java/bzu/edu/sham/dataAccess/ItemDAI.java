package bzu.edu.sham.dataAccess;

import java.util.List;

public interface ItemDAI {

    String getJsonItems();
String[] getCategories();
String[] getSizes();
List<Item> getItems(String cat);
}
