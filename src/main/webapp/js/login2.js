$("#login").click(function () {
  var account = $('input[id="account"]').val();
  var pwd = $('input[id="pwd"]').val();
  $.ajax({
    type: "POST",
    url: "/login.do",
    contentType: "application/json",
    data: JSON.stringify({
      "email": account,
      "password": pwd
    }),
    success: function (data) {
      if (data != "") {
        console.log("here");
        /*302也可能表示成功*/
        location.href = "../html/userList.html";
      }
    },
    error: function () {
      var account = $("#loginDiv").find("form span").eq(0);
      account.text("用户名或密码错误！");
    }
  });
});

$("#account").focus(function () {
  var account = $("#loginDiv").find("form span").eq(0);
  account.text("");
})

$("#register").click(function () {
  location.href = "register.html";
});