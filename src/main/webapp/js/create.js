var user;
$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "/mansys/user/current.do",
    success: function (data) {
      user = JSON.parse(data);
    },
    error: function (data) {
      if (data.status == 302) {
        console.log("302");
      }
    }
  });
});

$('#submit').on('click', function () {
  var name = $('#name').val();
  if (name == "") {
    $(".hintName").text("请输入用户名");
    return false;
  }
  var gender = $("input[type='radio']:checked").val();
  var phone_num = $('#phone_num').val();
  var remark = $('#remark').val();

  $.ajax({
    type: "POST",
    url: "/mansys/contact/insert.do",
    contentType: "application/json", // 必须有
    data: JSON.stringify({
      "name": name,
      "gender": parseInt(gender),
      "phoneNum": phone_num,
      "remark": remark
    }),
    success: function (data) {
      location.href = "userList.html";
    },
    error: function (data) {
      if (data.status == 302) {
        location.href = "/html/login.html";
      }
    }
  });
})