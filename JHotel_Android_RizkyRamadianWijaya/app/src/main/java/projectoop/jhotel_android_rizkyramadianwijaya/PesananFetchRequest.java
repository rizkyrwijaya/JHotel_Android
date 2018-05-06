package projectoop.jhotel_android_rizkyramadianwijaya;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananFetchRequest extends StringRequest {
    private static final String fetch_URL = "http://192.168.137.1:8080/pesanancustomer/";
    private Map<String, String> params;

    public PesananFetchRequest(int custID,Response.Listener<String> listener){
        super(Method.GET,fetch_URL + custID,listener,null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
