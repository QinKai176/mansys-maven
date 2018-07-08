var contactId;
$(document).ready(function () {
  contactId = getUrlParam("id");
  var user;
  $.ajax({
    type: "GET",
    url: "/user/current.do",
    success: function (data) {
      user = JSON.parse(data);
      console.log(data);
    },
    error: function (data) {
      if (data.status == 302) {
        console.log("302");
        location.href = "/html/login.html";
      }
    }
  });

  $.ajax({
    type: "GET",
    url: '/contact.do?id=' + contactId,
    contentType: "application/json",
    success: function (data) {
      data = JSON.parse(data);
      $("#name").val(data[0].name)
      if (data[0].gender == 1) {
        $("input[name='gender'][value=1]")
        .attr("checked", true);
      } else {
        $("input[name='gender'][value=2]")
        .attr("checked", true);
      }
      $(":radio").attr("disabled", "disabled");
      $("#phone_num").val(data[0].phone_num);
      $("#remark").val(data[0].remark);
    },
    error: function (data) {
      console.log("sth wrong");
    }
  });

});

$("#back").click(function () {
  location.href = "../html/userList.html";
});

$("#edit").click(function () {
  location.href = "../html/edit.html?id=" + contactId;
});

function getUrlParam(name) {
  let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  let r = window.location.search.substr(1).match(reg);
  if (r != null) {
    return decodeURIComponent(r[2]);
  } else {
    return null;
  }
}



