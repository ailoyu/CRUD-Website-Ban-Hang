package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class MaHoa {
	// Các thuật toán Mã hóa md5, md2, sha-1, SHA-256, SHA-384, SHA-512
	
	/* PHẢI MÃ HÓA MẬT KHẨU TRƯỚC KHI ĐĂNG KÝ VÀ ĐĂNG NHẬP */
	
	public static String toSHA1(String password) {
		// Làm cho mật khẩu phức tạp (THÊM MẮM, THÊM MUỐI)
		String salt = "ascvhasujcias;as12@njasnfckajk*asdmkas#%^Q#$$";
		String result = null;
		
		// lấy password + thêm đoạn mã phức tạp salt (THÊM MẮM, THÊM MUỐI)
		password = password + salt;
		try {
			// Lấy mỗi byte ký tự trong password (truyền bảng mã UTF-8)
			byte[] dataByte = password.getBytes("UTF-8"); 
			// Sử Dụng thuật toán mã hóa "SHA-1"
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// Bắt đầu mã hóa
			result = Base64.encodeBase64String(md.digest(dataByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(toSHA1("chanquang202"));
	}
}
