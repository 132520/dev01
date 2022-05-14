<%--
  Created by IntelliJ IDEA.
  User: jxd13
  Date: 2022/4/5
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="login">
        <table>
            <tr>
                <td>账号</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="reset" value="重置" > &nbsp; &nbsp;&nbsp;
                    <input type="submit" value="登录">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
