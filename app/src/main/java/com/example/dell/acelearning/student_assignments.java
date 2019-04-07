package com.example.dell.acelearning;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Map;

public class student_assignments extends Fragment {
    RecyclerView recyclerView;
    int i=0;
    String subject, lecture_name,lecture_date, lecture_by, lecture_url;

    public student_assignments() {
        // Required empty public constructor
    }
    public String getSubject()
    {return subject;}
    public void setSubject(String subject)
    {
        this.subject=subject;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_assignments, container, false);
        subject = getArguments().getString("subject");
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("study_material").child(subject).child("assignments");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {
                            Map<String,String> lec_stud=(Map) zoneSnapshot.getValue();
                            lecture_name=lec_stud.get("name");
                            lecture_by=lec_stud.get("upd_by");
                            lecture_url=lec_stud.get("url");
                            lecture_date=lec_stud.get("date");
                            //Toast.makeText(getContext(), String.valueOf(i++), Toast.LENGTH_SHORT).show();
 ((student_assignment_adapter)recyclerView.getAdapter()).update(lecture_name, lecture_by,lecture_date,lecture_url);

                           }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        student_assignment_adapter adapter=new student_assignment_adapter(recyclerView, getContext(), new ArrayList<String>(), new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(adapter);
        return view;
    }

}


