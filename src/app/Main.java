package app;

import controller.GerenciadorController;
import model.dao.AssinaturaDAO;
import model.service.GerenciadorService;
import ui.GerenciadorView;

public class Main {
    public static void main(String[] args) {

        AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
        GerenciadorService service = new GerenciadorService(assinaturaDAO);
        GerenciadorController controller = new GerenciadorController(service);
        GerenciadorView gerenciadorView = new GerenciadorView(controller);
        gerenciadorView.rodar();
    }
}