package projectoop.jhotel_android_rizkyramadianwijaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelesaiPesananActivity extends AppCompatActivity {

    int currentCustID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);
        Intent intent = getIntent();
        currentCustID = intent.getIntExtra("custID",0);

    }
}
