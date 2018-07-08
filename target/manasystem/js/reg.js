function reg() {
  var name = $('#tel').val();
  console.log(name)
  var veri_code = $("#veri-code").val();
  console.log(name + " " + veri_code);
  $.ajax({
    type: "POST",
    url: '/phoneRegister.do',
    contentType: "application/json", // 必须有
    data: JSON.stringify({
      "phone": name,
      "code": veri_code,
    }),
    success: function (data) {
      console.log(data)
      data = JSON.parse(data);
      console.log(data.success)
      if (data.success) {
        alert(data.msg);
      } else {
        alert(data.msg);
      }
    },
    error: function (data) {

    }
  });
}