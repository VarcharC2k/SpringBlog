let index = {
    init: function(){
        $("#btn-save").on("click",()=>{// function(){}을 사용하지 않고 ()=>{}를 사용하는 이유는 this를 바인딩 하기 위하여 사용
        this.save();
        });
        $("#btn-login").on("click",()=>{
            this.login();
            });
    },

    save: function(){
//        alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

//        console.log(data);

        //ajax 호출시 default가 비동기 호출
        //ajax 통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청
        //ajax가 통신을 성공하고 서버가  Json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data:JSON.stringify(data), //현재 data는 자바스크립트로 만들어진 오브젝트기 때문에 JAVA가 알아들을 수 있도록 JSON으로 변경하기 위해 Stringify 함수 사용
            //해당 data는 http의 body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어던 타입인지(MIME)
            dataType: "json" //요청을 서버로 해서 응답이 왔을 때의 데이터 타입(기본적으로 문자열), 해당 타입을 설정하면 타입이 맞게 오면 자동으로 자바스크립트 오브젝트로 변경
        }).done(function(resp){
            alert("회원 가입이 완료되었습니다.");
//            console.log(resp);
            location.href = "/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },

    login: function(){
    //        alert('user의 save함수 호출됨');
            let data = {
                username: $("#username").val(),
                password: $("#password").val()
            };

            $.ajax({
                type: "POST",
                url: "/blog/api/user/login",
                data:JSON.stringify(data), //현재 data는 자바스크립트로 만들어진 오브젝트기 때문에 JAVA가 알아들을 수 있도록 JSON으로 변경하기 위해 Stringify 함수 사용
                //해당 data는 http의 body 데이터
                contentType: "application/json; charset=utf-8", // body데이터가 어던 타입인지(MIME)
                dataType: "json" //요청을 서버로 해서 응답이 왔을 때의 데이터 타입(기본적으로 문자열), 해당 타입을 설정하면 타입이 맞게 오면 자동으로 자바스크립트 오브젝트로 변경
            }).done(function(resp){
                alert("로그인이 완료되었습니다.");
                location.href = "/blog";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });

        }
}

index.init();