package projectoop.jhotel_android_rizkyramadianwijaya;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {

    private int currentUserID, banyakHari, idHotel;
    double tariff;
    String roomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        Intent intent = getIntent();
        currentUserID = intent.getIntExtra("custID",0);
        idHotel = intent.getIntExtra("hotelID",0);
        roomNumber = intent.getStringExtra("roomNum");
        final TextView room_number = findViewById(R.id.room_number);
        final TextView tariffView = findViewById(R.id.tariff);
        final EditText durasi = findViewById(R.id.durasi_hari);
        final TextView total = findViewById(R.id.total_biaya);
        final Button hitungButton = findViewById(R.id.hitung);
        final Button pesanButton = findViewById(R.id.pesan);

        pesanButton.setVisibility(View.INVISIBLE);
        room_number.setText(roomNumber);
        tariffView.setText(String.valueOf(tariff));
        total.setText("0");

        hitungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banyakHari = Integer.parseInt(durasi.getText().toString());
                total.setText(String.valueOf(tariff*banyakHari));
                hitungButton.setVisibility(View.INVISIBLE);
                pesanButton.setVisibility(View.VISIBLE);
            }
        });

        pesanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse!=null){
                                AlertDialog.Builder builder=new AlertDialog.Builder(BuatPesananActivity.this);
                                builder.setMessage("Pesanan Sukses!")
                                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {
                                                finish();
                                            }
                                        })
                                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialog) {
                                                finish();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                        } catch(JSONException e){
                            AlertDialog.Builder builder=new AlertDialog.Builder(BuatPesananActivity.this);
                            builder.setMessage("Pesanan Gagal!")
                                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            finish();
                                        }
                                    })
                                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    }

                };
                BuatPesananRequest pesananRequest = new BuatPesananRequest(String.valueOf(banyakHari),String.valueOf(currentUserID),String.valueOf(idHotel),roomNumber,responseListener);
                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(pesananRequest);
            }
        });

    }
}
