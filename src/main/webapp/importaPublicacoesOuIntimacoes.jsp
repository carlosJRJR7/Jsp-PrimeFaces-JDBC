<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Importar Intimações e Publicações</title>
</head>
<body>
<h1>Importar Intimações e Publicações</h1>
<form method="POST" enctype="multipart/form-data" action="controladoraPublicacao?acao=ImportaPublicacoes">
  <p>
    <label for="jsonFile">Arquivo JSON:</label>
    <input type="file" name="jsonFile" id="jsonFile"/>
  </p>
  <button type="submit">Importar em Lote</button>
</form>
</body>
</html>