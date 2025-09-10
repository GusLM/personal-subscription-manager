package model.dao;

import model.domain.Assinatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO (Data Access Object) para a entidade Assinatura.
 * Simula o armazenamento e a recuperação de dados em uma lista em memória,
 * agindo como um banco de dados falso para esta aplicação.
 */

public class AssinaturaDAO {

    private static final List<Assinatura> assinaturas = new ArrayList<>();

    public void salvar(Assinatura assinatura) {
        assinaturas.add(assinatura);
    }

    public List<Assinatura> listarTodas() {
        return new ArrayList<>(assinaturas);
    }

    public Optional<Assinatura> buscarPorNome(String nome) {
        return assinaturas.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public boolean removerPorNome(String nome) {
        return assinaturas.removeIf(a -> a.getNome().equalsIgnoreCase(nome));
    }
}