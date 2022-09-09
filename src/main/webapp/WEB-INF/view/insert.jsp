<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert new tu lanh</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
	<%-- <p>${ error }</p> --%>
	<core:if test="${error != null}">
			<script>
				/*     window.addEventListener("load",function(){
				 alert("${loginError}");
				 }) */
				alert("${error}");
			</script>
		</core:if>
	<form action="${pageContext.request.contextPath}/tu-lanh/insert" method="post">
		<div class="row">
			<div class="col">
				<label for="id" class="form-label">ID</label> <input id="id"
					name="id" class="form-control" readonly="readonly">
			</div>
			<div class="col">
				<label for="brand" class="form-label">Nhãn hiệu</label> <input
					id="brand" name="brand" class="form-control">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="color" class="form-label">Màu</label> <input id="color"
					name="color" class="form-control">
			</div>
			<div class="col">
				<label for="weight" class="form-label">Trọng lượng</label> <input
					id="weight" name="weight" class="form-control" type="text">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="capacity" class="form-label">Dung tích</label> <input
					id="capacity" name="capacity" class="form-control" type="number" min="1" max="400">
			</div>
			<div class="col">
				<label for="numOfWing" class="form-label">Số cánh</label> <input
					id="numOfWing" name="numOfWing" class="form-control" type="number" min="1" max="10">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="quantity" class="form-label">Số lượng</label> <input
					id="quantity" name="quantity" class="form-control" type="number" min="1" max="1000">
			</div>
			<div class="col">
				<label for="price" class="form-label">Giá bán</label> <input
					id="price" name="price" class="form-control">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="isDeleted" class="form-label">Trạng thái</label><br />
				<input id="isDeleted" name="isDeleted" class="form-check-input"
					type="checkbox"> <label class="form-check-label"
					for="isDeleted"> còn hàng ?</label>
			</div>
			<!-- <div class="col">
				<label for="price" class="form-label">Giá bán</label> <input
					id="price" name="price" class="form-control">
			</div> -->
		</div>
		<div class="row">
			<div class="col">
				<label for="name" class="form-label">Tên</label> <input id="name"
					name="name" class="form-control">
			</div>
			<div class="col">
				<label for="description" class="form-label">Mô tả</label> <input
					id="description" name="description" class="form-control">
			</div>
		</div>
		<!-- <div class="row">
			<div class="col">
				<label for="createUser" class="form-label">Người tạo</label> <input
					id="createUser" name="createUser" class="form-control" readonly="readonly">
			</div>
			<div class="col">
				<label for="lastModifieldUser" class="form-label">Người
					thanh đổi gần nhất</label> <input id="lastModifieldUser"
					name="lastModifieldUser" class="form-control" readonly="readonly">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="createDate" class="form-label">Ngày tạo</label> <input
					id="createDate" name="createDate" class="form-control" type="date" readonly="readonly">
			</div>
			<div class="col">
				<label for="lastModifieldDate" class="form-label">Ngày thanh
					đổi gần nhất</label> <input id="lastModifieldDate" name="lastModifieldDate"
					class="form-control" type="date" readonly="readonly">
			</div>
		</div> -->
		<div>
			<input type="submit" class="btn btn-primary mt-2">
		</div>
	</form>
	</div>
</body>
</html>