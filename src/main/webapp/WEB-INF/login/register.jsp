<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>IF BOARD - Login</title>

    <!-- Bootstrap core CSS-->
    <link href="/res/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="/res/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="/res/css/sb-admin.css" rel="stylesheet">

  </head>

  <body class="bg-dark">

    <div class="container">
      <div class="card card-register mx-auto mt-5">
        <div class="card-header">계정 생성</div>
        <div class="card-body">
          <form id="registerForm" action="register_proc" method="post">
          
          <div class="form-group">
              <div class="form-label-group">
                <input type="text" id="user_id" name="user_id" class="form-control" placeholder="아이디 입력" required="required">
                <label for="user_id">아이디 입력</label>
              </div>
            </div>
          
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="user_pw" name="user_pw" class="form-control" placeholder="패스워드 입력" required="required" autofocus="autofocus">
                    <label for="user_pw">패스워드 입력</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="user_pw_confirm" name="user_pw_confirm" class="form-control" placeholder="패스워드 확인" required="required">
                    <label for="user_pw_confirm">패스워드 확인</label>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="user_name" name="user_name" class="form-control" placeholder="이름" required="required">
                    <label for="user_name">이름</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="user_pos" name="user_pos" class="form-control" placeholder="직급" required="required">
                    <label for="user_pos">직급</label>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="user_dep" name="user_dep" class="form-control" placeholder="부서명" required="required">
                    <label for="user_dep">부서명</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="user_team" name="user_team" class="form-control" placeholder="소속 팀" required="required">
                    <label for="user_team">소속 팀</label>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <div class="form-label-group">
                <input type="text" id="user_email" name="user_email" class="form-control" placeholder="이메일 주소 입력" required="required">
                <label for="user_email">이메일 주소 입력</label>
              </div>
            </div>
            
            <a class="btn btn-primary btn-block" onClick="register_proc();">가입하기</a>
          </form>
          <!-- <div class="text-center">
            <a class="d-block small mt-3" href="login.html">Login Page</a>
            <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
          </div> -->
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/res/vendor/jquery/jquery.min.js"></script>
    <script src="/res/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/res/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	<!-- Page Script -->
	<script>
		function register_proc(){
			
		    // 공통입력폼내의 모든 입력오브젝트
		    var inputObjs = $("#registerForm input");
		    // 미입력여부(경우에 따라 사용)
		    var bEmpty = true;
		    var focus;
		 
		    // 각 오브젝트에 대해 입력체크
		    inputObjs.each(function(index) {
		        if ($(this).val() == '') {
		            focus = $(this);
		            bEmpty = false;
		 
		            alert($(this).attr('placeholder') + "은 필수 입력사항입니다.");
		            focus.focus();
		 
		            // 여기서는 each문을 탈출
		            return false;
		        }
		    });
		 
		    // 필수입력사항에 누락이 있으면 진행금지
		    if (!bEmpty) return;
			
			var password = $('#user_pw').val();
			var password_confirm = $('#user_pw_confirm').val();
			
			if(password != password_confirm){
				alert("입력하신 패스워드가 다릅니다.");
				return;
			}
			
			registerForm.submit();
		}
	</script>
  </body>

</html>
