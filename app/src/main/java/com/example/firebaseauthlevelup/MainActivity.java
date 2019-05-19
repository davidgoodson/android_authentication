package com.example.firebaseauthlevelup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    TextView txtUserEmail;

    @BindView(R.id.userID)
    TextView txtUserID;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        super.onResume();
        String email = getIntent().getStringExtra("EMAIL");
        String userid = getIntent().getStringExtra("USERID");


        if(email == null)
            txtUserEmail.setText("NONE");
        else
            txtUserEmail.setText(email);

        if (userid == null)
            txtUserID.setText("NONE");
        else
            txtUserID.setText(userid);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

    }


}
