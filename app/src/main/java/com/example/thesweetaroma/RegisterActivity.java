package com.example.thesweetaroma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyhaveAccount;
    EditText emailReg,passwordReg,confirmReg;
    Button btn_Reg;
    String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.[a-z]+";
    ProgressDialog progressDialog;

       FirebaseAuth mAuth;
       FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyhaveAccount =findViewById(R.id.alreadyhaveAccount);

        emailReg= findViewById(R.id.emailReg);
        passwordReg =findViewById(R.id.passwordReg);
        confirmReg = findViewById(R.id.confirmReg);
        btn_Reg = (Button) findViewById(R.id.btn_Reg);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

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
        else if(password.equals(confirmReg))
        {
            confirmReg.setError("Password not match both field");
        }
        else
        {
            progressDialog.setMessage("Please wait while Registration");
            progressDialog.setTitle("Register");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                   } else
                   {
                       progressDialog.dismiss();
                       Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}