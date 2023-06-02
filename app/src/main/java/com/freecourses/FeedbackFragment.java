package com.freecourses;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import soup.neumorphism.NeumorphButton;

public class FeedbackFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText name, sugg;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_feedback, container, false);
        NeumorphButton button=view1.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    name = view1.findViewById(R.id.fname);
                    sugg = view1.findViewById(R.id.suggestion);
                    String s = name.getText().toString();
                    String s1 =sugg.getText().toString();
                    suggestion suggestion = new suggestion(s,s1);
                    db = FirebaseDatabase.getInstance();
                    if (s.isEmpty()) {
                        name.setError("Name Cannot be Empty");
                    } else if (s1.isEmpty()) {
                        sugg.setError("Suggestion cannot be empty");
                    } else {
                        reference = db.getReference("Feedback");
                        reference.child(s).setValue(suggestion).addOnCompleteListener(task -> {
                            FancyToast.makeText(getContext(), "FeedBack Sended Sucessfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            name.setText("");
                            sugg.setText("");
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }});
        return view1;
        }
    }



