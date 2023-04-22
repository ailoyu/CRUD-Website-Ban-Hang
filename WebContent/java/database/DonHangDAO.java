package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DonHang;
import model.KhachHang;
import util.JDBCUtil;

public class DonHangDAO implements DAOInterface<DonHang>{
	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM donhang";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String madonhang = rs.getString(1);
				String makhachhang = rs.getString(2);
				String diachimuahang = rs.getString(3);
				String diachinhanhang = rs.getString(4);
				String hinhthucthanhtoan = rs.getString(5);
				String trangthaithanhtoan = rs.getString(6);
				double sotienthanhtoan = rs.getDouble(7);
				double sotienconthieu = rs.getDouble(8);
				Date ngaydathang = rs.getDate(9);
				Date ngaygiaohang = rs.getDate(10);

				KhachHang khachHang = (new KhachHangDAO().selectById(new KhachHang(makhachhang, null, null, null, null, null, null, null, null, null, null, false)));
				DonHang dh = new DonHang(madonhang, khachHang, diachimuahang, diachinhanhang, hinhthucthanhtoan, trangthaithanhtoan, sotienthanhtoan, sotienconthieu, ngaydathang, ngaygiaohang);	
				
				ketQua.add(dh);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DonHang selectById(DonHang t) {
		DonHang ketQua = null;
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM donhang WHERE madonhang = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String madonhang = rs.getString(1);
				String makhachhang = rs.getString(2);
				String diachimuahang = rs.getString(3);
				String diachinhanhang = rs.getString(4);
				String hinhthucthanhtoan = rs.getString(5);
				String trangthaithanhtoan = rs.getString(6);
				double sotienthanhtoan = rs.getDouble(7);
				double sotienconthieu = rs.getDouble(8);
				Date ngaydathang = rs.getDate(9);
				Date ngaygiaohang = rs.getDate(10);

				KhachHang khachHang = (new KhachHangDAO().selectById(new KhachHang(makhachhang, null, null, null, null, null, null, null, null, null, null, false)));
				DonHang dh = new DonHang(madonhang, khachHang, diachimuahang, diachinhanhang, hinhthucthanhtoan, trangthaithanhtoan, sotienthanhtoan, sotienconthieu, ngaydathang, ngaygiaohang);
				
				ketQua = dh;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO donhang(madonhang, makhachhang, diachimuahang, diachinhanhang, hinhthucthanhtoan, trangthaithanhtoan, sotienthanhtoan, sotienconthieu, ngaydathang, ngaygiaohang)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			st.setString(2, t.getKhachHang().getMaKhachHang());
			st.setString(3, t.getDiaChiMuaHang());
			st.setString(4, t.getDiaChiNhanHang());
			st.setString(5, t.getHinhThucThanhToan());
			st.setString(6, t.getTrangThaiThanhToan());
			st.setDouble(7, t.getSoTienDaThanhToan());
			st.setDouble(8, t.getSoTienConThieu());
			st.setDate(9, t.getNgayDatHang());
			st.setDate(10, t.getNgayGiaoHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<DonHang> arr) {
		int kq = 0;
		for (DonHang donHang : arr) {
			kq += this.insert(donHang);
		}
		return kq;
	}

	@Override
	public int delete(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "DELETE FROM donhang WHERE madonhang = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<DonHang> arr) {
		int kq = 0;
		for (DonHang t : arr) {
			kq += this.delete(t);
		}
		return kq;
	}

	@Override
	public int update(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();

		String sql = "UPDATE donhang" + " SET " + "makhachhang=?" + ", diachimuahang=?" + ",diachinhanhang=?"
				+ ",hinhthucthanhtoan=?" + ",trangthaithanhtoan=?" + ",sotienthanhtoan=?" + ",sotienconthieu=?" + ",ngaydathang=?"
				+ ",ngaygiaohang=?" + " WHERE madonhang=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getKhachHang().getMaKhachHang());
			st.setString(2, t.getDiaChiMuaHang());
			st.setString(3, t.getDiaChiNhanHang());
			st.setString(4, t.getHinhThucThanhToan());
			st.setString(5, t.getTrangThaiThanhToan());
			st.setDouble(6, t.getSoTienDaThanhToan());
			st.setDouble(7, t.getSoTienConThieu());
			st.setDate(8, t.getNgayDatHang());
			st.setDate(9, t.getNgayGiaoHang());
			st.setString(10, t.getMaDonHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}
