package projectoop.jhotel_android_rizkyramadianwijaya;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
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

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();

    HashMap<String, Hotel> hotelHashMap = new HashMap<>();
    HashMap<String, ArrayList<Room>> roomsMap = new HashMap<>();

    ExpandableListView expListView;
    MenuListAdapter listAdapter;

    int currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        currentUserID = intent.getIntExtra("custID",0);
        final Button pesButton = (Button) findViewById(R.id.pesananButton);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        refreshList();
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Room selected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);
                Intent menuInt = new Intent(MenuActivity.this,BuatPesananActivity.class);
                menuInt.putExtra("custID",currentUserID);
                menuInt.putExtra("roomNum",selected.getRoomNumber());
                menuInt.putExtra("hotelID",listHotel.get(groupPosition).getId());
                MenuActivity.this.startActivity(menuInt);
                return false;
            }
        });
        pesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    public void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for(int i=0 ; i<jsonResponse.length() ; i++) {
                        JSONObject temp = jsonResponse.getJSONObject(i).getJSONObject("hotel");
                        JSONObject lokasi = temp.getJSONObject("lokasi");
                        JSONObject room_temp = jsonResponse.getJSONObject(i);

                        Hotel h = new Hotel(temp.getInt("id"), temp.getString("nama"),
                                new Lokasi(lokasi.getDouble("x"), lokasi.getDouble("y"), lokasi.getString("deskripsi")),
                                temp.getInt("bintang"));
                        hotelHashMap.put(h.getNama(), h);
                        Room room = new Room(room_temp.getString("nomorKamar"), room_temp.getString("statusKamar"),
                                room_temp.getDouble("dailyTariff"), room_temp.getString("tipeKamar"));
                        if(!roomsMap.containsKey(h.getNama())) {
                            //ArrayList<Room> listRoom = new ArrayList<>();
                            listRoom.add(room);
                            roomsMap.put(h.getNama(), listRoom);
                        } else {
                            //ArrayList<Room> listRoom = roomsMap.get(h.getNama());
                            listRoom.add(room);
                            roomsMap.put(h.getNama(), listRoom);
                        }
                    }

                    for(String key : hotelHashMap.keySet()) {
                        listHotel.add(hotelHashMap.get(key));
                        childMapping.put(hotelHashMap.get(key), roomsMap.get(key));
                    }

                    listAdapter = new MenuListAdapter(MenuActivity.this, listHotel, childMapping);
                    expListView.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
        queue.add(menuRequest);
    }
}
