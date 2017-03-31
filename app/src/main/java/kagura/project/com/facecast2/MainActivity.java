package kagura.project.com.facecast2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kagura.project.com.facecast2.adapters.FluxAdapter;
import kagura.project.com.facecast2.objects.Evenement;
import kagura.project.com.facecast2.objects.Flux;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    JSONObject jsonResponse;
    JSONArray jsonArray;
    ArrayList<Flux> items;
    ArrayList<Evenement> evenements;
    String id;
    String nom;
    String prenom;
    String email;
    String password;

    public String SERVER_URL = "http://192.168.43.32:3000/android/evenements";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id = preferences.getString("id", null);
        nom = preferences.getString("nom", null);
        prenom = preferences.getString("prenom", null);
        email = preferences.getString("email", null);
        password = preferences.getString("password", null);
        Toast.makeText(this, id + " " + nom + " "+  prenom + " "+ email + " "+  password, Toast.LENGTH_LONG).show();
    }

    public void onStart() {
        super.onStart();
        // Envoi d'une requete dans la file d'attente
        sendRequest();
    }

    private void sendRequest() {
        StringRequest stringRequest = new StringRequest(SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response", response);
                        Log.i("URL", SERVER_URL);
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void parseJSON(String response) {
        evenements = new ArrayList<>();

        try {
            jsonResponse = new JSONObject(response);
           JSONArray arr = jsonResponse.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++) {
                Evenement evenement = new Evenement();
                evenement.setNom(arr.getJSONObject(i).get("nom").toString());
                evenement.setId(arr.getJSONObject(i).get("_id").toString());
                evenement.setDate_debut(arr.getJSONObject(i).get("date_debut").toString());
                evenement.setDate_fin(arr.getJSONObject(i).get("date_fin").toString());
                evenement.setNb_jours(arr.getJSONObject(i).get("nb_jours").toString());
                evenement.setType(arr.getJSONObject(i).get("type").toString());
                evenements.add(evenement);
            }
            //jsonArray = jsonResponse.getJSONArray("evenements");
          /*  Flux currentFlux;

            // Pour chaque Ã©lÃ©ment du tableau
            for (int i = 0; i < jsonResponse.length(); i++) {
                currentFlux = new Flux(null, null);

                // CrÃ©ation d'un tableau Ã©lÃ©ment Ã Â  partir d'un JSONObject
                JSONObject jsonObj = jsonResponse.getJSONObject(i);

                // RÃ©cupÃ©ration Ã Â partir d'un JSONObject nommÃ©
                //JSONObject fields  = jsonObj.getJSONObject("fields");

                // RÃ©cupÃ©ration de l'item qui nous intÃ©resse
                String nom = jsonObj.getString("nom");
                String date_debut = jsonObj.getString("date_debut");
                Log.i("test", String.valueOf(jsonObj));

                currentFlux.setNom(nom);
                currentFlux.setAdresse(date_debut);

                // Ajout dans l'ArrayList
                items.add(currentFlux);
            }*/

            ArrayAdapter<Evenement> objAdapter = new FluxAdapter(this, R.layout.row, evenements);
            lv.setAdapter(objAdapter);
        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(this).setTitle("Postuler")
                .setMessage("Voulez-vous postuler pour cet évenement ?")
                .setIcon(android.R.drawable.ic_menu_help)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {




                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SERVER_URL = "http://192.168.43.32:3000/android/candidatures/" + evenements.get(position).getId() + "/" + MainActivity.this.id;
                        sendRequest();
                        Log.i("Test", SERVER_URL);
                    }


                })
                .setNegativeButton("Non", null).show();

    }


}
