package br.com.frontesteprogramador.AcaoProcessos;

import br.com.database.dao.ClientesDAO;
import br.com.database.dao.ProcessosDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Clientes;
import br.com.database.model.Processo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ListaProcessos implements AcaoProcesso{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        ProcessosDAO dao = new ProcessosDAO(connection);
        List<Processo> processos = dao.findAll();

        request.setAttribute("processos", processos);

        return "/lista-processos.jsp";

    }
}
