$(document).ready(function () {
  var user;
  $.ajax({
    type: "GET",
    url: "/user/current.do",
    success: function (data) {
      user = JSON.parse(data);
      console.log(data);
    },
    async: false,
    error: function (data) {
      if (data.status == 302) {
        console.log("302");
        location.href = "/html/login/login.html";
      }
    }
  });

  $.ajax({
    type: 'GET',
    url: '/contact.do?ownerId=' + user.id,
    async: false,
    success: function (data) {
      data = JSON.parse(data);
      let _data = [];
      for (let i = 0; i < data.length; i++) {
        var gender = data[i].gender;
        (gender == 2) ? gender = "女" : gender = "男";
        let obj = {
          name: data[i].name,
          gender: gender,
          phone_num: data[i].phone_num,
          remark: data[i].remark,
          operation: `
			            <button data-id=${data[i].id} class="find"><i class="fa fa-eye"></i>查看</button>
			            <button data-id=${data[i].id} class="edit"><i class="fa fa-edit"></i>编辑</button>
			            <button data-id=${data[i].id} class="delete"><i class="fa fa-trash"></i>删除</button>
			            `
        };
        _data.push(obj);
      }

      $('#table').DataTable({
        data: _data,
        columns: [
          {data: 'name', title: '姓名', orderable: false},
          {data: 'gender', title: '性别', orderable: false},
          {data: 'phone_num', title: '联系方式', orderable: false},
          {data: 'remark', title: '备注', orderable: false},
          {data: 'operation', title: '操作', orderable: false}
        ],
        language: {
          "sProcessing": "处理中...",
          "sLengthMenu": "每页展示 _MENU_ 条记录",
          "sZeroRecords": "没有匹配结果",
          "sInfo": "当前显示第 _START_ - _END_ 条记录（共 _TOTAL_ 条）",
          "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
          "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
          "sInfoPostFix": "",
          "sSearch": "搜索:",
          "sUrl": "",
          "sEmptyTable": "表中数据为空",
          "sLoadingRecords": "载入中...",
          "sInfoThousands": ",",
          "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
          },
          "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
          }
        },
        "ordering": false,
      });
    },
    error: function (data) {
      if (data.status == 302) {
        location.href = "/html/login.html";
      }
    },
  });
});

$(document).on('click', '.delete', function () {
  var msg = "您真的要删除吗？\n\n请确认！";
  if (confirm(msg) == true) {
    let currentId = $(this).data('id');
    $.ajax({
      type: 'DELETE',
      url: "/contact/delete.do?contactId=" + currentId,
      success: function (data) {
        location.href = '../html/userList.html';
      }
    });
  } else {
    return false;
  }
})

$(document).on('click', '.edit', function () {
  let currentId = $(this).data('id');
  location.href = 'edit.html?id=' + currentId + '';
})

$(document).on('click', '.find', function () {
  let currentId = $(this).data('id');
  location.href = 'contact.html?id=' + currentId + '';
})

$(document).on('click', '#logout', function () {
  $.ajax({
    type: "GET", // 用post方式传输
    url: '/logout.do', // 目标地址
    success: function (data) {
      location.href = '/';
    }
  });
})


