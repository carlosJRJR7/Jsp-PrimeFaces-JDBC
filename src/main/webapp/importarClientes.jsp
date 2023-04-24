<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Importar Clientes</title>
</head>
<body>
<h1>Importar Clientes</h1>
<form method="POST" enctype="multipart/form-data" action="controladoraClientes?acao=ImportaClientes">
  <p>
    <label for="jsonFile">Arquivo JSON:</label>
    <input type="file" name="jsonFile" id="jsonFile"/>
  </p>
  <p>
    <label for="jsonContent">Conteúdo JSON:</label>
    <textarea name="jsonContent" id="jsonContent" rows="10" cols="50"></textarea>
  </p>
  <button type="submit">Importar em Lote</button>
</form>
</body>
</html>