<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ideas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="d-flex justify-content-between">
        <h1 class="make-purple"><c:out value="${idea.content}"/></h1>
        <h4><a href="../ideas">Back to Dashboard</a></h4>
    </div>
    <br>
    <br>
    <h3>Created by: <c:out value="${idea.creator.username}"/></h3>
    <br>
    <br>

    <h1>Users who liked
        <c:if test="${user.id == idea.creator.id}">
        your
        </c:if>
        <c:if test="${user.id != idea.creator.id}">
        this
        </c:if>
        idea :</h1>
    <table class="table tbl">
        <thead>
             <th scope="row">Name</th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersThatLiked}">
            <tr>
                <td><c:out value="${user.username}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <br>
    <c:if test="${user.id == idea.creator.id}">
    <form method="get" action="/ideas/${idea.id}/edit">
            <button class="btn btn-secondary">Edit</button>
        </form>
    </c:if>
</body>
</html>
