<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardMain</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// alert("실행");

		// 글쓰기 버튼 클릭 시 글 정보를 저장
		$('#btnAdd').click(function() {
			// alert("시작");

			// 전달 정보(글쓰기 내용) JSON 형태로 준비
			let board = {
				title : document.getElementById("title").value,
				content : document.getElementById("content").value,
				writer : document.getElementById("writer").value
			}

			// 글 정보 저장 - REST URI 호출(비동기 방식 AJAX)
			$.ajax({
				url : '/boards',
				type : 'POST',
				data : JSON.stringify(board),
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					// alert("access INSERT");
					document.getElementById("result").innerText = data;
					document.getElementById("title").value = null;
					document.getElementById("content").value = null;
					document.getElementById("writer").value = null;
				},
				error : function() {
					alert("can't access")
					document.getElementById("result").innerText = data;
				},
				complete : function() {
					$('table tbody').empty();
					
					selectList();
				}
			});

			// alert("종료");
		});

		$('#btnRead').click(function() {
			// 글조회 버튼 클릭시 DB에 저장된 마지막 글 정보를 출력
			$.ajax({
				url : '/boards/' + document.getElementById("getBno").value,
				type : "GET",
				success : function(data) {
					// alert('access data');
					document.getElementById("getTitle").value = data.title;
					document.getElementById("getWriter").value = data.writer;
					document.getElementById("getContent").value = data.content;
					document.getElementById("getViewcnt").value = data.viewcnt;
					document.getElementById("getRegdate1").value = data.regdate;
					document.getElementById("getRegdate2").value = formatTimestamp(data.regdate);
					document.getElementById("updateTitle").value = data.title;
					document.getElementById("updateContent").value = data.writer;
					document.getElementById("updateWriter").value = data.content;
				}
			});
		});
		
		selectList();
		
		$('#btnUpdate').click(function(){
			// 비동기 방식 > REST 컨트롤러(수정정보 전달)
			// 전달 정보(글쓰기 내용) JSON 형태로 준비
			let updateBoardVO = {
				title : document.getElementById("updateTitle").value,
				content : document.getElementById("updateContent").value,
				writer : document.getElementById("updateWriter").value
			}
			
			$.ajax({
				url : '/boards/' + document.getElementById("getBno").value,
				type : 'PUT',
				data : JSON.stringify(updateBoardVO),
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					alert(data);
				},
				complete : function() {
					$('table tbody').empty();
					
					selectList();
				}
			});
		});
		
		$('#btnDelete').click(function(){
			$.ajax({
				url : '/boards/' + document.getElementById('deleteBno').value,
				type : 'DELETE',
				success : function(data) {
					alert(data);
				},
				complete : function() {
					$('table tbody').empty();
					
					selectList();
				}
			});
		});
		
		function selectList(){
			//DB에서 게시글 최근 10개 조회
			$.ajax({
				url : '/boards/list',
				success : function(data) {
					// alert('access list');
					console.log(data);
					$(data).each(function(idx, item){
						// console.log(idx + '//' + item);
						// console.log(item);
						
						// 테이블에 해당 내용을 추가
						$('table').append('<tr><td>' + item.bno + '</td><td>' + item.title + '</td><td>' + item.writer + '</td></tr>');
					});
				}
			});
		}
		
		function formatTimestamp(timestamp) {
		    // Date 객체로 변환
		    const date = new Date(timestamp);

		    // 날짜 및 시간을 포맷팅
		    const formattedDate = date.getFullYear() + '-' + 
		                          ('0' + (date.getMonth() + 1)).slice(-2) + '-' + 
		                          ('0' + date.getDate()).slice(-2) + ' ' + 
		                          ('0' + date.getHours()).slice(-2) + ':' + 
		                          ('0' + date.getMinutes()).slice(-2) + ':' + 
		                          ('0' + date.getSeconds()).slice(-2);

		    return formattedDate;
		}

	});
</script>
</head>
<body>
	<fieldset style="width: 300px;">
		<h2>상태: <span id="result"></span></h2>
		<legend>글쓰기(POST)</legend>
		<label for="title">글제목:</label>
		<input type="text" id="title" name="title">
		<br>
		<label for="content">글내용:</label>
		<textarea id="content" name="content" rows="5" cols="22"></textarea>
		<br>
		<label for="writer">작성자:</label>
		<input type="text" id="writer" name="writer">
		<br>
		<input type="button" id="btnAdd" value="글쓰기(POST)">
	</fieldset>

	<hr>

	<fieldset style="width: 300px;">
		<legend>글조회(GET)</legend>
		<label for="getBno">글번호:</label>
		<input type="text" id="getBno" name="getBno">
		<br>
		<label for="getTitle">글제목:</label>
		<input type="text" id="getTitle" name="getTitle" readonly="readonly">
		<br>
		<label for="getWriter">작성자:</label>
		<input type="text" id="getWriter" name="getWriter" readonly="readonly">
		<br>
		<label for="getContent">글내용:</label>
		<textarea id="getContent" name="getContent" rows="5" cols="22" readonly="readonly"></textarea>
		<br>
		<label for="getViewcnt">조회수:</label>
		<input type="text" id="getViewcnt" name="getViewcnt" readonly="readonly">
		<br>
		<label for="getRegdate1">작성일:</label>
		<input type="text" id="getRegdate1" name="getRegdate1" readonly="readonly">
		<br>
		<label for="getRegdate2">작성일:</label>
		<input type="text" id="getRegdate2" name="getRegdate2" readonly="readonly">
		<br>
		<input type="button" id="btnRead" value="글조회(GET)">
	</fieldset>
	
	<hr>
	
	<fieldset style="width: 300px;">
		<legend>게시판목록조회(GET)</legend>
		<table border="1" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th style="width: 70px;">글번호</th>
					<th style="width: 100px;">글제목</th>
					<th style="width: 100px;">작성자</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</fieldset>
	
	<hr>
	
	<fieldset style="width: 300px;">
		<legend>글수정(PUT)</legend>
		<label for="updateTitle">글제목:</label>
		<input type="text" id="updateTitle" name="updateTitle">
		<br>
		<label for="updateContent">글내용:</label>
		<textarea id="updateContent" name="updateContent" rows="5" cols="22"></textarea>
		<br>
		<label for="updateWriter">작성자:</label>
		<input type="text" id="updateWriter" name="updateWriter">
		<br>
		<input type="button" id="btnUpdate" value="글수정(PUT)">
	</fieldset>
	
	<hr>
	
	<fieldset style="width: 300px;">
		<legend>글삭제(DELETE)</legend>
		<label for="deleteBno">글번호:</label>
		<input type="text" id="deleteBno" name="deleteBno">
		<br>
		<input type="button" id="btnDelete" value="글삭제(DELETE)">
	</fieldset>
</body>
</html>