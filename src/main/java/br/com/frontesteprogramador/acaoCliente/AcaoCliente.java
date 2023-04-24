package br.com.frontesteprogramador.acaoCliente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface AcaoCliente {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
}
