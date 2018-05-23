package projectoop.jhotel_android_rizkyramadianwijaya;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class SelesaiPesananActivity extends AppCompatActivity {

    int currentCustID;

    private ConstraintLayout layoutSelesaiPesanan;

    private TextView idPesanan;
    private TextView biayaPesanan;
    private TextView hariPesanan;
    private TextView tanggalPesanan;

    private Button buttonPesananSelesai;
    private Button buttonPesananDibatalkan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);
        Intent intent = getIntent();
        currentCustID = intent.getIntExtra("custID",0);

        layoutSelesaiPesanan = (ConstraintLayout) findViewById(R.id.layoutSelesaiPesanan);

        idPesanan = (TextView) findViewById(R.id.idPesanan);
        biayaPesanan = (TextView) findViewById(R.id.biayaPesanan);
        hariPesanan = (TextView) findViewById(R.id.hariPesanan);
        tanggalPesanan  = (TextView) findViewById(R.id.tanggalPesanan);

        buttonPesananSelesai = (Button) findViewById(R.id.buttonPesananSelesai);
        buttonPesananDibatalkan = (Button) findViewById(R.id.buttonPesananDibatalkan);

        layoutSelesaiPesanan.setVisibility(View.INVISIBLE);
        buttonPesananSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse!=null){
                                AlertDialog.Builder builder=new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan Selesai!")
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
                            AlertDialog.Builder builder=new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    finish();
                                }
                            });
                            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    finish();
                                }
                            });
                            builder.setMessage("Selesai Failed")
                                    .create()
                                    .show();
                        }

                    }
                };

                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(Integer.parseInt(idPesanan.getText().toString()),responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);
            }
        });

        buttonPesananDibatalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse!=null){
                                AlertDialog.Builder builder=new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan DiBatalkan!")
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
                            AlertDialog.Builder builder=new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    finish();
                                }
                            });
                            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    finish();
                                }
                            });
                            builder.setMessage("Batal Failed")
                                    .create()
                                    .show();
                        }

                    }
                };

                PesananBatalRequest BatalRequest = new PesananBatalRequest(Integer.parseInt(idPesanan.getText().toString()),responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(BatalRequest);
            }
        });
        getPesanan();
    }

    public void getPesanan(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(jsonResponse != null) {
                        layoutSelesaiPesanan.setVisibility(View.VISIBLE);
                        idPesanan.setText(String.valueOf(jsonResponse.getInt("id")));
                        biayaPesanan.setText(String.valueOf(jsonResponse.getDouble("biaya")));
                        hariPesanan.setText(String.valueOf(jsonResponse.getDouble("jumlahHari")));
                        tanggalPesanan.setText((jsonResponse.getString("tanggalPesan")).substring(0,10));
                    } else {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    finish();
                }

            }
        };
        PesananFetchRequest fetchRequest = new PesananFetchRequest(this.currentCustID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);
    }

}
