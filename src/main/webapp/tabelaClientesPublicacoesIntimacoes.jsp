<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">
<head>
    <meta charset="UTF-8" />
    <title>Minha tabela</title>
    <!-- Importação de arquivos do PrimeFaces -->
    <link rel="stylesheet" href="#{request.contextPath}/primefaces/primefaces.css" />
    <script src="#{request.contextPath}/primefaces/jquery/jquery.js"></script>
    <script src="#{request.contextPath}/primefaces/primefaces.js"></script>
</head>
<body>
<h:form>
    <p:dataTable value="#{minhaBean.lista}" var="item">
        <!-- Colunas da tabela -->
        <p:column headerText="Tipo" sortBy="#{item.tipo}">
            <h:outputText value="#{item.tipo}" />
        </p:column>
        <p:column headerText="Nome do cliente" sortBy="#{item.nome_cliente}">
            <h:outputText value="#{item.nome_cliente}" />
        </p:column>
        <p:column headerText="Anterior a 10/04/2023" sortBy="#{item.anterior_a_10042023}">
            <h:outputText value="#{item.anterior_a_10042023}" />
        </p:column>
        <p:column headerText="10/04/2023" sortBy="#{item.10/04/2023}">
            <h:outputText value="#{item.10/04/2023}" />
        </p:column>
        <p:column headerText="11/04/2023" sortBy="#{item.11/04/2023}">
            <h:outputText value="#{item.11/04/2023}" />
        </p:column>
        <p:column headerText="12/04/2023" sortBy="#{item.12/04/2023}">
            <h:outputText value="#{item.12/04/2023}" />
        </p:column>
        <p:column headerText="13/04/2023" sortBy="#{item.13/04/2023}">
            <h:outputText value="#{item.13/04/2023}" />
        </p:column>
        <p:column headerText="14/04/2023" sortBy="#{item.14/04/2023}">
            <h:outputText value="#{item.14/04/2023}" />
        </p:column>
    </p:dataTable>
</h:form>
</body>
</html>
