package com.bharath.admin.snailtaxe_filingindia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        Button login = findViewById(R.id.llogin_button);
        Button lregister = findViewById(R.id.rregister);
        EditText et_email = findViewById(R.id.email);
        EditText et_password=findViewById(R.id.password);
        final String email=et_email.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Intent intent = new Intent(login.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
        lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r1 = new Intent(getApplicationContext(), Registration.class);
                startActivity(r1);
            }
        });
    }

    public void skip(View view){
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(i);
    }
}
