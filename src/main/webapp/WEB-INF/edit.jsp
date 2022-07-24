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
<h1>Edit <c:out value="${idea.content}"/></h1>
<div class="sub-form">
    <form:form action="../../ideas/${idea.id}/edit" method="post" modelAttribute="idea">
        <input type="hidden" name="_method" value="put">
        <div class="row mb-3 form-row">
            <form:label path="content" class="col-sm-3 col-form-label">Content: </form:label>
            <div class="col-sm-9">
                <form:input path="content" class="form-control"/>
                <form:errors path="content" class="text-danger"/>
            </div>
        </div>

        <form:input type="hidden" path="creator" value="${logedUser.id}"/>
        <input type="submit" value="Update" class="btn btn-primary">
    </form:form>
    <form method="post" action="../../ideas/delete/${idea.id}">
        <input type="hidden" name="_method" value="delete">
        <button class="btn btn-danger">Delete</button>
    </form>
</div>
</body>
</html>