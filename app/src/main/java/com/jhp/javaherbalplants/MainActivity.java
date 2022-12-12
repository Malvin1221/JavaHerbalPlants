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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login=findViewById(R.id.btn_login);
        TextView tv_To_Regis=findViewById(R.id.tv_to_register);
        EditText emailLogin = findViewById(R.id.edt_email_login);
        EditText passLogin = findViewById(R.id.edt_password_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailLogin.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email Belum Dimasukan",Toast.LENGTH_SHORT).show();
                }
                if (passLogin.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Belum Dimasukan",Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if(!(emailLogin.getText().toString().isEmpty() && passLogin.getText().toString().isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(),passLogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                String uid = task.getResult().getUser().getUid();
                                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int usertype=snapshot.getValue(Integer.class);
                                        if(usertype==0){
                                            Intent in = new Intent(MainActivity.this,HomeActivity.class);
                                            startActivity(in);

                                        }
                                        if(usertype==1){
                                            Intent in = new Intent(MainActivity.this,HomeAdminActivity.class);
                                            startActivity(in);
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tv_To_Regis.setOnClickListener((view) ->{
            Intent in= new Intent(MainActivity.this,RegisterActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
    }
}