package com.example.spectianadminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadAssignment extends AppCompatActivity {

    private Spinner semesterCategory;
    private CardView selectImage;
    private Button uploadAssignmnet;
    private ImageView assignImageView;

    private String category;
    private final int REQ = 1;
    private Bitmap bitmap;
    ProgressDialog pd;

    private DatabaseReference reference;
    private StorageReference storageReference;
    String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignment);
        selectImage = findViewById(R.id.addAssignment);
        semesterCategory = findViewById(R.id.semester_category);
        uploadAssignmnet = findViewById(R.id.uploadAssignBtn);
        assignImageView = findViewById(R.id.assignImageView);

        reference = FirebaseDatabase.getInstance().getReference().child("assignment");
        storageReference = FirebaseStorage.getInstance().getReference().child("assignment");

        pd = new ProgressDialog(this);

        String[] items = new String[]{"Select Semester", "1", "2", "3", "4", "5", "6", "7", "8"};
        semesterCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));

        semesterCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                category = semesterCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        uploadAssignmnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bitmap == null){
                    Toast.makeText(com.example.spectianadminapp.UploadAssignment.this, "Please upload assignment", Toast.LENGTH_SHORT).show();
                }else if(category.equals("Select Semester")){
                    Toast.makeText(com.example.spectianadminapp.UploadAssignment.this, "Please select Semester ", Toast.LENGTH_SHORT).show();
                }else {
                    pd.setMessage("Uploading...");
                    pd.show();
                    uploadAssignmnet();
                }
            }
        });
    }

    private void uploadAssignmnet() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50 , baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(com.example.spectianadminapp.UploadAssignment.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(com.example.spectianadminapp.UploadAssignment.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData() {
        reference = reference.child(category);
        final String uniqueKey = reference.push().getKey();

        reference.child(uniqueKey).setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(com.example.spectianadminapp.UploadAssignment.this, "Assignment uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(com.example.spectianadminapp.UploadAssignment.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assignImageView.setImageBitmap(bitmap);
        }
    }
}