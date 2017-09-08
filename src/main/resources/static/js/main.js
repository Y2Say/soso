/**
 * Created by So 2017/3/24.
 */
$(function () {

    $("#menuTreeUser").tree({
        data: _menu,
        onClick: function (functions) {

        }
    });
});
function addTab(title, url) {
    var tab = $('#stageTab').tabs('getTab', title);
    if (tab == null) {
        $('#stageTab').tabs('add', {
            title: title,

            content: "<iframe src='" + url + "' width='100%' height='100%' frameborder=0></iframe>",
            closable: true,
            tools: [{
                iconCls: 'icon-mini-refresh',
                handler: function () {
                    alert('refresh');
                }
            }]
        });
    } else {
        $('#stageTab').tabs('select', title);
    }
}