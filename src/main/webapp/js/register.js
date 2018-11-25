$(document).ready(function () {
  var nameHint = $(".content").find("span").eq(0);
  $('#name').blur(function () {
    var name = $('#name').val();
    if (name.length <= 2) {
      nameHint.text("用户名过短，请重新输入！");
    } else {
      nameHint.text("");
    }
    ;
    $.ajax({
      type: "GET",
      url: '/user/exist.do?username=' + name,
      contentType: "application/json", // 必须有
      success: function (data) {
        if (data != "") {
          $(".hintName").text("此用户名已被占用");
        }
      },
      error: function (data) {
        if (data.status == 400) {
          alert("wrong");
        }
      }
    });
  });

  $('.pwd').blur(function () {
    var pwd1 = $(".pwd").eq(0).val();
    var pwd2 = $(".pwd").eq(1).val();
    console.log(pwd1 + " " + pwd2);
    if (pwd1 != pwd2) {
      //修改属性用 $(".hintPwd2").attr("text","密码前后输入不一致");
      $(".hintPwd2").text("密码前后输入不一致");
    } else {
      $(".hintPwd2").text("");
    }
  });

  $('.lang-btn').on('click', function () {
    var name = $('#tel').val();
    var pwd = $("#passport").eq(0).val();
    var phone_num = '000000';
    console.log(name + " " + pwd + " " + phone_num);
    $.ajax({
      type: "POST",
      url: '/register.do',
      contentType: "application/json", // 必须有
      data: JSON.stringify({
        "username": name,
        "password": pwd,
        "phoneNum": phone_num
      }),
      success: function (data) {
        location.href = '../login/login.html';
      },
      error: function (data) {
        if (data.status == 400) {
          alert("wrong");
        }
      }
    });
  })
});
