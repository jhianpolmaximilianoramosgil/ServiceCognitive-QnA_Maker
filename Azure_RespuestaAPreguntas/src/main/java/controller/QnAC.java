package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.QnA;
import org.json.JSONObject;
import services.QnAS;
import lombok.Data;

@Data
@Named(value = "qnaC")
@SessionScoped
public class QnAC implements Serializable {

    QnA modelo;

    public QnAC() {
        modelo = new QnA();
//        modelo.setPREGUNTA("¿Cuál es tu nombre?");
//        modelo.setRESPUESTA("Mi nombre es Jhianpol");
    }

    public void obtenerDatosPregunta() throws Exception {
        try {
            JSONObject cadenaJson = QnAS.obtenerJSonPregunta(modelo);
            modelo.setID(cadenaJson.getInt("id"));
            modelo.setPUNTAJE(cadenaJson.getDouble("score"));
            modelo.setFUENTE(cadenaJson.getString("source"));
            modelo.setRESPUESTA(cadenaJson.getString("answer"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK RESPUESTA", "A tu pregunta"));
        } catch (Exception e) {
            System.out.println("Error en obtenerDatosPregunta: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


