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
import java.util.Optional;

public class AdicionaProcessos implements AcaoProcesso{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        String numeroProcesso = request.getParameter("numeroProcesso");
        String idCliente = request.getParameter("idCliente");

        ClientesDAO daoCli = new ClientesDAO(connection);

        long numeroProcessoLong = Long.parseLong(numeroProcesso);
        long idClienteLong = Long.parseLong(idCliente);

        //verifica se existe cliente com id passado
        if(daoCli.findById(idClienteLong).isPresent()){
            Optional<Clientes> clientesOptional = daoCli.findById(idClienteLong);
            Clientes cliente = clientesOptional.get();

            Processo processo = new Processo(numeroProcessoLong, cliente);
            ProcessosDAO dao = new ProcessosDAO(connection);
            dao.save(processo);
        }else{
            String mensagem = "O ID do cliente informado não existe.";
            request.setAttribute("mensagem", mensagem);
        }

        return "/processo-adicionado.jsp";

    }
}
