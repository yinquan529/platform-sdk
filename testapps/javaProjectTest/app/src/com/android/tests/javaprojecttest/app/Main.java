package com.android.tests.javaprojecttest.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.tests.basicjar.BasicJar;
import com.android.tests.basicjar2.BasicJar2;
import com.android.tests.basicjar3.BasicJar3;
import com.android.tests.javaprojecttest.javaproject.JavaProject;
import com.android.tests.javaprojecttest.javaproject2.JavaProject2;
import com.android.tests.javaprojecttest.javaproject3.JavaProject3;
import com.android.tests.javaprojecttest.lib1.Lib1;
import com.android.tests.javaprojecttest.lib2.Lib2;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView) findViewById(R.id.app);
        tv.setText("App: " + App.getContent());

        tv = (TextView) findViewById(R.id.lib1);
        tv.setText("Lib1: " + Lib1.getContent());

        tv = (TextView) findViewById(R.id.lib2);
        tv.setText("Lib2: " + Lib2.getContent());

        tv = (TextView) findViewById(R.id.javaProject1);
        tv.setText("JavaProject: " + JavaProject.getContent());

        tv = (TextView) findViewById(R.id.javaProject2);
        tv.setText("JavaProject2: " + JavaProject2.getContent());

        tv = (TextView) findViewById(R.id.javaProject3);
        tv.setText("JavaProject3: " + JavaProject3.getContent());

        tv = (TextView) findViewById(R.id.basicJar);
        tv.setText("BasicJar: " + BasicJar.getContent());

        tv = (TextView) findViewById(R.id.basicJar2);
        tv.setText("BasicJar2: " + BasicJar2.getContent());

        tv = (TextView) findViewById(R.id.basicJar3);
        tv.setText("BasicJar3: " + BasicJar3.getContent());

    }
}