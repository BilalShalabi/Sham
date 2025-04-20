package bzu.edu.sham;

import static bzu.edu.sham.AddToCart.AddedItem;
import static bzu.edu.sham.AddToCart.FlagCI;
import static bzu.edu.sham.Home.ChosenItem;
import static bzu.edu.sham.Home.FLAG;
import static bzu.edu.sham.Home.ITEMS;
import static bzu.edu.sham.Home.TotalPrice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bzu.edu.bilalshop.R;
import bzu.edu.sham.dataAccess.Item;
import bzu.edu.sham.dataAccess.ItemCart;
import bzu.edu.sham.dataAccess.ItemDA;
import bzu.edu.sham.dataAccess.ItemDAI;
import bzu.edu.sham.listAdapter.ProgramAdapter;

public class Cart extends AppCompatActivity {
    private TextView txtPrice;
    private Button btnHome;
    private Button btnCheckout;
    private ListView listitems;
    private ItemDAI itemDA;
    private List<Item> items;
    private List<ItemCart> CartItems=new ArrayList<>();
    private String[] categories;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private boolean flagCart;

    public static final String FLAGCART = "FlagCart";
    public static final String CART = "CART";
    public static final String ORDERS = "ORDERS";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();
        displayCartItems();
        manageClicks();


    }

    private void saveTotalPrice() {
        double price=0;
        for(int i=0;i<CartItems.size();i++){
            price+=CartItems.get(i).getTotalPrice();
        }
        txtPrice.setText(price+"$");
        editor.putString(TotalPrice, price+"$");
    }

    private void displayCartItems() {

        int size=CartItems.size();
        String[] name = new String[size];
        int[] image = new int[size];
        String[] desc = new String[size];
        for(int j=0;j<size;j++){
            name[j]=CartItems.get(j).getName();
            image[j]=CartItems.get(j).getImage();
            desc[j]=" for "+ CartItems.get(j).getType() +" "+ CartItems.get(j).getSize() +" "+ CartItems.get(j).getQuantity() +"pcs "+CartItems.get(j).getTotalPrice()+"$" ;;
        }
        ProgramAdapter adapter = new ProgramAdapter(Cart.this, name, image, desc);
        listitems.setAdapter(adapter);

    }

    private void manageClicks() {


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Cart.this, Home.class);
                startActivity(intent);
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson=new Gson();
                editor.putString(ORDERS,gson.toJson(CartItems.toArray(new ItemCart[0])));
                editor.putBoolean(FLAGCART,false);
                editor.putString(CART,"");
                editor.putString(TotalPrice,"0$");
                editor.commit();

                CartItems=new ArrayList<>();
                txtPrice.setText("0$");
                ArrayAdapter<ItemCart> adapter=new ArrayAdapter<>(Cart.this,R.layout.sham_list_item);
                listitems.setAdapter(adapter);

            }
        });

    }

    private void checkPrefs() {
        Gson gson = new Gson();
        flagCart = prefs.getBoolean(FLAGCART, false);
        boolean flagChosenItem = getIntent().getBooleanExtra(FlagCI,false);
        Item[] items;
        items = gson.fromJson(prefs.getString(ITEMS, ""), Item[].class);

        ItemCart ci = null;
        if (flagChosenItem) {
            ci = gson.fromJson((getIntent().getStringExtra(AddedItem)), ItemCart.class);
            Item chosenItem = gson.fromJson((getIntent().getStringExtra(ChosenItem)), Item.class);
            for (int i = 0; i < items.length; i++) {
                if (items[i].getID() == chosenItem.getID())
                    items[i] = chosenItem;
            }
        }


        if (flagCart) {
            ItemCart[] citems;
            citems = gson.fromJson(prefs.getString(CART, ""), ItemCart[].class);
            CartItems.addAll(Arrays.asList(citems));
        }
        if (flagChosenItem)
            CartItems.add(ci);

        itemDA = new ItemDA(items);
        editor.putString(ITEMS, itemDA.getJsonItems());
        editor.putBoolean(FLAG, true);

        editor.putString(CART, gson.toJson(CartItems.toArray(new ItemCart[0])));
        editor.putBoolean(FLAGCART, true);

        saveTotalPrice();
        editor.commit();


    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(Cart.this);
        editor = prefs.edit();
    }

    private void setupViews() {
        btnHome=findViewById(R.id.btnhome);
        btnCheckout=findViewById(R.id.btnCheckout);
        listitems=findViewById(R.id.listitems);
        txtPrice=findViewById(R.id.txttotal);
    }
}