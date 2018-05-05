package projectoop.jhotel_android_rizkyramadianwijaya;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();


    ExpandableListView expListView;
    MenuListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        refreshList();
    }

    public void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i = 0;i<jsonResponse.length();i++){
                        JSONObject e = jsonResponse.getJSONObject(i).getJSONObject("hotel");
                        JSONObject lokasi = e.getJSONObject("lokasi");
                        JSONObject jsonRoom = jsonResponse.getJSONObject(i);
                        Hotel h = new Hotel(e.getInt("id"),
                                e.getString("nama"),
                                new Lokasi(
                                        lokasi.getDouble("x"),
                                        lokasi.getDouble("y"),
                                        lokasi.getString("deskripsi")),
                                e.getInt("bintang"));
                        listHotel.add(h);
                        Room room = new Room(
                                jsonRoom.getString("nomorKamar"),
                                jsonRoom.getString("statusKamar"),
                                jsonRoom.getDouble("dailyTariff"),
                                jsonRoom.getString("tipeKamar")
                        );
                        listRoom.add(room);
                    }
                    childMapping.put(listHotel.get(0),listRoom);
                    listAdapter = new MenuListAdapter(MainActivity.this,listHotel,childMapping);
                    expListView.setAdapter(listAdapter);
                } catch(JSONException ex){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("JSON PARSING Failed")
                            .create()
                            .show();
                }

            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}
