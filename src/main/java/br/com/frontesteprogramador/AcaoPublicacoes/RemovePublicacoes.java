package br.com.frontesteprogramador.AcaoPublicacoes;

import br.com.database.dao.ProcessosDAO;
import br.com.database.dao.PublicacoesAndIntimacoesDAO;
import br.com.database.infra.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class RemovePublicacoes implements AcaoPublicacoes{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
        String id = request.getParameter("id");
        Long idLong = Long.parseLong(id);
        dao.delete(idLong);

        return "/controladoraPublicacao?acao=ListaPublicacoes";
    }
}
