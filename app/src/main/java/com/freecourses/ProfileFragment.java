package com.freecourses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileFragment extends Fragment{
    EditText t1,t2,t3,t4,t5,t6;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    Button updated;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressBar progressBar;
    public String textname,textmail,textpass,textclg,textcouse;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        final SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        final String type=sharedPreferences.getString("Name","");
        t1 = view.findViewById(R.id.namefixed);
        t2 = view.findViewById(R.id.usernamefixed);
        t3 = view.findViewById(R.id.pmail);
        t4 = view.findViewById(R.id.ppass);
        t5=view.findViewById(R.id.college);
        t6=view.findViewById(R.id.course);

        ProgressBar progressBar = view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();

        progressBar.setIndeterminateDrawable(doubleBounce);
        updated=view.findViewById(R.id.pbutton);
        TextView textView=view.findViewById(R.id.visibletext);


        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("User");
        myref.child(type).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot=task.getResult();
                        textname=String.valueOf(dataSnapshot.child("name").getValue());
                        textmail=String.valueOf(dataSnapshot.child("email").getValue());
                        textpass=String.valueOf(dataSnapshot.child("pass").getValue());
                        textclg=String.valueOf(dataSnapshot.child("college").getValue());
                        textcouse=String.valueOf(dataSnapshot.child("course").getValue());

                        t1.setText(textname);
                        t2.setText(type);
                        t3.setText(textmail);
                        t4.setText(textpass);
                        t5.setText(textclg);
                        t6.setText(textcouse);
                        progressBar.setVisibility(View.GONE);
                        textView.setText("");
                    }
                }

            }
        });
        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedname = t1.getText().toString();
                String updateduser = t2.getText().toString();
                String updatedemail = t3.getText().toString();
                String updatedpass=t4.getText().toString();
                String updatedclg=t5.getText().toString();
                String updatedcourse=t6.getText().toString();

                    Users users = new Users(updatedname, updatedemail, updatedpass, updateduser, updatedcourse, updatedclg);
                    db = FirebaseDatabase.getInstance();
                    myref = db.getReference("User");
                    myref.child(updateduser).setValue(users).addOnCompleteListener(task -> {
                        FancyToast.makeText(getContext(), "Updation Sucessfull", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                    });
                }
        });

        return view;
    }
}