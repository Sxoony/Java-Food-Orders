package com.example.foodorder;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radMeal;
    private CheckBox chkCheese, chkSauce, chkFries;
    private Spinner spnDrinks;
    private Switch swtDelivery;
    private RatingBar rateApp;
    private Button btnPlace;
    private TextView txtSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radMeal=findViewById(R.id.rdgMeal);
        chkCheese=findViewById(R.id.chkCheese);
        chkSauce=findViewById(R.id.chkSauce);
        chkFries=findViewById(R.id.chkFries);
        spnDrinks=findViewById(R.id.spnDrinks);
        swtDelivery=findViewById(R.id.swtDelivery);
        rateApp=findViewById(R.id.rateApp);
        btnPlace=findViewById(R.id.btnOrder);
        txtSummary=findViewById(R.id.txtSummary);

        String[] drinks ={"Coke","Pepsi","Fanta"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,drinks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDrinks.setAdapter(adapter);
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                int selectedID=radMeal.getCheckedRadioButtonId();
                //Get Meal
                if (selectedID!=-1){
                    RadioButton selectedMeal = findViewById(selectedID);
                    result.append("Meal: ").append(selectedMeal.getText()).append("\n");
                }
                //Get Extras
                result.append("Extras: ");
                if (chkCheese.isChecked())result.append("Cheese ");
                if (chkSauce.isChecked())result.append("Sauce ");
                if (chkFries.isChecked())result.append("Fries ");
                result.append("\n");
                //Drinks
                result.append("Drink: ").append(spnDrinks.getSelectedItem().toString()).append("\n");

                //Delivery
                if (swtDelivery.isChecked()){
                    result.append("Delivery: Yes\n");
                }else{
                    result.append("Delivery: No\n");
                }

                //Rating
                result.append("Rating: ").append(rateApp.getRating()).append(" stars\n");
                txtSummary.setText(result.toString());
            }
        });
    }
}