package com.example.firstproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {
    EditText empnameEd,empidEd,empdepartmentEd,empsalaryEd;
    EmployeeSqlite employeeSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //set ids

        empidEd = findViewById(R.id.emp_id);
        empnameEd  =findViewById(R.id.emp_name);
        empdepartmentEd = findViewById(R.id.emp_department);
        empsalaryEd = findViewById(R.id.emp_salary);
        employeeSqlite = new EmployeeSqlite(this);
    }

    public void add_data (View view){

    String emp_name =empnameEd.getText().toString();
    String emp_department =empdepartmentEd.getText().toString();
    String emp_salary =empsalaryEd.getText().toString();

    boolean isInserted  = employeeSqlite.insertData(emp_name,emp_department,emp_salary);
    if(isInserted==true){
        Toast.makeText(this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(this, "NOT INSERTED", Toast.LENGTH_SHORT).show();
    }
    }

    public void create_data(View view) {

    }

    public void update_data(View view) {
        String emp_id = empidEd.getText().toString();
        String emp_name =empnameEd.getText().toString();
        String emp_department =empdepartmentEd.getText().toString();
        String emp_salary =empsalaryEd.getText().toString();

        Boolean isUpdated = employeeSqlite.updateData(emp_id,emp_department,emp_name,emp_salary);
        if(isUpdated == true){
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
    }


    public void all_data(View view) {
        Cursor cursor = employeeSqlite.allData();

        if(cursor.getCount()==0) {
            displayMessage("ALL DATA","NO DATA FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            buffer.append("ID:"+cursor.getString(0)+"\n");
            buffer.append("Name: "+cursor.getString(1)+"\n");
            buffer.append("Department : "+cursor.getString(2)+"\n");
            buffer.append("Salary : "+cursor.getString(3)+"\n\n");
        }
        displayMessage("All Data",buffer.toString());
    }
    private void displayMessage(String employee, String data) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(employee);
        builder.setCancelable(true);
        builder.setMessage(data);
        builder.create().show();
    }

    public void delete_data(View view) {
        String emp_id = empidEd.getText().toString();
        Integer delete = employeeSqlite.deleteData(emp_id);
        if(delete >0) {
            Toast.makeText(this, "Details Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Details Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void read_data(View view) {
        String empid = empidEd.getText().toString();
        Cursor cursor = employeeSqlite.readData(empid);
        String data = null;
        if(cursor.moveToFirst()){
            data = "ID: "+cursor.getString(0)+"\n"+ "Name : "+cursor.getString(1)+"\n"+"Department :"+cursor.getString(2)+"\n"+
                    "Salary : "+cursor.getString(3)+"\n";
        }
        displayMessage("Employee",data);

    }
}

