package projectoop.jhotel_android_rizkyramadianwijaya;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MenuRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.137.1:8080/vacantrooms";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener){
        super(Method.GET,Regis_URL,listener,null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

