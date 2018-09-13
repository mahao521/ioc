package com.moudle.iocutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moudle.iocutils.annotation.ContentView;
import com.moudle.iocutils.annotation.OnClick;
import com.moudle.iocutils.annotation.OnLongClick;
import com.moudle.iocutils.annotation.ViewInject;
import com.moudle.iocutils.proxy.ProxyInstance;
import com.moudle.iocutils.proxy.UpdateInfoListener;

/**
 *   点击事件 ： 反射  + 动态代理；
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private UpdateInfoListener mSubject;
    @ViewInject(R.id.btn_three)
    Button  btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_main);
        new ProxyInstance().registerProxyClick(this,btn);
    }

    //动态代理的对象 ------------传递过来了。 参数个数，必须和代理对象参数相同,返回类型
    @OnClick({R.id.btn_one})
    public void setClick(View view){
        Toast.makeText(this,"i want to be surper man",Toast.LENGTH_SHORT).show();
        new ProxyInstance().getProxy();
    }

    @OnLongClick({R.id.btn_two})
    public boolean setOnClick(View view){
        Toast.makeText(this,"i was the second menu",Toast.LENGTH_SHORT).show();
        return  true;
    }

    /**
     *    代理onClick接口，，谁调用的不关心
     *    代理的方法必须和被代理的方法拥有相同的参数。返回值
     * @param view
     */
    public void realClick(View view){

        ((Button)view).setText("成功代理原来的Click方法");
    }

}
