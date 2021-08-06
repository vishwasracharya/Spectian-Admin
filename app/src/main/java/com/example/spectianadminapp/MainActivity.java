package com.example.spectianadminapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView uploadNotice , addGalleryImage, addAssignment, addEbook, faculty, deleteNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addAssignment = findViewById(R.id.addAssignment);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addAssignment.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.addNotice:
                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent = new Intent(MainActivity.this, UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addAssignment:
                intent = new Intent(MainActivity.this, UploadAssignment.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(MainActivity.this, UploadPDFActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
        }
    }
}