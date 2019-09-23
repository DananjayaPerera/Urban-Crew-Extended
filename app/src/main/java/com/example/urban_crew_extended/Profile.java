package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> origin/master
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
=======
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
>>>>>>> origin/master
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
<<<<<<< HEAD
import com.google.android.material.floatingactionbutton.FloatingActionButton;
=======
>>>>>>> origin/master
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theartofdev.edmodo.cropper.CropImageView.Guidelines;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private CircleImageView profilePic;
    private TextView profile_userName, profile_userEmail, profile_userPhone;
    private FirebaseAuth firebaseAuth;
    Uri imageUri;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private String currentUser;
    private StorageReference storageReference;
    private static final int GalleryPick = 1;
<<<<<<< HEAD
    private TextView profile_inf_email;
    private EditText profile_inf_username,profile_inf_phone;
    private FloatingActionButton floatingActionButton;
=======
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        profilePic = findViewById(R.id.profile_pic);
        profile_userName =  findViewById(R.id.profile_username_1);
        profile_userEmail = findViewById(R.id.profile_email_1);
        profile_userPhone = findViewById(R.id.profile_phone_1);


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Image");



        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String UserName = dataSnapshot.child("UserName").getValue().toString();
               String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
               String UserPhone = dataSnapshot.child("UserPhone").getValue().toString();
               profile_userName.setText(UserName);
               profile_userEmail.setText(UserEmail);
               profile_userPhone.setText(UserPhone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

<<<<<<< HEAD
        floatingActionButton = findViewById(R.id.btn_float_profile);
        floatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });
    }

    public void onChooseFile(View v){

        CropImage.activity().start(Profile.this);
=======
        profilePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GalleryPick);
            }
        });
>>>>>>> origin/master

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
<<<<<<< HEAD

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                imageUri = result.getUri();
                profilePic.setImageURI(imageUri);

                StorageReference myRef = storageReference.child(firebaseAuth.getUid()).child("Profile Image");
                myRef.putFile(imageUri);

                Toast.makeText(Profile.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            }

            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                Exception e = result.getError();
                Toast.makeText(this, "Error"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openDialog() {

        final AlertDialog.Builder dialog = new Builder(Profile.this);
        dialog.setTitle("Update Profile");
        LayoutInflater inflater = LayoutInflater.from(Profile.this);
        View profile_layout = inflater.inflate(R.layout.profile_dialog, null);

        profile_inf_username = profile_layout.findViewById(R.id.profile_info_username);
        profile_inf_email = profile_layout.findViewById(R.id.profile_info_email);
        profile_inf_phone = profile_layout.findViewById(R.id.profile_info_phone);

        dialog.setView(profile_layout);

        final DatabaseReference databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid())
                .child("User Info");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String UserName = dataSnapshot.child("UserName").getValue().toString();
                String Email = dataSnapshot.child("UserEmail").getValue().toString();
                String Phone = dataSnapshot.child("UserPhone").getValue().toString();

                profile_inf_username.setText(UserName);
                profile_inf_email.setText(Email);
                profile_inf_phone.setText(Phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final DatabaseReference databaseReference2 = firebaseDatabase.getReference(firebaseAuth.getUid())
                        .child("User Info");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        databaseReference2.child("UserName").setValue(profile_inf_username.getText().toString());
                        databaseReference2.child("UserEmail").setValue(profile_inf_email.getText().toString());
                        databaseReference2.child("UserPhone").setValue(profile_inf_phone.getText().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
=======
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null){

            Uri imageUri = data.getData();

            CropImage.activity().setGuidelines(Guidelines.ON).setAspectRatio(1,1).start(Profile.this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK){

                final Uri resultUri = result.getUri();

                final StorageReference filePath = storageReference.child(currentUser + ".jpg");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] data1 = baos.toByteArray();

                UploadTask uploadTask = storageReference.putBytes(data1);

                uploadTask = filePath.putFile(resultUri);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()) {

                            Picasso.get().load(resultUri).into(profilePic);
                            Toast.makeText(Profile.this, "Profile Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        } else {

                            // Handle failures
                            // ...
                        }
                    }
                });

                /*filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<TaskSnapshot> task) {


                        if (task.isSuccessful()){

                            Toast.makeText(Profile.this, "Profile Image Uploaded Successfully", Toast.LENGTH_SHORT).show();


                        } else {

                            String message = task.getException().toString();
                            Toast.makeText(Profile.this, "Error :" +message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

            }
        }
    }

    /*public void onChooseFile(View v){

        CropImage.activity().start(Profile.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                imageUri = result.getUri();
                profilePic.setImageURI(imageUri);

                StorageReference myRef = storageReference.child(firebaseAuth.getUid()).child("Profile Image");

                Toast.makeText(Profile.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            }

        }
    }*/


>>>>>>> origin/master
}
