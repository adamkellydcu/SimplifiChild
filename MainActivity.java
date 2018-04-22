package dcu.simplifichild;

/**
 * Created by adamkelly on 08/04/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ButtonRegister=(Button) findViewById(R.id.Button_Register);
        Button testbutton = (Button)findViewById(R.id.bt_test);

//test new dfghjk




        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToWifiControl = new Intent(MainActivity.this, PostActivity.class);
                startActivity(GoToWifiControl);
            }
        });





        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoWiFISwitch = new Intent(MainActivity.this, TestActivity.class);
                startActivity(gotoWiFISwitch);
            }
        });
/**
 loginOK.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Intent loginOK = new Intent(MainActivity2.this, Simplifi_Main_Page.class);
startActivity(loginOK);
}
}); **/



    }








/*public void getSimplificontgent(){
    URL url = new URL("http://www.android.com/");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    try {
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        readStream(in);
    } finally {
        urlConnection.disconnect();
    }
}

    private void readStream(InputStream in) {
    }*/


}
