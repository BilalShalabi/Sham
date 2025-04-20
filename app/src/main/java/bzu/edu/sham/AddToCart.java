package bzu.edu.sham;

import static bzu.edu.sham.Home.ChosenItem;
import static bzu.edu.sham.Home.TotalPrice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import bzu.edu.bilalshop.R;
import bzu.edu.sham.dataAccess.Item;
import bzu.edu.sham.dataAccess.ItemCart;
import bzu.edu.sham.dataAccess.ItemDA;
import bzu.edu.sham.dataAccess.ItemDAI;


public class AddToCart extends AppCompatActivity {

    LinearLayout goToCart;
    private TextView txtPrice;
    private ImageView imgBack;
    private Button btnAdd;
    private Spinner spnsize;
    private RadioButton radioAdult;
    private RadioButton radioChild;
    private ImageView imgItem;
    private ImageView imgPlus;
    private ImageView imgMinus;
    private TextView txtCount;
    private ItemDAI itemDA;
    private Item item;
    private ItemCart addedItem;
    private String[] sizes;
    private int Count=1;
    private String itemJson;
    public  static final String AddedItem="AddedItem";
    public  static final String FlagCI="FlagChosenItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Gson gson =new Gson();
        itemJson=getIntent().getStringExtra(ChosenItem);
        item = gson.fromJson(itemJson, Item.class);

        itemDA=new ItemDA();
        sizes=itemDA.getSizes();

        setupViews();
        bindSpinner();
        manageClicks();
    }

    private void manageClicks() {
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(Count<10)
                    Count+=1;
                txtCount.setText(""+Count);
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(Count>1)
                    Count-=1;
                txtCount.setText(""+Count);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AddToCart.this,ItemInfo.class);
                intent.putExtra(ChosenItem,itemJson);
                intent.putExtra(TotalPrice,txtPrice.getText().toString());
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(manageOrder()) {
                    Gson gson = new Gson();
                    Intent intent = new Intent(AddToCart.this, Cart.class);
                    intent.putExtra(AddedItem, gson.toJson(addedItem));
                    intent.putExtra(ChosenItem, gson.toJson(item));
                    intent.putExtra(FlagCI, true);
                    startActivity(intent);
                }

            }
        });

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AddToCart.this,Cart.class);
                startActivity(intent);
            }
        });
    }

    private boolean manageOrder() {
        String size=spnsize.getSelectedItem().toString();
        int quantityStock;
        int quantitySelected=Integer.parseInt(txtCount.getText().toString());
        if(radioAdult.isChecked()){
            addedItem=new ItemCart(item.getID(),item.getItemName(),"Adults", size,quantitySelected, quantitySelected*item.getAdult().getPrice(),item.getImage());
            switch (size){
                case "Small":
                    quantityStock=item.getAdult().getSmall().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getAdult().getSmall().setQuantity(quantityStock-quantitySelected);
                    break;

                case "Medium":
                    quantityStock=item.getAdult().getMedium().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getAdult().getMedium().setQuantity(quantityStock-quantitySelected);
                    break;
                case "Large":
                    quantityStock=item.getAdult().getLarge().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getAdult().getLarge().setQuantity(quantityStock-quantitySelected);
                    break;
            }
        } else{
            addedItem=new ItemCart(item.getID(), item.getItemName(),"Children", size,quantitySelected, quantitySelected*item.getChild().getPrice(), item.getImage());
            switch (size){
                case "Small":
                    quantityStock=item.getChild().getSmall().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getChild().getSmall().setQuantity(quantityStock-quantitySelected);
                    break;

                case "Medium":
                    quantityStock=item.getChild().getMedium().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getChild().getMedium().setQuantity(quantityStock-quantitySelected);
                    break;
                case "Large":
                    quantityStock=item.getChild().getLarge().getQuantity();
                    if(quantityStock-quantitySelected<0){
                        if(quantityStock!=0)
                            Toast.makeText(AddToCart.this,"Sorry there is only "+quantityStock+" left",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddToCart.this,"Sorry this item is out of stock",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    else item.getChild().getLarge().setQuantity(quantityStock-quantitySelected);
                    break;
            }

        }
        return true;
    }

    private void bindSpinner() {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AddToCart.this,R.layout.sham_spinner_item,sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnsize.setAdapter(adapter);
    }

    private void setupViews() {
        goToCart=findViewById(R.id.CartPrice);
        txtPrice=findViewById(R.id.txttotal);
        imgBack=findViewById(R.id.imgback);
        btnAdd=findViewById(R.id.btnadd);
        spnsize=findViewById(R.id.spnsize);
        radioAdult=findViewById(R.id.radioadult);
        radioChild=findViewById(R.id.radiochild);
        imgItem=findViewById(R.id.imgItem);
        imgPlus=findViewById(R.id.imgplus);
        imgMinus=findViewById(R.id.imgminus);
        txtCount=findViewById(R.id.txtcount);

        txtPrice.setText(getIntent().getStringExtra(TotalPrice));
        radioAdult.setChecked(true);
        imgItem.setImageResource(item.getAltImage());
    }
}