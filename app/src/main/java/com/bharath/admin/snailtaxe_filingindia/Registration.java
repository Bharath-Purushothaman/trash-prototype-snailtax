package com.bharath.admin.snailtaxe_filingindia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        final EditText reg_email= findViewById(R.id.remail);
        final EditText reg_password= findViewById(R.id.rpassword);
        final EditText repassword= findViewById(R.id.rrepassword);
        Button r_reg_button = findViewById(R.id.register12);
        Button reset= findViewById(R.id.rreset);
        Button login= findViewById(R.id.rlogin);
        final String semail=reg_email.getText().toString().trim();
        final String spassword=reg_password.getText().toString().trim();
        //final String srepassword=repassword.getText().toString().trim();
        //final ProgressBar pb= findViewById(R.id.progressBar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(),login.class);
                startActivity(l);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_email.setText(null);
                reg_password.setText(null);
                repassword.setText(null);
            }
        });
        r_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    auth.createUserWithEmailAndPassword(semail, spassword)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task){
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent k = new Intent(Registration.this, Dashboard.class);
                                        startActivity(k);
                                        finish();
                                    }
                                }
                            });
            }
        });
    }
}