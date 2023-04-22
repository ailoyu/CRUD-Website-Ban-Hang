<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<style>
.red {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	
	<div class="container mt-5">
		<div class="text-center"><h1>Đổi mật khẩu</h1></div>
		<%
		Object obj = session.getAttribute("KhachHang");
				KhachHang khachHang = null;
				if (obj != null) {
			khachHang = (KhachHang) obj;
				}

				if (khachHang == null) {
			// response.sendRedirect("index.jsp");
		%>
		<h1>Bạn chưa đăng nhập vào hệ thống. Vui lòng quay lại trang chủ!</h1>
		<%
		} else {
		String baoLoi = request.getAttribute("baoLoi") + "";
		if (baoLoi.equals("null")) {
			baoLoi = "";
		}
		%>
		<span style="color: red"><%=baoLoi%></span>
		<%
		String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
		%>
		<form action="<%= url1 %>/khach-hang" method="post">
		<input type="hidden" name="hanhDong" value="doi-mat-khau"/>
			<div class="mb-3">
				<label for="matKhauHienTai" class="form-label">Mật khẩu hiện
					tại</label> <input type="password" class="form-control" id="matKhauHienTai"
					name="matKhauHienTai">
			</div>
			<div class="mb-3">
				<label for="matKhauMoi" class="form-label">Mật khẩu mới</label> <input
					type="password" class="form-control" id="matKhauMoi"
					name="matKhauMoi" onkeyup="kiemTraMatKhauMoi_HienTai()"> <span
					id="msg" class="red"></span>
			</div>
			<div class="mb-3">
				<label for="matKhauMoiNhapLai" class="form-label">Nhập lại
					mật khẩu mới</label> <input type="password" class="form-control"
					id="matKhauMoiNhapLai" name="matKhauMoiNhapLai"
					onkeyup="kiemTraMatKhauMoi()"><span id="msg1" class="red"></span>
			</div>
			<button type="submit" class="btn btn-primary">Lưu mật khẩu</button>
		</form>
	</div>

	<script type="text/javascript">
		function kiemTraMatKhauMoi_HienTai() {
			matKhauHienTai = document.getElementById("matKhauHienTai").value;
			matKhauMoi = document.getElementById("matKhauMoi").value;
			matKhauMoiNhapLai = document.getElementById("matKhauMoiNhapLai").value;

			if (matKhauHienTai == matKhauMoi) {
				document.getElementById("msg").innerHTML = "(*) Mật khẩu mới phải khác mật khẩu hiện tại";
				return false;
			} else {
				document.getElementById("msg").innerHTML = "";
				return true;
			}
		}

		function kiemTraMatKhauMoi() {
			matKhauMoi = document.getElementById("matKhauMoi").value;
			matKhauMoiNhapLai = document.getElementById("matKhauMoiNhapLai").value;

			if (matKhauMoi != matKhauMoiNhapLai) {
				document.getElementById("msg1").innerHTML = "(*) Mật khẩu nhập lại không khớp";
				return false;
			} else {
				document.getElementById("msg1").innerHTML = "";
				return true;
			}
		}
	</script>

	<%
	}
	%>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>