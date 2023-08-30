package com.s4r1m.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solutionTv , resultTv;
    MaterialButton button_0;
    MaterialButton button_1;
    MaterialButton button_2;
    MaterialButton button_3;
    MaterialButton button_4;
    MaterialButton button_5;
    MaterialButton button_6;
    MaterialButton button_7;
    MaterialButton button_8;
    MaterialButton button_9;
    MaterialButton button_c;
    MaterialButton button_ac;
    MaterialButton button_add;
    MaterialButton button_multiply;
    MaterialButton button_divide;
    MaterialButton button_subtract;
    MaterialButton button_dot;
    MaterialButton button_equals;
    MaterialButton button_open_bracket;
    MaterialButton button_close_bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        button_0=findViewById(R.id.button_0);
        button_0.setOnClickListener(this);
        button_1=findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        button_2=findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        button_3=findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        button_4=findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        button_5=findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        button_6=findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        button_7=findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        button_8=findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        button_9=findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        button_dot=findViewById(R.id.button_dot);
        button_dot.setOnClickListener(this);
        button_add=findViewById(R.id.button_add);
        button_add.setOnClickListener(this);
        button_subtract=findViewById(R.id.button_subtract);
        button_subtract.setOnClickListener(this);
        button_divide=findViewById(R.id.button_divide);
        button_divide.setOnClickListener(this);
        button_multiply=findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(this);
        button_c=findViewById(R.id.button_c);
        button_c.setOnClickListener(this);
        button_ac=findViewById(R.id.button_ac);
        button_ac.setOnClickListener(this);
        button_open_bracket=findViewById(R.id.button_open_bracket);
        button_open_bracket.setOnClickListener(this);
        button_close_bracket=findViewById(R.id.button_close_bracket);
        button_close_bracket.setOnClickListener(this);
        button_equals=findViewById(R.id.button_equals);
        button_equals.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText= button.getText().toString();
        String data = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            resultTv.setText("0");
            solutionTv.setText("");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText().toString());
            return;
        }
        if(buttonText.equals("C")){
            if(data.length()<=1)
            {
                data=null;
            }
            else
            {
                data=data.substring(0,data.length()-1);
            }

        }
        else{
            data+=buttonText;
        }
    solutionTv.setText(data);
        String finalResult = getResult(data);
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    public String getResult(String data)
    {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e) {
            return "Err";
        }
    }
}