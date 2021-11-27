package com.example.mobile_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements registerDialog.DialogListener, loginDialog.DialogListener{
    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        registerButton = findViewById(R.id.registerButton);
        //update the address given by the user
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialog();
                //String address = registerButton.getText().toString();
                //double[] answer;
                //double checker = 200;

                /*check to ensure user inputted a value
                if (address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter location you would like to update!", Toast.LENGTH_SHORT).show();
                    return;
                }
                answer = dbHandler.readLocationAddress(address);
                if (checker == answer[0]){
                    Toast.makeText(MainActivity.this, "Address is not in database... please try a different address!", Toast.LENGTH_SHORT).show();
                }
                else{

                    openDialog();//calls the alert dialog to change an existing address
                } */
            }
        });
        loginButton = findViewById(R.id.loginButton);
        //update the address given by the user
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog();
                //String address = registerButton.getText().toString();
                //double[] answer;
                //double checker = 200;

                /*check to ensure user inputted a value
                if (address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter location you would like to update!", Toast.LENGTH_SHORT).show();
                    return;
                }
                answer = dbHandler.readLocationAddress(address);
                if (checker == answer[0]){
                    Toast.makeText(MainActivity.this, "Address is not in database... please try a different address!", Toast.LENGTH_SHORT).show();
                }
                else{

                    openDialog();//calls the alert dialog to change an existing address
                } */
            }
        });

    }
    public void registerDialog(){
        //calls Dialog pop up
        registerDialog Dialog = new registerDialog();
        Dialog.show(getSupportFragmentManager(), "register dialog");
    }
    public void loginDialog(){
        //calls Dialog pop up
        loginDialog newDialog = new loginDialog();
        newDialog.show(getSupportFragmentManager(), "login dialog");
    }
    @Override
    public void text(String username, String password) {
        //String address = addressInputVal.getText().toString();

        //calls db to update
        //dbHandler.updateLocationAddress(newAddress, address);

        Toast.makeText(MainActivity.this, "New user has been created", Toast.LENGTH_SHORT).show();
        //addressInputVal.setText("");
    }
}