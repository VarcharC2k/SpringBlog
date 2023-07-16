<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp"%>


<div class="container">

      <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
      <button id="btn-update" class="btn btn-warning">수정</button>
      <button id="btn-delete" class="btn btn-danger">삭제</button>
      <br/>
      <br/>
      <div class="form-group">
        <h3>${board.title}</h3>
      </div>

<hr/>
      <div class="form-group">
        <div>
        ${board.content}
        </div>
        </div>
        <hr/>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

