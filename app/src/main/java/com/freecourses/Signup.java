package com.freecourses;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText nm, mail,pwd,userdata,college,course;
    TextView text;
    Button reg,button,updated;
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText t1,t2,t3,t4;
    FirebaseFirestore firestore;
    String userid;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        button=findViewById(R.id.button1);
        nm = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        text=findViewById(R.id.signuptext);
        userdata=findViewById(R.id.username);
        reg = findViewById(R.id.register);
        college=findViewById(R.id.college);
        course=findViewById(R.id.course);

        Intent i=new Intent(Signup.this,login.class);


        reg.setOnClickListener(v -> PerformAuth());
        button.setOnClickListener(view -> {
            startActivity(i);

        });

        Animation myanimation = AnimationUtils.loadAnimation(Signup.this,R.anim.fade_in);
        text.startAnimation(myanimation);
        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        final String type = sharedPreferences.getString("Name", "");
        if (type.isEmpty()) {

        } else {
            Intent i1 = new Intent(Signup.this, MainActivity.class);
            startActivity(i1);
            finish();

        }

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        nm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used in this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used in this example
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                // Define the pattern to accept only characters (letters)
                String pattern = "^[a-zA-Z]+$";

                if (!input.matches(pattern)) {
                    nm.setError("Only letters are allowed");
                } else if(input.isEmpty()){
                    nm.setError("Name is Required");
                }else {
                    nm.setError(null);
                }
            }
        });
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String uname1=userdata.getText().toString();
                if(uname1.isEmpty()){
                    userdata.setError("Please Enter User Name");
                }
            }
        });





    }



    private void PerformAuth() {
        String name1 = nm.getText().toString();
        String email1 = mail.getText().toString();
        String pwdd1 = pwd.getText().toString();
        String uname1=userdata.getText().toString();
        String clg=college.getText().toString();
        String co=course.getText().toString();



        if (name1.isEmpty())
        {
            nm.setError("Please Enter Name");
        } else if (email1.isEmpty()) {
            mail.setError("Please Enter Email");
        } else if (pwdd1.isEmpty() || pwdd1.length() < 6) {
            pwd.setError("Enter 6 Digit Password");
        } else if(clg.isEmpty()){
            college.setError("Please Enter College");
        } else if (co.isEmpty()){
            course.setError("Please enter course");
        } else{

            Users users = new Users(name1, email1, pwdd1, uname1, clg, co);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("User");
            auth.createUserWithEmailAndPassword(email1,pwdd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser fuser=auth.getCurrentUser();
                        fuser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Signup.this, "Sucessful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG,"On failure:Email Not send");
                            }
                        });
                    }
                }
            });
        reference.child(uname1).setValue(users).addOnCompleteListener(task -> {

                Toast.makeText(Signup.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Signup.this, login.class);
                startActivity(i);
                finish();
            });

        }



    }
    public boolean isvalidemail(final String email){
        Pattern mpattern;
        Matcher matcher;
        final String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        mpattern=Pattern.compile(emailpattern);
        matcher=mpattern.matcher(email);
        return matcher.matches();
    }
    private boolean isConnected(){
        ConnectivityManager manager=(ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetwork() !=null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}