package com.example.unitconverter2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Declaring widgets

    Integer SpinnerSelection;
    EditText user_input;
    TextView Output1;
    TextView Output2;
    TextView Output3;
    TextView Unit1;
    TextView Unit2;
    TextView Unit3;
    ImageButton img1;
    ImageButton img2;
    ImageButton img3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare variables to work with the widgets.
        user_input = findViewById(R.id.edittext);
        Output1 = findViewById(R.id.output1);
        Output2 = findViewById(R.id.output2);
        Output3 = findViewById(R.id.output3);
        Unit1 = findViewById(R.id.unit1);
        Unit2 = findViewById(R.id.unit2);
        Unit3 = findViewById(R.id.unit3);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        //Create an ArrayAdapter using the string array and a default spinner layout
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String option = parent.getItemAtPosition(position).toString();

        if(option.toString().equals("Choose Unit")){
            Selectnull(parent);

        }
        if (option.toString().equals("Metre")){
            SelectMeters(parent);
        }
        if (option.toString().equals("Celsius")){
            SelectCelsius(parent);
        }
        if (option.toString().equals("Kilograms")){
            SelectKilograms(parent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Selectnull (AdapterView<?> parent) {

        SpinnerSelection = 0;
        user_input.setText("");
        Output1.setText("");
        Output2.setText("");
        Output3.setText("");
        Unit1.setText("");
        Unit2.setText("");
        Unit3.setText("");

    }


    public void SelectMeters (AdapterView<?> parent) {

        SpinnerSelection = 1;
        user_input.setText("0");
        Output1.setText("");
        Output2.setText("");
        Output3.setText("");
        Unit1.setText("Centimetre");
        Unit2.setText("Foot");
        Unit3.setText("Inch");

    }

    public void ConvertMetres(View view) {

        if (SpinnerSelection != 1){
            Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;

        try{
            String stringInput = user_input.getText().toString();
            inputValue = Double.parseDouble(stringInput);
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter a valid value", Toast.LENGTH_SHORT).show();
            return;
        }

        double out1 = (inputValue * 100); // m to cm
        double out2 = (inputValue * 3.28084); // m to ft
        double out3 = inputValue * 39.3701; // m to in


        DecimalFormat df = new DecimalFormat("#.##");
        Output1.setText(df.format(out1));
        Output2.setText(df.format(out2));
        Output3.setText(df.format(out3));
    }


    public void SelectCelsius(AdapterView<?> parent) {

        SpinnerSelection = 2;
        user_input.setText("0");
        Output1.setText("");
        Output2.setText("");
        Output3.setText("");
        Unit1.setText("Fahrenheit");
        Unit2.setText("Kelvin");
        Unit3.setText("");
    }

    public void ConvertCelsius(View view) {

        if (SpinnerSelection != 2){
            Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;

        try{
            String stringInput = user_input.getText().toString();
            inputValue = Double.parseDouble(stringInput);
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter a valid value", Toast.LENGTH_SHORT).show();
            return;
        }

        double out1 = (inputValue * (9/5) + 32);
        double out2 = (inputValue + 273.15);


        DecimalFormat df = new DecimalFormat("#.##");
        Output1.setText(df.format(out1));
        Output2.setText(df.format(out2));
        Output3.setText("");
    }

    public void SelectKilograms(AdapterView<?> parent) {

        SpinnerSelection = 3;
        user_input.setText("0");
        Output1.setText("");
        Output2.setText("");
        Output3.setText("");
        Unit1.setText("Gram");
        Unit2.setText("Ounce (Oz)");
        Unit3.setText("Pound(lb)");
    }


    public void ConvertKilograms(View view) {

        if (SpinnerSelection != 3){
            Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;

        try{
            String stringInput = user_input.getText().toString();
            inputValue = Double.parseDouble(stringInput);
        }
        catch(Exception e){
            Toast.makeText(this, "Enter a valid value", Toast.LENGTH_SHORT).show();
            return;
        }


        double out1 = (inputValue * 1000);
        double out2 = (inputValue * 35.274);
        double out3 = (inputValue * 2.20462);


        DecimalFormat df = new DecimalFormat("#.##");
        Output1.setText(df.format(out1));
        Output2.setText(df.format(out2));
        Output3.setText(df.format(out3));
    }
}