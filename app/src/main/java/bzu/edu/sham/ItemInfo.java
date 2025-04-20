package bzu.edu.sham;

import static bzu.edu.sham.Home.ChosenItem;
import static bzu.edu.sham.Home.TotalPrice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import bzu.edu.bilalshop.R;
import bzu.edu.sham.dataAccess.Item;

public class ItemInfo extends AppCompatActivity {
    private LinearLayout goToCart;
    private ImageView imgItem;
    private TextView txtPrice;
    private TextView txtItem;
    private TextView txtItemPrice;
    private TextView txtDescription;
    private Button btnProceed;
    private ImageView imgBack;
    private Item item;
    String itemJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Gson gson =new Gson();
        itemJson=getIntent().getStringExtra(ChosenItem);
        item = gson.fromJson(itemJson, Item.class);

        setupViews();
        manageOnClicks();

    }

    private void manageOnClicks() {
        Intent intent= new Intent(ItemInfo.this,AddToCart.class);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(ChosenItem,itemJson);
                intent.putExtra(TotalPrice,txtPrice.getText().toString());
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ItemInfo.this,Home.class);
                startActivity(intent);
            }
        });

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ItemInfo.this,Cart.class);
                startActivity(intent);
            }
        });

    }

    private void setupViews() {

        btnProceed=findViewById(R.id.btnproceed);
        imgBack =findViewById(R.id.imgback);
        imgItem=findViewById(R.id.imgItem);
        txtPrice=findViewById(R.id.txttotal);
        txtItem=findViewById(R.id.txtItemName);
        txtDescription=findViewById(R.id.txtItemDescription);
        txtItemPrice=findViewById(R.id.txtItemPrice);
        goToCart=findViewById(R.id.CartPrice);

        imgItem.setImageResource(item.getImage());
        txtPrice.setText(getIntent().getStringExtra(TotalPrice));
        txtItem.setText(item.getItemName());
        txtDescription.setText((item.getDescription()));
        txtItemPrice.setText(+item.getAdult().getPrice()+"$"+" Cost for Adult all sizes\n"+item.getChild().getPrice()+"$"+" Cost for Children all sizes");

    }


}