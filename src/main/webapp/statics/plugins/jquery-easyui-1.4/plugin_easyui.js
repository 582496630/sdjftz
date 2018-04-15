$(function () {
    $.extend($.fn.datagrid.defaults, {
    });
    $.extend($.fn.validatebox.defaults.rules, {
        ip: {
            validator: function (value) {
                if (!isIP(value)) {
                    return false;
                }
                return true;
            },
            message: "IP地址不符合规范"
        },
        mac: {
            validator: function (value) {
                if (value.match(/^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$/) || value.match(/^([0-9A-Fa-f]{2}-){5}[0-9A-Fa-f]{2}$/) || value.match(/^([0-9A-Fa-f]{2}){5}[0-9A-Fa-f]{2}$/)) {
                    return true;
                }
                return false;
            },
            message: "mac地址不符合规范"
        },
        comboboxMustValue: {
            validator: function (value, id) {
                var v = $("#" + id).combobox("getValue");
                return v;
            },
            message: "需要从列表中选择"
        }

    });


    var buttonDir = { north: 'down', south: 'up', east: 'left', west: 'right' };
    $.extend($.fn.layout.paneldefaults, {
        onBeforeCollapse: function () {
            /**/
            var popts = $(this).panel('options');
            var dir = popts.region;
            var btnDir = buttonDir[dir];
            if (!btnDir) return false;

            setTimeout(function () {
                var pDiv = $('.layout-button-' + btnDir).closest('.layout-expand').css({
                    textAlign: 'center', lineHeight: '18px', fontWeight: 'bold'
                });

                if (popts.title) {
                    var vTitle = popts.title;
                    if (dir == "east" || dir == "west") {
                        var vTitle = popts.title.split('').join('<br/>');
                        pDiv.find('.panel-body').html(vTitle);
                    } else {
                        $('.layout-button-' + btnDir).closest('.layout-expand').find('.panel-title')
                        .css({ textAlign: 'left' })
                        .html(vTitle)
                    }

                }
            }, 100);

        }
    });




});


