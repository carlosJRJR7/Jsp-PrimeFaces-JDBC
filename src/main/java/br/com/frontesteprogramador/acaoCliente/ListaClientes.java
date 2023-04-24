package br.com.frontesteprogramador.acaoCliente;

import br.com.database.dao.ClientesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Clientes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ListaClientes implements AcaoCliente {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        ClientesDAO dao = new ClientesDAO(connection);
        List<Clientes> clientes = dao.findAll();

        request.setAttribute("clientes", clientes);

        return "/lista-clientes.jsp";
    }
}
