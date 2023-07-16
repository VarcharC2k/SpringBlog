<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="layout/header.jsp"%>


<div class="container">

<c:forEach var="board" items="${boards.content}">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="/board/${board.id}" class="btn btn-primary stretched-link">상세 보기</a>
        </div>
    </div>
</c:forEach>

<!-- 부트스트랩의 정렬 명령어 = justify-content-center 가운데 정렬, end 끝 정렬, start 시작 정렬 -->
<ul class="pagination justify-content-center">
    <c:choose>
    <c:when test="${boards.first}">
        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:when>
    <c:otherwise>
     <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:otherwise>
    </c:choose>

      <c:choose>
        <c:when test="${boards.last}">
         <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>        </c:when>
        <c:otherwise>
         <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>        </c:otherwise>
        </c:choose>
  </ul>

</div>

<%@ include file="layout/footer.jsp"%>

