<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Thay đổi ảnh</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<style type="text/css">
.red {
	color: red;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>

<%
Object obj = session.getAttribute("KhachHang");
		KhachHang khachHang = null;
		if(obj != null){
	khachHang = (KhachHang) obj;
		}
		
		if(khachHang == null){
	 // response.sendRedirect("index.jsp");
%>
	 <h1>Bạn chưa đăng nhập vào hệ thống. Vui lòng quay lại trang chủ!</h1>
	<%
		}else{
			
	 %>
	<div class="container">
<%
	// Hiện thị thông tin lỗi
	String baoLoi = (request.getAttribute("baoLoi") + "");
	baoLoi = baoLoi.equals("null") ? "" : baoLoi;
	
	// Giữ lại thông tin nhập sau khi bắt lỗi
	String duongDanAnh = khachHang.getDuongDanAnh();
	
 %>
 
	<div class="container">
		<div class="text-center">
			<h1>THÔNG TIN TÀI KHOẢN</h1>
		</div>
		<div class="red" id="baoLoi">
			<%=baoLoi %>
		</div>
		<%
		String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
		%>
		<form action="<%= url1 %>/khach-hang-thay-doi-anh" class="form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="hanhDong" value="thay-doi-anh"/>
			<div class="row">
				<div class="col-md-6">
					<h3>Thông tin khách hàng</h3>
					<img src="<%= url1 %>/avatar/<%= duongDanAnh %>" width="200" height="250" alt="Ảnh avatar">
					<div class="mb-3">
						<label for="duongDanAnh" class="form-label">Đường dẫn ảnh</label> <input
							type="file" class="form-control" id="duongDanAnh" name="duongDanAnh">
					</div>
				<input class="btn btn-primary form-control" type="submit"
						value="Lưu thông tin" name="submit" id="submit"/>
			</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		
	</script>
	</div>
	<% } %>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>