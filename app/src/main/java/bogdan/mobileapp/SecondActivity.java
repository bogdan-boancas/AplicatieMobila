package bogdan.mobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    protected int splashTime = 500;
    TextView tv1;
    String[] name = {"Q","R","S","C", "A", "N", "N", "E", "R"};
    int timer=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
/*experiment thread delay*/
        tv1=(TextView)findViewById(R.id.textView1);
        Thread th= new Thread(){
            @Override
            public void run(){
              try{
                for (timer=0; timer<9; timer++){
                    int waited = 0;
                    while(waited < splashTime){
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    tv1.setText(name[timer]);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        waited += 100;
                    }
                }
              }  catch(InterruptedException e){
              }

            }
        };
        th.start();

    }
}
