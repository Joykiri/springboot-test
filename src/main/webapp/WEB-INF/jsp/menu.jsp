<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>メニュー画面</title>

<style>

/* 전체 */
body {

    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #ffffff;
}

/* 전체 중앙 정렬 */
.wrapper {
    width: 520px;   /* ★ 게임화면과 동일 크기 */
    margin: 80px auto;
	border: 1px solid #333;
}

/* 상단 제목바 */
.title-bar {
    height: 40px;
    background-color: #FFF064;
    color: black;
    display: flex;
    justify-content: center;  /* ★ 가운데 정렬 */
    align-items: center;
    font-size: 18px;
    border-bottom: 1px solid #333;
}

/* 메뉴 영역 */
.menu-area {

    padding: 50px 0;
    text-align: center;
}

/* 버튼 */
.menu-btn {
    width: 200px;
    height: 50px;
    font-size: 16px;
    background-color: #B0C4DE;
    border: 1px solid #333;
    cursor: pointer;
    margin: 10px 0;
}

.menu-btn:hover {
    background-color: #91B9F5;
}

</style>

</head>

<body>

<div class="wrapper">

    <!-- 제목 -->
    <div class="title-bar">
        システムメニュー
    </div>
	
	<!-- 이름 (박스 밖 오른쪽 위) -->
	<div style="position:absolute; top:-25px; right:0;">
	    ${sessionScope.user_name} さん
	</div>
    <!-- 버튼 영역 -->
    <div class="menu-area">

        <!-- 게임 이동 -->
        <a href="/game">

            <button class="menu-btn">
                数当てゲーム
            </button>

        </a>

        <br>

        <!-- 로그아웃 -->
        <a href="/login">

            <button class="menu-btn">
                ログアウト
            </button>

        </a>

    </div>

</div>

</body>

</html>