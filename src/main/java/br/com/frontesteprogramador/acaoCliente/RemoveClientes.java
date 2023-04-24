package br.com.frontesteprogramador.acaoCliente;

import br.com.database.dao.ClientesDAO;
import br.com.database.infra.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RemoveClientes implements AcaoCliente {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Connection connection = ConnectionFactory.getConnection();
        ClientesDAO dao = new ClientesDAO(connection);
        String id = request.getParameter("id");
        Long idLong = Long.parseLong(id);
        dao.delete(idLong);

        return "/controladoraClientes?acao=ListaClientes";
    }
}
