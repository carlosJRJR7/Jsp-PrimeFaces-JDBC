package br.com.frontesteprogramador.AcaoProcessos;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface AcaoProcesso {
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
}

