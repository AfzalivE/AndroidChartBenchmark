package com.afzaln.androidchartbenchmark;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.afzaln.androidchartbenchmark.androidplot.ApRt3AxesFragment;
import com.afzaln.androidchartbenchmark.androidplot.ApRtFragment;
import com.afzaln.androidchartbenchmark.ui.BaseChartFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    public static final int RATE = 100; // Hz
    private Handler handler;
    private String EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(EXTERNAL_STORAGE_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{EXTERNAL_STORAGE_PERMISSION}, PERMISSION_REQUEST_CODE);
                return;
            }
        }

        initiateBenchmark(RATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE &&
                permissions[0].equals(EXTERNAL_STORAGE_PERMISSION)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initiateBenchmark(RATE);
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Need permission to store results", Snackbar.LENGTH_INDEFINITE).show();
            }
        }
    }

    private void initiateBenchmark(int rate) {
        handler = new Handler();

        List<String> fragmentList = new ArrayList<>();
        fragmentList.add(ApRtFragment.class.getName());
        fragmentList.add(ApRt3AxesFragment.class.getName());
//        fragmentList.add(ApRtFifoFragment.class.getName());
//        fragmentList.add(ApRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Ap5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(GvRtFragment.class.getName());
//        fragmentList.add(GvRt3AxesFragment.class.getName());
//        fragmentList.add(GvRtFifoFragment.class.getName());
//        fragmentList.add(GvRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Gv5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(HcRtFragment.class.getName());
//        fragmentList.add(HcRt3AxesFragment.class.getName());
//        fragmentList.add(HcRtFifoFragment.class.getName());
//        fragmentList.add(HcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Hc5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(MpRtFragment.class.getName());
//        fragmentList.add(MpRt3AxesFragment.class.getName());
//        fragmentList.add(MpRtFifoFragment.class.getName());
//        fragmentList.add(MpRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Mp5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(ScRtFragment.class.getName());
//        fragmentList.add(ScRt3AxesFragment.class.getName());
//        fragmentList.add(ScRtFifoFragment.class.getName());
//        fragmentList.add(ScRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Sc5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(TcRtFragment.class.getName());
//        fragmentList.add(TcRt3AxesFragment.class.getName());
//        fragmentList.add(TcRtFifoFragment.class.getName());
//        fragmentList.add(TcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Tc5GraphsFifoFragment.class.getName());
//
//        fragmentList.add(WcRtFragment.class.getName());
//        fragmentList.add(WcRt3AxesFragment.class.getName());
//        fragmentList.add(WcRtFifoFragment.class.getName());
//        fragmentList.add(WcRtFifo3AxesFragment.class.getName());
//        fragmentList.add(Wc5GraphsFifoFragment.class.getName());

//        showFragment(fragmentList.get(0));
        runFragmentList(fragmentList, rate);
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    private void runFragmentList(final List<String> fragmentList, final int rate) {
        final int iSize = fragmentList.size();
        for (int i = 0; i <= iSize; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalI == iSize) {
                        ChartBenchmarkApp.get(MainActivity.this).finishStats();
                        hideFragment();
                        return;
                    }
                    String fragmentName = fragmentList.get(finalI);
                    showFragment(fragmentName, rate);
                    String[] nameSplit = fragmentName.split("\\.");
                    ChartBenchmarkApp.get(MainActivity.this).addStatHolder(nameSplit[nameSplit.length - 1]);
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

    private void showFragment(String fragmentName, int rate) {
        BaseChartFragment fragment = (BaseChartFragment) Fragment.instantiate(this, fragmentName);
        fragment.setRate(rate);
        FragmentManager fragmentManager = getFragmentManager();
        // clean up the fragment
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
