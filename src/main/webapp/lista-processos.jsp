
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
    <th>Numero do processo</th>
    <th>Id do cliente vinculado</th>

  </tr>
  <c:forEach var="processos" items="${processos}">
    <tr>
      <td> ${processos.getNumeroDoProcesso()}</td>
      <td> ${processos.getCliente().getId()}</td>
      <td><a href="controladoraProcesso?acao=RemoveProcessos&id=${processos.id}"> Remover</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
