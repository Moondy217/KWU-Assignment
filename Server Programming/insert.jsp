<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 등록</title>
</head>
<body>
    <h2>회원 정보 등록</h2>
    <% 
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        // 데이터베이스 연결 설정
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "scott";
        String password = "tiger";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, username, password);
        
        // 입력된 회원 정보를 받아와서 SQL 쿼리 실행
        String sql = "INSERT INTO custom_01 (p_id, p_pw, c_name, c_email, c_tel) VALUES (?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, request.getParameter("p_id"));
        pstmt.setString(2, request.getParameter("p_pw"));
        pstmt.setString(3, request.getParameter("c_name"));
        pstmt.setString(4, request.getParameter("c_email"));
        pstmt.setString(5, request.getParameter("c_tel"));
        
        // SQL 쿼리 실행 후 결과 확인
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            out.println("<p>회원 정보가 성공적으로 등록되었습니다.</p>");
        } else {
            out.println("<p>회원 정보 등록에 실패했습니다.</p>");
        }
    } catch (Exception e) {
        out.println("<p>오류 발생: " + e.getMessage() + "</p>");
    } finally {
        // 자원 해제
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            out.println("<p>오류 발생: " + ex.getMessage() + "</p>");
        }
    }
    %>
</body>
</html>
