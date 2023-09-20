package com.freecourses;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    public String type;
    EditText umail, upwd;
    Button clicklogin, newac;
    TextView e2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    FirebaseAuth auth;
    BroadcastReceiver broadcastReceiver;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);
        umail = findViewById(R.id.mail);
        upwd = findViewById(R.id.password);
        e2 = findViewById(R.id.error2);

        clicklogin = findViewById(R.id.Login);
        newac = findViewById(R.id.newacc);



        Intent regintent = new Intent(login.this, Signup.class);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("User");

        newac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regintent);
                finish();

            }
        });

        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        type = sharedPreferences.getString("Name", "");


        if (type.isEmpty()) {

        } else {
            Intent i = new Intent(login.this, MainActivity.class);
            startActivity(i);
            finish();

        }
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        myref = firebaseDatabase.getReference("User");
//        clicklogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nm = umail.getText().toString();
//                String pass1 = upwd.getText().toString();
//                Query query = myref.orderByChild("uname").equalTo(nm);
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()) {
//                            myref.child(type).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                    if (task.isSuccessful()) {
//                                        if (task.getResult().exists()) {
//                                            DataSnapshot dataSnapshot = task.getResult();
//                                            String loginmail = String.valueOf(dataSnapshot.child("email").getValue());
//                                            String loginpass = String.valueOf(dataSnapshot.child("pass").getValue());
//                                            if (nm.equals(loginmail) && pass1.equals(loginpass)) {
//                                                Toast.makeText(login.this, "Login Sucess", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(login.this, "Wrong Information" + loginmail + " " + loginpass, Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    } else
//                                    {
//                                        Toast.makeText(login.this, "No Data Found", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        }
//                        else{
//                            Toast.makeText(login.this, "No Record Found", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//        });
        clicklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth=FirebaseAuth.getInstance();
                String email = umail.getText().toString();
                String pass1 = upwd.getText().toString();
                if (email.isEmpty()) {
                    umail.setError("Please Enter UserName");
                } else if (pass1.isEmpty()) {
                    e2.setText("Please Enter Password");
                }else {
                    e2.setText("");

//                    auth.signInWithEmailAndPassword(email,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()){
//                                Toast.makeText(login.this, "Log In Sucessfully", Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(login.this, MainActivity.class);
//                                startActivity(i);
//                                finish();
//                            }else {
//                                Toast.makeText(login.this, "Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                    Query query = myref.orderByChild("uname").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String p1 = snapshot.child(email).child("pass").getValue(String.class);
                            if (pass1.equals(p1)) {
                                //For Skip login Activity

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String nm = umail.getText().toString();
                                String pass1 = upwd.getText().toString();
                                editor.putString("Name1", nm);
                                editor.putString("Password", pass1);
//                                editor.commit();
                                
                                Toast.makeText(login.this, "Log In Sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(login.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                                builder.setCancelable(true);
                                builder.setTitle("Incorrect Password");
                                builder.setMessage("Password Does Not Match With UserName");
                                builder.show();
                            }
                        } else {

                            AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                            builder.setCancelable(true);
                            builder.setTitle("User Not Found");
                            builder.setMessage("No UserName Exist In Records");
                            builder.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                }
        }
        });
    }
    protected void  registered(){
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    private boolean isConnected(){
        ConnectivityManager manager=(ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetwork() !=null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
