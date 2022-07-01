package model;

import lombok.Data;

@Data
public class QnA {
    
    //AZURE PREGUNTAS Y RESPUESTAS
    int ID;
    String PREGUNTA;
    String RESPUESTA;
    double PUNTAJE;
    String FUENTE;
}
