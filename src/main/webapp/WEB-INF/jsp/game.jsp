<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    width: 420px;
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

/* 로그아웃 영역 */
.logout-area {
    width: 100px;
    position: relative; /* ← 기준점 */
}

/* 사용자 이름 (로그아웃 바로 위, 박스 밖) */
.user-name {
    position: absolute;
    bottom: 42px;   /* ← 로그아웃 바로 위 */
    right: 0;
    font-size: 13px;
	white-space: nowrap;
}

/* 로그아웃 버튼 */
.logout-btn {
    width: 100px;
    height: 40px;
    background-color: #9BC3FF;
    border: none;
    border-left: 1px solid #333;
    cursor: pointer;
}

/* 포인트 */
.point-area {
    text-align: right;
    padding: 5px 20px 0 0;
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
}

/* 확인 버튼 */
.confirm-btn {
    margin-left: 15px;
    background-color: #9BC3FF;
    border: 1px solid #333;
    padding: 5px 15px;
    cursor: pointer;
}

/* 테이블 */
.result-table {
    margin-top: 20px;
    border-collapse: collapse;
    width: 100%;
}

.result-table th,
.result-table td {
    border: 1px solid #333;
    padding: 6px;
    text-align: center;
}

.result-table th {
    background-color: #78EFAD;
    font-weight: normal;
}

.message-area {
			    text-align: center;
			    color: red;
			    margin-top: 10px;
			    font-weight: bold;
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

        <!-- 로그아웃 영역 -->
        <div class="logout-area">

            <!-- 이름 (로그아웃 바로 위) -->
            <div class="user-name">
                ${sessionScope.user_name} さん
            </div>

            <!-- 로그아웃 -->
			<form action="/logout" method="get"
			      onsubmit="return confirm('ログアウトしますか？')">
                <button type="submit" class="logout-btn">
                    ログアウト
                </button>
            </form>

        </div>

    </div>

    <!-- 현재 포인트 -->
    <div class="point-area">

        現在のポイント　${point}

    </div>

    <div class="main-area">

        <!-- 숨긴 숫자 -->
        <div class="hidden-number">

            隠れ数字：＊＊＊

        </div>

        <!-- 입력 -->
		<form action="/game" method="post">
        <div class="input-row">

            入力

            <input type="text" class="number-input"
			name="input_num0" maxlength="1">
			
            <input type="text" class="number-input"
			name="input_num1" maxlength="1">
            
			<input type="text" class="number-input"
			name="input_num2" maxlength="1">

            <button type="submit" class="confirm-btn">
                確認
            </button>
			
        </div>
		
		</form>
		

        <!-- 결과 테이블 -->
		<table class="result-table">

		<tr>
		    <th>入力回数</th>
		    <th>入力数字</th>
		    <th>判定結果</th>
		</tr>

		<%
		List<String> inputList =
		    (List<String>) request.getAttribute("inputList");

		List<String> resultList =
		    (List<String>) request.getAttribute("resultList");

		if(inputList == null){
		    inputList = new ArrayList<>();
		}

		if(resultList == null){
		    resultList = new ArrayList<>();
		}

		for(int i=0;i<10;i++){
		%>

		<tr>

		<td><%= i+1 %>回目</td>

		<td>
		<%= (i < inputList.size())
		        ? inputList.get(i)
		        : "" %>
		</td>

		<td>
		<%= (i < resultList.size())
		        ? resultList.get(i)
		        : "" %>
		</td>

		</tr>

		<%
		}
		%>

		</table>

    </div>

</div>
<c:if test="${not empty errMsg}">
<script>
    alert("${errMsg}");
</script>
</c:if>
</body>
</html>