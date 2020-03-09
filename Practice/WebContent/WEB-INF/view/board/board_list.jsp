<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/top.jsp" %>

<style>
 	.center2 {
 		text-align: center;
	}
	
	.board {
		 text-align: center;
	}
	
	.menu {
		width:900px; left:0; right:0; margin-left:auto; margin-right:auto; /* 가로 중앙 정렬 */ 
		height:20px; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; /* 세로 중앙 정렬 */
		text-align: center;
	}
	
	.tap {
		margin-left: 50px;
		margin-right: 50px;
	}
	
	.btn {
		float:right; color: #bc5e00; width: 60px; height: 50px;
	}
</style>
<script>
$(function() {
	var msg = "${msg}";
	if (msg == "notLogin") {
		alert("로그인을 해주세요.");
	}
	
	function getList(nowPage) {
		$("input[name=nowPage]").val(nowPage);
		$("#searchForm").submit();
	}
	$(".paging").click(function (e) {
		e.preventDefault(); // prevent : 막다, 방지하다, default : 기본
							// 브라우저의 기본 기능 막기
		var page = $(this).attr("href");
		getList(page);
	});
	
	$("select[name=perPage]").change(function () {
		getList(1);
	});
});
</script>
</head>
<body>
	
	<%@ include file="../include/title.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-3">
					</div>
					<div class="col-md-6">
						<span style="font-size: 30px; font-style: bold;">자유게시판</span>&nbsp;&nbsp;&nbsp;
						<a href="boardWriteForm.user"><input class="btn" type="submit" value="글쓰기"/></a>
							<br><br>
							<table class="table table-hover table-bordered">
								<tr>
									<th class="board">글번호</th>
									<th class="board">제목</th>
									<th class="board">작성자</th>
									<th class="board">작성일</th>
									<th class="board">조회수</th>
								</tr>
								<!-- for (BaordVo boardVo : list) -->
							<c:forEach items="${list}" var="boardVo">
								<tr>
									<td class="board" width="70">${boardVo.board_num}</td>
									<td >
									<a href="boardContent.pan?board_num=${boardVo.board_num}" 
											title="${boardVo.board_content}">${boardVo.board_subject} [${boardVo.board_reply_count}]</a></td>
									<td class="board" width="100">${boardVo.board_writer}</td>
									<td class="board" width="200">${boardVo.board_reg_date}</td>
									<td class="board" width="70">${boardVo.board_read_count}</td>
								</tr>
							</c:forEach>

						</table>
<!-- 						페이징 상단 -->
						<div class="center2">
							<ul class="pagination" style="margin: auto;">
								<c:if test="${pagingDto.startPage != 1}">
									<li class="page-item"><a class="paging" href="${pagingDto.startPage - 1}">[이전]</a></li>
								</c:if>
								<c:forEach var="i" begin="${pagingDto.startPage}" end="${pagingDto.endPage}">
									<li class="page-item"><a class="paging" href="${i}">${i}</a></li>
								</c:forEach>
								<c:if test="${pagingDto.endPage != pagingDto.totalPage}">
									<li class="page-item"><a class="paging" href="${pagingDto.endPage + 1}">[다음]</a></li>
								</c:if>
							</ul>
<!-- 						페이징 하단 -->
							<br><br>
<!-- 						검색 상단 -->
							<form action="boardForm.pan" id="searchForm">
								<input type="hidden" name="nowPage" value="${pagingDto.nowPage}"/>
								<select name="searchType">
									<option value="board_subject"
									<c:if test="${searchDto.searchType == 'board_subject'}">
									selected
									</c:if>
									>제목</option>
									<option value="board_content"
									<c:if test="${searchDto.searchType == 'board_content'}">
									selected
									</c:if>
									>내용</option>
									<option value="board_writer"
									<c:if test="${searchDto.searchType == 'board_writer'}">
									selected
									</c:if>
									>작성자</option>
								</select>
								<input type="text" name="keyword" value="${searchDto.keyword}"/>
								<input type="submit" value="검색"/>
								<!--  5 ~ 20 줄 -->
								<select name="perPage">
								<c:forEach var="i" begin="5" end="20" step="5">
									<option value="${i}"
									<c:if test="${i == pagingDto.perPage}">selected</c:if>
									>${i}줄씩 보기</option>
								</c:forEach>
								</select>
							</form>
<!-- 					검색 하단 -->
						</div>
					</div>
					<div class="col-md-3">
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../include/bottom.jsp"%>