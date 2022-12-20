package com.example.mykpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;

    Button btn_register;

    EditText textSurname, textName, textPatronymic, textEmail, textPassword, textPassword_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Регистрация");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, AuthActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        textSurname = findViewById(R.id.textSurname);
        textName = findViewById(R.id.textName);
        textPatronymic = findViewById(R.id.textPatronymic);
        textPassword = findViewById(R.id.textPassword);
        textEmail = findViewById(R.id.textEmail);
        textPassword_2 = findViewById(R.id.textPassword2);

        btn_register = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name, Surname, Patronymic, Email, Password, Password_2;
                Name = textName.getText().toString();
                Surname = textSurname.getText().toString();
                Patronymic = textPatronymic.getText().toString();
                Email = textEmail.getText().toString();
                Password = textPassword.getText().toString();
                Password_2 = textPassword_2.getText().toString();

                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Surname) || TextUtils.isEmpty(Patronymic) ||
                        TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                    Toast.makeText(RegisterActivity.this, "Заполните все поля",
                            Toast.LENGTH_SHORT).show();
                } else if (!Password.equals(Password_2)) {
                    Toast.makeText(RegisterActivity.this, "Пароли не совпадают",
                            Toast.LENGTH_SHORT).show();
                } else {
                    register(Name, Surname, Patronymic, Email, Password);
                }
            }
        });

    }

    private void register(String Name, String Surname, String Patronymic, String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("Name", Name);
                            hashMap.put("Surname", Surname);
                            hashMap.put("Patronymic", Patronymic);
                            hashMap.put("Email", Email);
                            hashMap.put("Password", Password);
                            hashMap.put("LicenseIMG", "false");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Регистрация не удалась",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}