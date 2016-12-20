package ers.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ers.beans.*;

public class DataFacade {

	public List<Reimbursement> selectAllReimbs() {
		Connection conn = null;
		List<Reimbursement> list = null;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			list = new ReimbursementDAO(conn).selectAllReimbursements();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Reimbursement> selectReimbsByUser(String Username) {
		Connection conn = null;
		List<Reimbursement> list = null;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			list = new ReimbursementDAO(conn).selectAllReimbursementsFromUser(Username);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean validateUsers(String username, String password) {
		Connection conn = null;
		boolean booly = false;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			booly = new UserDAO(conn).validUser(username, password);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return booly;
	}

	public boolean validateManager(String username) {
		Connection conn = null;
		boolean boo = false;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			boo = new UserDAO(conn).isManager(username);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boo;
	}

	public User getUser(String username) {
		Connection conn = null;
		User user = null;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			user = new UserDAO(conn).getUser(username);
			return user;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.getStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
		return user;
	}

	public void insertReimbursement(double amount, String description, int authorId, int typeId) {
		Connection conn = null;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			conn.setAutoCommit(false);
			new ReimbursementDAO(conn).insertReimbursement(amount, description, authorId, typeId);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.getStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void updateReimbursementStatus(int reimbId, int newStatusNumb) {
		Connection conn = null;
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
			conn.setAutoCommit(false);
			new ReimbursementDAO(conn).updateReimbursementStatus(reimbId, newStatusNumb);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
