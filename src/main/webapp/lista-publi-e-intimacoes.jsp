
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="pt">
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Tipo</th>
        <th>Data</th>
        <th>Numero do processo</th>
        <th>Texto</th>

    </tr>
    <c:forEach var="publiAndIntimacoes" items="${publiAndIntimacoes}">
    <tr>
        <td> ${publiAndIntimacoes.getId()}</td>
        <td> ${publiAndIntimacoes.getTipo()}</td>
        <td> ${publiAndIntimacoes.getData()}</td>
        <td> ${publiAndIntimacoes.getProcesso().getNumeroDoProcesso()}</td>
        <td> ${publiAndIntimacoes.getTexto()}</td>
        <td><a href="controladoraPublicacao?acao=RemovePublicacoes&id=${publiAndIntimacoes.id}"> Remover</a></td>
    </tr>
    </c:forEach>

    <form action="controladoraPublicacao?acao=ExportaPublicacoes" method="POST">
        <input type="hidden" name="acaoExportar" value="ExportaPublicacoes">
        <input type="submit" value="Exportar para Excel">
    </form>
</table>
</body>
</html>
