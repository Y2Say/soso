/**
 * Created by So 2017/3/23.
 */
$('#dg').datagrid({
    url:'datagrid_data.json',
    columns:[[
        {field:'id',title:'Id',width:100},
        {field:'name',title:'Name',width:100},
        {field:'funtionIds',title:'FunctionIds',width:100,align:'right'}
    ]]
});