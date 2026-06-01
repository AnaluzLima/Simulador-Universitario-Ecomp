package PBL.model.repository;

import PBL.exception.JogoException;
import PBL.model.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Essa classe é a responsável com lidar com o criação, salvamento, carregamento e exclusão do save de um jogo*/

public class JogoRepository {
    private Map<String, Jogo> save;
    private Gson gson;

    public JogoRepository() throws JogoException {
        //cria o JSON formatado e bonitinho
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.save = carregarArquivo(); //carrega os saves existentes ao iniciar
    }

    private Map<String, Jogo> carregarArquivo() throws JogoException {
        File arquivo = new File("saves.json");

        //se o arquivo não existe, é a primeira vez que o jogador abre o jogo
        if (!arquivo.exists()) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            //informa ao Gson o tipo exato do dicionário para reconstruir corretamente
            Type tipoMap = new TypeToken<Map<String, Jogo>>(){}.getType();
            Map<String, Jogo> savesCarregados = gson.fromJson(reader, tipoMap);

            return savesCarregados != null ? savesCarregados : new HashMap<>();

        } catch (IOException e) {
            throw new JogoException("Não foi possível carregar os dados.");
        }
    }

    private void salvarArquivo() throws JogoException {
        try (FileWriter writer = new FileWriter("saves.json")) {
            gson.toJson(this.save, writer); //converte o map inteiro para JSON e grava no disco
        } catch (IOException e) {
            throw new JogoException("ERRO! Seu jogo não foi salvo.");
        }
    }

    public void salvar(Jogo jogo) throws JogoException {
        this.save.put(jogo.getSlot(), jogo); //adiciona ou substitui o slot
        salvarArquivo(); //se mudou o slot, já salva no arquivo
    }

    public Jogo carregar(String slot) {
        return this.save.get(slot); //busca o jogo pelo nome do slot
    }

    public void deletar(String slot) throws JogoException {
        if (this.save.containsKey(slot)) {
            this.save.remove(slot); //remove do map
            salvarArquivo(); //atualiza o arquivo
        } else {
            throw new JogoException("Não é possível apagar um slot vazio");
        }
    }

    public boolean existeSave(String slot) {
        return this.save.containsKey(slot); //verifica se o slot existe antes de carregar ou deletar
    }

    public List<String> listarSlots() {
        return new ArrayList<>(this.save.keySet());
    }
}