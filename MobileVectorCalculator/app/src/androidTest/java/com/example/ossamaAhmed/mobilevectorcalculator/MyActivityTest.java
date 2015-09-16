package com.example.ossamaAhmed.mobilevectorcalculator;

import android.support.v7.app.ActionBarActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by ossama_mady on 2/22/15.
 */
public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {
    private MyActivity myActivity;
    private EditText tArgument1_1,tArgument1_2,tArgument2_1,tArgument2_2,tArgument3_1,tArgument3_2;
    private Button tCalculate;
    private TextView tError;
    private TextView tResult;
    private RadioButton tCartesian;
    private RadioButton tPolar;
    private RadioButton tAddition;
    private RadioButton tProductScalar;
    private RadioButton tProduct;

    public MyActivityTest() {
        super(MyActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myActivity = getActivity();

        //Set up the needed TextViews, Buttons, and EditTexts
        tArgument1_1 = (EditText) myActivity.findViewById(R.id.Argument1_1);
        tArgument1_2 = (EditText) myActivity.findViewById(R.id.Argument1_2);
        tArgument2_1 = (EditText) myActivity.findViewById(R.id.Argument2_1);
        tArgument2_2 = (EditText) myActivity.findViewById(R.id.Argument2_2);
        tArgument3_1 = (EditText) myActivity.findViewById(R.id.Argument3_1);
        tArgument3_2 = (EditText) myActivity.findViewById(R.id.Argument3_2);
        tCalculate = (Button) myActivity.findViewById(R.id.calculate);
        tError= (TextView) myActivity.findViewById(R.id.error);
        tResult=(TextView) myActivity.findViewById(R.id.result);
        tCartesian=(RadioButton) myActivity.findViewById(R.id.cartesian);
        tPolar=(RadioButton) myActivity.findViewById(R.id.polar);
        tAddition=(RadioButton) myActivity.findViewById(R.id.addition);
        tProductScalar=(RadioButton) myActivity.findViewById(R.id.scalarproduct);
        tProduct=(RadioButton) myActivity.findViewById(R.id.product);

    }

    /**
     * Test 1
     * Test to see the result output when no mode is chosen
     */
    @UiThreadTest
    public void testNoModeChosen(){

        //Click on the Calculate button
        tCalculate.performClick();

        assertEquals("Error: Please choose either cartesian or polar", tError.getText().toString());
    }
    /**
     * Test 2
     * Test to see the result output when no operation is chosen
     */
    @UiThreadTest
    public void testNoOperationChosen(){

        tCartesian.performClick();
        tCalculate.performClick();
        assertEquals("Error: Please choose any operation", tError.getText().toString());
        tCartesian.setChecked(false);
        tPolar.performClick();
        tCalculate.performClick();
        assertEquals("Error: Please choose any operation", tError.getText().toString());
    }
    /**
     * Test 3
     * Test to see the result output when no value is inputted for the first vector
     */
    @UiThreadTest
    public void testNoVector1Value(){

        tCartesian.performClick();
        tAddition.performClick();
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Please enter a value for the first vector", tError.getText().toString());
    }
    /**
     * Test 4
     * Test to see the result output when no value is inputted for the second vector
     */
    @UiThreadTest
    public void testNoVector2Value(){

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Please enter a value for the second vector", tError.getText().toString());
    }
    /**
     * Test 5
     * Test to see the result output when there is value is inputted for the third vector but product or scalar product
     * operation is chosen
     */
    @UiThreadTest
    public void testVector3ValueWithUnallowedOperation(){

        tCartesian.performClick();
        tProduct.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        tArgument3_1.setText("1");
        tArgument3_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: This operation is not supported for three vectors", tError.getText().toString());
    }
    /**
     * Test 6
     * Test to see the result output when there is value is inputted for one of the arguments of
     * the third vector only and addition operation is chosen
     */
    @UiThreadTest
    public void testVector3PartialValueWithAllowedOperation(){

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        tArgument3_1.setText("1");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Please enter the two argumnets of the third vector", tError.getText().toString());
    }
    /**
     * Test 7
     * Test to see the result output when there is an invalid type in any of the arguments
     */
    @UiThreadTest
    public void testInValidTypesInVectors(){

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("abc");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("abc");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText(",.a");
        tArgument2_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("nm");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("3");
        tArgument3_1.setText("m,./");
        tArgument3_2.setText("4");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("3");
        tArgument3_1.setText("5");
        tArgument3_2.setText("kdk");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("Error: Invalid Type", tError.getText().toString());
    }

    /**
     * Test 8
     * Test to see the result when addition is performed on 2 cartesian vectors
     */
    @UiThreadTest
    public void testAddVectorCartesianTwo(){

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(2.0,4.0)", tResult.getText().toString());

        //Negative Numbers
        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("-3");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(0.0,-1.0)", tResult.getText().toString());
    }
    /**
     * Test 9
     * Test to see the result when addition is performed on 2 polar vectors
     */
    @UiThreadTest
    public void testAddVectorPolarTwo(){


        tPolar.performClick();
        tAddition.performClick();
        tArgument1_1.setText("12");
        tArgument1_2.setText("40");
        tArgument2_1.setText("13");
        tArgument2_2.setText("80");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(10.242818204209929,0.39898829686862725)", tResult.getText().toString());

        //Negative Numbers
        tPolar.performClick();
        tAddition.performClick();
        tArgument1_1.setText("-12");
        tArgument1_2.setText("-35");
        tArgument2_1.setText("14");
        tArgument2_2.setText("-50");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(24.39785110866455,-0.06008011780347788)", tResult.getText().toString());
    }
    /**
     * Test 10
     * Test to see the result when addition is performed on 3 cartesian vectors
     */
    @UiThreadTest
    public void testAddVectorCartesianThree(){

        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("2");
        tArgument3_1.setText("4");
        tArgument3_2.setText("6");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(6.0,10.0)", tResult.getText().toString());

        //Negative Numbers
        tCartesian.performClick();
        tAddition.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("1");
        tArgument2_2.setText("-3");
        tArgument3_1.setText("-10");
        tArgument3_2.setText("-15");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(-10.0,-16.0)", tResult.getText().toString());
    }
    /**
     * Test 11
     * Test to see the result when addition is performed on 3 polar vectors
     */
    @UiThreadTest
    public void testAddVectorPolarThree(){


        tPolar.performClick();
        tAddition.performClick();
        tArgument1_1.setText("12");
        tArgument1_2.setText("40");
        tArgument2_1.setText("13");
        tArgument2_2.setText("80");
        tArgument3_1.setText("19");
        tArgument3_2.setText("35");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(29.24,.43)", tResult.getText().toString());

        //Negative Numbers
        tPolar.performClick();
        tAddition.performClick();
        tArgument1_1.setText("-12");
        tArgument1_2.setText("-35");
        tArgument2_1.setText("14");
        tArgument2_2.setText("-50");
        tArgument3_1.setText("19");
        tArgument3_2.setText("0");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(43.38,-.03)", tResult.getText().toString());
    }
    /**
     * Test 12
     * Test to see the result when product is performed on 2 polar vectors
     */
    @UiThreadTest
    public void testProductVectorPolar(){

        tPolar.performClick();
        tProduct.performClick();
        tArgument1_1.setText("5");
        tArgument1_2.setText("40");
        tArgument2_1.setText("3");
        tArgument2_2.setText("80");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(15.00,120.00)", tResult.getText().toString());

        //Negative Numbers
        tPolar.performClick();
        tProduct.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("2");
        tArgument2_1.setText("0");
        tArgument2_2.setText("-3");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(.00,.00)", tResult.getText().toString());
    }
    /**
     * Test 13
     * Test to see the result when product is performed on 2 polar vectors
     */
    @UiThreadTest
    public void testProductVectorCartesian(){

        tCartesian.performClick();
        tProduct.performClick();
        tArgument1_1.setText("5");
        tArgument1_2.setText("2");
        tArgument2_1.setText("3");
        tArgument2_2.setText("9");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(-3.00,51.00)", tResult.getText().toString());

        //Negative Numbers
        tCartesian.performClick();
        tProduct.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("9");
        tArgument2_1.setText("0");
        tArgument2_2.setText("4");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(36.00,4.00)", tResult.getText().toString());
    }
    /**
     * Test 14
     * Test to see the result when scalar product is performed on 2 cartesian vectors
     */
    @UiThreadTest
    public void testScalarProductVectorCartesian(){

        tCartesian.performClick();
        tProductScalar.performClick();
        tArgument1_1.setText("5");
        tArgument1_2.setText("2");
        tArgument2_1.setText("3");
        tArgument2_2.setText("9");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(15.00,18.00)", tResult.getText().toString());

        //Negative Numbers
        tCartesian.performClick();
        tProductScalar.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("9");
        tArgument2_1.setText("0");
        tArgument2_2.setText("4");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(.00,36.00)", tResult.getText().toString());
    }

    /**
     * Test 15
     * Test to see the result when scalar product is performed on 2 polar vectors
     */
    @UiThreadTest
    public void testScalarProductVectorPolar(){

        tPolar.performClick();
        tProductScalar.performClick();
        tArgument1_1.setText("5");
        tArgument1_2.setText("2");
        tArgument2_1.setText("3");
        tArgument2_2.setText("9");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(8.00,.78)", tResult.getText().toString());

        //Negative Numbers
        tPolar.performClick();
        tProductScalar.performClick();
        tArgument1_1.setText("-1");
        tArgument1_2.setText("9");
        tArgument2_1.setText("0");
        tArgument2_2.setText("4");
        //Click on the Calculate button
        tCalculate.performClick();
        assertEquals("(.00,.00)", tResult.getText().toString());
    }



}
