package com.example.sesh.fc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FC extends AppCompatActivity {

    private Button fb;
    private Button cb;
    private EditText fv;
    private EditText cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc);

        fb = (Button)findViewById(R.id.fbutton);
        cb = (Button)findViewById(R.id.cbutton);
        fv = (EditText)findViewById(R.id.fval);
        cv = (EditText)findViewById(R.id.cval);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float fval = Float.parseFloat(fv.getText().toString());
                float cval = (fval-32)*5/9;
                String cstr = String.format("%4.1f",cval);
                cv.setText(cstr);
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float cval = Float.parseFloat(cv.getText().toString());
                float fval = cval*9/5+32;
                String fstr = String.format("%4.1f",fval);
                fv.setText(fstr);
            }
        });
    }
}
