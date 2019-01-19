package bogdan.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*cream variabilele necesare*/
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    /*/end*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*asignam variabilele la id-urile elementelor*/
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login= (Button)findViewById(R.id.btnLogin);
        /*end*/
    }

    privade void validate(String userName, String userPassword){
        if((userName == "Admin") && (userPassword == "1234")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        }
    }

}
