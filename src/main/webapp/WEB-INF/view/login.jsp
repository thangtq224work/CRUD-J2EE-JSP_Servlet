<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="card p-0"  style="width: 50rem;">
                <div class="card-header m-0">
                    <div class="text-primary fw-bold">Hệ thống đăng nhập</div>
                </div>
                <div class="card-body">
                    <p class="text-danger">${mess }</p>
                    <form action="${pageContext.request.contextPath }/login" method="post">
                        <div class="row mt-3">
                            <div class="col col-md-3">
                                <label class="form-label" for="username">Username : </label>
                            </div>
                            <div class="col col-md-9">
                                <input type="text" id="username" name="user" class="form-control">
                            </div>
                        </div>
                        <div class="row my-3">
                            <div class="col col-md-3">
                                <label class="form-label" for="password">Password : </label>
                            </div>
                            <div class="col col-md-9">
                                <input type="password" id="password" name="pass" class="form-control">
                            </div>
                        </div>
                        <input type="submit" class="btn btn-outline-primary mt-2" value="Login">
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>