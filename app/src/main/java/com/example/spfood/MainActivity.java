package com.example.spfood;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //connecting php script
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.54/test/index.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        String fname = jsonObject.getString("food_name");
                        String fprice = jsonObject.getString("food_price");
                        String Sname = jsonObject.getString("store_name");

                        //log
                        Log.d("Item", "name: " + fname + ", price: " + fprice + ", Storename: " + Sname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        },
        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //handling errors
                Toast.makeText(getApplicationContext(),"failed to fetch data",Toast.LENGTH_SHORT).show();
                Log.e("Volley Error",error.toString());
            }
        });

        queue.add(jsonArrayRequest);

    }
}