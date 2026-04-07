package PBL.model.model;


import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private List<Local> campus;
    private Local spawn;

    public Mapa(boolean spawnF6) {
        this.campus = new ArrayList<>();
    }

    public List<Local> getCampus() {
        return campus;
    }

    public void setCampus(List<Local> campus) {
        this.campus = campus;
    }

    public Local getSpawn() {
        return this.spawn;
    }

    public void setSpawn(Local spawn) {
        this.spawn = spawn;
    }

}
