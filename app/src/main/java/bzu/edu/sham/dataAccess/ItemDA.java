package bzu.edu.sham.dataAccess;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bzu.edu.bilalshop.R;

public class ItemDA implements ItemDAI{
    private final List<Item> items =new ArrayList<>();

    public ItemDA(){
        createInitialData();
    }

    public ItemDA(Item[] list) {
        items.addAll(Arrays.asList(list));
    }


    private void createInitialData() {
        items.add(new Item(1,"Syrian bab al hara", "Syrian", "Traditional syrian bab al hara outfit for men", new ItemType("Adult", 100, 5, 5, 5), new ItemType("Child", 50, 5, 5, 5), R.drawable.item, R.drawable.item11));
        items.add(new Item(2,"kufyah outfit", "Syrian", "Traditional syrian with kufyah colors outfit for men", new ItemType("Adult", 120, 5, 5, 5), new ItemType("Child", 55, 5, 5, 5), R.drawable.item2, R.drawable.item22));
        items.add(new Item(3,"Labanese Mukhtar", "Lebanese", "Traditional Labanese outfit for men", new ItemType("Adult", 150, 5, 5, 5), new ItemType("Child", 80, 5, 5, 5), R.drawable.item3, R.drawable.item3));
        items.add(new Item(4,"Labanese Traditional", "Lebanese", "Traditional Labanes outfit for men ", new ItemType("Adult", 120, 5, 5, 5), new ItemType("Child", 55, 5, 5, 5), R.drawable.item4, R.drawable.item4));
        items.add(new Item(5,"Paletinian Traditional", "Palestinian", "Traditional Palestinian outfit for men", new ItemType("Adult", 100, 5, 5, 5), new ItemType("Child", 40, 5, 5, 5), R.drawable.item5, R.drawable.item5));
        items.add(new Item(6,"Paletinian National", "Palestinian", "National Palestinian outfit with kufyah for men", new ItemType("Adult", 120, 5, 5, 5), new ItemType("Child", 60, 5, 5, 5), R.drawable.item6, R.drawable.item66));
        items.add(new Item(7,"Jordanian National", "Jordanian", "Traditional Jordanian outfit for men", new ItemType("Adult", 130, 5, 5, 5), new ItemType("Child", 60, 5, 5, 5), R.drawable.item7, R.drawable.item7));
        items.add(new Item(8,"Jordanian Sheikh", "Jordanian", "National Jordanian outfit for men", new ItemType("Adult", 110, 5, 5, 5), new ItemType("Child", 55, 5, 5, 5), R.drawable.item8, R.drawable.item8));

    }

    @Override
    public String getJsonItems() {
        Gson gson =new Gson();
        Item[] i= this.items.toArray(new Item[0]);
        return gson.toJson(i);
    }

    @Override
    public String[] getCategories() {
        return new String[]{"All","Syrian","Palestinian","Lebanese","Jordanian"};
    }

    @Override
    public String[] getSizes() {
        return new String[]{"Small","Medium","Large"};
    }

    @Override
    public List<Item> getItems(String cat) {

        if(cat.equals("All"))
            return items;

        List<Item> result=new ArrayList<>();
        for(Item i : items){
            if(i.getCategory().equals(cat) ) {
                result.add(i);
            }
        }
        return result;
    }
}