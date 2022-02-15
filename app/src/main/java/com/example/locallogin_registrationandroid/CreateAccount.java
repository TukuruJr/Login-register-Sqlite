package com.example.locallogin_registrationandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class CreateAccount extends AppCompatActivity {
    TextInputLayout username,email,mobile,password,confirm;
    Button Create;
    TextView Back;
    String name,mail,number,pass1,pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Create = findViewById(R.id.create_Ac);
        Create.setOnClickListener(view -> {
            Initialize();
        });
    }

    private void Initialize(){
        username = findViewById(R.id.getusername);
        email = findViewById(R.id.getemail);
        mobile = findViewById(R.id.getnumber);
        password = findViewById(R.id.getpass1);
        confirm = findViewById(R.id.getpass2);

         name = username.getEditText().getText().toString();
         mail = email.getEditText().getText().toString();
         number = mobile.getEditText().getText().toString();
         pass1 = password.getEditText().getText().toString();
         pass2 = confirm.getEditText().getText().toString();


        //validate

        if (name.isEmpty()){
            username.setError("This field is required");
        }
        else if (mail.isEmpty()){
            email.setError("This field is required");
        }
        else if (number.isEmpty()){
            mobile.setError("This field is required");
        }
        else if (pass1.isEmpty()){
            password.setError("This field is required");
        }
        else if (pass2.isEmpty()){
            confirm.setError("This field is required");
        }
        else if(!pass1.equals(pass2)){
            Toast.makeText(getApplicationContext(), "Passwords mismatch!!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            CreateUser(name);
        }

    }

    private boolean CreateUser(String M_name) {
        MyDataBase mydb = new MyDataBase(this);

        //check if username exists
        if(mydb.CheckUser(M_name)){
           if (mydb.CreateUser(name,mail,number,pass2)){
               AlertDialog.Builder builder = new AlertDialog.Builder(this)
                       .setTitle("Account Creation")
                       .setMessage(R.string.success)
                       .setCancelable(false)
                       .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               finish();
                           }
                       });
           }
        }
        Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
        return false;
    }
}