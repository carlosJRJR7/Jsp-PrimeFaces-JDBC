package br.com.frontesteprogramador.Clientes;

import br.com.frontesteprogramador.acaoCliente.AcaoCliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controladoraClientes")
public class ControladoraClienteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Class classe = null;

        try {
            String acaoName = request.getParameter("acao");
            String pacote = "br.com.frontesteprogramador.acaoCliente.";
            classe = Class.forName(pacote + acaoName);

            AcaoCliente acao =  (AcaoCliente) classe.newInstance();

            String resultado = acao.executa(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(resultado);
            dispatcher.forward(request, response);

        } catch (Exception e) {

        }


    }
}
