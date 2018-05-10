package org.cmdmac.aoptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.cmdmac.aspect.annotation.Async;
import org.cmdmac.aspect.annotation.Trace;
import org.cmdmac.aspect.utils.AOPConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AOPConfig.setASyncHandler(new AOPConfig.IASyncHandler() {
            @Override
            public void run(final ProceedingJoinPoint joinPoint) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

//    @Async(value="1111111111")
    @Trace
    public void onText(View view) {
        Toast.makeText(this, "onText", Toast.LENGTH_SHORT).show();
        cost();
    }

    @Async
    @Trace
    private void cost() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
