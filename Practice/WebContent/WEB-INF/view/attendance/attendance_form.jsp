<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/top.jsp" %>
<!-- css, script -->
<style>
	.inputButton {
	    border: 1px solid #ccc;
	    line-height: 60px;
	    background-color: white;
	    cursor: pointer;
	}
	
	textarea {
		width:100%; border: 0; resize: none;
		background-color: white;
	}
</style>
<script>
$(function() {
	getAttendanceList();
	
	$("#btnCommentInput").click(function() {
		var reply_text = $("#commentText").val();
		var url = "attendanceWrite.ajax";
		var sData = {
				"reply_text" : reply_text,
		};
		$.post(url, sData, function(rData) {
			console.log(rData);
			if (rData.trim() == "success") {
				getAttendanceList();
			} else {
				alert("오늘은 이미 출석을 했어요~♬");
			}
		});
		$("#commentText").val("");
	});
	
	function getAttendanceList() {
		var url = "attendanceList.ajax";
		var sData = {
// 				"board_num" : "${BoardVo.board_num}"
				};
		$.getJSON(url, sData, function(rData) {
			console.log(rData);	
			$("#replyTable").empty();
			$.each(rData, function() {
				var tr = "<tr>";
				tr += "<th style='width: 150px; height:50px; text-align: center;'>" + this.att_id + "</th>";
				tr += "<td>" + this.att_text + "</td>";
				tr += "<td style='text-align: right;'>" + this.att_date + "</td>";
				tr += "</tr>";
				$("#replyTable").after().append(tr);
			}); // $.each
		}); // $.getJSON
	};
});
</script>
<%@ include file="../include/title.jsp" %>
<!-- 내용 -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-6">
			<span style="font-size: 30px; font-style: bold;">출석체크</span>
			<table class="table"  style="background-color: #e9e9e9; ">
				<c:choose>
					<c:when test="${not empty user_id}">
						<tr>
							<th colspan="5" style="text-align: center;">
								<textarea rows="3" cols="" id="commentText" placeholder="멘트를 입력해주세요 ~ ♬ (1000포인트 지급!)" required></textarea>
							</th>
							<th style="text-align: center; width: 150px;" >
								<div class="inputButton" id="btnCommentInput">등록</div>
							</th>
						</tr>
					</c:when>
					<c:otherwise>
						<tr style="background-color: #e9e9e9; ">
							<th colspan="6" style="text-align: center;" >
								<textarea rows="3" cols="" id="commentText" placeholder="로그인을 해주세요 ~ ♬" disabled></textarea>
							</th>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<th colspan="6" >
						<table class="table" id="replyTable" style="background-color: #e9e9e9">
						</table>
					</th>
				</tr>
			</table>
		</div>
		<div class="col-md-3">
		</div>
	</div>
</div>
<%@ include file="../include/bottom.jsp" %>