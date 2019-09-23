package com.example.urban_crew_extended;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class BookNowAltoK10 extends AppCompatActivity{

    Button button_1;
    EditText userLocation_1,userpickupLocation,editText_date, editText_time;
    TextView user,email,phone;
    int year, month, day;
    String DMY,YourLocation,PickUpLocation,Date,Time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Calendar calendar_1;
    int currentHour;
    int currentMinute;
    String amPm;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private EditText tripLocation, tripPickup, tripDate, tripTime, tripBook, tripExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now_alto_k10);


        Toolbar toolbar = findViewById(R.id.toolbar_bookNowAlto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AltoK10 2019");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_1 = (Button)findViewById(R.id.selectPayment);
        button_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                YourLocation = userLocation_1.getText().toString();
                PickUpLocation = userpickupLocation.getText().toString();
                Date = editText_date.getText().toString();
                Time = editText_time.getText().toString();

                if (YourLocation.isEmpty() | PickUpLocation.isEmpty() | Date.isEmpty() | Time.isEmpty()){

                    Toast.makeText(BookNowAltoK10.this, "Field's can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                SendInput();
                openDialog();

            }
        });


        editText_date = findViewById(R.id.datePicker_alto);
        final Calendar calendar = Calendar.getInstance();
        editText_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookNowAltoK10.this, new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        DMY = dayOfMonth+"/"+(month+1)+"/"+year;
                        editText_date.setText(DMY);

                    }
                },year, month, day);

                datePickerDialog.show();

                    }
                });

        editText_time = findViewById(R.id.timePicker_alto);
        editText_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar_1 = Calendar.getInstance();
                currentHour = calendar_1.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar_1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(BookNowAltoK10.this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 12){

                            amPm = "PM";
                        } else {

                            amPm = "AM";
                        }

                        editText_time.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);

                    }
                },currentHour,currentMinute,false);

                timePickerDialog.show();
            }
        });

        userLocation_1 = findViewById(R.id.alto_your_location);
        userpickupLocation = findViewById(R.id.alto_pickup_location);
        user = findViewById(R.id.username_bookAlto);
        email = findViewById(R.id.email_bookAlto);
        phone = findViewById(R.id.phone_bookAlto);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String UserName = dataSnapshot.child("UserName").getValue().toString();
                String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
                String UserPhone = dataSnapshot.child(("UserPhone")).getValue().toString();
                user.setText(UserName);
                email.setText(UserEmail);
                phone.setText(UserPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(BookNowAltoK10.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void SendInput(){

        YourLocation = userLocation_1.getText().toString();
        PickUpLocation = userpickupLocation.getText().toString();
        Date = editText_date.getText().toString();
        Time = editText_time.getText().toString();

        DatabaseReference myref1 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information").child("UserLocation");
        DatabaseReference myref2 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information").child("PickUpLocation");
        DatabaseReference myref3 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information").child("Date");
        DatabaseReference myref4 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information").child("Time");

        myref1.setValue(YourLocation);
        myref2.setValue(PickUpLocation);
        myref3.setValue(Date);
        myref4.setValue(Time);
    }

    private void openDialog() {

        final AlertDialog.Builder dialog = new Builder(BookNowAltoK10.this);
        dialog.setTitle("Booking Information");
        LayoutInflater inflater = LayoutInflater.from(BookNowAltoK10.this);
        View book_layout = inflater.inflate(R.layout.layout_dialog, null);

        tripLocation = book_layout.findViewById(R.id.trip_info_location);
        tripPickup = book_layout.findViewById(R.id.trip_info_pickup);
        tripDate = book_layout.findViewById(R.id.trip_info_date);
        tripTime = book_layout.findViewById(R.id.trip_info_time);
        tripBook = book_layout.findViewById(R.id.trip_info_book);
        tripExtra = book_layout.findViewById(R.id.trip_info_extra);

        dialog.setView(book_layout);

        final DatabaseReference databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid())
                .child("AltoK10").child("Booking Information");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String location = dataSnapshot.child("UserLocation").getValue().toString();
                String pickUp = dataSnapshot.child("PickUpLocation").getValue().toString();
                String date = dataSnapshot.child("Date").getValue().toString();
                String time = dataSnapshot.child("Time").getValue().toString();
                String book = dataSnapshot.child("Booking Price").getValue().toString();
                String extra = dataSnapshot.child("Rental Extra Options").getValue().toString();

                tripLocation.setText(location);
                tripPickup.setText(pickUp);
                tripDate.setText(date);
                tripTime.setText(time);
                tripBook.setText(book);
                tripExtra.setText(extra);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final DatabaseReference databaseReference2 = firebaseDatabase.getReference(firebaseAuth.getUid())
                        .child("AltoK10").child("Booking Information");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        databaseReference2.child("UserLocation").setValue(tripLocation.getText().toString());
                        databaseReference2.child("PickUpLocation").setValue(tripPickup.getText().toString());
                        databaseReference2.child("Date").setValue(tripDate.getText().toString());
                        databaseReference2.child("Time").setValue(tripTime.getText().toString());
                        databaseReference2.child("Booking Price").setValue(tripBook.getText().toString());
                        databaseReference2.child("Rental Extra Options").setValue(tripExtra.getText().toString());

                        Intent intent = new Intent(BookNowAltoK10.this, PaymentSelection.class);
                        startActivity(intent);

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
}
