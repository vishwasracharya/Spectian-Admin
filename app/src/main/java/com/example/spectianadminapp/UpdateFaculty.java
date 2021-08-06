package com.example.spectianadminapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView itDepartment, mechanicalDepartment, electricalDepartment, csDepartment, civilDepartment;
    private LinearLayout itNoData, mechNoData, ecNoData, csNoData, civilNoData;
    private List<com.example.spectianadminapp.TeacherData> list1, list2, list3, list4, list5;
    private com.example.spectianadminapp.TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        itDepartment = findViewById(R.id.itDepartment);
        mechanicalDepartment = findViewById(R.id.mechanicalDepartment);
        electricalDepartment = findViewById(R.id.electricalDepartment);
        csDepartment = findViewById(R.id.csDepartment);
        civilDepartment = findViewById(R.id.civilDepartment);

        itNoData = findViewById(R.id.itNoData);
        mechNoData = findViewById(R.id.mechNoData);
        ecNoData = findViewById(R.id.ecNoData);
        csNoData = findViewById(R.id.csNoData);
        civilNoData = findViewById(R.id.civilNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        itDepartment();
        mechanicalDepartment();
        electricalDepartment();
        csDepartment();
        civilDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.spectianadminapp.UpdateFaculty.this, com.example.spectianadminapp.AddTeacher.class));
            }
        });
    }

    private void itDepartment() {
        dbRef = reference.child("Information Technology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    itNoData.setVisibility(View.VISIBLE);
                    itDepartment.setVisibility(View.GONE);
                }else {

                    itNoData.setVisibility(View.GONE);
                    itDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        com.example.spectianadminapp.TeacherData data = snapshot.getValue(com.example.spectianadminapp.TeacherData.class);
                        list1.add(data);
                    }
                    itDepartment.setHasFixedSize(true);
                    itDepartment.setLayoutManager(new LinearLayoutManager(com.example.spectianadminapp.UpdateFaculty.this));
                    adapter = new com.example.spectianadminapp.TeacherAdapter(list1, com.example.spectianadminapp.UpdateFaculty.this, "Information Technology");
                    itDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.example.spectianadminapp.UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechanicalDepartment() {
        dbRef = reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    mechNoData.setVisibility(View.VISIBLE);
                    mechanicalDepartment.setVisibility(View.GONE);
                }else {

                    mechNoData.setVisibility(View.GONE);
                    mechanicalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        com.example.spectianadminapp.TeacherData data = snapshot.getValue(com.example.spectianadminapp.TeacherData.class);
                        list2.add(data);
                    }
                    mechanicalDepartment.setHasFixedSize(true);
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(com.example.spectianadminapp.UpdateFaculty.this));
                    adapter = new com.example.spectianadminapp.TeacherAdapter(list2, com.example.spectianadminapp.UpdateFaculty.this, "Mechanical");
                    mechanicalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.example.spectianadminapp.UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void electricalDepartment() {
        dbRef = reference.child("Electrical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    ecNoData.setVisibility(View.VISIBLE);
                    electricalDepartment.setVisibility(View.GONE);
                }else {

                    ecNoData.setVisibility(View.GONE);
                    electricalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        com.example.spectianadminapp.TeacherData data = snapshot.getValue(com.example.spectianadminapp.TeacherData.class);
                        list3.add(data);
                    }
                    electricalDepartment.setHasFixedSize(true);
                    electricalDepartment.setLayoutManager(new LinearLayoutManager(com.example.spectianadminapp.UpdateFaculty.this));
                    adapter = new com.example.spectianadminapp.TeacherAdapter(list3, com.example.spectianadminapp.UpdateFaculty.this, "Electrical");
                    electricalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.example.spectianadminapp.UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void csDepartment() {
        dbRef = reference.child("Computer");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else {

                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        com.example.spectianadminapp.TeacherData data = snapshot.getValue(com.example.spectianadminapp.TeacherData.class);
                        list4.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(com.example.spectianadminapp.UpdateFaculty.this));
                    adapter = new com.example.spectianadminapp.TeacherAdapter(list4, com.example.spectianadminapp.UpdateFaculty.this, "Computer");
                    csDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.example.spectianadminapp.UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civilDepartment() {
        dbRef = reference.child("Civil");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    civilNoData.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }else {

                    civilNoData.setVisibility(View.GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        com.example.spectianadminapp.TeacherData data = snapshot.getValue(com.example.spectianadminapp.TeacherData.class);
                        list5.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(com.example.spectianadminapp.UpdateFaculty.this));
                    adapter = new com.example.spectianadminapp.TeacherAdapter(list5, com.example.spectianadminapp.UpdateFaculty.this, "Civil");
                    civilDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.example.spectianadminapp.UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}