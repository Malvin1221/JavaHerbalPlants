package com.jhp.javaherbalplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tv_To_Login=findViewById(R.id.tv_to_login);
        Button regis=findViewById(R.id.btn_register);
        final EditText userRegis=findViewById(R.id.edt_username_register);
        EditText emailRegis=findViewById(R.id.edt_email_register);
        EditText passRegis=findViewById(R.id.edt_password_register);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userRegis.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Belum Dimasukan",Toast.LENGTH_SHORT).show();
                }
                if (emailRegis.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email Belum Dimasukan",Toast.LENGTH_SHORT).show();
                }
                if (passRegis.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Belum Dimasukan",Toast.LENGTH_SHORT).show();
                }

                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

                if(!(userRegis.getText().toString().isEmpty() && emailRegis.getText().toString().isEmpty() && passRegis.getText().toString().isEmpty())){

                    firebaseAuth.createUserWithEmailAndPassword(emailRegis.getText().toString(), passRegis.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid=task.getResult().getUser().getUid();

                                Users user= new Users(uid, userRegis.getText().toString(),emailRegis.getText().toString(),passRegis.getText().toString(),0);
                                firebaseDatabase.getReference().child("Users").child(uid).setValue(user);

                                Intent in= new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(in);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        tv_To_Login.setOnClickListener((view) ->{
            Intent in= new Intent(RegisterActivity.this,MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
    }
}