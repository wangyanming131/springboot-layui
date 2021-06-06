/**
 * 端口号管理
 */
var pageCurr;
var form;
$(function () {
    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#portList',
            url: '/settings/port/getPortList',
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type: 'numbers'}
                , {field: 'value', title: '端口号值', align: 'center'}
                , {field: 'description', title: '应用描述', align: 'center'}
                , {field: 'link', title: '链接地址', align: 'center'}
                , {field: 'typeName', title: '启动方式', align: 'center'}
                , {field: 'createdTime', title: '创建时间', align: 'center'}
                , {field: 'updatedTime', title: '修改时间', align: 'center'}
                , {field: 'status', title: '是否有效', align: 'center'}
                , {title: '操作', align: 'center', toolbar: '#optBar'}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);

                $("[data-field='status']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("有效")
                    } else if ($(this).text() == '0') {
                        $(this).text("失效")
                    }
                });
                //得到数据总量
                //console.log(count);
                pageCurr = curr;
            }
        });

        //监听工具条
        table.on('tool(portTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delPort(data, data.id, data.value);
            } else if (obj.event === 'edit') {
                //编辑
                openPort(data, "编辑");
            } else if (obj.event === 'recover') {
                //恢复
                recoverPort(data, data.id);
            }
        });

        //监听提交
        form.on('submit(portSubmit)', function (data) {
            // TODO 校验
            formSubmit(data);
            return false;
        });
    });

    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, layer = layui.layer
            , laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#startTime'
        });
        laydate.render({
            elem: '#endTime'
        });
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });
    });
});

// 提交表单
function formSubmit(obj) {
    $.ajax({
        type: "POST",
        data: $("#portForm").serialize(),
        url: "/settings/port/setPort",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试", function () {
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}

//新增端口号
function addPort() {
    openPort(null, "新增端口号");
}

function openPort(data, title) {
    if (data == null || data == "") {
        $("#id").val("");

    } else {
        $("#id").val(data.id);
        $("#value").val(data.value);
        $("#description").val(data.description);
        $("#link").val(data.link);
        // 需要先赋值后加载下拉框控件

    }
    var list = [];
    var obj0 = {};
    obj0.value = "";
    obj0.text = "请选择";
    list.push(obj0);
    var obj = {};
    obj.value = "1";
    obj.text = "手动";
    list.push(obj);
    var obj2 = {};
    obj2.value = "2";
    obj2.text = "随机启动";
    list.push(obj2)
    setStartingType(form, list, data == null ? "" : data.starting_type, "starting_type");
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['550px'],
        content: $('#setPort'),
        end: function () {
            cleanPort();
        }
    });
}

function delPort(obj, id, name) {

    layer.confirm('您确定要删除' + name + '端口号吗？', {
        btn: ['确认', '返回'] //按钮
    }, function () {
        $.post("/settings/port/updatePortStatus", {"id": id, "status": 0}, function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        });
    }, function () {
        layer.closeAll();
    });

}

//恢复
function recoverPort(obj, id) {
    if (null != id) {
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/settings/port/updatePortStatus", {"id": id, "status": 1}, function (data) {
                if (data.code == 1) {
                    layer.alert(data.msg, function () {
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }
}

/**
 * 加载列表,走后台请求,获取结果展示表格
 * @param obj 筛选条件参数
 */
function load(obj) {
    // 查询条件参数有问题
    var params = {};
    params.value = $("#valueSearch").val();
    params.startTime = $("#startTime").val();
    params.endTime = $("#endTime").val();
    obj.field = params;
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

/**
 * 清空新增/编辑页面域值
 */
function cleanPort() {
    $("#id").val("");
    $("#value").val("");
    $("#description").val("");
    $("#link").val("");
    // 下拉框清空option
    $("#starting_type").html("");
}

/**
 * 加载下拉框控件
 */
function setStartingType(form, data, selectedValue, elementId) {
    $.each(data, function (index, item) {
        var option = new Option(item.text, item.value);
        if (selectedValue != null) {
            // 如果是之前的parentId则设置选中
            if (item.value == selectedValue) {
                option.setAttribute("selected", 'true');
            }
        }
        //往下拉菜单里添加元素
        $('#' + elementId).append(option);
        //这个很重要
        form.render('select');
    })

}