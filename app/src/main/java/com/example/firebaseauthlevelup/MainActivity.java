package com.example.firebaseauthlevelup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }



    private  void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("New User", "createUserWithEmailAndPassword: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w("New User", "createUserWithEmailAndPassword failed", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication Fail!", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    private void signIn(String email, String password{
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Sign In", "signInWithEmailAndPassword: successful");
                    FirebaseUser user = mAuth.getCurrentUser();
                }else{
                    Log.w("New User", "signInWithEmailAndPassword: fail!", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication Fail", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    private  void updateUI(FirebaseUser user){}
}
