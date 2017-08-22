package com.afzaln.androidchartbenchmark;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afzaln.androidchartbenchmark.mpandroidchart.MpRtFifoFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new ApRtFragment());
//        fragmentList.add(new ApRt3AxesFragment());
//        fragmentList.add(new ApRtFifoFragment());
//        fragmentList.add(new ApRtFifo3AxesFragment());
//        fragmentList.add(new Ap5GraphsFifoFragment());

//        fragmentList.add(new GvRtFragment());
//        fragmentList.add(new GvRt3AxesFragment());
//        fragmentList.add(new GvRtFifoFragment());
//        fragmentList.add(new GvRtFifo3AxesFragment());
//        fragmentList.add(new Gv5GraphsFifoFragment());

//        fragmentList.add(new HcRtFragment());
//        fragmentList.add(new HcRt3AxesFragment());
//        fragmentList.add(new HcRtFifoFragment());
//        fragmentList.add(new HcRtFifo3AxesFragment());
//        fragmentList.add(new Hc5GraphsFifoFragment());

//        fragmentList.add(new MpRtFragment());
//        fragmentList.add(new MpRt3AxesFragment());
        fragmentList.add(new MpRtFifoFragment());
//        fragmentList.add(new MpRtFifo3AxesFragment());
//        fragmentList.add(new Mp5GraphsFifoFragment());

//        fragmentList.add(new SciRtFragment());
//        fragmentList.add(new SciRt3AxesFragment());
//        fragmentList.add(new SciRtFifoFragment());
//        fragmentList.add(new SciRtFifo3AxesFragment());
//        fragmentList.add(new Sci5GraphsFifoFragment());

//        fragmentList.add(new TcRtFragment());
//        fragmentList.add(new TcRt3AxesFragment());
//        fragmentList.add(new TcRtFifoFragment());
//        fragmentList.add(new TcRtFifo3AxesFragment());
//        fragmentList.add(new Tc5GraphsFifoFragment());

//        fragmentList.add(new WcRtFragment());
//        fragmentList.add(new WcRt3AxesFragment());
//        fragmentList.add(new WcRtFifoFragment());
//        fragmentList.add(new WcRtFifo3AxesFragment());
//        fragmentList.add(new Wc5GraphsFifoFragment());

        runFragmentList(fragmentList);
    }

    private void runFragmentList(final List<Fragment> fragmentList) {
        final int iSize = fragmentList.size();
        for (int i = 0; i <= iSize; i++) {
            final int finalI = i;
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalI == iSize) {
                        ChartBenchmarkApp.get(MainActivity.this).finishStats();
                        return;
                    }
                    Fragment fragment = fragmentList.get(finalI);
                    showFragment(fragment);
                    ChartBenchmarkApp.get(MainActivity.this).addStatHolder(fragment.getClass().getSimpleName());
                }
            }, 10000 * (i));
        }
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
