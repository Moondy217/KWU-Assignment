<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 조회</title>
</head>
<body>
    <h2>회원 정보 조회</h2>
    <% 
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try {
        // 데이터베이스 연결 설정
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "scott";
        String password = "tiger";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, username, password);
        
        // 회원 정보를 조회하는 SQL 쿼리 실행
        String sql = "SELECT * FROM custom_01";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        // 조회 결과를 테이블로 출력
    %>
    <table border="1">
        <tr>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>성명</th>
            <th>이메일</th>
            <th>연락처</th>
        </tr>
        <% 
        while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("p_id") %></td>
            <td><%= rs.getString("p_pw") %></td>
            <td><%= rs.getString("c_name") %></td>
            <td><%= rs.getString("c_email") %></td>
            <td><%= rs.getString("c_tel") %></td>
        </tr>
        <% 
        }
        %>
    </table>
    <% 
    } catch (Exception e) {
        out.println("<p>오류 발생: " + e.getMessage() + "</p>");
    } finally {
        // 자원 해제
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            out.println("<p>오류 발생: " + ex.getMessage() + "</p>");
        }
    }
    %>
</body>
</html>
