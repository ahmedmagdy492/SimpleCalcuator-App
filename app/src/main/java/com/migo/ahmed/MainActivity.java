package com.migo.ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText txtOutput;
    private EditText txtOne;
    private OperationType operationType;
    private double firstNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOutput = findViewById(R.id.txt_output);
        txtOne = findViewById(R.id.txt_one);
        firstNumber = 0;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            newConfig.orientation = Configuration.ORIENTATION_PORTRAIT;
            /*Toast toast = Toast.makeText(this, "Orientation has changed", Toast.LENGTH_SHORT);
            toast.show();*/
        }
        super.onConfigurationChanged(newConfig);
    }

    @SuppressLint("SetTextI18n")
    public void onNumBtnClicked(View view)
    {
        String strTxt = txtOutput.getText().toString();
        Button clickedBtn = (Button)view;

        if(strTxt.equals("0"))
        {
            // remove the 0 and add the value of the clicked button
            txtOutput.setText(String.valueOf(clickedBtn.getText().toString()));
        }
        else
        {
            // concate the value of the button to the output text
            txtOutput.setText(txtOutput.getText().toString() + clickedBtn.getText().toString());
        }
    }

    public void onOperationClicked(View view)
    {
        try {
            Button clickedBtn = (Button)view;

            if(clickedBtn.getText().equals("+"))
                this.operationType = OperationType.SUM;
            else if(clickedBtn.getText().equals("-"))
                this.operationType = OperationType.SUBTRACT;
            else if(clickedBtn.getText().toString().toLowerCase().equals("x"))
                this.operationType = OperationType.MUL;
            else if(clickedBtn.getText().equals("/"))
                this.operationType = OperationType.DIV;

            firstNumber = Double.parseDouble(txtOutput.getText().toString());
            txtOutput.setText("0");
            txtOne.setText(String.valueOf(firstNumber));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void equalBtnClicked(View view)
    {
        if(!txtOutput.getText().toString().equals("") && !txtOne.getText().toString().equals(""))
        {
            try{
                double secondNumber = Double.parseDouble(txtOutput.getText().toString());
                double result = 0;
                MathOperation operation = new MathOperation();


                switch (this.operationType)
                {
                    case SUM: {
                        result = operation.sum(firstNumber, secondNumber);
                        break;
                    }
                    case SUBTRACT: {
                        result = operation.sub(firstNumber, secondNumber);
                        break;
                    }
                    case MUL: {
                        result = operation.mul(firstNumber, secondNumber);
                        break;
                    }
                    case DIV: {
                        result = operation.div(firstNumber, secondNumber);
                        break;
                    }
                }
                txtOutput.setText(String.valueOf(result));
                firstNumber = 0;
                operationType = null;
            }
            catch (ArithmeticException e)
            {
                Toast toast = Toast.makeText(this, R.string.divideByZero, Toast.LENGTH_SHORT);
                toast.show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onClearClicked(View view)
    {
        txtOutput.setText("0");
        txtOne.setText("0");
        firstNumber = 0;
        operationType = null;
    }

    public void clearOneChar(View view)
    {
        if(!txtOutput.getText().toString().equals("0"))
        {
            if(txtOutput.getText().toString().length() == 1)
            {
                txtOutput.setText("0");
            }
            else
            {
                String fullTxt = txtOutput.getText().toString();
                StringBuilder newStr = new StringBuilder();

                for(int i= 0; i < fullTxt.length()-1; i++)
                {
                    newStr.append(fullTxt.charAt(i));
                }
                txtOutput.setText(newStr.toString());
            }
        }
    }
}
