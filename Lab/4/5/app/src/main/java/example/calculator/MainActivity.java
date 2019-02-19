package example.calculator;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.lang.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        EditText t1;
        EditText t2;
        MainActivity activity;
        ImageView plus;
        ImageView minus;
        ImageView multiply;
        ImageView divide;

        TextView displayResult;

        String oper = "";
        Socket socket;
        String response = "";
        //Called when the activity is created

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //Finding the EditText Element (this is found in res/layout/activity_main.xml
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);

        plus = (ImageView) findViewById(R.id.plus);
        minus = (ImageView) findViewById(R.id.minus);
        multiply = (ImageView) findViewById(R.id.multiply);
        divide = (ImageView) findViewById(R.id.divide);

        displayResult = (TextView) findViewById(R.id.displayResult);

        //Setting Listeners
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
            multiply.setOnClickListener(this);
            divide.setOnClickListener(this);
        }
        public void onClick(View view){
            //check to see if the fields are empty
            if(TextUtils.isEmpty(t1.getText().toString())) {
                return;
            }
            //Perform the operation
            //Save the operator in oper for use later on
            switch (view.getId() ) {
                case R.id.plus:
                    oper = "+";
                    break;
                case R.id.minus:
                    oper = "-";
                    break;
                case R.id.multiply:
                    oper = "*";
                    break;
                case R.id.divide:
                    oper = "/";
                    break;
                default:
                    break;
            }

            }
        }
}

