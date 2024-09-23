package com.example.bookshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    // Button
    private Button move1;
    private MaterialButton move2;
    private MaterialButton move3;
    // Button

    // Database
    FirebaseAuth auth;
    EditText username ,pass ;
    public String found ;
    // Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Login Button starts
        auth = FirebaseAuth.getInstance();
        // search for id's
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        move2 = findViewById(R.id.login);

        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();

                // if pass and user box empty : show a message to fill all box
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
                    Toast.makeText(loginActivity.this, "Please! Fill Up All Field", Toast.LENGTH_SHORT).show();

                else
                {
                    // Authentication : checking database with email and password
                    auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        // authentication successful with database
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginActivity.this, homeActivity.class);
                                // pass login data
                                intent.putExtra("emails" ,user);
                                // pass login data
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(loginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        // login Button ends


        // Registration Button Starts
        move3 = findViewById(R.id.registration);
        move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this , registrationActivity.class );
                startActivity(intent); // go to registration screen
            }
        });
        // Registration Button Ends
    }


}