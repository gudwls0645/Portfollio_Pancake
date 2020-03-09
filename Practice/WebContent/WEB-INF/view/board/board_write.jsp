<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/top.jsp" %>
<!-- css, 스크립트 -->
<style>
	input:focus, textarea:focus{     outline: none; }

	.td input:focus, .td textarea:focus{     outline: none; }
	
	th {
		text-align: center;
	}
</style>
<script>

</script>
<%@ include file="../include/title.jsp" %>
<!-- 내용 -->

<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
		</div>
		<div class="col-md-6">
		
		<form action="boardWritePro.user" method="post">
			<span style="font-size: 30px; font-style: bold;">글쓰기</span><span style="float:right; color: #bc5e00;"><input class="btn" type="submit" value="발행" style="text-align: center; width: 60px; height: 50px;"/></span>
			<br><br>
			<table class="table table-bordered">
				<tr>
					<td><input type="text" name="subject" size="20" style="width:100%; border: 0;" placeholder="제목" required></td>
				</tr>
				<tr>
					<td><textarea name="content" rows="20" style="width:100%; border: 0; resize: none;" placeholder="내용을 입력해 주세요!" required></textarea></td>
				</tr>
			</table>
		</form>
		
		</div>
		<div class="col-md-3">
		</div>
	</div>
</div>
<%@ include file="../include/bottom.jsp" %>
