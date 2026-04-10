<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>数当てゲーム</title>

<style>

/* 전체 흰 배경 */
body {
    margin: 0;
    background-color: white;
    font-family: Arial, sans-serif;
}

/* 중앙 큰 네모 */
.container {
    width: 420px;   /* ← 가로 조금 넓힘 (② 해결) */
    margin: 50px auto;
    border: 1px solid #333;
    background-color: #FFFFFF;
}

/* 상단 바 */
.title-bar {
    display: flex;
    height: 40px;
    border-bottom: 1px solid #333;
}

/* 타이틀 */
.title-text {

    flex: 1;
    background-color: #FFF064;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    border-right: 1px solid #333;
}

/* 로그아웃 */
.logout-btn {
    width: 100px;
    background-color: #9BC3FF;
    border: none;
    border-left: 1px solid #333;
    cursor: pointer;
}

/* 포인트 (① 위로 올림) */
.point-area {
    text-align: right;
    padding: 5px 20px 0 0;  /* 위쪽 여백 줄임 */
    font-size: 16px;
}

/* 본문 */
.main-area {
    padding: 20px 40px;
}

/* 숨긴 숫자 */
.hidden-number {
    margin-top: 10px;
}

/* 입력줄 */
.input-row {
    margin-top: 10px;
    display: flex;
    align-items: center;
}

/* 숫자 입력칸 */
.number-input {
    width: 35px;
    height: 35px;
    text-align: center;
    margin-right: 0; /* 네모끼리는 붙음 */
}

/* 확인 버튼 */
.confirm-btn {
    margin-left: 15px;
    background-color: #9BC3FF;
    border: 1px solid #333;
    padding: 5px 15px;
    cursor: pointer;
}

/* 에러 */
.error-message {
    color: red;
    margin-top: 10px;
    display: none; /* 에러 있을 때만 표시 */
}

/* 테이블 */
.result-table {
    margin-top: 20px;
    border-collapse: collapse;
    width: 100%;
}

/* 셀 */
.result-table th,
.result-table td {
    border: 1px solid #333;
    padding: 6px;
    text-align: center;
}

/* 헤더 */
.result-table th {
    background-color: #78EFAD;
	font-weight: normal;
}

</style>

</head>

<body>

<div class="container">

    <!-- 상단 -->
    <div class="title-bar">

        <div class="title-text">
            数当てゲーム
        </div>

        <button class="logout-btn">
            ログアウト
        </button>

    </div>

    <!-- 현재 포인트 -->
    <div class="point-area">

        現在のポイント　0

    </div>

    <div class="main-area">

        <!-- 숨긴 숫자 -->
        <div class="hidden-number">

            隠れ数字：＊＊＊

        </div>

        <!-- 입력 -->
        <div class="input-row">

            入力

            <input type="text" class="number-input">
            <input type="text" class="number-input">
            <input type="text" class="number-input">

            <button class="confirm-btn">
                確認
            </button>

        </div>

        <!-- 에러 -->
        <div class="error-message">

            エラーメッセージ出力

        </div>

        <!-- 결과 테이블 -->
        <table class="result-table">

            <tr>
                <th>入力回数</th>
                <th>入力数字</th>
                <th>判定結果</th>
            </tr>

            <% for(int i=1;i<=10;i++){ %>

            <tr>

                <td><%= i %>回目</td>
                <td></td>
                <td></td>
            </tr>
            <% } %>
        </table>
    </div>

</div>

</body>
</html>