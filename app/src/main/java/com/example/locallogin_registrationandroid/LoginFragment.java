package com.example.locallogin_registrationandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    View myView;
    MyDataBase mydb;
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_login, container, false);

        // Floating Action Button Functionality
      fab = myView.findViewById(R.id.fbLogin);
      fab.setOnClickListener(view -> {
          TextInputLayout getuser,getpass;
          getuser = myView.findViewById(R.id.getuser);
          getpass = myView.findViewById(R.id.getpass);
          String name = getuser.getEditText().getText().toString();
          String password = getpass.getEditText().getText().toString();

          //verify user input
          if(name.isEmpty()){
              getuser.setError("Please fill in username");
              return;
          }else if(password.isEmpty()){
              getpass.setError("Please fill in your password");
          }
          else if(name.isEmpty()||password.isEmpty()){
              Toast.makeText(getActivity(), "Please fill in both Fields!!", Toast.LENGTH_SHORT).show();
              getuser.requestFocus();
              return;
          }
          else UserLogin(name,password);
      });
        return myView;
    }

    private void UserLogin( String username,String password) {

        mydb = new MyDataBase(getContext());
        if (mydb.Login(username,password)){
            Toast.makeText(getActivity(), "Login Success!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(),UserProf.class));
        }
    }

}