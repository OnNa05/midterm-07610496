package com.example.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText merterEditText;
    EditText timeEditText ;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        merterEditText = findViewById(R.id.meter_edit_text);
        timeEditText = findViewById(R.id.time_edit_text);
        resultTextView = findViewById(R.id.result_textview);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String merter =  merterEditText.getText().toString();
                String time = timeEditText.getText().toString();
                if(merter.isEmpty() || time.isEmpty()){
                    Toast t = Toast.makeText(
                            MainActivity.this,
                            R.string.requrired,
                            Toast.LENGTH_LONG);
                    t.show();
                }
                else if(time.equals("0")){
                    Toast t = Toast.makeText(
                            MainActivity.this,
                            R.string.time_zero,
                            Toast.LENGTH_LONG);
                    t.show();
                }
                else{
                    double merterD = Double.parseDouble(merter);
                    double timeD = Double.parseDouble(time);
                    double result =  (((1/timeD)*merterD)*18)/5;
                    String resultStr = String.format(Locale.getDefault(),"%.2f",result);
                    resultTextView.setText(resultStr);
                    if(result > 80){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("SPEED CALCULATOR");
                        dialog.setMessage(R.string.over);
                        dialog.setPositiveButton("OK", null);
                        dialog.show();
                    }
                }
            }
        });
        Button clearbutton = findViewById(R.id.clear_button);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                merterEditText.setText("");
                timeEditText.setText("");
                resultTextView.setText("");
            }
        });
    }
}