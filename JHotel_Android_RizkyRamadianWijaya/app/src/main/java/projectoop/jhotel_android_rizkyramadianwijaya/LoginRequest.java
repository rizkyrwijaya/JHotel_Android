package projectoop.jhotel_android_rizkyramadianwijaya;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.137.1:8080/logincust";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener){
        super(Method.POST,Regis_URL,listener,null);
        params = new HashMap<>();
        params.put("email",email);
        params.put("pass",password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
