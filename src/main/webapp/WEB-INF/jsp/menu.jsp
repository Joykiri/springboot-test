<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>メニュー画面</title>

<style>

/* 전체 */
body {

    margin: 0;
    font-family: Arial, sans-serif;
}

/* 상단 제목바 */
.title-bar {

    height: 50px;
    background-color: #6495ED;

    color: white;

    display: flex;
    align-items: center;

    padding-left: 15px;

    font-size: 18px;
    font-weight: bold;
}

/* 본문 프레임 */
.main-frame {

    height: calc(100vh - 50px);

    border-top: 1px solid #333;

    padding: 20px;
}

/* 메뉴 버튼 */
.menu-btn {

    width: 200px;

    height: 50px;

    font-size: 16px;

    background-color: #B0C4DE;

    border: 1px solid #333;

    cursor: pointer;
}

</style>

</head>

<body>

<!-- 제목바 -->
<div class="title-bar">

    システムメニュー

</div>

<!-- 본문 프레임 -->
<div class="main-frame">

    <button onclick="location.href='/game'">

        数当てゲーム

    </button>

</div>

<!-- 게임 화면 -->
<div class="menu-area">

    <button onclick="location.href='/game'">
        数当てゲーム
    </button>

</div>

</body>

</html>