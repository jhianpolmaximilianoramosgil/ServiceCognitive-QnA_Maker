package services;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import model.QnA;

public class QnAS {

    public static void main(String[] args) throws Exception {
        QnA modelo = new QnA();
        modelo.setPREGUNTA("¿Cuál es tu nombre?");
        obtenerJSonPregunta(modelo);
        JSONObject cadenaJsonFinal = obtenerJSonPregunta(modelo);
        System.out.println("ID = " + cadenaJsonFinal.getInt("id"));
        System.out.println("PUNTAJE = " + cadenaJsonFinal.getDouble("score"));
        System.out.println("FUENTE = " + cadenaJsonFinal.getString("source"));
        System.out.println("PREGUNTA = " + modelo.getPREGUNTA());
        System.out.println("RESPUESTA = " + cadenaJsonFinal.getString("answer"));
    }

    public static JSONObject obtenerJSonPregunta(QnA modelo) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"question\":\"" + modelo.getPREGUNTA() + "\"\r\n}");
        Request request = new Request.Builder()
                .url("https://preguntasdemo1.azurewebsites.net/qnamaker/knowledgebases/aba04c1e-f09a-4b99-bfd9-5c2ff1a28b76/generateAnswer")
                .method("POST", body)
                .addHeader("Authorization", "EndpointKey 63a6cbe0-c586-4b7a-8991-5fbe704b64ec")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "ARRAffinity=135f536e0d078a815c3b83fda3468ab8a3a2abc604a35eefb28648d3961cfa9b; "
                        + "ARRAffinitySameSite=135f536e0d078a815c3b83fda3468ab8a3a2abc604a35eefb28648d3961cfa9b")
                
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        JSONObject cadenaJson = new JSONObject(response.body().string());
        JSONObject cadenaJsonFinal = cadenaJson.getJSONArray("answers").getJSONObject(0);
        return cadenaJsonFinal;
    }

}
