package com.example.y.creditcardimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_enter_card_balace;
    EditText et_enter_yearly_interst_rate;
    EditText et_enter_minimum_payment;
    EditText et_final_card_balance;
    EditText et_months_remaining;
    EditText et_interest_paid_will_be;

    Button bt_compute;
    Button bt_clear;

    float principle, rate, min_payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_enter_card_balace = (EditText) findViewById(R.id.et_enter_card_balace);
        et_enter_yearly_interst_rate = (EditText) findViewById(R.id.et_enter_yearly_interst_rate);
        et_enter_minimum_payment = (EditText) findViewById(R.id.et_enter_minimum_payment);
       et_final_card_balance = (EditText) findViewById(R.id.et_final_card_balance);
        et_months_remaining = (EditText) findViewById(R.id.et_months_remaining);
        et_interest_paid_will_be = (EditText) findViewById(R.id.et_interest_paid_will_be);

        bt_compute = (Button) findViewById(R.id.bt_compute);
        bt_clear = (Button) findViewById(R.id.bt_clear);

        bt_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(et_enter_card_balace.getText().toString().length()==0) {
                   Toast.makeText(MainActivity.this, "enter card balance", Toast.LENGTH_SHORT).show();
                   return;
               }
                else if(et_enter_yearly_interst_rate.getText().toString().length()==0){
                   Toast.makeText(MainActivity.this, "enter yearly interst rate", Toast.LENGTH_SHORT).show();
                   return;
               }
                else if (et_enter_minimum_payment.getText().toString().length()==0){
                   Toast.makeText(MainActivity.this, "enter minimum payment", Toast.LENGTH_SHORT).show();
                   return;
               }
                else {

                   principle = Float.parseFloat(String.valueOf(et_enter_card_balace.getText()));
                   rate = Float.parseFloat((et_enter_yearly_interst_rate.getText()).toString());
                   min_payment = Float.parseFloat(String.valueOf(et_enter_minimum_payment.getText()));
                   float[] soln = total_floatinterest_payable(principle, rate, min_payment);
                   et_final_card_balance.setText(String.valueOf(soln[1]));
                   et_months_remaining.setText(String.valueOf(soln[2]));
                   et_interest_paid_will_be.setText(String.valueOf(soln[0]));
               }
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                et_interest_paid_will_be.setText(null);
                et_months_remaining.setText(null);
                et_final_card_balance.setText(null);
                et_enter_minimum_payment.setText(null);
                et_enter_yearly_interst_rate.setText(null);
                et_enter_card_balace.setText(null);


            }
        });
    }

    private float[] total_floatinterest_payable(float principle, float rate, float minimum_payment){
        float monthlyfloatInterestPaid, monthlyPrinciple, monthlyBalance;
        float data[] = new float[3];
        float count = 0;


        do {

            count++;
            monthlyfloatInterestPaid = Math.round((principle * (rate / (100 * 12))));// 1
            monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;// 2
            monthlyBalance = principle - monthlyPrinciple;// 3
            principle = monthlyBalance;// 4

            if (count == 1) { //for 1stt month

                data[0] = monthlyfloatInterestPaid;

                data[1] = monthlyBalance;

                Log.e("Monthly", "Interest and balance" + monthlyfloatInterestPaid +"," +monthlyBalance);


            }

        } while (monthlyBalance > 0);


        Log.e("Count", "is " + count);
        data[2] = count - 1; //months remaining

        return data;


    }
    }

