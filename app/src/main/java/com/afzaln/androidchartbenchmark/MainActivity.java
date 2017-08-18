package com.afzaln.androidchartbenchmark;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afzaln.androidchartbenchmark.hellocharts.HcRtFifo3AxesFragment;
import com.afzaln.androidchartbenchmark.scichart.SciRtFifoFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SciRtFifoFragment fragment = (SciRtFifoFragment) getFragmentManager().findFragmentById(R.id.content_frame);
        showFragment(fragment);
    }

    private void showFragment(Fragment fragment) {
        if (fragment == null) {
            fragment = new HcRtFifo3AxesFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }
}
