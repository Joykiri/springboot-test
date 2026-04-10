<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>ログイン画面</title>

<style>

/* 전체 중앙 */
body {

    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/* 로그인 박스 */
.login-box {

    width: 420px;
    border: 1px solid #333;
}

/* 제목바 (더 연노랑) */
.login-title {
    background-color: #FFFFE0; /* 연노랑 */
    text-align: center;
    font-weight: bold;
    font-size: 22px;
    padding: 6px;
    border-bottom: 1px solid #333;
}

/* 내부 영역 */
.login-content {
    background-color: #ffffff;
    padding: 40px 60px;
}

/* 입력영역 한줄 */
.form-row {
    display: flex;
    align-items: center;
}

/* 라벨 (会員ID / パスワード) */
.form-label {
    width: 100px;
    font-size: 18px;
}

/* 입력칸 묶음 */
.input-box {
    border: 1px solid #333;
}

/* 입력칸 (붙어있게) */
.input-box input {
    width: 180px;
    height: 32px;
    border: none;
    outline: none;
    padding-left: 5px;
}

/* 입력칸 사이 선 */
.input-divider {
    border-top: 1px solid #333;
}

/* 로그인 버튼 */
.login-button {
    margin-top: 25px;
    width: 280px; /* 입력칸 길이 맞춤 */
    height: 36px;
    background-color: #F0FFFF; /* 하늘색 */
    border: 1px solid #333;
    font-size: 18px;
}

/* 에러 메시지 */
.error-message {
    color: red;
    text-align: center;
    margin-top: 10px;
}

</style>

</head>

<body>

<div class="login-box">

    <div class="login-title">
        会員認証
    </div>

    <div class="login-content">

        <form action="/login" method="post">

            <div class="form-row">

                <div class="form-label">
                    会員ID<br>
                    パスワード
                </div>

                <div class="input-box">

                    <input type="text" name="memberId">

                    <div class="input-divider"></div>

                    <input type="password" name="password">

                </div>

            </div>

            <div style="text-align:center;">

                <input type="submit"
                       value="ログイン"
                       class="login-button">

            </div>

        </form>

        <div class="error-message">
            ${err_msg}
        </div>

    </div>

</div>

</body>

</html>