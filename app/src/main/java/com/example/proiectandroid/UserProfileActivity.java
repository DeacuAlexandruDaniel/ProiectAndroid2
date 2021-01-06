package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    private static final int CHOSE_IMAGE = 101;
    ImageView imgViewProfilePicture;
    EditText editTextUsername;
    Button btnSaveProfileInfo;

    Uri uriProfileImage;

    String profileImageURL;

    FirebaseAuth mAuth;

    private void showImageChoser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), CHOSE_IMAGE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imgViewProfilePicture = findViewById(R.id.imgViewProfilePicture);
        editTextUsername = findViewById(R.id.editTextUsername);
        btnSaveProfileInfo = findViewById(R.id.btnSaveProfileInfo);

        mAuth = FirebaseAuth.getInstance();

        imgViewProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChoser();
            }
        });

        loadUserInformation();

        btnSaveProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(UserProfileActivity.this, MainActivity.class));
        }
    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgViewProfilePicture);
            }
            if (user.getDisplayName() != null) {
                editTextUsername.setText(user.getDisplayName());
            }
        }
    }

    private void uploadImageToFirebaseStorage(){
        final StorageReference profileImageRef;
        profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
        if(uriProfileImage != null){
            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    profileImageURL = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //delete @Nullable??? pt ca se verifica in if daca data e null??
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriProfileImage = data.getData(); // returneaza Uri object cu imaginea

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imgViewProfilePicture.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveUserInformation(){
        String username = editTextUsername.getText().toString();

        if(username.isEmpty()){
            editTextUsername.setError("Name required");
            editTextUsername.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null && profileImageURL != null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                             .setDisplayName(username)
                                             .setPhotoUri(Uri.parse(profileImageURL))
                                             .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserProfileActivity.this, "Profile Saved", Toast.LENGTH_LONG).show();
                    }
                }
            });

    }
}}