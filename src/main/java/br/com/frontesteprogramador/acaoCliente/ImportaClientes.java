package br.com.frontesteprogramador.acaoCliente;

import br.com.database.dao.ClientesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Clientes;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

public class ImportaClientes implements AcaoCliente {


    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Connection connection = ConnectionFactory.getConnection();

        // Verifica se é um upload de arquivo
        Part filePart = request.getPart("file");
        if (filePart != null) {
            InputStream inputStream = filePart.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try {
                // Converte o JSON em uma lista de objetos Cliente
                List<Clientes> clientes = gson.fromJson(reader, new TypeToken<List<Clientes>>(){}.getType());

                ClientesDAO dao = new ClientesDAO(connection);
                // Salva os clientes no banco de dados
                for (Clientes cliente : clientes) {
                    dao.save(cliente);
                }
                return "sucesso";
            } catch (JsonSyntaxException e) {
                request.setAttribute("mensagem", "Erro ao importar clientes: formato de JSON inválido.");
                return "Importação Falhou";
            }
        } else {
            // Caso contrário, verifica se é um envio de JSON via textarea
            String jsonText = request.getParameter("jsonText");
            if (jsonText != null && !jsonText.isEmpty()) {
                try {
                    // Converte o JSON em uma lista de objetos Cliente
                    List<Clientes> clientes = gson.fromJson(jsonText, new TypeToken<List<Clientes>>(){}.getType());
                    // Salva os clientes no banco de dados
                    ClientesDAO dao = new ClientesDAO(connection);
                    // Salva os clientes no banco de dados
                    for (Clientes cliente : clientes) {
                        dao.save(cliente);
                    }
                    return "sucesso";
                } catch (JsonSyntaxException e) {
                    request.setAttribute("mensagem", "Erro ao importar clientes: formato de JSON inválido.");
                    return "Importação Falhou";
                }
            } else {
                request.setAttribute("mensagem", "Nenhum arquivo ou texto JSON fornecido.");
                return "Nenhum arquivo ou texto JSON fornecido.";
            }
        }
    }
}
