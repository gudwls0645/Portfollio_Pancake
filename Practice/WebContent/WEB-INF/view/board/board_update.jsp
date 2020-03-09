<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/top.jsp" %>
<!-- css, script -->
<style>
	input:focus, textarea:focus{     outline: none; }

	.td input:focus, .td textarea:focus{     outline: none; }
</style>
<script>
$(function() {
	
});
</script>
<%@ include file="../include/title.jsp" %>
<!-- 내용 -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-6">
		
		<form action="boardUpdatePro.user" method="post">
			<input type="hidden" name="board_num" value="${BoardVo.board_num}"/>
			<span style="font-size: 30px; font-style: bold;">글수정</span><span style="float:right; color: #bc5e00;"><input class="btn" type="submit" value="발행" style="text-align: center; width: 60px; height: 50px;"/></span>
			<br><br>
			<table class="table table-bordered">
				<tr>
					<td><input id="subject" type="text" name="subject" size="20" value="${BoardVo.board_subject}" 
					style="width:100%; border: 0;" placeholder="제목" required></td>
				</tr>
				<tr>
					<td><textarea id="content" name="content" rows="20" style="width:100%; border: 0; resize: none;" placeholder="내용을 입력해 주세요!" required>
${BoardVo.board_content}</textarea></td>
				</tr>
			</table>
		</form>
		
		</div>
		<div class="col-md-3">
		</div>
	</div>
</div>
<%@ include file="../include/bottom.jsp" %>