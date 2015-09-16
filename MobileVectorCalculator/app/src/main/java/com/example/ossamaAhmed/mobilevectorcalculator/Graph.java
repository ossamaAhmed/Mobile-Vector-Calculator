package com.example.ossamaAhmed.mobilevectorcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by ossama_mady on 2/22/15.
 */
public class Graph extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_my);
        Button btnClose = (Button) findViewById(R.id.btnClose);


        Intent i = getIntent();
        // Receiving the Data
        String argument1 = i.getStringExtra("Result_Horizontal");
        String argument2 = i.getStringExtra("Result_Vertical");
        // Displaying Received data
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 0),
                new DataPoint(Double.parseDouble(argument1), Double.parseDouble(argument2))
        });
        graph.addSeries(series);
        // Binding Click event to Button
        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });

    }
}
