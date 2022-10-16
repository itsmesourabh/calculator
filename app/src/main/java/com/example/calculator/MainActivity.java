package com.example.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resulttv,solutiontv;
    MaterialButton button_c,button_openbraket,button_closebraket;
    MaterialButton button_divide,button_multiply,button_plus,button_minus,button_equal;
    MaterialButton button_9,button_8,button_7,button_6,button_5,button_4,button_3,button_2,button_1,button_0;
    MaterialButton button_ac,button_Dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv=findViewById(R.id.resulttv);
        solutiontv=findViewById(R.id.solutiontv);

        assignId(button_c,R.id.button_c);
        assignId(button_openbraket,R.id.button_openbraket);
        assignId(button_closebraket,R.id.button_closebraket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_plus,R.id.button_plus);
        assignId(button_minus,R.id.button_minus);
        assignId(button_equal,R.id.button_equal);
        assignId(button_9,R.id.button_9);
        assignId(button_8,R.id.button_8);
        assignId(button_7,R.id.button_7);
        assignId(button_6,R.id.button_6);
        assignId(button_5,R.id.button_5);
        assignId(button_4,R.id.button_4);
        assignId(button_3,R.id.button_3);
        assignId(button_2,R.id.button_2);
        assignId(button_1,R.id.button_1);
        assignId(button_0,R.id.button_0);
        assignId(button_ac,R.id.button_ac);
        assignId(button_Dot,R.id.button_Dot);


    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTocalculate = solutiontv.getText().toString();

        if(buttonText.equals("AC")){
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutiontv.setText(resulttv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocalculate=dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else{
            dataTocalculate = dataTocalculate+buttonText;
        }

        solutiontv.setText(dataTocalculate);

        String finalResult = getResult(dataTocalculate);

        if(!finalResult.equals("Error")){
            resulttv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter() ;
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult= finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "";
        }
    }
}