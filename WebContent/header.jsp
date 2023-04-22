<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=url%>/index.jsp"> <img
			src="https://titv.vn/wp-content/uploads/2022/08/Screenshot-2023-01-29-230321.png"
			alt="Bootstrap" height="24">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#">Trang Chủ</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Combo
						Giảm Giá</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Thể Loại </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="#">Quần Jean</a></li>
						<li><a class="dropdown-item" href="#">Áo Thun</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">Áo Sơ Mi</a></li>
					</ul></li>
				<li class="nav-item"><a class="nav-link disabled">Hết Hàng</a>
				</li>
			</ul>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search"
					placeholder="Nội Dung Tìm Kiếm" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Tìm</button>
				<%
				Object obj = session.getAttribute("KhachHang");
						KhachHang khachHang = null;
						if (obj != null)
							khachHang = (KhachHang) obj;

						if (khachHang == null) {
				%>
				<a class="btn btn-primary"
					style="white-space: nowrap; margin-left: 5px;"
					href="<%= url %>/khachhang/dangnhap.jsp">Đăng Nhập</a>
				<%
				} else {
				%>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Xin chào, <b
							style="display: inline;"><%=khachHang.getTenDangNhap()%></b>
					</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
							<li><a class="dropdown-item" href="#">Thông báo</a></li>
							<li><a class="dropdown-item"
								href="<%=url%>/khachhang/thaydoianh.jsp">Thay đổi ảnh đại diện</a></li>
							<li><a class="dropdown-item"
								href="<%=url%>/khachhang/thaydoithongtin.jsp">Thay đổi
									thông tin</a></li>
							<li><a class="dropdown-item"
								href="<%=url%>/khachhang/doimatkhau.jsp">Đổi mật khẩu</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="<%=url%>/khach-hang?hanhDong=dang-xuat">Đăng xuất</a></li>
						</ul></li>
				</ul>
				<%
				}
				%>
			</form>
		</div>
	</div>
</nav>
<!-- End Navbar -->