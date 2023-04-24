package br.com.frontesteprogramador.AcaoPublicacoes;

import br.com.database.dao.ClientesDAO;
import br.com.database.dao.ProcessosDAO;
import br.com.database.dao.PublicacoesAndIntimacoesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Clientes;
import br.com.database.model.Processo;
import br.com.database.model.PublicacoesEIntimacoes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdicionaPublicacoes implements AcaoPublicacoes{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        String tipo = request.getParameter("tipo");
        String data = request.getParameter("data");
        String numeroProcesso = request.getParameter("numeroProcesso");
        String texto = request.getParameter("texto");

        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long numeroProcessoLong = Long.parseLong(numeroProcesso);
        ProcessosDAO daoPRO = new ProcessosDAO(connection);
        Optional<Processo> processoOpt = daoPRO.findByNumeroDoProcesso(numeroProcessoLong);
        Processo processo = processoOpt.get();
        if(processoOpt.isPresent()){
            PublicacoesEIntimacoes peb = new PublicacoesEIntimacoes(tipo, localDate, processo, texto);
            PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
            dao.save(peb);

            return "/publicacaoEIntimacao-adicionado.jsp";
        }
        return "Error ao inserir Publicação ou Intimação !! Processo não existe";

    }
}
