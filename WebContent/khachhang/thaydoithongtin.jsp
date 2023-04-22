<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Thông tin</title>
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
	String hoVaTen = khachHang.getHoVaTen();
	
	String gioiTinh = khachHang.getGioiTinh();
	
	String ngaySinh = khachHang.getNgaySinh().toString();
	
	String diaChiKhachHang = khachHang.getDiaChi();
	
	String diaChiMuaHang = khachHang.getDiaChiMuaHang();
	
	String diaChiNhanHang = khachHang.getDiaChiNhanHang();
	
	String dienThoai = khachHang.getSoDienThoai();
	
	String email = khachHang.getEmail();
	
	boolean dongYNhanMail = khachHang.isDangKyNhanBangTin();
	
	
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
		<form action="<%= url1 %>/khach-hang" class="form" method="post">
		<input type="hidden" name="hanhDong" value="thay-doi-thong-tin"/>
			<div class="row">
				<div class="col-md-6">
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="hoVaTen" class="form-label">Họ Và Tên</label> <input
							type="text" class="form-control" id="hoVaTen" name="hoVaTen" value="<%=hoVaTen %>">
					</div>
					<div class="mb-3">
						<label for="gioiTinh" class="form-label">Giới tính</label> <select
							class="form-control" id="gioiTinh" name="gioiTinh">
							<option></option>
							<option value="Nam" <%=(gioiTinh.equals("Nam"))?"selected = 'selected'":"" %> >Nam</option>
							<option value="Nữ" <%=(gioiTinh.equals("Nữ"))?"selected = 'selected'":"" %> >Nữ</option>
							<option value="Khác" <%=(gioiTinh.equals("Khác"))?"selected = 'selected'":"" %>>Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="ngaySinh" class="form-label">Ngày Sinh</label> <input
							type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="<%= ngaySinh %>">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email" value="<%= email %>">
					</div>
					<hr />
					<div class="mb-3">
						<input type="checkbox" class="form-check-input" id="dongYNhanMail"
							name="dongYNhanMail" <%= (dongYNhanMail?"checked":"" ) %> > <label for="dongYNhanMail"
							class="form-label">Đồng ý nhận email</label>
					</div>
				</div>
				<div class="col-md-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="diaChiKhachHang" class="form-label">Địa chỉ
							khách hàng</label> <input type="text" class="form-control"
							id="diaChiKhachHang" name="diaChiKhachHang" value="<%= diaChiKhachHang %>">
					</div>
					<div class="mb-3">
						<label for="diaChiMuaHang" class="form-label">Địa chỉ mua
							hàng</label> <input type="text" class="form-control" id="diaChiMuaHang"
							name="diaChiMuaHang" value="<%= diaChiMuaHang %>" >
					</div>
					<div class="mb-3">
						<label for="diaChiNhanHang" class="form-label">Địa chỉ
							nhận hàng</label> <input type="text" class="form-control"
							id="diaChiNhanHang" name="diaChiNhanHang" value="<%= diaChiNhanHang %>">
					</div>
					<div class="mb-3">
						<label for="dienThoai" class="form-label">Điện thoại</label> <input
							type="tel" class="form-control" id="dienThoai" name="dienThoai"
							value="<%= dienThoai %>">
					</div>
					
					
				</div>
				<input class="btn btn-primary form-control" type="submit"
						value="Lưu thông tin" name="submit" id="submit"/>
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