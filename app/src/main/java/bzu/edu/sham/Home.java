package bzu.edu.sham;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import bzu.edu.bilalshop.R;
import bzu.edu.sham.dataAccess.Item;
import bzu.edu.sham.dataAccess.ItemDA;
import bzu.edu.sham.dataAccess.ItemDAI;
import bzu.edu.sham.dataAccess.ItemType;
import bzu.edu.sham.listAdapter.ProgramAdapter;


public class Home extends AppCompatActivity  {

    private LinearLayout goToCart;
    private TextView txtPrice;
    private Spinner spnitem;
    private ListView listitems;
    private ItemDAI itemDA;
    private List<Item> items;
    private String[] categories;
    public static final String ITEMS = "ITEMS";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private boolean flag;
    public static final String FLAG = "Flag";
    public static final String ChosenItem ="Item";
    public static final String TotalPrice = "TotalPrice";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();
        categories=itemDA.getCategories();
        bindSpinner();
        manageOnClicks();

    }

    private void manageOnClicks() {
        Intent intent = new Intent(Home.this,ItemInfo.class);

        spnitem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                items=itemDA.getItems(spnitem.getSelectedItem().toString());


                //ArrayAdapter<Item> adapter=new ArrayAdapter<>(Home.this,R.layout.sham_list_item,items);

                int size=items.size();
                String[] name = new String[size];
                int[] image = new int[size];
                String[] desc = new String[size];
                for(int j=0;j<size;j++){
                    name[j]=items.get(j).getItemName();
                    image[j]=items.get(j).getImage();
                    desc[j]=items.get(j).getDescription()+" "+items.get(j).getAdult().getPrice()+"$ - "+items.get(j).getChild().getPrice()+"$";
                }
                ProgramAdapter adapter = new ProgramAdapter(Home.this, name, image, desc);
                listitems.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        listitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Gson gson =new Gson();
                intent.putExtra(ChosenItem,gson.toJson(items.get(i)));
                intent.putExtra(TotalPrice,txtPrice.getText().toString());
                startActivity(intent);

            }
        });

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Home.this,Cart.class);
                startActivity(intent);
            }
        });
    }

    private void checkPrefs() {
        Gson gson=new Gson();
        flag = prefs.getBoolean(FLAG, false);
        txtPrice.setText(prefs.getString(TotalPrice, "0$"));
        Item[] items;
        if(flag){
            items=gson.fromJson(prefs.getString(ITEMS,""),Item[].class);
            itemDA=new ItemDA(items);
            Toast.makeText(this,"Data from SharedPrefs",Toast.LENGTH_LONG).show();


        }
        else {
            itemDA= new ItemDA();
            editor.putString(ITEMS,itemDA.getJsonItems());
            editor.putBoolean(FLAG,true);
            editor.commit();
            Toast.makeText(this,"Data from DA",Toast.LENGTH_LONG).show();}

    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(Home.this);
        editor = prefs.edit();
    }

    private void bindSpinner() {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(Home.this,R.layout.sham_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnitem.setAdapter(adapter);
    }

    private void setupViews() {
        goToCart=findViewById(R.id.CartPrice);
        txtPrice=findViewById(R.id.txttotal);
        spnitem=findViewById(R.id.spnitem);
        listitems=findViewById(R.id.listitems);
    }
}