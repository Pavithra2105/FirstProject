package com.example.firstproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText userEd,emailEd,mobileEd,passwordEd;
    Button signupBtn;
    CheckBox checkBox;
    Button dateBtn;
    RatingBar ratingBar;
    Button gohome;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set ids


        userEd = findViewById(R.id.user_pavi);
        emailEd= findViewById(R.id.email_pavi);
        mobileEd = findViewById(R.id.mobile_pavi);
        passwordEd = findViewById(R.id.password_pavi);
        signupBtn = findViewById(R.id.button_pavi);
        checkBox = findViewById(R.id.checkbox_pavi);
        dateBtn = findViewById(R.id.date_picker_actions_pavi);
        ratingBar=findViewById(R.id.ratingbar);
        gohome=findViewById(R.id.button1_pavi);


        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,HomeActivity.class);
                intent1.putExtra("username",userEd.getText().toString());
                intent1.putExtra("mobile",mobileEd.getText().toString());
                startActivity(intent1);
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dateBtn.setText(dayOfMonth+"-"+(month+1)+"-"+year); //dd-mm-yyyy
                    }
                },mYear, mMonth,mDay);
                datePickerDialog.show();

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true) {
                    Toast.makeText(MainActivity.this, "You have agreed for the following text", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "You are not accepted for the terms and condition", Toast.LENGTH_SHORT).show();

                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LOGICS

                String user_s = userEd.getText().toString();
                String email_s = emailEd.getText().toString();
                String mobile_s = mobileEd.getText().toString();
                String pass_s = passwordEd.getText().toString();


                String rating = String.valueOf(ratingBar.getRating());
                String date = dateBtn.getText().toString();

                showMessage(user_s , email_s,mobile_s,pass_s,rating,date);
            }
        });
    }

    private void showMessage(String user_s, String email_s, String mobile_s, String pass_s,String rating,String date) {
   //display data

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Data");
        builder.setMessage("User: "+user_s+"\nEmail: "+email_s+"\nMobile: "+mobile_s+"\nPassword: "+pass_s
        + "\nRating: "+rating+"\nDate: "+date);
        builder.create();
        builder.show();
    }
}