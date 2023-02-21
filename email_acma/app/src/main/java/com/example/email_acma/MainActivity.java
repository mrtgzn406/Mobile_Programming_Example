package com.example.email_acma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mRecipientEt,mSubjectEt,mMessageEt;
    Button mSendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipientEt = findViewById(R.id.recipientEt);
        mSubjectEt = findViewById(R.id.subjectEt);
        mMessageEt = findViewById(R.id.messageEt);
        mSendEmailBtn = findViewById(R.id.sendEmailBtn);

        mSendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipient = mRecipientEt.getText().toString().trim();
                String subject = mSubjectEt.getText().toString().trim();
                String message = mMessageEt.getText().toString();

                mSendEmail(recipient, subject, message);
            }
        });
    }

    private void mSendEmail(String recipient, String subject, String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);

        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");

        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }
}