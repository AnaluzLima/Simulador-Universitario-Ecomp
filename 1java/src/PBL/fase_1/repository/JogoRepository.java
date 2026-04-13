package PBL.fase_1.repository;

import PBL.exception.JogoException;
import PBL.fase_1.model.Jogo;
import java.util.HashMap;
import java.util.Map;

/**Essa classe tem como objetivo controlar a permanencia de dados do jogo futuramente, sendo possível salvar uma partida
 * abrir uma partida salva, verificar a existência de um jogo alvo e deletar a partida*/

public class JogoRepository {
    private Map<Integer, Jogo> save; //usa um 'dicionário' para os saves

    public JogoRepository() {
        this.save = new HashMap<>();
    }

    public void salvar(Jogo jogo) {
        this.save.put(jogo.getSlot(), jogo);
    }

    public Jogo carregar(int save) {
        return this.save.get(save);
    }

    public void deletar(int save) throws JogoException {
        if (this.save.containsKey(save)) {
            this.save.remove(save);

        }
        else{
            throw new JogoException("Não é possível apagar um slot vazio");
        }
    }

    public boolean existeSave(int save) {
        return this.save.containsKey(save);
    }
}