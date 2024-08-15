package com.example.mit033calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_TV, solution_TV;
   MaterialButton buttonC,buttonBrackOpean,buttonBrackClose;
   MaterialButton buttonDevided,buttonMul,buttonPlus,buttonMinus,buttonEquals;

    MaterialButton button0,button1,button2,button3,button4,button5;
    MaterialButton button6,button7,button8,button9;


    MaterialButton buttonAC,buttondot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        result_TV=findViewById(R.id.result_tv);
        solution_TV=findViewById(R.id.solution_tv);

 /*  Assigning ID s  */
        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpean,R.id.button_openbracket);
        assignId(buttonBrackClose,R.id.button_closebracket);
        assignId(buttonDevided,R.id.button_devide);
        assignId(buttonMul,R.id.button_mul);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_sub);
        assignId(buttonEquals,R.id.button_equal);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(buttonAC,R.id.button_allclear);
        assignId(buttondot,R.id.button_dot);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
    /* Assign IDs for buttons */
    void assignId(MaterialButton btn,int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }


    /* On click of any button */
@Override
    public void onClick(View view){
    MaterialButton button=(MaterialButton) view;
    String buttonText=button.getText().toString();
    String dataToCalculate=solution_TV.getText().toString();




    /* Check Button is   C , AC , =  */

    if(buttonText.equals("AC")){
        solution_TV.setText("");
        result_TV.setText("0");
        return;
    }
    if(buttonText.equals("=")){
        solution_TV.setText(result_TV.getText());
        return;
    }
    if(buttonText.equals("C")){
        dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);

    }else {
        dataToCalculate = dataToCalculate + buttonText;

    }
    solution_TV.setText(dataToCalculate);
String finalResult=getResult(dataToCalculate);
if(!finalResult.equals("error"))
{
    result_TV.setText(finalResult);
}



}

String getResult(String data){
    try {
        Context context=Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable=context.initStandardObjects();
        String finalResult=context.evaluateString(scriptable,data,"JavaScript", 1 ,null).toString();
/*to remove the decimal digit if the value is full number*/
        if(finalResult.endsWith(".0")){
          finalResult=finalResult.replace(".0","");
        }
        return finalResult;

    }catch (Exception e){
        return "error";
    }






}




}