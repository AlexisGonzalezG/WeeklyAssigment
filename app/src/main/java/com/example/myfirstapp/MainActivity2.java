package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    EditText email,pass1,pass2;
    TextView tv_1,tv_2,tv_3,tv_4,tv_5;
    Button button_create;

    private SharedPreferences sp;
    String emailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        email = findViewById(R.id.email);
        button_create = findViewById(R.id.button_create);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);

        pass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visibilityButton();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
            }

        );

        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visibilityButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        }

        );

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visibilityButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        }

        );

        sp = getSharedPreferences("MyEmailPrefers", Context.MODE_PRIVATE); //save
        SharedPreferences sh = getSharedPreferences("MyEmailPrefers", Context.MODE_PRIVATE);


        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = sh.getString("email", ""); // Get emails saved

                emailStr = email.getText().toString(); //get email in EditText

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email",emailStr + " , " + s1);//Concatenates email introduced and the last
                editor.apply();

                String group = sh.getString("email", ""); // Get emails saved again

                Log.d(LOG_TAG, "DATOS GUARDADOS:" +  group );

                Toast.makeText(getApplicationContext(), s1 + " WAS SAVED!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private boolean validatePass(String password){

       Pattern uppercase = Pattern.compile("[A-Z]");
       Pattern lowwercase = Pattern.compile("[a-z]");
       Pattern digitCase = Pattern.compile("[0-9]");

       boolean validatePass = true;

       if(!lowwercase.matcher(password).find()){
           tv_1.setTextColor(Color.RED);
           validatePass = false;
       }
       else{
           tv_1.setTextColor(Color.WHITE);
       }

       if(!uppercase.matcher(password).find()){
           tv_2.setTextColor(Color.RED);
           validatePass = false;
       }
       else{
           tv_2.setTextColor(Color.WHITE);
       }

       if(!digitCase.matcher(password).find()){
           tv_3.setTextColor(Color.RED);
           validatePass = false;
       }
       else{
           tv_3.setTextColor(Color.WHITE);
       }

       if(pass1.length() < 8){
           tv_4.setTextColor(Color.RED);
           validatePass = false;
       }
       else{
           tv_4.setTextColor(Color.WHITE);
       }

       /*Log.d(LOG_TAG, "ALEX:" + match ); LOGS*/



       if(pass1.getText().toString().equals(pass2.getText().toString()) && !pass1.getText().toString().matches("")){
           tv_5.setTextColor(Color.WHITE);
       }
       else{
           tv_5.setTextColor(Color.RED);
           validatePass = false;
       }

       return validatePass;

   }

    private boolean validateEmailAddress(EditText email){
        String emailInput = email.getText().toString();
        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            //Toast.makeText(getApplicationContext(), "CORRECT EMAIL FORMAT", Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            //Toast.makeText(getApplicationContext(), "INCORRECT EMAIL FORMAT", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean visibilityButton (){
        String pass = pass1.getText().toString();
        boolean validatePass = validatePass(pass);
        boolean validateEmailAddress = validateEmailAddress(email);

        if((validateEmailAddress) && (validatePass)){
            button_create.setVisibility(View.VISIBLE);
        }
        else{
            button_create.setVisibility(View.INVISIBLE);
        }
        return true;
    }

}