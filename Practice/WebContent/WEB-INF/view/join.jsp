<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/top.jsp" %>
<style>
	.center {
		width:200px; left:0; right:0; margin-left:auto; margin-right:auto; /* 가로 중앙 정렬 */ 
		height:90px; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; /* 세로 중앙 정렬 */
	}
	
	.center2 {
		width:300px; left:0; right:0; margin-left:auto; margin-right:auto; /* 가로 중앙 정렬 */ 
		height:150px; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; /* 세로 중앙 정렬 */
	}
	
	.margin {
		margin-left:auto; margin-right:auto;
		margin-top:auto; margin-bottom:auto;
	}
	
	.input {
		width: 300px;
		height: 50px;
	}
	
	.table {
		width: 100px;
		height: 50px;
		
		
	}
	
	.tabletext {
		text-align: center;
		
	}
	
	#join {
		width: 250px;
		height: 60px;
		margin-left:auto;
		margin-right:auto;
		cursor : pointer;
		background-color: #bc5e00;
		color: white;
	}
</style>
<script>
$(function() {
	var msg = "${msg}";
	if (msg == "x") {
		$("#joinCheck").text("가입 실패");
	} else if (msg == "o") {
		location.href = "home.pan";
	}
});
</script>

</head>
<body>
	<%@ include file="include/title.jsp" %>
	<form action="joinPro.pan" method="post">
		<div class="center2">
			<table>
				<tr class="table">
					<th class="tabletext">아이디</th>
					<td><input type="text" name="id" required/></td>
				</tr>
				<tr class="table">
					<th class="tabletext"><b>비밀번호</b></th>
					<td><input type="password" name="pw" required/></td>
				</tr>
				<tr class="table">
					<th class="tabletext">비밀번호 확인</th>
					<td><input type="password" name="pw2" required/></td>
				</tr>
<!-- 				<tr class="table"> -->
<!-- 					<th class="tabletext">닉네임</th> -->
<!-- 					<td><input type="text" name="name" required/></td> -->
<!-- 				</tr> -->
				<tr class="table">
					<th class="tabletext">이메일</th>
					<td><input type="email" name="email" required/></td>
				</tr>
				<tr>
					<th style="text-align: center; color: red;" colspan="2"><div id="joinCheck"><br></div></th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;"><input  id="join" type="submit" value="가입하기"/></th>
				</tr>
			</table>
		</div>
	</form>
	<table border="1" style="height: 200px;">
	</table>
<%@ include file="include/bottom.jsp"%>