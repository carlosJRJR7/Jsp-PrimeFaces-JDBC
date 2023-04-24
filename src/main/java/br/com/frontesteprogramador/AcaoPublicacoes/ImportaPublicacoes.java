package br.com.frontesteprogramador.AcaoPublicacoes;

import br.com.database.dao.ProcessosDAO;
import br.com.database.dao.PublicacoesAndIntimacoesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Processo;
import br.com.database.model.PublicacoesEIntimacoes;
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

public class ImportaPublicacoes implements AcaoPublicacoes{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Connection connection = ConnectionFactory.getConnection();
        // Verifica se é um upload de arquivo
        Part filePart = request.getPart("jsonFile");
        if (filePart != null) {
            InputStream inputStream = filePart.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try {
                // Converte o JSON em uma lista de objetos IntimacaoEPublicacao
                List<PublicacoesEIntimacoes> intimacoesEPublicacoes = gson.fromJson(reader, new TypeToken<List<PublicacoesEIntimacoes>>(){}.getType());

                PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
                // Salva as intimacoes e publicacoes no banco de dados
                for (PublicacoesEIntimacoes iep : intimacoesEPublicacoes) {
                    dao.save(iep);
                }
                return "Sucesso ao importar Publicações e Intimaçoes";
            } catch (JsonSyntaxException e) {
                return "Erro ao importar intimacoes e publicacoes: formato de JSON inválido.";
            }
        } else {
            // Caso contrário, verifica se é um envio de JSON via textarea
            String jsonText = request.getParameter("jsonContent");
            if (jsonText != null && !jsonText.isEmpty()) {
                try {
                    // Converte o JSON em uma lista de objetos IntimacaoEPublicacao
                    List<PublicacoesEIntimacoes> intimacoesEPublicacoes = gson.fromJson(jsonText, new TypeToken<List<PublicacoesEIntimacoes>>(){}.getType());

                    PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
                    // Salva as intimacoes e publicacoes no banco de dados
                    for (PublicacoesEIntimacoes iep : intimacoesEPublicacoes) {
                        dao.save(iep);
                    }
                    return "Sucesso ao importar Publicações e Intimaçoes";
                } catch (JsonSyntaxException e) {
                    return "Erro ao importar intimacoes e publicacoes: formato de JSON inválido.";
                }
            } else {
                // Caso contrário, exibe uma mensagem de erro
                return"Erro ao importar intimacoes e publicacoes: nenhum arquivo ou conteúdo JSON encontrado.";
            }
        }
    }
}
