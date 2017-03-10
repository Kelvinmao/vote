/*Mock.mock('http://g.cn', {
    'success|1': true,
    'number|1-10': 10
});*/
$(function() {
    $('#vote-button').on('click', function() {
        var username = $('#vote-user input').val();
        var password = $('#vote-psw input').val();
        var ProjectID = $('#ProjectID').val();
        if(username == "" || password == "") {
            $('#vote-attention').show();
        }
        else {
            $('#vote-attention').hide();
            $.ajax({
                url: 'userlogin.action',
                type: 'post',
                data:
                {
                    'userNo': username,
                    'password': password,
                    'ProjectID': ProjectID    
                },
                //dataType: "JSON"
            }).done(function(data, status, xhr) {
                 if(data=="fail") {
                    $('#vote-attention').text("请检查学号和密码是否输入正确");
                    $('#vote-attention').show();
                }
                else if(data=="timeout") {
                    $('#vote-attention').text("未有记录或超时，请去前台重新记录");
                    $('#vote-attention').show();
                }
                else if(data=="voteout") {
                    $('#vote-attention').text("您的投票已达上限");
                    $('#vote-attention').show();
                }
                else if(data=="repeat") {
                    $('#vote-attention').text("您已投过此项目");
                    $('#vote-attention').show();
                }
                else {
                    alert("恭喜您投票成功\n您还有"+data+"次投票机会");
                }
                
            }).fail(function() {
                $('#vote-attention').text("提交失败，请检查网络或您的刷卡时间已经超过6小时");
                $('#vote-attention').show();
            });
        }
    });
});




