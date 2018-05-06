package projectoop.jhotel_android_rizkyramadianwijaya;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String finish_URL = "http://192.168.137.1:8080/finishpesanan";
    private Map<String, String> params;

    public PesananSelesaiRequest(int pesananID,Response.Listener<String> listener){
        super(Method.POST,finish_URL,listener,null);
        params = new HashMap<>();
        params.put("id_pesanan",String.valueOf(pesananID));
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
