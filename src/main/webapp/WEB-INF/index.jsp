<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Belt Exam</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
    <br>
    <h1 class="position-absolute top-20 start-50 translate-middle make-blue">Ideas Platform</h1>
    <br>
    <br>
</div>
<div class="container-flex">
    <div class="sub-form">
        <h2 class="form-header">Register</h2>
        <form:form action="../register" method="post" modelAttribute="newUser">
            <div class="row mb-3 form-row">
                <form:label path="username" class="col-sm-3 col-form-label">Name:</form:label>
                <div class="col-sm-9">
                    <form:input path="username" class="form-control"/>
                    <form:errors path="username" class="text-danger"/>
                </div>
            </div>
            <div class="row mb-3  form-row">
                <form:label path="email" class="col-sm-3 col-form-label">Email:</form:label>
                <div class="col-sm-9">
                    <form:input path="email" class="form-control"/>
                    <form:errors path="email" class="text-danger"/>
                </div>
            </div>
            <div class="row mb-3 form-row">
                <form:label path="password" class="col-sm-3 col-form-label">Password:</form:label>
                <div class="col-sm-9">
                    <form:input type="password" path="password" class="form-control"/>
                    <form:errors path="password" class="text-danger"/>
                </div>
            </div>
            <div class="row mb-3 form-row">
                <form:label path="confirm" class="col-sm-3 col-form-label">Password Conf:</form:label>
                <div class="col-sm-9">
                    <form:input type="password" path="confirm" class="form-control"/>
                    <form:errors path="confirm" class="text-danger"/>
                </div>
            </div>
            <input type="submit" value="Register" class="btn btn-primary form-row">
        </form:form>
    </div>
    <div class="sub-form">
        <h2 class="form-header">Log in</h2>
        <form:form action="../login" method="post" modelAttribute="newLogin">
            <div class="row mb-3 form-row">
                <form:label path="email" class="col-sm-3 col-form-label">Email:</form:label>
                <div class="col-sm-9">
                    <form:input path="email" class="form-control"/>
                    <form:errors path="email" class="text-danger"/>
                </div>
            </div>
            <div class="row mb-3 form-row">
                <form:label path="email" class="col-sm-3 col-form-label">Password:</form:label>
                <div class="col-sm-9">
                    <form:input type="password" path="password" class="form-control"/>
                    <form:errors path="password" class="text-danger"/>
                </div>
            </div>
            <input type="submit" value="Login" class="btn btn-primary form-row">
        </form:form>
    </div>
</div>
</body>
</html>