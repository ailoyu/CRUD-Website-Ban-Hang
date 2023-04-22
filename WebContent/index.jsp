<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Book Store</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
	integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
	crossorigin="anonymous"></script>
</head>
<body>
	
	<!-- Navbar -->
	<!-- Chèn jsp theo kiểu web động (tính toán) -->
	<jsp:include page="header.jsp"/>
	
	<!-- End Navbar -->

	<!-- Page Content -->
	<div class="container">
		<div class="row mt-4">
			<!-- Menu left -->
			<!-- Chèn jsp theo kiểu kiểu web tĩnh -->
			<jsp:include page="left.jsp"></jsp:include>
			<!-- End Menu left -->
			<!--  Slider and Products -->
			<div class="col-lg-9">
				<!-- Slider -->
				<div id="carouselExampleIndicators" class="carousel slide">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="${pageContext.request.contextPath}/img/slider/1.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="${pageContext.request.contextPath}/img/slider/2.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="${pageContext.request.contextPath}/img/slider/3.png" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
				<!-- End Slider -->
				<!-- Products -->
				<div class="row mt-4">
					<div class="col-lg-4 col-md-3 mb-4">
						<div class="card" style="width: 16rem;">
							<img src="${pageContext.request.contextPath}/img/products/1.png" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">Áo Thun</h5>
								<p class="card-text">500.000 VND</p>
								<a href="#" class="btn btn-primary">Mua Hàng</a>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-3 mb-4">
						<div class="card" style="width: 16rem;">
							<img src="${pageContext.request.contextPath}/img/products/2.png" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">Áo Thun</h5>
								<p class="card-text">500.000 VND</p>
								<a href="#" class="btn btn-primary">Mua Hàng</a>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-3 mb-4">
						<div class="card" style="width: 16rem;">
							<img src="${pageContext.request.contextPath}/img/products/3.png" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">Áo Thun</h5>
								<p class="card-text">500.000 VND</p>
								<a href="#" class="btn btn-primary">Mua Hàng</a>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-3 mb-4">
						<div class="card" style="width: 16rem;">
							<img src="${pageContext.request.contextPath}/img/products/2.png" class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">Áo Thun</h5>
								<p class="card-text">500.000 VND</p>
								<a href="#" class="btn btn-primary">Mua Hàng</a>
							</div>
						</div>
					</div>
				</div>
				<!-- End Products -->
			</div>
			<!-- End Slider and Products -->
		</div>
	</div>
	<!-- End Page Content -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- End footer -->
</body>
</html>