<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ideas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="position-relative">
        <h1 class="position-absolute top-0 start-0 translate-middle">Welcome, <c:out value="${user.username}"/></h1>
        <br>
        <h5 class="position-absolute top-0 start-100 translate-middle"><a href="../logout">Logout</a></h5>
    </div>
    <br>
    <br>
    <div class="d-flex justify-content-between">
        <h2>Ideas</h2>
        <div class="links-tab d-flex justify-content-between">
            <h5><a href="/orderDescending">Low Likes</a></h5>
            <h5><a href="/orderAscending">High Likes</a></h5>
        </div>
    </div>
    <table class="table tbl">
        <thead>
        <th scope="row">Ideas</th>
        <th scope="row">Created By</th>
        <th scope="row">Likes</th>
        <th scope="row">Action</th>
        </thead>
        <tbody>
        <c:forEach var="idea" items="${allIdeas}">
            <tr>
                <td><a href="/ideas/${idea.id}"><c:out value="${idea.content}"/></a></td>
                <td><c:out value="${idea.creator.username}"/></td>
                <td><c:out value="${idea.users.size()}"/></td>
                <td>
                    <c:if test="${idea.users.contains(user)}">
                        <a href="/removeLike/${idea.id}">Unlike</a>
                    </c:if>
                    <c:if test="${!(idea.users.contains(user))}">
                        <a href="/addLike/${idea.id}">Like</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br>
    <a href="../ideas/new"><button class="btn btn-primary">Create an idea</button></a>
    <br>
    <br>
</body>
</html>