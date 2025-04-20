package bzu.edu.sham.listAdapter;

import static bzu.edu.sham.Home.ChosenItem;
import static bzu.edu.sham.Home.TotalPrice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import bzu.edu.bilalshop.R;
import bzu.edu.sham.ItemInfo;
import bzu.edu.sham.dataAccess.Item;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] programName;
    String[] programDescription;
    //String[] urls;

    public ProgramAdapter(Context context, String[] itemName, int[] itemImage, String[] itemDescription) {
        super(context, R.layout.sham_list_item_1, R.id.textView1, itemName);
        this.context = context;
        this.images = itemImage;
        this.programName = itemName;
        this.programDescription = itemDescription;
    }

    /*
    Additional code
    public ProgramAdapter(Context context, String[] programName, int[] images, String[] programDescription, String[] urls) {
        super(context, R.layout.single_item, R.id.textView1, programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
        this.urls = urls;
    }
     */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // The parameter convertView is null when your app is creating a new item for the first time. It's not null when
        // recycling.
        // Assign the convertView in a View object
        View singleItem = convertView;
        // Find a View from the entire View hierarchy by calling findViewById() is a fairly expensive task.
        // So, you'll create a separate class to reduce the number of calls to it.
        // First, create a reference of ProgramViewHolder and assign it to null.
        ProgramViewHolder holder = null;
        // Since layout inflation is a very expensive task, you'll inflate only when creating a new item in the ListView. The first
        // time you're creating a new item, convertView will be null. So, the idea is when creating an item for the first time,
        // we should perform the inflation and initialize the ViewHolder.
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.sham_list_item_1, parent, false);
            // Pass the singleItem to the constructor of ProgramViewHolder. This singleItem object contains a LinearLayout
            // as the root element for single_item.xml file that contains other Views as well for the ListView.
            holder = new ProgramViewHolder(singleItem);
            // When you create an object of ProgramViewHolder, you're actually calling findViewById() method inside the constructor.
            // By creating ProgramViewHolder only when making new items, you call findViewById() only when making new rows.
            // At this point all the three Views have been initialized. Now you need to store the holder so that you don't need to
            // create it again while recycling and you can do this by calling setTag() method on singleItem and passing the holder as a parameter.
            singleItem.setTag(holder);
        }
        // If singleItem is not null then we'll be recycling
        else{
            // Get the stored holder object
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        // Set the values for your views in your item by accessing them through the holder
        holder.itemImage.setImageResource(images[position]);
        holder.programTitle.setText(programName[position]);
        holder.programDescription.setText(programDescription[position]);

        return singleItem;
    }

    public static class ProgramViewHolder {
        // Declare the object references for our views
        ImageView itemImage;
        TextView programTitle;
        TextView programDescription;
        // Get the handles by calling findViewById() on View object inside the constructor
        ProgramViewHolder(View v)
        {
            itemImage = v.findViewById(R.id.imageView);
            programTitle = v.findViewById(R.id.textView1);
            programDescription = v.findViewById(R.id.textView2);
        }
    }
}
