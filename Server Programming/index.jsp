<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 입력</title>
</head>
<body>
    <div class="container">
        <h2>회원정보 입력</h2>
        <form action="insert.jsp" method="post">
            <table border="1">
                <tr>
                    <td><label for="p_id">아이디</label></td>
                    <td><input type="text" id="p_id" name="p_id" required></td>
                </tr>
                <tr>
                    <td><label for="p_pw">비밀번호</label></td>
                    <td><input type="password" id="p_pw" name="p_pw" required></td>
                </tr>
                <tr>
                    <td><label for="c_name">성명</label></td>
                    <td><input type="text" id="c_name" name="c_name" required></td>
                </tr>
                <tr>
                    <td><label for="c_email">이메일</label></td>
                    <td><input type="email" id="c_email" name="c_email" required></td>
                </tr>
                <tr>
                    <td><label for="c_tel">연락처</label></td>
                    <td><input type="tel" id="c_tel" name="c_tel" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="form-buttons">
                            <input type="submit" value="등록">
                            <input type="button" value="조회" onclick="location.href='view.jsp'">
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
