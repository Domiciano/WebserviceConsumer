package appmoviles.com.webserviceconsumer;

import com.google.gson.Gson;

import java.io.IOException;

public class ServiceManager {

    public static final String SIMPLEGET_URL = "https://www.icesi.edu.co/";
    public static final String USUARIOS_URL = "https://fir-androidicesi.firebaseio.com/user.json";

    public static class SimpleGET{
        OnResponseListener listener;
        public SimpleGET(OnResponseListener listener){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(SIMPLEGET_URL);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }
    }

    public static class usuariosGET{
        OnResponseListener listener;
        public usuariosGET(OnResponseListener listener){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String response = util.GETrequest(USUARIOS_URL);
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }
    }

    public static class usuariosPOST{
        OnResponseListener listener;
        public usuariosPOST(Usuario usuario, OnResponseListener listener){
            this.listener = listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                Gson g = new Gson();
                String response = util.POSTrequest( USUARIOS_URL, g.toJson(usuario) );
                listener.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String response);
        }
    }
}
