$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "/user/current.do",
    success: function (data) {
      location.href = "html/userList.html";
    },
    error: function (data) {
      if (data.status == 302) {
        console.log("302");
      }
    }
  });
});

function login() {
  location.href = "html/login/login.html";
}

$('#register').click(function () {
  location.href = "html/register.html";
});
