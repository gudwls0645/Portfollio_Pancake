<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/top.jsp" %>
<!-- css, script -->
<style>
	.btn {
		float:right; color: #bc5e00; width: 60px; height: 50px;
		margin-left: 10px;
		
	}
	
	th {
		width : 150px;
		text-align: center;
	}
	
	textarea {
		width:100%; border: 0; resize: none;
		background-color: white;
	}
	
	#title {
		font-size: 30px;
		font-style: bold;
		margin-left: 10px;
	}
	
	.inputButton {
	    border: 1px solid #ccc;
	    line-height: 60px;
	    background-color: white;
	    cursor: pointer;
	}
</style>

<script>
$(function() {
	var user_id = "${sessionScope.user_id}";
	
	getCommentList();
	
	function getCommentList() {
		var url = "CommentList.ajax";
		var sData = {
				"board_num" : "${BoardVo.board_num}"
				};
		$.getJSON(url, sData, function(rData) {
			console.log(rData);	
			$("#replyTable").empty();
			$.each(rData, function() {
				var tr = "<tr>";
				tr += "<th style='width: 150px; height: 50px;'>" + this.replyer + "</th>";
				
				if (user_id == this.replyer) {
					tr += "<td style='text-align: left;'>" + this.reply_text + "</td>";
					tr += "<td style='text-align: right;'><input type='button' data-num='"+this.reply_num+"' value='삭제' class='btnCommentDelete'/>[" + this.reply_date  + "]</td>";
					tr += "</tr>";
				} else {
					tr += "<td style='text-align: left;'>" + this.reply_text + "</td>";
					tr += "<td style='text-align: right; width:250px;'>[" + this.reply_date  + "]</td>";
				}
				$("#replyTable").append(tr);
				
			}); // $.each
		}); // $.getJSON
	};
	
	$("#btnCommentInput").click(function() {
		var reply_text = $("#commentText").val();
		var url = "CommentWrite.ajax";
		var sData = {
				"reply_text" : reply_text,
				"board_num" : "${BoardVo.board_num}"
		};
		$.post(url, sData, function(rData) {
			console.log(rData);
			if (rData.trim() == "success") {
				
				getCommentList();
			} 
		});
		$("#commentText").val("");
	});
	
	$("#replyTable").on("click", ".btnCommentDelete", function() {
		var that = $(this);
// 		console.log(that);
// 		console.log("댓글 삭제 버튼 클릭됨");
		var reply_num = that.attr("data-num");
		var board_num = "${BoardVo.board_num}";
		console.log(reply_num);
		console.log(board_num);
		var url = "CommentDelete.ajax";
		var sData = {
			"reply_num" : reply_num,
			"board_num" : board_num
		};
		$.get(url, sData, function(rData) {
			var data = rData.trim();
			console.log(data);
			if (data == "success") {
				getCommentList();
			}
		});
	});
	
});
</script>
<%@ include file="../include/title.jsp" %>
<!-- 내용 -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-6">
			<span style="font-size: 30px; font-style: bold;">내용</span>
			
			<a href="boardForm.pan"><input class="btn" type="button" value="목록"/></a>
			<c:if test="${BoardVo.board_writer == sessionScope.user_id}">
			<a href="boardUpdateForm.user?board_num=${BoardVo.board_num}"><input class="btn" type="button" value="수정"/></a>
			<a href="boardDeletePro.user?board_num=${BoardVo.board_num}"><input class="btn" type="button" value="삭제"/></a>
			</c:if>
			<br><br>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="6" id="title">
					${BoardVo.board_subject}
					</td>
				</tr>
				<tr>
					<th>
					작성자
					</th>
					<td style="width: 150px;">
					${BoardVo.board_writer}
					</td>
					<th>
					작성일
					</th>
					<td>
					${BoardVo.board_reg_date}
					</td>
					<th>
					조회수
					</th>
					<td style="width: 100px; text-align: center;">
					${BoardVo.board_read_count}
					</td>
				</tr>	
				<tr>
					<td colspan="6">
					<textarea name="content" rows="20" required disabled>
${BoardVo.board_content}
					</textarea>
					</td>
				</tr>
<!-- 				덧글 뷰 상단 -->
				<tr style="background-color: #e9e9e9">
					<th colspan="6">
						<table id="replyTable" style="width: 100%;">
						</table>
					</th>
				</tr>
<!-- 				덧글 뷰 하단 -->
				<c:if test="${not empty sessionScope.user_id}">
				<tr style="background-color: #e9e9e9">
					<th colspan="5">
						<textarea rows="3" cols="" id="commentText"></textarea>
					</th>
					<th>
						<div class="inputButton" id="btnCommentInput">등록</div>
					</th>
				</tr>
				</c:if>
			</table>
			
		</div>
		<div class="col-md-3">
		</div>
	</div>
</div>
<%@ include file="../include/bottom.jsp" %>