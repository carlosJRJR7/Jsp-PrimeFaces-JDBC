package br.com.frontesteprogramador.AcaoProcessos;

import br.com.database.dao.ProcessosDAO;
import br.com.database.infra.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RemoveProcessos implements AcaoProcesso{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        ProcessosDAO dao = new ProcessosDAO(connection);
        String id = request.getParameter("id");
        Long idLong = Long.parseLong(id);
        dao.delete(idLong);

        return "/controladoraProcesso?acao=ListaProcessos";
    }
}
