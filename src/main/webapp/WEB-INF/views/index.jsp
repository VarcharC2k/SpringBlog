<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="layout/header.jsp"%>


<div class="container">
    <h2>Stretched Link in Card</h2>
    <p>Add the .stretched-link class to a link inside the card, and it will make the whole card clickable (the card will
        act as a link):</p>
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">수정 중</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary stretched-link">상세 보기</a>
        </div>
    </div>

    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">제목 적는 부분</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary stretched-link">상세 보기</a>
        </div>
    </div>

    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">제목 적는 부분</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary stretched-link">상세 보기</a>
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp"%>

