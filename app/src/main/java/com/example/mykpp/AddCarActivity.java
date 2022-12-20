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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddCarActivity extends AppCompatActivity {

    EditText carNum, carMark, carModel, carColor, carTypeTC, carNumPTC;

    DatabaseReference reference;
    FirebaseUser fuser;
    String userNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Добавление авто");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        userNow = fuser.getUid();

        carNum = findViewById(R.id.carNum);
        carMark = findViewById(R.id.carMark);
        carModel = findViewById(R.id.carModel);
        carColor = findViewById(R.id.carColor);
        carTypeTC = findViewById(R.id.carTypeTC);
        carNumPTC = findViewById(R.id.carTypePTC);

        Button add = findViewById(R.id.btnCarAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carNum1, carMark1, carModel1, carColor1, carTypeTC1, carNumPTC1;
                carNum1 = carNum.getText().toString();
                carMark1 = carMark.getText().toString();
                carModel1 = carModel.getText().toString();
                carColor1 = carColor.getText().toString();
                carTypeTC1 = carTypeTC.getText().toString();
                carNumPTC1 =  carNumPTC.getText().toString();
                reference = FirebaseDatabase.getInstance().getReference("Cars");
                if (TextUtils.isEmpty(carNum1) || TextUtils.isEmpty(carMark1) || TextUtils.isEmpty(carModel1) ||
                        TextUtils.isEmpty(carColor1) || TextUtils.isEmpty(carTypeTC1) || TextUtils.isEmpty(carNumPTC1)) {
                    Toast.makeText(AddCarActivity.this, "Заполните все поля",
                            Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("carNum", carNum.getText().toString());
                    hashMap.put("carMark", carMark.getText().toString());
                    hashMap.put("carModel", carModel.getText().toString());
                    hashMap.put("carColor", carColor.getText().toString());
                    hashMap.put("carTypeTC", carTypeTC.getText().toString());
                    hashMap.put("carNumPTC", carNumPTC.getText().toString());
                    hashMap.put("carDriver", userNow);
                    hashMap.put("carAccess", "false");
                    reference.push().setValue(hashMap);
                    Toast.makeText(AddCarActivity.this, "Авто добавлен",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCarActivity.this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }

            }
        });

    }
}