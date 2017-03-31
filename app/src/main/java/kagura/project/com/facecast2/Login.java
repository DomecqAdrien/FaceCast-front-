package kagura.project.com.facecast2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText login;
    EditText password;
    String NEW_SERVER_URL;
    Intent intentMain;
    JSONObject jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = (EditText) findViewById(R.id.password);
        login = (EditText) findViewById(R.id.login);
    }

    public static final  String SERVER_URL = "http://192.168.43.32:3000/android/login";

    private void sendRequest() {
        StringRequest stringRequest = new StringRequest(NEW_SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonResponse = new JSONObject(response);
                            Log.i("Json",jsonResponse.toString());
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("id", jsonResponse.getString("_id"));
                            editor.putString("nom", jsonResponse.getString("nom"));
                            editor.putString("prenom", jsonResponse.getString("prenom"));
                            editor.putString("email", jsonResponse.getString("email"));
                            editor.putString("password", jsonResponse.getString("password"));
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        newIntent();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,"Mot de passe incorrect", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

   public void changeURL(View view){

        NEW_SERVER_URL = SERVER_URL + "/" + login.getText().toString().trim().toLowerCase() + "/" + password.getText().toString().trim();
        Log.i("URL", NEW_SERVER_URL);
        sendRequest();
    }
    private void newIntent() {
        intentMain = new Intent(this, MainActivity.class);
        this.startActivityForResult(intentMain, 0);
    }

}

