package PBL.fase_1.repository;

import PBL.exception.JogoException;
import PBL.fase_1.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JogoRepository {
    private Map<Integer, Jogo> save;
    private Gson gson;

    public JogoRepository() throws JogoException {
        //cria o JSON formatado e bonitinho
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.save = carregarArquivo();
    }

    private Map<Integer, Jogo> carregarArquivo() throws JogoException {
        File arquivo = new File("saves.json");

        //se o arquivo não existe, é a primeira vez que o jogador abre o jogo
        if (!arquivo.exists()) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type tipoMap = new TypeToken<Map<Integer, Jogo>>(){}.getType();
            Map<Integer, Jogo> savesCarregados = gson.fromJson(reader, tipoMap);

            return savesCarregados != null ? savesCarregados : new HashMap<>();

        } catch (Exception e) {
            throw new JogoException("Não foi possível carregar os dados.");
        }
    }

    private void salvarArquivo() throws JogoException {
        try (FileWriter writer = new FileWriter("saves.json")) {
            gson.toJson(this.save, writer);
        } catch (Exception e) {
            throw new JogoException("ERRO! Seu jogo não foi salvo.");
        }
    }

    public void salvar(Jogo jogo) throws JogoException {
        this.save.put(jogo.getSlot(), jogo);
        salvarArquivo();
    }

    public Jogo carregar(int slot) {
        return this.save.get(slot);
    }

    public void deletar(int slot) throws JogoException {
        if (this.save.containsKey(slot)) {
            this.save.remove(slot);
            salvarArquivo();
        } else {
            throw new JogoException("Não é possível apagar um slot vazio");
        }
    }

    public boolean existeSave(int slot) {
        return this.save.containsKey(slot);
    }

}