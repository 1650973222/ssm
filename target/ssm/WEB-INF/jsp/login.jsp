<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>


    <script type="application/javascript">
        $(function () {
            $("#btn_login").click(function () {
                var code = $("#user_code").val();
                if (code == "") {
                    alert("账户不能为空");
                    return false;
                }
                var password = $("#password").val();
                if (password == "") {
                    alert("密码不能为空");
                    return false;
                }

                $.ajax({
                    type: "post",
                    url: "dologin",
                    data: "user_code=" + code + "&password=" + password,
                    dataType: "json",
                    success: function (result) {
                        if (result.msg == "pwderror") {
                            $('#error').html(result.errorMsg);
                            alert("密码错误");
                        } else if (result.msg == "yhmerror") {
                            $('#error').html(result.errorMsg);
                            alert("用户名不正确");
                        } else {
                            $('#error').html(result.error);
                        }
                    }
                });
            });
        });
    </script>

</head>
<body>
<div class="container" align="center">
    <form class="form-inline" action="dologin" method="post">
        <h2 class="form-signin-heading">图书借阅系统</h2>
        <div class="form-group">
            <table class="table">
                <tr>
                    <td style="vertical-align: middle;"><label for="user_code">登录账号：</label></td>
                    <td>
                        <input type="text" required
                               class="form-control" id="user_code" name="user_code"/>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align: middle;"><label for="password">登录密码：</label></td>
                    <td>
                        <input type="password" required
                               class="form-control" id="password" name="password"/>
                        <span id="error" name="errorMsg" type="text"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="button" id="btn_login" class="btn btn-primary">登录</button>
                        <button type="button" id="btn_register" class="btn btn-primary">注册用户</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>