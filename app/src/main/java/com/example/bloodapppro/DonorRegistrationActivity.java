package com.example.bloodapppro;
//Validate user input
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonorRegistrationActivity extends AppCompatActivity {
    private TextView backButton;

    private CircleImageView profile_image;
    private TextInputEditText registerFullName, registerIdNumber, registerPhoneNumber, registerEmail, registerPassword;
    private Spinner bloodGroupSpinner;
    private Button registerButton;

    private Uri resultUri;
    private ProgressDialog loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_registration);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_image= findViewById(R.id.profile_image);
        registerFullName = findViewById(R.id.registerFullName);
        registerIdNumber = findViewById(R.id.registerIdNumber);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        bloodGroupSpinner= findViewById(R.id.bloodGroupSpinner);
        registerButton = findViewById(R.id.registerButton);
        loader = new ProgressDialog(this);

        profile_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final String email = registerEmail.getText().toString().trim();
             final String password = registerPassword.getText().toString().trim();
             final String fullName = registerFullName.getText().toString().trim();
             final String idNumber = registerIdNumber.getText().toString().trim();
             final String phoneNumber = registerPhoneNumber.getText().toString().trim();
             final String bloodGroup = bloodGroupSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    registerPassword.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registerFullName.setError("Full name is required");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)){
                    registerIdNumber.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    registerPhoneNumber.setError("Email is required");
                    return;
                }
                if (bloodGroup.equals("Select your blood group")){
                    Toast.makeText(DonorRegistrationActivity.this, "Select Blood group", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    loader.setMessage( "Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode ==1 && resultCode == RESULT_OK && data !=null){
            resultUri = data.getData();
            profile_image.setImageURI(resultUri);
        }
    }
}