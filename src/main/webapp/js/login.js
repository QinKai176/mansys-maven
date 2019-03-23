$(function () {
  var tab = 'account_number';
  // 选项卡切换
  $(".account_number").click(function () {
    $('.tel-warn').addClass('hide');
    tab = $(this).attr('class').split(' ')[0];
    checkBtn();
    $(this).addClass("on");
    $(".message").removeClass("on");
    $(".form2").addClass("hide");
    $(".form1").removeClass("hide");
  });
  // 选项卡切换
  $(".message").click(function () {
    $('.tel-warn').addClass('hide');
    tab = $(this).attr('class').split(' ')[0];
    checkBtn();
    $(this).addClass("on");
    $(".account_number").removeClass("on");
    $(".form2").removeClass("hide");
    $(".form1").addClass("hide");

  });

  $('#num').keyup(function (event) {
    $('.tel-warn').addClass('hide');
    checkBtn();
  });

  $('#pass').keyup(function (event) {
    $('.tel-warn').addClass('hide');
    checkBtn();
  });

  $('#veri').keyup(function (event) {
    $('.tel-warn').addClass('hide');
    checkBtn();
  });

  $('#num2').keyup(function (event) {
    $('.tel-warn').addClass('hide');
    checkBtn();
  });

  $('#veri-code').keyup(function (event) {
    $('.tel-warn').addClass('hide');
    checkBtn();
  });

  // 按钮是否可点击
  function checkBtn() {
    $(".log-btn").off('click');
    if (tab == 'account_number') {
      var inp = $.trim($('#num').val());
      var pass = $.trim($('#pass').val());
      if (inp != '' && pass != '') {
        if (!$('.code').hasClass('hide')) {
          code = $.trim($('#veri').val());
          if (code == '') {
            $(".log-btn").addClass("off");
          } else {
            $(".log-btn").removeClass("off");
            sendBtn();
          }
        } else {
          $(".log-btn").removeClass("off");
          sendBtn();
        }
      } else {
        $(".log-btn").addClass("off");
      }
    } else {
      var phone = $.trim($('#num2').val());
      var code2 = $.trim($('#veri-code').val());
      if (phone != '' && code2 != '') {
        $(".log-btn").removeClass("off");
        sendBtn();
      } else {
        $(".log-btn").addClass("off");
      }
    }
  }

  function checkAccount(username) {
    if (username == '') {
      $('.num-err').removeClass('hide').find("em").text('请输入账户');
      return false;
    } else {
      $('.num-err').addClass('hide');
      return true;
    }
  }

  function checkPass(pass) {
    if (pass == '') {
      $('.pass-err').removeClass('hide').text('请输入密码');
      return false;
    } else {
      $('.pass-err').addClass('hide');
      return true;
    }
  }

  function checkCode(code) {
    if (code == '') {
      // $('.tel-warn').removeClass('hide').text('请输入验证码');
      return false;
    } else {
      // $('.tel-warn').addClass('hide');
      return true;
    }
  }

  function checkPhone(phone) {
    var status = true;
    if (phone == '') {
      $('.num2-err').removeClass('hide').find("em").text('请输入手机号');
      return false;
    }
    var param = /^1[34578]\d{9}$/;
    if (!param.test(phone)) {
      // globalTip({'msg':'手机号不合法，请重新输入','setTime':3});
      $('.num2-err').removeClass('hide');
      $('.num2-err').text('手机号不合法，请重新输入');
      return false;
    }
    return status;
  }

  function checkPhoneCode(pCode) {
    if (pCode == '') {
      $('.error').removeClass('hide').text('请输入验证码');
      return false;
    } else {
      $('.error').addClass('hide');
      return true;
    }
  }

  // 登录点击事件
  function sendBtn() {
    if (tab == 'account_number') {
      $(".log-btn").click(function () {
        // var type = 'phone';
        var inp = $.trim($('#num').val());
        var pass = $.trim($('#pass').val());
        if (checkAccount(inp) && checkPass(pass)) {
          var ldata = {userinp: inp, password: pass};
          if (!$('.code').hasClass('hide')) {
            code = $.trim($('#veri').val());
            if (!checkCode(code)) {
              return false;
            }
            ldata.code = code;
          }
          $.ajax({
            url: '/mansys/login.do',
            type: "POST",
            datatype: 'json',
            async: true,
            contentType: 'application/json',
            data: JSON.stringify({"email": inp, "password": pass}),
            success: function (data) {
              data = JSON.parse(data);
              if (data.success) {
                location.href = "/mansys/html/userList.html";
              } else {
                alert(data.msg);
              }
            },
            error: function () {
              console.log("sth wrong");
            }
          });
        } else {
          return false;
        }
      });
    } else {
      $(".log-btn").click(function () {
        // var type = 'phone';
        var phone = $.trim($('#num2').val());
        var pcode = $.trim($('#veri-code').val());
        console.log(phone + " " + pcode);
        if (checkPhone(phone) && checkPass(pcode)) {
          $.ajax({
            url: '/mansys/phoneLogin.do',
            contentType: "application/json", // 必须有
            type: "POST",
            async: true,
            data: JSON.stringify({"phone": phone, "code": pcode}),
            success: function (data) {
              data = JSON.parse(data);
              if (data.success) {
                location.href = "/mansys/html/userList.html";
              } else {
                alert(data.msg);
              }
            },
            error: function () {

            }
          });
        } else {
          $(".log-btn").off('click').addClass("off");
          // $('.tel-warn').removeClass('hide').text('登录失败');
          return false;
        }
      });
    }
  }

  // 登录的回车事件
  $(window).keydown(function (event) {
    if (event.keyCode == 13) {
      $('.log-btn').trigger('click');
    }
  });

  $(".form-data").delegate(".send", "click", function () {
    var phone = $.trim($('#num2').val());
    console.log(phone);
    console.log(checkPhone(phone));
    if (checkPhone(phone)) {

      $.ajax({
        url: '/mansys/getcode.do',
        datatype: 'json',
        contentType: 'application/json',
        type: "POST",
        data: JSON.stringify({phone: phone}),
        success: function (jsonMsg) {
          jsonMsg = eval("(" + jsonMsg + ")");
          if (jsonMsg.success) {

          } else {
            alert(jsonMsg.msg);
          }
        },
        error: function (data) {
          alert("获取验证码失败");
        }
      });
      var oTime = $(".form-data .time"),
          oSend = $(".form-data .send"),
          num = parseInt(oTime.text()),
          oEm = $(".form-data .time em");
      $(this).hide();
      oTime.removeClass("hide");
      var timer = setInterval(function () {
        var num2 = num -= 1;
        oEm.text(num2);
        if (num2 == 0) {
          clearInterval(timer);
          oSend.text("重新发送验证码");
          oSend.show();
          oEm.text("120");
          oTime.addClass("hide");
        }
      }, 1000);
    }
  });
});