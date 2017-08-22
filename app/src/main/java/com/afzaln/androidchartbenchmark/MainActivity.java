package com.afzaln.androidchartbenchmark;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afzaln.androidchartbenchmark.androidplot.ApRt3AxesFragment;
import com.afzaln.androidchartbenchmark.androidplot.ApRtFifo3AxesFragment;
import com.afzaln.androidchartbenchmark.androidplot.ApRtFifoFragment;
import com.afzaln.androidchartbenchmark.androidplot.ApRtFragment;
import com.afzaln.androidchartbenchmark.hellocharts.HcRt3AxesFragment;
import com.afzaln.androidchartbenchmark.hellocharts.HcRtFifo3AxesFragment;
import com.afzaln.androidchartbenchmark.hellocharts.HcRtFifoFragment;
import com.afzaln.androidchartbenchmark.hellocharts.HcRtFragment;
import com.afzaln.androidchartbenchmark.mpandroidchart.MpRt3AxesFragment;
import com.afzaln.androidchartbenchmark.mpandroidchart.MpRtFifo3AxesFragment;
import com.afzaln.androidchartbenchmark.mpandroidchart.MpRtFifoFragment;
import com.afzaln.androidchartbenchmark.mpandroidchart.MpRtFragment;
import com.afzaln.androidchartbenchmark.scichart.SciRt3AxesFragment;
import com.afzaln.androidchartbenchmark.scichart.SciRtFifo3AxesFragment;
import com.afzaln.androidchartbenchmark.scichart.SciRtFifoFragment;
import com.afzaln.androidchartbenchmark.scichart.SciRtFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> fragmentList = new ArrayList<>();
        fragmentList.add(ApRtFragment.class.getName());
        fragmentList.add(ApRt3AxesFragment.class.getName());
        fragmentList.add(ApRtFifoFragment.class.getName());
        fragmentList.add(ApRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Ap5GraphsFifoFragment.class.getName());

//        fragmentList.add(GvRtFragment.class.getName());
//        fragmentList.add(GvRt3AxesFragment.class.getName());
//        fragmentList.add(GvRtFifoFragment.class.getName());
//        fragmentList.add(GvRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Gv5GraphsFifoFragment.class.getName());

        fragmentList.add(HcRtFragment.class.getName());
        fragmentList.add(HcRt3AxesFragment.class.getName());
        fragmentList.add(HcRtFifoFragment.class.getName());
        fragmentList.add(HcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Hc5GraphsFifoFragment.class.getName());

        fragmentList.add(MpRtFragment.class.getName());
        fragmentList.add(MpRt3AxesFragment.class.getName());
        fragmentList.add(MpRtFifoFragment.class.getName());
        fragmentList.add(MpRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Mp5GraphsFifoFragment.class.getName());

        fragmentList.add(SciRtFragment.class.getName());
        fragmentList.add(SciRt3AxesFragment.class.getName());
        fragmentList.add(SciRtFifoFragment.class.getName());
        fragmentList.add(SciRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Sci5GraphsFifoFragment.class.getName());

//        fragmentList.add(TcRtFragment.class.getName());
//        fragmentList.add(TcRt3AxesFragment.class.getName());
//        fragmentList.add(TcRtFifoFragment.class.getName());
//        fragmentList.add(TcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Tc5GraphsFifoFragment.class.getName());

//        fragmentList.add(WcRtFragment.class.getName());
//        fragmentList.add(WcRt3AxesFragment.class.getName());
//        fragmentList.add(WcRtFifoFragment.class.getName());
//        fragmentList.add(WcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Wc5GraphsFifoFragment.class.getName());

        runFragmentList(fragmentList);
//        showFragment(fragmentList.get(0));
    }

    private void runFragmentList(final List<String> fragmentList) {
        final int iSize = fragmentList.size();
        for (int i = 0; i <= iSize; i++) {
            final int finalI = i;
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalI == iSize) {
                        ChartBenchmarkApp.get(MainActivity.this).finishStats();
                        hideFragment();
                        return;
                    }
                    String fragmentName = fragmentList.get(finalI);
                    showFragment(fragmentName);
                    ChartBenchmarkApp.get(MainActivity.this).addStatHolder(fragmentName);
                }
            }, 10000 * (i));
        }
    }

    private void hideFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new Fragment())
                .commit();
    }

    Fragment lastFragment;

    private void showFragment(String fragmentName) {
        Fragment fragment = Fragment.instantiate(this, fragmentName);
        FragmentManager fragmentManager = getFragmentManager();
        if (lastFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .remove(lastFragment)
                    .commit();
        }
        lastFragment = null;
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        lastFragment = fragment;
    }
}
