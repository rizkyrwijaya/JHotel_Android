package projectoop.jhotel_android_rizkyramadianwijaya;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BuatPesananRequest extends StringRequest {
    private static final String pesan_URL = "http://192.168.137.1:8080/bookpesanan";
    private Map<String, String> params;

    public BuatPesananRequest(String jumlah_hari,String id_customer,String id_hotel,String nomor_kamar, Response.Listener<String> listener){
        super(Request.Method.POST,pesan_URL,listener,null);
        params = new HashMap<>();
        params.put("jumlah_hari",jumlah_hari);
        params.put("id_cust",id_customer);
        params.put("id_hotel",id_hotel);
        params.put("room_no",nomor_kamar);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

