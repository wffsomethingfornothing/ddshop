
var ddshop = {
    //点击左侧导航树上的节点，添加选项卡
    registerMenuEvent:function(){
        var _this =this;
        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        $tree.tree({
            onClick:function(node){
                var href = node.attributes.href;//item-add
                var text=node.text;
                _this.addTabs(text,href);
            }
        });
    },
    addTabs:function(text,href){
        if ($('#tab').tabs('exists',text)){
            $('#tab').tabs('select',text);
        }else {
            $('#tab').tabs('add', {
                title: text,
                href: href,
                closable: true
            });
        }
    }
};



