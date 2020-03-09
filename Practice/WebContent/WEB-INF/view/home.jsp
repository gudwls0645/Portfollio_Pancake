<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="include/top.jsp"%>
<style>
	.login {
	 	height: auto; width: 300px; 
	 	border:1px solid #bc5e00;
		text-align: center;
	}
	
	#loginlink {
		width: 150px;
		height: 25px;
		margin-left:auto; margin-right:auto;
		cursor : pointer;
		background-color: #bc5e00;
		color: white;
	}
	
	.container {
		width: 1000px;
	}
</style>
			
<!--  -->

<script>
$(function() {
	
	if (${not empty user_id}) {
// 		console.log("테스트");
		getPoint();
	}
	
	
	function getPoint() {
		var url = "getUserPoint.ajax";
		var sData = {
				
		} 
		
		$.get(url, sData, function(rData) {
			var a = rData.trim();
			$("#user_point").text(a);
		});
		
	}
	
	$('#myCarousel').carousel({ interval: 3000 });
	
	$("#loginlink").click(function() {
		location.href = "loginForm.pan"
	});
	
	
});
</script>

<!--  -->

<%@ include file="include/title.jsp" %>

<!--  -->
	
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-1">
<!-- 		여백 -->
		
		</div>
		<div class="col-md-7">
<!-- 		슬라이드 이미지 -->
		
			<div class="container">
			  <div id="myCarousel" class="carousel slide" data-ride="carousel">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item active">
			        <img src="images/1.jpg" alt="Los Angeles" style="width:100%;">
			      </div>
			
			      <div class="item">
			        <img src="images/2.jpg" alt="Chicago" style="width:100%;">
			      </div>
			    
			      <div class="item">
			        <img src="images/3.jpg" alt="New york" style="width:100%;">
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
			</div>
		</div>
		<div class="col-md-4">
<!-- 		우측 로그인, 안내문 -->
			<div class="row">
				<div class="col-md-12">
<!-- 				로그인 부분 -->
					<c:choose>
						<c:when test="${not empty user_id}">
							<div class="login">
								<br>
								<span style="color: gray;">Pancake를 이용해주셔서 감사합니다.</span><br><br>
								${user_id}  님 &nbsp;&nbsp;&nbsp; <a href="logout.pan">로그아웃</a> <br><br>
								<span>포인트 (<span id="user_point"></span>)</span>
								<br><br>
							</div>
							<table border="1" class="center">
							</table>
						</c:when>
						<c:otherwise>
								<div class="login">
									<br>
									<span style="color: gray;">Pancake를 이용해주셔서 감사합니다.</span><br><br>
									<div id="loginlink">Pancake 로그인</div><br>
									<span><a href="joinForm.pan">회원가입</a></span>
									<br><br>
								</div>
							<table border="1" class="center">
							</table>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
<!-- 				안내문 부분 -->
					<div class="login">
					<span>
						<br>
						<b>Pancake 사이트 안내</b><br>
						팬케잌은 자유로운 커뮤니티 사이트입니다 욕설이나 비방은 자제해 주시고 언제나 편하게 들려주세요~♪
						<br><br>
					</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<!--  -->
	
<%@ include file="include/bottom.jsp"%>