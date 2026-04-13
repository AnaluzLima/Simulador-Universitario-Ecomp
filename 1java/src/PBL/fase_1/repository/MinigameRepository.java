package PBL.fase_1.repository;

import PBL.fase_1.model.minigames.*;
import PBL.fase_1.model.minigames.Minigame;

//ESSA CLASSE NÃO ESTÁ PRONTA, sofrerá alterações futuramente

public class MinigameRepository {

    public Minigame buscarMinigamePorArea(String area) {
        switch(area) {
            case "Texto":
                return new MinigameTexto();
            case "Matemática":
                return new MinigameMatematica();
            case "Software":
                return new MinigameSoftware();
            case "Hardware":
                return new MinigameHardware();
            default:
                return new MinigameTexto(); //Padrão
        }
    }
}