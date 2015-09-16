package com.example.ossamaAhmed.mobilevectorcalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MyActivity extends ActionBarActivity {
    //initializing variables
    private EditText Argument1_1,Argument1_2,Argument2_1,Argument2_2,Argument3_1,Argument3_2;
    private Button Calculate;
    private TextView Error;
    private TextView Result;
    private RadioButton Cartesian;
    private RadioButton Polar;
    private RadioButton Addition;
    private RadioButton ProductScalar;
    private RadioButton Product;
    private Button Graph;
    private double tempResult[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my);
        //Set up the needed TextViews, Buttons, and EditTexts
        Argument1_1 = (EditText) findViewById(R.id.Argument1_1);
        Argument1_2 = (EditText) findViewById(R.id.Argument1_2);
        Argument2_1 = (EditText) findViewById(R.id.Argument2_1);
        Argument2_2 = (EditText) findViewById(R.id.Argument2_2);
        Argument3_1 = (EditText) findViewById(R.id.Argument3_1);
        Argument3_2 = (EditText) findViewById(R.id.Argument3_2);
        Calculate = (Button) findViewById(R.id.calculate);
        Error= (TextView) findViewById(R.id.error);
        Result=(TextView) findViewById(R.id.result);
        Cartesian=(RadioButton) findViewById(R.id.cartesian);
        Polar=(RadioButton) findViewById(R.id.polar);
        Addition=(RadioButton) findViewById(R.id.addition);
        ProductScalar=(RadioButton) findViewById(R.id.scalarproduct);
        Product=(RadioButton) findViewById(R.id.product);
        Graph = (Button) findViewById(R.id.Graphing);

        //Listening to button event
        Calculate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //clear viewtextfields
                Error.setText("");
                Result.setText("");

                //test that there is a mode chosen
                if(!Cartesian.isChecked() && !Polar.isChecked()) {
                    Log.e("No mode was selected",".");
                    Error.setText("Error: Please choose either cartesian or polar");
                }
                else if( !(Addition.isChecked() || ProductScalar.isChecked() ||Product.isChecked())){
                    Log.e("No operation was selected",".");
                    Error.setText("Error: Please choose any operation");

                }
                else if(TextUtils.isEmpty(Argument1_1.getText().toString().trim()) || TextUtils.isEmpty(Argument1_2.getText().toString().trim()))
                {
                    Log.e("No Arguments for the first vector were entered",".");
                    Error.setText("Error: Please enter a value for the first vector");

                }
                else if(TextUtils.isEmpty(Argument2_1.getText().toString().trim()) || TextUtils.isEmpty(Argument2_2.getText().toString().trim()))
                {
                    Log.e("No Arguments for the second vector were entered",".");
                    Error.setText("Error: Please enter a value for the second vector");
                }
                else if(!(TextUtils.isEmpty(Argument3_1.getText().toString().trim())
                        && TextUtils.isEmpty(Argument3_2.getText().toString().trim()))
                        && (ProductScalar.isChecked()||Product.isChecked())){

                    Log.e("Cannot do that operation on three vectors",".");
                    Error.setText("Error: This operation is not supported for three vectors");
                }
                else if((TextUtils.isEmpty(Argument3_1.getText().toString().trim())
                        ^ TextUtils.isEmpty(Argument3_2.getText().toString().trim())
                        && Addition.isChecked())){

                    Log.e("Addition chosen and one argument of the 3 arguments is not chosen",".");
                    Error.setText("Error: Please enter the two argumnets of the third vector");
                }
                else if(!checkNumericType(Argument1_1.getText().toString().trim())
                        || !checkNumericType(Argument1_2.getText().toString().trim())
                        ||!checkNumericType(Argument2_1.getText().toString().trim())
                        ||!checkNumericType(Argument2_2.getText().toString().trim())
                        ||!checkNumericType(Argument3_1.getText().toString().trim())
                        ||!checkNumericType(Argument3_2.getText().toString().trim()))
                {
                    Log.e("InValid Type entered",".");
                    Error.setText("Error: Invalid Type");
                }
                else
                {
                    //preform the desired calculation
                    if((TextUtils.isEmpty(Argument3_1.getText().toString().trim())
                            && TextUtils.isEmpty(Argument3_2.getText().toString().trim()))&& Addition.isChecked()
                            && Cartesian.isChecked())
                    {
                                tempResult=addVectorCartesianTwo(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));

                        Result.setText("("+tempResult[0]+","+tempResult[1]+")");
                    }
                    else if((TextUtils.isEmpty(Argument3_1.getText().toString().trim())
                            && TextUtils.isEmpty(Argument3_2.getText().toString().trim()))&& Addition.isChecked()
                            && Polar.isChecked())
                    {
                                tempResult=addVectorPolarTwo(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));

                        Result.setText("("+tempResult[0]+","+tempResult[1]+")");
                    }
                    else if( Addition.isChecked()&& Cartesian.isChecked())
                    {
                                tempResult=addVectorCartesianThree(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()),
                                Double.parseDouble(Argument3_1.getText().toString().trim()),
                                Double.parseDouble(Argument3_2.getText().toString().trim()));

                        Result.setText("("+tempResult[0]+","+tempResult[1]+")");
                    }
                    else if( Addition.isChecked()&& Polar.isChecked())
                    {
                                tempResult=addVectorPolarThree(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()),
                                Double.parseDouble(Argument3_1.getText().toString().trim()),
                                Double.parseDouble(Argument3_2.getText().toString().trim()));
                                DecimalFormat df = new DecimalFormat("#.00");
                        Result.setText("("+df.format(tempResult[0])+","+df.format(tempResult[1])+")");
                    }
                    else if( Product.isChecked()&& Polar.isChecked())
                    {
                               tempResult=productPolar(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));
                        DecimalFormat df = new DecimalFormat("#.00");
                        Result.setText("("+df.format(tempResult[0])+","+df.format(tempResult[1])+")");
                    }
                    else if( Product.isChecked()&& Cartesian.isChecked())
                    {
                                tempResult=productCartesian(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));
                        DecimalFormat df = new DecimalFormat("#.00");
                        Result.setText("("+df.format(tempResult[0])+","+df.format(tempResult[1])+")");
                    }
                    else if( ProductScalar.isChecked()&& Cartesian.isChecked())
                    {
                                tempResult=scalarProductCartesian(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));
                        DecimalFormat df = new DecimalFormat("#.00");
                        Result.setText("("+df.format(tempResult[0])+","+df.format(tempResult[1])+")");
                    }
                    else if( ProductScalar.isChecked()&& Polar.isChecked())
                    {
                                tempResult=scalarProductPolar(Double.parseDouble(Argument1_1.getText().toString().trim()),
                                Double.parseDouble(Argument1_2.getText().toString().trim()),
                                Double.parseDouble(Argument2_1.getText().toString().trim()),
                                Double.parseDouble(Argument2_2.getText().toString().trim()));
                        DecimalFormat df = new DecimalFormat("#.00");
                        Result.setText("("+df.format(tempResult[0])+","+df.format(tempResult[1])+")");
                    }
                }


            }
        });
        //Listening to button event
        Graph.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Graph.class);
                //Sending data to another Activity
                if(tempResult==null)
                {
                    tempResult=new double[2];
                    tempResult[0]=0;
                    tempResult[1]=0;
                }
                nextScreen.putExtra("Result_Horizontal",""+tempResult[0]);
                nextScreen.putExtra("Result_Vertical",""+tempResult[1]);
                startActivity(nextScreen);

            }
        });



    }

    public boolean checkNumericType(String input)
    {
        if(input.length()>0) {
            int start;
            if (input.charAt(0) == '-')
                start = 1;
            else
                start = 0;
            for (int i = start; i < input.length(); i++) {
                if (!(input.charAt(i) >= '0' && input.charAt(i) <= '9'))
                    return false;
            }
        }
        return true;
    }
    public double[] addVectorCartesianTwo(double x1,double y1,double x2,double y2)
    {
        double result []=new double[2];
        result[0]=x1+x2;
        result[1]=y1+y2;
        return result;
    }
    public double[] addVectorPolarTwo(double r1,double a1,double r2,double a2)
    {
        double result []=new double[2];
        double x1=r1*Math.cos(a1);
        double y1=r1*Math.sin(a1);
        double x2=r2*Math.cos(a2);
        double y2=r2*Math.sin(a2);
        result[0]=Math.sqrt(Math.pow(x1+x2,2)+Math.pow(y1+y2,2));
        result[1]=Math.atan((y1+y2)/(x1+x2));
        return result;
    }

    public double[] addVectorCartesianThree(double x1,double y1,double x2,double y2,double x3,double y3)
    {
        double result []=new double[2];
        result[0]=x1+x2+x3;
        result[1]=y1+y2+y3;
        return result;
    }
    public double[] addVectorPolarThree(double r1,double a1,double r2,double a2,double r3,double a3)
    {
        double result []=new double[2];
        double x1=r1*Math.cos(a1);
        double y1=r1*Math.sin(a1);
        double x2=r2*Math.cos(a2);
        double y2=r2*Math.sin(a2);
        double x3=r3*Math.cos(a3);
        double y3=r3*Math.sin(a3);
        result[0]=Math.sqrt(Math.pow(x1+x2+x3,2)+Math.pow(y1+y2+y3,2));
        result[1]=Math.atan((y1+y2+y3)/(x1+x2+x3));
        return result;
    }
    public double[] productCartesian(double x1,double y1,double x2,double y2)
    {
        double result []=new double[2];
        double r1=Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2));
        double r2=Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2));
        double a1=Math.atan(y1/x1);
        double a2=Math.atan(y2/x2);
        result[0]=(r1*r2)*Math.cos(a1+a2);
        result[1]=(r1*r2)*Math.sin(a1+a2);
        return result;
    }
    public double[] productPolar(double r1,double a1,double r2,double a2)
    {
        double result []=new double[2];
        result[0]=r1*r2;
        result[1]=a1+a2;
        if(result[0]==0)
            result[1]=0;
        return result;
    }
    public double[] scalarProductCartesian(double x1,double y1,double x2,double y2)
    {
        double result []=new double[2];
        result[0]=x1*x2;
        result[1]=y1*y2;
        return result;
    }
    public double[] scalarProductPolar(double r1,double a1,double r2,double a2)
    {
        double result []=new double[2];
        double x1=r1*Math.cos(a1);
        double y1=r1*Math.sin(a1);
        double x2=r2*Math.cos(a2);
        double y2=r2*Math.sin(a2);
        result[0]=Math.sqrt(Math.pow(x1*x2,2)+Math.pow(y1*y2,2));
        result[1]=Math.atan((y1*y2)/(x1*x2));
        if(result[0]==0)
            result[1]=0;
        return result;
    }

}
