
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
    <th>Nome do cliente</th>
  </tr>
  <c:forEach var="clientes" items="${clientes}">
    <tr>
      <td> ${clientes.getNome()}</td>
      <td><a href="controladoraClientes?acao=RemoveClientes&id=${clientes.id}"> Remover</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
