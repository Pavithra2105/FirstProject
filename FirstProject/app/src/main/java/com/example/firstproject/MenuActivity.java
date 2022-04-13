package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    TextView accTxt;
    LinearLayout ll;
    TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //set ids

        accTxt = findViewById(R.id.acc_txt);
        ll = findViewById(R.id.ll);
        text3 = findViewById(R.id.text3);

         registerForContextMenu(accTxt);

    }
public void changeBackground(View view) {
    PopupMenu popupMenu = new PopupMenu(MenuActivity.this,text3);
    popupMenu.getMenuInflater().inflate(R.menu.menu2,popupMenu.getMenu());


    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.white:
                    ll.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.blue:
                    ll.setBackgroundColor(Color.BLUE);
                    break;
                case R.id.cyan:
                    ll.setBackgroundColor(Color.CYAN);
                    break;
                case R.id.red:
                    ll.setBackgroundColor(Color.RED);
                    break;
            }
            return true;
        }

    });
    popupMenu.show();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:
                ll.setBackgroundColor(Color.GRAY);
                Toast.makeText(this,"Selected Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.qrcode:
                ll.setBackgroundColor(Color.WHITE);
                Toast.makeText(this,"QR Code is not activated to your profile",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //create menu
        menu.setHeaderTitle("Account Options");
        menu.add( 0 ,v.getId(),   0,"Profile");
        menu.add( 0  ,v.getId(),  0,"Info");
        menu.add( 0   ,v.getId(), 0,"Logout");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "Profile") {
            Toast.makeText(this, "Your Profile is shortlisted...!!", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Info") {
            Toast.makeText(this, "You are a Prime member", Toast.LENGTH_SHORT).show();

        } else if (item.getTitle() == "Logout") {
            finish();
        }
        return true;
    }
}