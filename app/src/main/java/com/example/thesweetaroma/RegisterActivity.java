package com.example.thesweetaroma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyhaveAccount;
    EditText emailReg,passwordReg,confirmReg;
    Button btn_Reg;
    String emailPattern= "[a-zA-z0-9._-]+@[a-z]+\\.[a-z]+";
    ProgressDialog progressDialog;


    //TheSweetAroma mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyhaveAccount =findViewById(R.id.alreadyhaveAccount);

        emailReg= findViewById(R.id.emailReg);
        passwordReg =findViewById(R.id.passwordReg);
        confirmReg = findViewById(R.id.confirmReg);
        btn_Reg = findViewById(R.id.btn_Reg);
        progressDialog = new ProgressDialog(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                alreadyhaveAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    }
                });

                btn_Reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PerforAuth();
                    }
                });
    }

    private void PerforAuth() {
        String email= emailReg.getText().toString();
        String password = passwordReg.getText().toString();
        String confirmPassword = confirmReg.getText().toString();

        if(!email.matches(emailPattern))
        {
            emailReg.setError("Enter context email");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            passwordReg.setError("Enter proper password");
        }
        else if(!password.equals(confirmReg))
        {
            confirmReg.setError("Password not match both field");
        }
        else
        {
            progressDialog.setMessage("Please wait while Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }
}