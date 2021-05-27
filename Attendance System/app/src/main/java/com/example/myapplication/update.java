package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class update extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    TextView text5, text6, text7, text8, text9, text10, text11, text12, text13, text14;
    EditText firstname, lastname, phone, address, email2, pass2;
    Spinner spinner1, spinner2, spinner3;
    Button button2;


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    ImageView image;
    Button button;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);
        text10 = findViewById(R.id.text10);
        text11 = findViewById(R.id.text11);
        text12 = findViewById(R.id.text12);
        text13 = findViewById(R.id.text13);
        text14 = findViewById(R.id.text14);
        email2 = findViewById(R.id.email2);
        pass2 = findViewById(R.id.pass2);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        button2 = findViewById(R.id.button2);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);


        image = findViewById(R.id.image);
        button = findViewById(R.id.button);


        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "pick an image"), GALLERY_REQUEST_CODE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;


                email = email2.getText().toString();
                pass = pass2.getText().toString();


                email = email2.getText().toString();
                pass = pass2.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(update.this, "Email required", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(update.this, "Password required", Toast.LENGTH_SHORT).show();

                } else {
                    //Authenticaion
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){

            Uri imagedata = data.getData();

            image.setImageURI(imagedata);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}