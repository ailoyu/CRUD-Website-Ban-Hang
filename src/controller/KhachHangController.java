package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.KhachHangDAO;
import model.KhachHang;
import util.Email;
import util.MaHoa;
import util.MaXacThuc;

/**
 * Servlet implementation class KhachHang
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		String hanhDong = request.getParameter("hanhDong");
		if(hanhDong.equals("dang-nhap")) {
			dangNhap(request, response);
		}else if(hanhDong.equals("dang-xuat")) {
			dangXuat(request, response);
		}else if(hanhDong.equals("dang-ky")) {
			dangKy(request, response);
		}else if(hanhDong.equals("doi-mat-khau")) {
			doiMatKhau(request, response);
		}else if(hanhDong.equals("thay-doi-thong-tin")) {
			thayDoiThongTin(request, response);
		}else if(hanhDong.equals("xac-thuc")) {
			xacThuc(request, response);
		}else if(hanhDong.equals("thay-doi-anh")) {
			thayDoiAnh(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			matKhau = MaHoa.toSHA1(matKhau);
			
			
			request.setAttribute("tendangnhap", tenDangNhap);
			
			KhachHang kh = new KhachHang();
			kh.setTenDangNhap(tenDangNhap);
			kh.setMatKhau(matKhau);
			
			KhachHangDAO khd = new KhachHangDAO();
			KhachHang khachHang = khd.selectByUsernameAndPassword(kh);
			
			String url = "";
			// Khách hàng đã tồn tại trong db & đã xác thực thành công
			if(khachHang != null) {
				if(khachHang.isTrangThaiXacThuc()) {
					HttpSession session = request.getSession();
					session.setAttribute("KhachHang", khachHang);
					url = "/index.jsp";
				}else {
					request.setAttribute("baoLoi", "(*) Tài khoản này chưa xác thực");
					url = "/khachhang/dangnhap.jsp";
				}
			}else {
				request.setAttribute("baoLoi", "(*) Tên đăng nhập hoặc mật khẩu không đúng!");
				url = "/khachhang/dangnhap.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			HttpSession session = request.getSession();
			// Hủy bỏ session sau khi đăng xuất
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dangKy(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");
			
			
			// Giữ lại thông tin nhập sau khi bắt lỗi
			request.setAttribute("tenDangNhap", tenDangNhap);
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			
			if(khachHangDAO.selectByTenDangNhap(tenDangNhap) != null) {
				KhachHang khachHang = khachHangDAO.selectByTenDangNhap(tenDangNhap);
				if(!khachHang.isTrangThaiXacThuc()) {
					khachHangDAO.delete(khachHang);
					baoLoi = "Đã xóa tài khoản chưa xác thực";
				}else {
					baoLoi = "(*) Tên Đăng Nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
				}
				
				
			}
			
			if(!matKhau.equals(matKhauNhapLai)) {
				baoLoi = "(*) Mật Khẩu không khớp.<br/>";
			}else {
				matKhau = MaHoa.toSHA1(matKhau);
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			String url = "";
			if(baoLoi.length() > 0) {
				url = "/khachhang/dangky.jsp";
			}
			if(baoLoi.length() == 0 || baoLoi.equals("Đã xóa tài khoản chưa xác thực")) {
				Random rd = new Random();
				String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail != null);
				if(khachHangDAO.insert(kh) > 0) {
					// Lấy dãy số xác thực
					String soNgauNhien = MaXacThuc.getSoNgauNhien();
					
					// Set thời gian hiệu lực
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MINUTE, 2); // thời gian hiệu lực: 2 phút
					Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis());
					
					// Trạng thái xác thực (mặc định ban đầu = false)
					boolean trangThaiXacThuc = false;
					
					kh.setMaXacThuc(soNgauNhien);
					kh.setThoiGianHieuLucCuaMaXacThuc(thoiGianHieuLucXacThuc);
					kh.setTrangThaiXacThuc(trangThaiXacThuc);
					
					if(khachHangDAO.updateVerifyInformation(kh) > 0) {
						// Gửi email cho khách hàng
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
						LocalDateTime now = LocalDateTime.now();  
						
						String link = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath()+"/khach-hang?hanhDong=xac-thuc&maKhachHang="+kh.getMaKhachHang()+"&maXacThuc="+kh.getMaXacThuc();
						
						Email.sendEmail(kh.getEmail(), "Xác thực tài khoản để đăng nhập | " + dtf.format(now), getNoiDungEmail(kh, link));
					}
					
				}
						
				url = "/khachhang/thanhcong.jsp";
			}
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			PrintWriter out = response.getWriter();
			
			String matKhauHienTai = request.getParameter("matKhauHienTai");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");
			
			String matKhauHienTai_Sha1 = MaHoa.toSHA1(matKhauHienTai);
			String url = "/khachhang/doimatkhau.jsp";
			String baoLoi = "";
			
			
			// Kiểm tra mật khẩu cũ có giống hay không?
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("KhachHang");
			KhachHang khachHang = null;
			if(obj != null) {
				khachHang = (KhachHang) obj;
			}
			
			if(khachHang == null) {
				baoLoi = "(*) Bạn chưa đăng nhập vào hệ thống";
			}else {
				if(!matKhauHienTai_Sha1.equals(khachHang.getMatKhau())) {
					baoLoi = "(*) Mật khẩu hiện tại không chính xác";
				}else {
					if(!matKhauMoi.equals(matKhauMoiNhapLai)) {
						baoLoi = "(*) Mật khẩu nhập lại không khớp";
					}else {
						String matKhauMoi_Sha1 = MaHoa.toSHA1(matKhauMoi);
						khachHang.setMatKhau(matKhauMoi_Sha1);
						
						KhachHangDAO khd = new KhachHangDAO();
						if(matKhauHienTai_Sha1.equals(matKhauMoi_Sha1)) {
							baoLoi = "(*) Mật khẩu mới phải khác mật khẩu hiện tại";
						}else {
							if(khd.changePassword(khachHang)) {
								baoLoi = "Mật khẩu đã thay đổi thành công";
								url = "/index.jsp";
							}else {
								baoLoi = "(*) Quá trình thay đổi mật khẩu không thành công";
							}
						}
					}
				}
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void thayDoiThongTin(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");
			
			
			// Giữ lại thông tin nhập sau khi bắt lỗi
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			
			request.setAttribute("baoLoi", baoLoi);
			
			String url = "";
			if(baoLoi.length() > 0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Object obj = request.getSession().getAttribute("KhachHang");
				KhachHang khachHang = null;
				if(obj != null){
					khachHang = (KhachHang) obj;
				}
				
				if(khachHang != null){
					String maKhachHang = khachHang.getMaKhachHang();
					KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail != null);
					khachHangDAO.updateInfo(kh);
					KhachHang kh2 = khachHangDAO.selectById(kh);
					request.getSession().setAttribute("KhachHang", kh2);
					url = "/khachhang/thanhcong.jsp";
				}else {
					url = "/index.jsp";
				}
				
			}
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void thayDoiAnh(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	
	private void xacThuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String maKhachHang = request.getParameter("maKhachHang");
			String maXacThuc = request.getParameter("maXacThuc");
			

			String url = "";
			String msg = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			
			
			KhachHang kh = new KhachHang();
			kh.setMaKhachHang(maKhachHang);
			KhachHang khachHang = khachHangDAO.selectById(kh);
			
			
			if(khachHang != null){
				// Kiểm tra mã xác thực nhập vào có giống trong mã xac thuc trong dtbase kh?
				if(khachHang.getMaXacThuc().equals(maXacThuc)) {
					// Kiểm tra xem mã xác thực còn hiệu lực hay không?
					java.sql.Date now = new Date(System.currentTimeMillis());
					if(now.before(khachHang.getThoiGianHieuLucCuaMaXacThuc())) {
						khachHang.setTrangThaiXacThuc(true);
						khachHangDAO.updateVerifyInformation(khachHang);
						msg = "Xác thực thành công";
					}else {
						msg = "Hết thời gian xác thực, vui lòng đăng ký lại!";
						khachHangDAO.delete(khachHang);
					}
				}else {
					msg = "Xác thực không thành công";
				}
			}else {
				msg = "Tài khoản không tồn tại";
			}
			
			request.setAttribute("baoLoi", msg);
			url = "/khachhang/thongbao.jsp";
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static String getNoiDungEmail(KhachHang kh, String link) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 2); // thời gian hiệu lực: 2 phút
		Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis());
		sdf.format(thoiGianHieuLucXacThuc);
		
		String noiDung = "<table style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "<tbody>\r\n"
				+ "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\r\n"
				+ "<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">Xin ch&agrave;o bạn <strong>"+ kh.getHoVaTen() +"</strong></td>\r\n"
				+ "</tr>\r\n"
				+ "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\r\n"
				+ "<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\r\n"
				+ "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập mã xác thực này <strong><h1>"+ kh.getMaXacThuc() +"</h1></strong></p>\r\n"
				+ "<p>hoặc click trực tiếp v&agrave;o n&uacute;t <strong>\"X&aacute;c thực email\"</strong> ở b&ecirc;n dưới:</p>\r\n"
				+ "</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\r\n"
				+ "<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\"><a style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #fff; text-decoration: none; line-height: 2em; font-weight: bold; text-align: center; display: inline-block; border-radius: 5px; text-transform: capitalize; background-color: #f06292; margin: 0; border-color: #f06292; border-style: solid; border-width: 8px 16px;\" href=\""+link+"\">X&aacute;c thực email</a></td>\r\n"
				+ "</tr>\r\n"
				+ "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\r\n"
				+ "<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\r\n"
				+ "<h2>Bạn có 2 phút để xác thực tài khoản này!</h2>"
				+ "<h2>Thời gian hiệu lực tới "+ sdf.format(thoiGianHieuLucXacThuc) +"</h2>"
				+ "<p>Đ&acirc;y l&agrave; email tự động vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
				+ "<p>Tr&acirc;n trọng cảm ơn,</p>\r\n"
				+ "<strong>Twinkle (Chan Quang Dev)</strong>\r\n"
				+ "<p>Support Team</p>\r\n"
				+ "</td>\r\n"
				+ "</tr>\r\n"
				+ "<tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\r\n"
				+ "<td style=\"text-align: center; font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0;\" valign=\"top\">&copy; 2018 Twinkle</td>\r\n"
				+ "</tr>\r\n"
				+ "</tbody>\r\n"
				+ "</table>";
		return noiDung;
	}

}
