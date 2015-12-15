import com.getfivestars.api.AuthToken;
import com.getfivestars.api.JsonRequest;
import com.getfivestars.api.NetClient;
import com.getfivestars.api.Response;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {   
        AuthToken token = new AuthToken("43543646", "43643743743437437437"); // clientId, privateKey
        JsonRequest request = new JsonRequest("/feedbacks/get", "{"
            + "'from':'2013-01-23',"
            + "'page':'1',"
            + "'to':'2015-01-23'"
        + "}");
        
        token.signRequest(request);
        
        NetClient client = new NetClient(request);
        Response response = client.sendRequest();
        
        if(!response.getStatus()) {
            System.out.println(response.getErrorCode() + " " + response.getErrorMessage());
        } else {
            HashMap<String, String> r = response.getResponse();
            Object[] keys = r.keySet().toArray();
            for(Object key : keys) {
                System.out.println( key + " = " + r.get(key));
            }
        }
    }
} 
