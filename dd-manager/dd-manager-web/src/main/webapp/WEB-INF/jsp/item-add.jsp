<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">商品描述</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>

<script>
    function submitForm() {
        $('#itemAddForm').form('submit',{
            //提交表单到item进行处理
            url:'item',
            onSubmit:function(){
                $('#price').val($('#priceView').val()*100);
               return $(this).form('validate');
            },
            success:function(data){
                if(data>0) {
                    $.messager.alert('温馨提示','恭喜！添加商品成功');
                }
            }
        });
    }
    //实例化富文本编辑器
    var ue=UE.getEditor('container');
    //加载商品类目的树形下拉
    $('#cid').combotree({
        url: 'itemCats?parentId=0',
        required: true,
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#cid').combotree('tree');
            //调用easyui tree组件的options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats?parentId=' + node.id;
        },
        onBeforeSelect: function (node) {
            //判断选中节点是否为叶子节点，如果是，返回true
            var isLeaf = $('#cid').tree('isLeaf', node.target);
            //如果后台管理员选中的不是叶子节点的话，给出警告框
            if (!isLeaf) {
                $.messager.alert('警告', '请选中最终的类别！', 'warning');
                return false;
            }else{
                $.get(
                    //如果不是叶子节点就发送ajax请求，请求查询tb_item_param
                    'itemParam/query/' + node.id,
                        //success
                        function (data) {
                            var $outerTd = $('#itemAddForm .paramsShow td').eq(1);
                            var $ul = $('<ul>');
                            $outerTd.empty().append($ul);
                            if (data) {
                                var paramData = data.paramData;
                                paramData = JSON.parse(paramData);
                                //遍历分组
                                $.each(paramData, function (i, e) {
                                    var groupName = e.group;
                                    var $li = $('<li>');
                                    var $table = $('<table>');
                                    var $tr = $('<tr>');
                                    var $td = $('<td colspan="2" class="group">' + groupName + '</td>');

                                    $ul.append($li);
                                    $li.append($table);
                                    $table.append($tr);
                                    $tr.append($td);

                                    //遍历分组项
                                    if (e.params) {
                                        $.each(e.params, function (_i, paramName) {
                                            var _$tr = $('<tr><td class="param">' + paramName + '</td><td><input></td></tr>');
                                            $table.append(_$tr);
                                        });
                                    }
                                });

                                $("#itemAddForm .paramsShow").show();
                            } else {

                                $("#itemAddForm .paramsShow").hide();
                                $("#itemAddForm .paramsShow td").eq(1).empty();//第二个td
                            }
                        }
                );
            }

        }
    });

</script>