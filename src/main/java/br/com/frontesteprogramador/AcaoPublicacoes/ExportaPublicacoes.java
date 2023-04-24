package br.com.frontesteprogramador.AcaoPublicacoes;

import br.com.database.dao.ProcessosDAO;
import br.com.database.dao.PublicacoesAndIntimacoesDAO;
import br.com.database.infra.ConnectionFactory;
import br.com.database.model.Processo;
import br.com.database.model.PublicacoesEIntimacoes;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ExportaPublicacoes implements AcaoPublicacoes{
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        // Recupera a lista de publicações e intimacoes do banco de dados
        PublicacoesAndIntimacoesDAO dao = new PublicacoesAndIntimacoesDAO(connection);
        List<PublicacoesEIntimacoes> publiAndIntimacoes = dao.findAll();

        // Cria o arquivo Excel
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("PublicacoesEIntimacoes");

        // Cria a primeira linha (cabeçalho)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Tipo");
        headerRow.createCell(2).setCellValue("Data");
        headerRow.createCell(3).setCellValue("Numero do processo");
        headerRow.createCell(4).setCellValue("Texto");

        // Cria as linhas com os dados das publicações e intimacoes
        int rowNum = 1;
        for (PublicacoesEIntimacoes peb : publiAndIntimacoes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(peb.getId());
            row.createCell(1).setCellValue(peb.getTipo());
            row.createCell(2).setCellValue(peb.getData().toString());
            row.createCell(3).setCellValue(peb.getProcesso().getNumeroDoProcesso());
            row.createCell(4).setCellValue(peb.getTexto());
        }

        // Formata as células da coluna "Data"
        CellStyle dateCellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        dateCellStyle.setDataFormat(dataFormat.getFormat("dd/mm/yyyy"));
        sheet.setDefaultColumnStyle(2, dateCellStyle);

        // Define o tipo de conteúdo da resposta
        response.setContentType("application/vnd.ms-excel");

        // Define o cabeçalho da resposta
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=publicacoes_e_intimacoes.xls";
        response.setHeader(headerKey, headerValue);

        // Escreve o arquivo Excel no fluxo de saída da resposta HTTP
        workbook.write(response.getOutputStream());

        // Fecha o arquivo Excel
        workbook.close();
        return null;

    }
}
