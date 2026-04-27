<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Gerenciamento de Editoras</title>
</head>

<body>
<div align="center">
    <h1>Gerenciamento de Editoras</h1>
    <h2>
        <a href="lista">Lista de Editoras</a>
    </h2>
</div>

<div align="center">
    <c:choose>
        <%-- SE A EDITORA EXISTIR (MODO DE EDIÇÃO) --%>
        <c:when test="${editora != null}">
            <form action="atualizacao" method="post">

                <input type="hidden" name="id" value="${editora.id}" />

                <table border="1" cellpadding="5">
                    <caption>Edição de Editora</caption>
                    <tr>
                        <th>CNPJ:</th>
                        <td><input type="text" name="cnpj" size="18" value="${editora.CNPJ}" required /></td>
                    </tr>
                    <tr>
                        <th>Nome:</th>
                        <td><input type="text" name="nome" size="45" value="${editora.nome}" required /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
                    </tr>
                </table>
            </form>
        </c:when>

        <%-- SE NÃO EXISTIR (MODO DE INSERÇÃO) --%>
        <c:otherwise>
            <form action="insercao" method="post">
                <table border="1" cellpadding="5">
                    <caption>Nova Editora</caption>
                    <tr>
                        <th>CNPJ:</th>

                        <td><input type="text" name="cnpj" size="18" required /></td>
                    </tr>
                    <tr>
                        <th>Nome:</th>
                        <td><input type="text" name="nome" size="45" required /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
                    </tr>
                </table>
            </form>
        </c:otherwise>
    </c:choose>
</div>

<c:if test="${!empty requestScope.mensagens}">
    <ul class="erro">
        <c:forEach items="${requestScope.mensagens}" var="mensagem">
            <li>${mensagem}</li>
        </c:forEach>
    </ul>
</c:if>
</body>

</html>