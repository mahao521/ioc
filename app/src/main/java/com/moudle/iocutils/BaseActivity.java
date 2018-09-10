package com.moudle.iocutils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moudle.iocutils.util.ViewInjetUtils;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjetUtils.inject(this);
    }
}
