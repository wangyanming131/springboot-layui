/**
 * 支付方式管理
 */
var pageCurr;
var form;
$(function () {
    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#payTypeList',
            url: '/settings/payType/getPayTypeList',
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
                , {field: 'typeCode', title: '支付方式代码', align: 'center'}
                , {field: 'typeName', title: '中文名称', align: 'center'}
                , {field: 'createdTime', title: '创建时间', align: 'center'}
                , {field: 'updatedTime', title: '修改时间', align: 'center'}
                , {field: 'typeStatus', title: '是否有效', align: 'center'}
                , {title: '操作', align: 'center', toolbar: '#optBar'}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                $("[data-field='typeStatus']").children().each(function () {
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
        table.on('tool(payTypeTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delPayType(data, data.id, data.typeName);
            } else if (obj.event === 'edit') {
                //编辑
                openPayType(data, "编辑");
            } else if (obj.event === 'recover') {
                //恢复
                recoverPayType(data, data.id);
            }
        });

        //监听提交
        form.on('submit(payTypeSubmit)', function (data) {
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
        data: $("#payTypeForm").serialize(),
        url: "/settings/payType/setPayType",
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

//新增支付方式
function addPayType() {
    openPayType(null, "新增支付方式");
}

function openPayType(data, title) {
    if (data == null || data == "") {
        $("#id").val("");
        $("#typeCode").removeAttr("readonly");
    } else {
        $("#id").val(data.id);
        $("#typeCode").val(data.typeCode);
        $("#typeName").val(data.typeName);
        $("#typeCode").attr("readonly", "readonly")
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['550px'],
        content: $('#setPayType'),
        end: function () {
            cleanPayType();
        }
    });
}

function delPayType(obj, id, name) {

    layer.confirm('您确定要删除' + name + '支付方式吗？', {
        btn: ['确认', '返回'] //按钮
    }, function () {
        $.post("/settings/payType/updatePayTypeStatus", {"id": id, "typeStatus": 0}, function (data) {
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
function recoverPayType(obj, id) {
    if (null != id) {
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/settings/payType/updatePayTypeStatus", {"id": id, "typeStatus": 1}, function (data) {
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
    params.typeName = $("#typeNameSearch").val();
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
function cleanPayType() {
    $("#typeCode").val("");
    $("#typeName").val("");
}
