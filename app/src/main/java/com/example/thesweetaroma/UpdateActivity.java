package com.example.thesweetaroma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private EditText inputName, inputEmail, inputAddress, inputPhone;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);

        inputAddress = (EditText) findViewById(R.id.address);
        inputPhone   = (EditText) findViewById(R.id.phone);
        btnSave = (Button) findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("User");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String address = inputAddress.getText().toString();
                String phone = inputPhone.getText().toString();

                createUser(name, email,address,phone);
            }
        });
    }

    private void createUser(String name, String email, String address, String phone) {
        String key =mFirebaseDatabase.push().getKey();
        User user = new User(name, email,address,phone);
        mFirebaseDatabase.child(key).setValue(user);
        finish();
    }
}
