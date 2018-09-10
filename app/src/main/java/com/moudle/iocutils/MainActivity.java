package com.moudle.iocutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moudle.iocutils.annotation.ContentView;
import com.moudle.iocutils.annotation.OnClick;
import com.moudle.iocutils.annotation.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_main);
    }

    //动态代理的对象 ------------传递过来了。 参数个数，必须和代理对象参数相同,返回类型
    @OnClick({R.id.btn_one})
    public void setClick(View view){
        Toast.makeText(this,"i want to be surper man",Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn_two})
    public boolean setOnClick(View view){
        Toast.makeText(this,"i was the second menu",Toast.LENGTH_SHORT).show();
        return  true;
    }


}
