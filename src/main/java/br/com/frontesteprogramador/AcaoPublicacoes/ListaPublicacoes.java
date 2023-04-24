package br.com.frontesteprogramador.AcaoPublicacoes;

import br.com.database.dao.ProcessosDAO;
import br.com.database.dao.PublicacoesAndIntimacoesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Processo;
import br.com.database.model.PublicacoesEIntimacoes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ListaPublicacoes implements AcaoPublicacoes{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
        List<PublicacoesEIntimacoes> publiAndIntimacoes = dao.findAll();

        request.setAttribute("publiAndIntimacoes", publiAndIntimacoes);

        return "/lista-publi-e-intimacoes.jsp";
    }
}
