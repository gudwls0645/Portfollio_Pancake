<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/top.jsp" %>
<style>
	.center {
		width:200px; left:0; right:0; margin-left:auto; margin-right:auto; /* 가로 중앙 정렬 */ 
		height:90px; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; /* 세로 중앙 정렬 */
	}
	
	.int {
    font-size: 15px;
    line-height: 16px;
    position: relative;
    z-index: 9;
    width: 100%;
    height: 16px;
    padding: 7px 0 6px;
    color: #000;
    border: none;
    background: #fff;
    -webkit-appearance: none;
	}
		
	#login {
		width: 200px;
		height: 60px;
		margin-left:auto; margin-right:auto;
		cursor : pointer;
		background-color: #bc5e00;
		color: white;
		text-align: center;
	}
	
	.input {
		width:200px; 
		height:50px;
	}
</style>
<script>
$(function() {
	var msg = "${msg}";
	
	if (msg == "o") {
		location.href = "home.pan";
	} else if (msg == "pw") {
		$("#inputPw").text("비밀번호가 틀렸습니다.");
	} else if (msg == "id") {
		$("#inputId").text("존재하지 않는 아이디입니다.");
	}
	
	console.log(msg);
});
</script>
</head>
<body>
	<%@ include file="include/title.jsp" %>
	<form action="loginPro.pan" method="post">
		<div class="center">
			<div><input type="text" placeholder="아이디" class="input" name="id" required></div>
			<span id="inputId" style="color: red"><br></span>
			<div><input type="password" placeholder="비밀번호" class="input" name="pw" required></div>
			<span id="inputPw" style="color: red"><br></span>
			<input id="login" type="submit" value="로그인"/><br><br>
			<div style="text-align: center;"><a href="joinForm.pan">회원가입</a></div>
		</div>
	</form>
	<table border="1" style="height: 200px;">
	</table>
<%@ include file="include/bottom.jsp"%>