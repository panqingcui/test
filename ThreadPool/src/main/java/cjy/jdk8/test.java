
/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.jdk8;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2016年6月22日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class test {
   /* public static boolean updatePassword(String username, String password, String newpassword) {
        return connection(conn -> statement(conn, "select id, password from user where username = ?", stmt -> {
            stmt.setString(1, username);
            return resultSet(stmt, rs -> {
                if (rs.next()) {
                    if (rs.getString("password").equals(password)) {
                        long id = rs.getLong("id");
                        return statement(conn, "update user set password = ? where id = ?", stmt2 -> {
                            stmt2.setString(1, newpassword);
                            stmt2.setLong(2, id);
                            return stmt2.executeUpdate() == 1;
                        });
                    }
                }
                return false;
            });
        }));
    }

    Intopass
    链接：https:// www.zhihu.com/question/25654738/answer/31302541
    来源：
    知乎 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public interface ConnectionCallback<T> {
        T doConnection(Connection conn) throws SQLException;
    }

    public interface StatementCallback<T> {
        T doStatement(PreparedStatement stmt) throws SQLException;
    }

    public interface ResultSetCallback<T> {
        T doResultSet(ResultSet rs) throws SQLException;
    }

    public static <T> T connection(ConnectionCallback<T> callback) {
        Connection conn = null;
        T result = null;
        try {
            conn = beginTransaction();
            result = callback.doConnection(conn);
            commitTransaction(conn);
        } catch (SQLException e) {
            rollbackTransaction(conn);
            throw new RuntimeException(e);
        } finally {
            safeClose(conn);
        }
        return result;
    }

    public static <T> T statement(Connection conn, String sql, StatementCallback<T> callback) throws SQLException {
        PreparedStatement stmt = null;
        T result = null;
        try {
            stmt = conn.prepareStatement(sql);
            result = callback.doStatement(stmt);
        } finally {
            safeClose(stmt);
        }
        return result;
    }

    public static <T> T resultSet(PreparedStatement stmt, ResultSetCallback<T> callback) throws SQLException {
        ResultSet rs = null;
        T result = null;
        try {
            rs = stmt.executeQuery();
            result = callback.doResultSet(rs);
        } finally {
            safeClose(rs);
        }
        return result;
    }*/
}
