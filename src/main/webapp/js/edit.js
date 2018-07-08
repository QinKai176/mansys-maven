let contactId;
$(document).ready(function () {
  contactId = getUrlParam("id");
  // checked[i].checked = true
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

      $("#phone_num").val(data[0].phone_num);
      $("#remark").val(data[0].remark);
    },
    error: function (data) {
      console.log("sth wrong");
    }
  });
});

$('#submit').on('click', function () {
  $.ajax({
    type: "PUT",
    url: '/contact/update.do',
    contentType: "application/json", // 必须有
    data: JSON.stringify({
      "id": parseInt(contactId),
      "name": $('#name').val(),
      "gender": parseInt($(
          "input[type='radio']:checked").val()),
      "phoneNum": $('#phone_num').val(),
      "remark": $('#remark').val()
    }),
    success: function (data) {
      location.href = 'userList.html';
    },
    error: function (data) {
      console.log(""
          + contactId
          + $('#name').val()
          + $("input[type='radio']:checked")
          .val() + $('phone_num').val());
      if (data.status == 400) {
        alert("wrong");
      }
    }
  });
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
