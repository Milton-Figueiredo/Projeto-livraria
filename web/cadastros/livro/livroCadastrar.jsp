<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarlivro" action="LivroCadastrar" method="POST">
    
    <table align="center" border="0">
        
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastros de Livros
                </th>
            </tr> 
            <tr>
                <th colspan="2" aling="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="idlivro" id="idlivro" value="${livro.idLivro}" readonly="readonly"/> </td>
            </tr>
            <tr>
                <td>Titulo: </td>
                <td><input type="text" name="titulolivro" id="titulolivro" value="${livro.tituloLivro}" size="50" maxlength="50" /></td>
            </tr>
            <tr>
                <td>Autores: </td>
                <td>
                <select name="idautor" id="idautor">
                    <option value="">selecione</option>
                    <c:forEach var="autor" items="${autores}">
                        <option value="${autor.idAutor}" ${livro.autor.idAutor == autor.idAutor ? "selected" : ""} > ${autor.descricaoAutor} </option>
                    </c:forEach>
                </select>
                </td>
                
                <td>Editoras: </td>
                <td>
                <select name="ideditora" id="ideditora">
                    <option value="">selecione</option>
                    <c:forEach var="editora" items="${editoras}">
                        <option value="${editora.idEditora}" ${livro.editora.idEditora == editora.idEditora ? "selected" : ""} > ${editora.descricaoEditora} </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            
           <td>Generos: </td>
                <td>
                <select name="idgenero" id="idgenero">
                    <option value="">selecione</option>
                    <c:forEach var="genero" items="${generos}">
                        <option value="${genero.idGenero}" ${livro.genero.idGenero == genero.idGenero ? "selected" : ""} > ${genero.descricaoGenero} </option>
                    </c:forEach>
                </select>
                </td>
                
            <tr><td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="cadastrar"/>
                    <input type="reset" name="limpar" id="limpar" value="limpar" />
                </td> 
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp"> Voltar à Pagina Principal</a></h5></td>
            </tr>
        </tbody>
    </table>    
</form>
<%@ include file="/footer.jsp" %>
