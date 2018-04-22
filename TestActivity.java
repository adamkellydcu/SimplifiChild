package dcu.simplifichild;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;
import java.util.Collections;


import android.content.Context;
import android.location.Location;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;






public class TestActivity extends AppCompatActivity implements OnClickListener{

    static Handler mHandler;
    static String mACADDRESS="";
    String serverCommand = "ON";
    Button TurnWifion, TurnWifioff, getTheURL;
    TextView wifistatus, URL_textview,macAddress;
    WifiManager wifi;
    //String urlAddress = "http://www.dialfx.com/simplifi/simplifi.php";
    //String urlAddress = "http://student.computing.dcu.ie/~kella256/adamphp.php";
    //String urlAddress = "http://www.dryerjet.com/scripts/simplifi.php?mac="+mACADDRESS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wi_fi_switch_layout);
        String macAddressReturned=getMacAddr();
        switchOnPolling();  //## SWITCH ON POLLINg
        TurnWifion = (Button) findViewById(R.id.WiFi_ON_Buttton);
        TurnWifioff = (Button) findViewById(R.id.WiFi_OFF_Button);
        getTheURL = (Button) findViewById(R.id.gettheURL);
        wifistatus = (TextView) findViewById(R.id.wifistatus_textview);
        macAddress=(TextView)findViewById(R.id.macAddress);
        URL_textview = (TextView) findViewById(R.id.URL_textview);
        // Getting the WiFi Services
        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //Checking whether  Wifi is on or off
        macAddress.setText("Device MAC Address: "+macAddressReturned);
        if (wifi.isWifiEnabled()) {
            wifistatus.setText("Connected");//If WiFi is on, enable "Turn WiFi off" button.
            TurnWifioff.setEnabled(true);
            TurnWifion.setEnabled(false);

        } else {
            wifistatus.setText("Disconnected");//If WiFi is off, enable "Turn WiFi on" button.
            TurnWifion.setEnabled(true);
            TurnWifioff.setEnabled(false);
        }
        TurnWifion.setOnClickListener(this);
        TurnWifioff.setOnClickListener(this);
        //getTheURL.setOnClickListener(this);

        getTheURL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                sendTheUrl();
//                final RequestQueue requestQueue = Volley.newRequestQueue(WiFISwitch.this);
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAddress, new Response.Listener<String>() {
//                    /**
//                     * Called when a response is received.
//                     *
//                     * @param response
//                     */
//                    @Override
//                    public void onResponse(String response) {
//                        URL_textview.setText(response);
//
//                        requestQueue.stop();
//                        String setresponseOFF="OFF ";
//                        String setresponseON="ON ";
//
//                        if(response.equals(setresponseOFF)) {
//                            //wifi.setWifiEnabled(false); //Disabling WiFi
//                            URL_textview.setText("Switched OFF Via URL");
//                        } else{
//                            URL_textview.setText("NOT RESPONDING To URL");
//                        }
//
//                        if(response.equals(setresponseON)) {
//                           // wifi.setWifiEnabled(true); //Disabling WiFi
//                            URL_textview.setText("Switched ON Via URL");
//
//                        }
//                        serverCommand = response.toString();
//                        switchCommand(response);
//                        Log.i("ServerResponse ", "Response= " + response+ " Response.toString()= "+ response.toString());
//
//
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        URL_textview.setText(error.toString());
//                        requestQueue.stop();
//                        serverCommand = "ERROR";
//                        switchCommand("ERROR"); }
//
//
//                });
//                requestQueue.add(stringRequest);
            }
        });
    }

    public void switchCommand(String response) {
        Log.i("switchCommand ", response);
        String setresponseOFF="OFF ";
        String setresponseON="ON ";
        if (response.equals(setresponseON)) {

            //Log.i("switchCommand ", "servercommand= " + serverCommand);
            wifi.setWifiEnabled(true);// Enabling WiFi
            TurnWifioff.setEnabled(true);
            TurnWifion.setEnabled(false);
            wifistatus.setText("Connected");
            URL_textview.setText("Switched " +response+ " remotely");


        }
        //if (response.equals(serverCommand))  {
        if (response.equals(setresponseOFF))  {
            //Log.i("switchCommand ", response);
            //Log.i("switchCommand ", "servercommand= " + serverCommand);
            wifi.setWifiEnabled(false); //Disabling WiFi
            TurnWifion.setEnabled(true);
            TurnWifioff.setEnabled(false);
            wifistatus.setText("Disconnected");
            URL_textview.setText("Switched " +response+ "remotely");

        }
        if (response.equals("ERROR")) {
            Log.i("switchCommand ", "servercommand= " + serverCommand);
            wifi.setWifiEnabled(true);// Enabling WiFi
            TurnWifioff.setEnabled(true);
            TurnWifion.setEnabled(false);

            wifistatus.setText("ERROR Connect Now");

        }
    }


    @Override
    public void onClick(View view) {

        if (TurnWifion.isPressed())    //If "Turn WiFi on" button is Pressed
        {
            wifi.setWifiEnabled(true);// Enabling WiFi
            TurnWifioff.setEnabled(true);
            TurnWifion.setEnabled(false);
            wifistatus.setText("Connected");
        }
        if (TurnWifioff.isPressed())   //If "Turn WiFi off" button is Pressed
        {
            wifi.setWifiEnabled(false); //Disabling WiFi
            TurnWifion.setEnabled(true);
            TurnWifioff.setEnabled(false);
            wifistatus.setText("Disconnected");
        }


    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    // res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                //String res2=res1.toString();
                //Log.i("macaddress",res2);
                mACADDRESS=res1.toString();
                Log.i("MacAddress",mACADDRESS);

                return res1.toString();



            }
        } catch (Exception ex) {
            //handle exception
        }
        return "MAC Address Not Found";
    }

    protected void switchOnPolling() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //Location l = (Location) msg.obj;
//                Toast.makeText(getApplicationContext(), "Message: " + String.valueOf(msg) + " Longitude: " + String.valueOf(l.getLongitude()),
//                        Toast.LENGTH_LONG).show();

                //Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                //Location l = BasicReceiver.fieldLocation;
                                String test="POLLING SERVER";
                                Message msg = new Message();


                                sendTheUrl();

                                msg.obj = test;


                                mHandler.sendMessage(msg);
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    private void sendTheUrl() {



       // mACADDRESS="12:19:!9:";// REMOVE  this line when live

        String urlAddress = "http://student.computing.dcu.ie/~kella256/child_instruction.php?mac="+mACADDRESS;
        final RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAddress, new Response.Listener<String>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(String response) {
                URL_textview.setText(response);

                requestQueue.stop();
                response=response.toString().trim();
                String setresponseOFF="0";
                String setresponseON="1";

                if(response.equals(setresponseOFF)) {
                    wifi.setWifiEnabled(false); //Disabling WiFi
                    URL_textview.setText("Switched OFF Via URL");
                } else{
                    URL_textview.setText(response);
                }

                if(response.equals(setresponseON)) {
                    wifi.setWifiEnabled(true); //Enabling WiFi
                    URL_textview.setText("Switched ON Via URL");

                }
                serverCommand = response.toString();
                switchCommand(response);
                Log.i("ServerResponse ", "Response= " + response+ " Response.toString()= "+ response.toString());

                //test2
                //test 3
                //test 4
                //test 5

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                URL_textview.setText(error.toString());
                requestQueue.stop();
                serverCommand = "ERROR";
                switchCommand("ERROR"); }


        });
        requestQueue.add(stringRequest);
    }

}

