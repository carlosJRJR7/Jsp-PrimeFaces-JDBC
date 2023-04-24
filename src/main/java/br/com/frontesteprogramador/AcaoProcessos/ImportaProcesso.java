package br.com.frontesteprogramador.AcaoProcessos;

import br.com.database.dao.ClientesDAO;
import br.com.database.dao.ProcessosDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Clientes;
import br.com.database.model.Processo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

public class ImportaProcesso implements AcaoProcesso{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        Gson gson = new Gson();
        // Verifica se é um upload de arquivo
        Part filePart = request.getPart("jsonFile");
        if (filePart != null) {
            InputStream inputStream = filePart.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try {
                // Converte o JSON em uma lista de objetos Processo
                List<Processo> processos = gson.fromJson(reader, new TypeToken<List<Processo>>(){}.getType());

                ProcessosDAO dao = new ProcessosDAO(connection);
                // Salva os processos no banco de dados
                for (Processo processo : processos) {
                    dao.save(processo);
                }
                return "Sucesso";
            } catch (JsonSyntaxException e) {
                request.setAttribute("mensagem", "Erro ao importar processos: formato de JSON inválido.");
                return "Erro ao importar processos: formato de JSON inválido";
            }
        } else {
            // Caso contrário, verifica se é um envio de JSON via textarea
            String jsonText = request.getParameter("jsonContent");
            if (jsonText != null && !jsonText.isEmpty()) {
                try {
                    // Converte o JSON em uma lista de objetos Processo
                    List<Processo> processos = gson.fromJson(jsonText, new TypeToken<List<Processo>>(){}.getType());
                    ProcessosDAO dao = new ProcessosDAO(connection);
                    // Salva os processos no banco de dados
                    for (Processo processo : processos) {
                        dao.save(processo);
                    }
                    return "Sucesso";
                } catch (JsonSyntaxException e) {
                    request.setAttribute("mensagem", "Erro ao importar processos: formato de JSON inválido.");
                    return "Erro ao importar processos: formato de JSON inválido";
                }
            } else {
                request.setAttribute("mensagem", "Nenhum arquivo ou texto JSON fornecido.");
                return "Importacão Falhou";
            }
        }
    }
}
