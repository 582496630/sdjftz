$(function () {
    document.onmousemove = function (e) {
        //$("#tooltip").css({
        //    left: e.clientX+10,
        //    top: e.clientY-10
        //});
    };

    document.onmousedown = function (event) {

        var wx = event.clientX;
        var wy = event.clientY;

        var d_left = document.getElementById('tooltip').offsetLeft;
        var d_top = document.getElementById('tooltip').offsetTop;
        var d_width = document.getElementById('tooltip').clientWidth + 20;
        var d_height = document.getElementById('tooltip').clientHeight;
        if (wx < d_left || wy < d_top || wx > (d_left + d_width) || wy > (d_top + d_height)) {
            hideTooltip();
        }
        
    }

})

function showTooltip(e, str, node) {

    var ap = node.zGetAbsolutePosition();

    $("#tooltip").html(str).css({
        left: ap.left  + node.width-5,
        top: ap.top +  + node.height / 2-5
    }).fadeIn(400);


}
function hideTooltip(e, str) {
    $("#tooltip").hide();
}


function scene_updateSwitchPorts(scene) {
    var switchPorts = scene.switchPort.switchPorts;
    //排序
    for (var i = 0; i < switchPorts.length-1; i++) {
        for (var j = 0; j < switchPorts.length-1; j++) {
            var r1 = switchPorts[j];
            var r2 = switchPorts[j + 1];
            var r1_strs = r1.name.split('/');
            var r2_strs = r2.name.split('/');
            for (var k = 0; k < r1_strs.length; k++) {
                var k_r1_str = r1_strs[k];
                var k_r2_str = r2_strs[k];
                if (k_r1_str == k_r2_str) {
                    continue;
                }

                if (k > 0) {
                    k_r1_str = parseInt(k_r1_str);
                    k_r2_str = parseInt(k_r2_str);
                }
            
                if (k_r1_str > k_r2_str) {
                    switchPorts[j + 1] = r1;
                    switchPorts[j] = r2;
                }
                break;
            }
        }
    }

    var xx = scene.switchPort.xx;
    var yy = scene.switchPort.yy;
    var tn = scene.switchPort.tn;
    var ww = scene.switchPort.ww;
    var hh = scene.switchPort.hh;

    var zl = 0;
    var len_1 = 1;
    var index_1 = 0;
    for (var i = 1; i < switchPorts.length; i++) {
        var r1 = switchPorts[i - 1];
        var r2 = switchPorts[i];
        if (r1.name.split('/')[0] != r2.name.split('/')[0]) {
            zl++;
            index_1 = 1;
        } else {
            index_1++;
        }
        if (index_1 % 2 == 1) {
            len_1++;
        }
    }

    //for (var i = 0; i < switchPorts.length-1; i++) {
    //    var r1 = switchPorts[i];
    //    var r2 = switchPorts[i + 1];
    //    if (r1.name.split('/')[0] != r2.name.split('/')[0]) {
    //        zl++;
    //        index_1 = 0;
    //    } else {
    //        index_1++;
    //    }
    //    if (index_1 % 2 == 1) {
    //        len_1++;
    //    }
    //}

    var dw = 610 / (2 + zl + len_1);

    if (dw > 35) {
        dw = 35;
    } else if (dw < 25) {
        dw = 25;
    }


    var x = 0;


    var wh = dw;

    var y = hh / 2 - wh / 2 - 15;
    var list = [];
    var fx = false;


    for (var i = 0; i < switchPorts.length; i++) {
        var r = switchPorts[i];

        var node = new JTopo.Node();
        switch (r.name.toLowerCase().charAt(0)) {
            case "e":
            case "f":
            default:
                //百兆
                node.img1 = "/images/bz_up_online.png"
                node.img2 = "/images/bz_up_offline.png";
                node.img = "/images/bz_up_.png";
                node.img2_adminOpen = "/images/bz_up_offline_adminOpen.png";

                break;
            case "g":
                node.img1 = "/images/qz_up_online.png"
                node.img2 = "/images/qz_up_offline.png";
                node.img = "/images/qz_up_.png";
                node.img2_adminOpen = "/images/qz_up_offline_adminOpen.png";
                //千兆
                break;
            case "f":
            case "c":
                node.img1 = "/images/wz_up_online.png"
                node.img2 = "/images/wz_up_offline.png";
                node.img = "/images/wz_up_.png";
                node.img2_adminOpen = "/images/wz_up_offline_adminOpen.png";
                //万兆
                break;

        }

        node.setImage(node.img);
        node.width = wh;
        node.height = wh;
        node.text = i + 1;
        node.textPosition = "Top_Center";
        node.font = "" + parseInt(wh / 3) + "px 微软雅黑";
    
        if (i > 0) {
            if (r.name.split('/')[0]!= switchPorts[i - 1].name.split('/')[0]) {
                x += dw;
                fx = false;
            }
        }
        if (!fx) {
            x += wh;
        }

        node.x = x;
        node.y = y;
        fx = !fx;

        if (fx) {
            node.rotate = 3.14;
            node.y += wh;
            node.textPosition = "Bottom_Center";
        }
        
        node.entity = r;




        node.mouseover(function (e) {
            if (this.tooltipText) {
                showTooltip(e, this.tooltipText, this);
            }
        });

        node.mouseout(function (e) {
            hideTooltip();
        });

        node.x += xx;
        node.y += yy;

        
        node.dragable = false;

        scene.add(node);
        list.push(node);
    }
    if (x + 50 > 610) {
        scene.stage.canvas.width = x + 50;
       
    } else {
        scene.stage.canvas.width = 610;
    }
    setTimeout(function () {
        $("#" + scene_switchPort.stage.canvas.id).parent("div").scrollLeft(0)
    })

    querySwitchPortState(tn, list);
    if (scene.switchPort.dealNode) {
        for (var i = 0; i < list.length; i++) {
            scene.switchPort.dealNode(list[i]);
        }
    }
    return list;

}

function huaSwitchPorts(scene, tn) {
        scene.switchPort.switchPorts = tn.switchPorts;
        scene.switchPort.tn = tn;
    return scene_updateSwitchPorts(scene);
}

function getMainPort(entity) {
    var mainPort = null;
    var ports = entity.ports;
    if (ports) {
        for (var j = 0; j < ports.length; j++) {
            var port = ports[j];
            if (port.star == 1 && port.tjId) {
                mainPort = port;
                break;
            }
        }
    }
    return mainPort;
}
function smallTime(t) {
    return "<span style='color:green;'>【</span>" + t.split(" ")[1] + "<span style='color:green;'>】</span>";
}
function getItemByName(name, tn) {
    var item = null;
    for (var i = 0; i < tn.ports.length; i++) {
        var port = tn.ports[i];
        var tjId = port.tjId;
        item = itemData[tjId + "_"+name];
        if (item) {
            break;
        }
    }

    return item;

}

function updateSwitchPortInfo(ele, tn) {


    var tjId = null;
    //if (mainPort) {
   //     tjId = mainPort.tjId;
   // }

    var node = ele;
    var item = getItemByName("ifAdminStatus[" + node.entity.name + "]", tn);
    node.entity.stateAdmin = null;
    if (item) {
         node.entity.stateAdmin = item.lastvalue;
    }

    item = getItemByName("ifOperStatus[" + node.entity.name + "]", tn);
    node.entity.state = null;
    if (item) {
        if (!item.overtime || item.overtime <= 0) {
            node.entity.state = item.lastvalue;
        }

        if (node.entity.state == 1) {
            node.setImage(node.img1);
        } else if (node.entity.state == 2) {
            if (node.entity.stateAdmin == 1) {
                node.setImage(node.img2_adminOpen);
            } else {
                node.setImage(node.img2);
            }
        }
        else {//未知
            node.setImage(node.img);
        }

        node.entity.lastclock = item.lastclock;
        node.entity.state = item.lastvalue;//
    }

    //设置tooltipText内容

    //if (node.entity.mac) {
    //    str += "<label>端口Mac:</label>" + node.entity.mac + "<br>";
    //}

    var str = "";
    str += node.entity.name;

    if (node.entity.ifVlanType == 1) {
        str += "<span class='ifVlanType'>trunk</span>"
    }
    else if (node.entity.ifVlanType == 2) {
        str += "<span class='ifVlanType'>access</span>";
    }



    str += "<br>";
    var item = getItemByName("ifDescr[" + node.entity.name + "]", tn);
    if (item) {
        str += "<label>别名:</label>" + item.lastvalue + "<br>";
    }

    str += "<label>状态:</label>";

    switch (node.entity.state) {
        case "1":
            str += "开启";
            break;
        case "2":
            str += "关闭";
            break;
        default:
            str += "未知";
    }
    str += "/";
    switch (node.entity.stateAdmin) {
        case "1":
            str += "开启";
            break;
        case "2":
            str += "关闭";
            break;
        default:
            str += "未知";
            break;
    }

    if (node.entity.lastclock) {
        str += smallTime(node.entity.lastclock);
    }
    str += "<br>";




    item = getItemByName("ifInOctets[" + node.entity.name + "]", tn);
    if (item) {
        var val = "";
        if (item.lastvalue < 1000) {
            val = item.lastvalue + " bps";
        }
        else if (item.lastvalue < 1000000) {
            val = (item.lastvalue / 1000).toFixed(2) + " Kbps";
        }
        else if(item.lastvalue<1000000000) {
            val = (item.lastvalue / 1000000).toFixed(2) + " Mbps";
        } else {
            val = (item.lastvalue / 1000000000).toFixed(2) + " Gbps";
        }
        
        str += "<label>流入流量:</label>" + val + smallTime(item.lastclock) + "<a href='javascript:switchTrend(2," + node.entity.id + ")' style='color:green;'>趋势</a><br>";
    }

    item = getItemByName("ifOutOctets[" + node.entity.name + "]", tn);
    if (item) {

        var val = "";
        if (item.lastvalue < 1000) {
            val = item.lastvalue + " bps";
        }
        else if (item.lastvalue < 1000000) {
            val = (item.lastvalue / 1000).toFixed(2) + " Kbps";
        }
        else if (item.lastvalue < 1000000000) {
            val = (item.lastvalue / 1000000).toFixed(2) + " Mbps";
        } else {
            val = (item.lastvalue / 1000000000).toFixed(2) + " Gbps";
        }

        str += "<label>流出流量:</label>" + val + smallTime(item.lastclock) + "<a href='javascript:switchTrend(3," + node.entity.id + ")' style='color:green;'>趋势</a><br>";
    }
    item = getItemByName("ifMtu[" + node.entity.name + "]", tn);
    if (item) {
        str += "<label>端口MTU:</label>" + item.lastvalue  + "<br>";
    }

    item = getItemByName("ifDescr[" + node.entity.name + "]", tn);
    if (item) {
       // str += "<label>端口描述:</label>" + item.lastvalue + smallTime(item.lastclock) + "<br>";
    }


    item = getItemByName("ifSpeed[" + node.entity.name + "]", tn);
    var isSpeed = false;

    if (item) {
        isSpeed = true;
        var val = item.lastvalue;
        var len = ("" + item.lastvalue).length;
        if (len < 4) {
            val = item.lastvalue + " bps";
        }
        else if (len < 7) {
            val = item.lastvalue / 1000 + " Kbps";
        } else if (len < 10) {
            val = item.lastvalue / 1000000 + " Mbps";
        }
        else {
            val = item.lastvalue / 1000000000 + " Gbps";
        }

        str += "<label>速率:</label>" + val + "<br>";
       
    }
    if (!isSpeed) {
        item = getItemByName("ifHighSpeed[" + node.entity.name + "]", tn);
        if (item) {

            var val = item.lastvalue;
            var len = ("" + item.lastvalue).length;
            if (len < 4) {
                val = item.lastvalue + " Mbps";
            } else {
                val = item.lastvalue / 1000 + " Gbps";
            }

            str += "<label>速率:</label>" + val + "<br>";
           
        }
    }

  



    var topo_node = ele;
    if (topo_node) {
        var tn = null;
        if (topo_node.findDeviceNode) {
            tn = topo_node.findDeviceNode();
        }
        if (tn) {
            var pNode = tn.getParentNode();
            if (pNode && pNode.entity && pNode.entity.classify == 1) {

                var portStr = "";
                if (topo_node.inLinks) {
                    for (var i = 0; i < topo_node.inLinks.length; i++) {
                        var link = topo_node.inLinks[i];
                        if (link.nodeA.entity) {
                            var port = link.zGetNodeAPort();
                            var switchPort = null;
                            if (port) {
                                switchPort = link.zGetNodeZSwitchPort();
                            }
                            if (switchPort && switchPort.id == node.entity.id) {
                                portStr += link.nodeA.text + port.name + "(" + port.ip + ")<br />";
                            }
                        }
                    }
                }
                if (portStr != "") {
                    str += "<hr>" + portStr;
                }

            }

        }

    }


    //列举端口下mac和ip

    //if (node.entity.connMacIp) {
    //    str += "<label>检测到下联主机:</label><br>";
    //    var ss = node.entity.connMacIp.split(';');
    //    for (var i = 0; i < ss.length; i++) {
    //        var s = ss[i];
    //        str += s + "<br>";
    //    }
    //}
    if (node.entity.asps && node.entity.asps.length > 0) {
        str += "<label>检测到ARP信息:</label><br>";
        for (var i = 0; i < node.entity.asps.length; i++) {
            var asp = node.entity.asps[i];
            if (asp.mac) {
                str += asp.mac;
            }
            str += "-";
            if (asp.ip) {
                str += asp.ip;
            }
            str += "<br>";
        }
    }


   




    node.tooltipText = str;


    //设置tooltipText内容

}
//刷新交换机端口状态
function refreshSwitchPort() {
    scene_updateSwitchPorts(scene_switchPort);

}

//查询交换机端口信息
function querySwitchPortState(tn, eles) {
    var mainPort = getMainPort(tn);

    if (eles.length == 0) {
        return;
    }
    var itemIds = "";
    for (var i = 0; i < eles.length; i++) {
        var ele = eles[i];
        if (ele.entity && ele.entity.itemIds) {
            itemIds += "," + ele.entity.itemIds;
        }
        //暂时根据现有的信息更新
        //updateSwitchPortInfo(ele, mainPort);
    }

    if (itemIds == "") {
        return;
    }
    $("#refreshSwitchPort").linkbutton({iconCls:"icon-loading"});
    $.post("/Zabbix", { action: "queryItemData", itemIds: itemIds.substr(1) }, function (result) {
        if (result.success === false) {
            $.messager.show({ msg: "加载网口信息出错,请刷新页面后重试", title: "加载数据信息出错", iconCls: "icon-cancel" })
            return;
        }
        for (var key in result) {
            itemData[key] = result[key];
        }
        for (var i = 0; i < eles.length; i++) {
            eles[i].hideText = "加载数据中...";
            updateSwitchPortInfo(eles[i],tn);
        }
    }, "json").complete(function () {
        $("#refreshSwitchPort").linkbutton({ iconCls: "icon-reload" });

    }).error(function () {
        $.messager.show({ msg: "加载网口信息出错,请刷新页面后重试", title: "系统错误", iconCls: "icon-cancel" })
    });
}
//查询网卡信息
function queryPortState(tn, eles) {
    if (eles.length == 0) {
        return;
    }
    var itemIds = "";
    for (var i = 0; i < eles.length; i++) {
        var ele = eles[i];
        itemIds += "," + ele.entity.itemIds;
    }
}


function scene_updatePort(scene) {
    //画图
    var ports = scene.port.ports;

    var dw = 10;
    if (ports.length > 9) {
        dw = 90 / ports.length;
    }


    var wh = dw * 4;
    var x = dw*5;
    var ju = dw*6;
    var y = scene.port.hh / 2 - wh / 2;

    var list = [];
    for (var i = 0; i < ports.length; i++) {
        var r = ports[i];
        var node = new JTopo.Node();    // 创建一个节点
        node.img = "/images/bz_up_offline.png";
        node.img_star = "/images/bz_up_offline_star.png";
        //node.img_error = "/images/bz_up_offline_error.png";

        var portStatus = getPortStatus(r);
        if (r.star == 1) {
            node.setImage(node.img_star);
        } else {
            node.setImage(node.img);
        }
        switch (portStatus) {
            case "error":
                node.alarm = "";
                node.nodeAlarmColor = null;
                node.notDrawAlarmImage = false;
                node.nodeAlarmColor = "red_port";
                break;
            case "normal":
                //状态正常
                node.alarm = "";
                node.nodeAlarmColor = null;
                node.notDrawAlarmImage = false;
                node.nodeAlarmColor = "green_port";
                break;
            default:
                node.alarm = "";
                node.nodeAlarmColor = null;
                node.alarmColor = null;
                node.notDrawAlarmImage = false;
                node.nodeAlarmColor = null;
                break;

        }


        node.width = wh;
        node.height = wh;
        node.text = r.name;
        node.textPosition = "Bottom_Center";

        node.setLocation(x, y);    // 设置节点坐标
        node.rotate = 3.14;

        node.x += scene.port.xx;
        node.y += scene.port.yy;
        node.mouseover(function (e) {

            var str = "";
            str += "<label>NAME:</label>" + this.entity.name + "<br>";
            //            str += "状态:" + (this.entity.state == 1 ? "开启" : "关闭<br>");
            str += "<label>IP:</label>" + this.entity.ip + "<br>";
            str += "<label>MAC:</label>" + this.entity.mac + "<br>";

          //  str += "checkType:" + this.entity.checkType + "<br>";


           

            if (this.entity.fq) {
                var fqStr = "";
                switch (this.entity.fq) {
                    case 1:
                        fqStr = "I区";
                        break;
                    case 2:
                        fqStr = "II区";
                        break;
                    case 3:
                        fqStr = "III区";
                        break;
                    case 4:
                        fqStr = "IV区";
                        break;
                    default:
                        fqStr = this.entity.fq;
                        
                }
                str += "<label>分区:</label>" + fqStr + "<br>";
            }

            if (this.entity.pm) {
                str += "平面:" + this.entity.pm + "<br>";
            }
            if (window.itemData) {
                str += "<hr>";
                if (this.entity.star == 1) {
                    str += "<label>状态:</label>";
                    //itemData[this.entity.tjId+"_"]
                    str += getPortStatusStr(this.entity);
           
                    str += "<hr>";
                    var item_icmpping = itemData[this.entity.tjId + "_icmpping"];
                    var item_sysUpTime = itemData[this.entity.tjId + "_sysUpTime"];
                    var item_tcp2404 = itemData[this.entity.tjId + "_net.tcp.service[tcp,,2404]"];
                    if (item_icmpping) {
                        str += "PING结果: " + (item_icmpping.lastvalue == 1 ? "UP" : "DOWN") + "  <span style='color:green;'>【</span>" + item_icmpping.lastclock + "<span style='color:green;'>】</span><br>";
                    }
                    if (item_sysUpTime) {
                        str += "系统UP时间: " + SecoToStr(item_sysUpTime.lastvalue) + "   <span style='color:green;'>【</span>" + item_sysUpTime.lastclock + "<span style='color:green;'>】</span><br>";

                    }
                    if (item_tcp2404) {
                        str += "IEC104 2040端口: " + (item_icmpping.lastvalue == 1 ? "正常" : "异常") + "  <span style='color:green;'>【</span>" + item_icmpping.lastclock + "<span style='color:green;'>】</span><br>";
                    }

                }
                else {
                    str += "未监视<br>";
                }

            }

            showTooltip(e, str,this);

        });
        node.mouseout(function (e) {
            hideTooltip();
        });

        node.entity = r;
        list.push(node);
        scene.add(node);
        node.dragable = false;
        x += ju;
    }

    if (scene.port.dealNode) {
        for (var i = 0; i < list.length; i++) {
            scene.port.dealNode(list[i]);
        }
    }


    return list;
}

function huaPorts(scene, tn) {
    scene.port.ports = cloneArr(tn.ports);
    scene.port.tn = tn;
    var eles = scene_updatePort(scene);
    return eles;
}

function getPortStatusStr(port) {
    var status = getPortStatus(port);
    var str = "";
    switch (status) {
        case "noStar":
            str += "<strong style='color:gray'>未监视</strong>";
            break;
        case "waitResult":
            str += "<strong style='color:gray'>等待结果</strong>";
            break;
        case "waitSyn":
            str += "<strong style='color'>等待监视</strong>";
            break;
        case "normal":
            str += "<strong style='color:green'>正常</strong>";
            break;
        case "error":
            str += "<strong style='color:red'>异常</strong>";
            break;
        default:
            str += "...";
            break;
    }

    if (port.star == 1) {
        if (getPortSnmpStatus(port) != "normal") {
            snmpIsError = true;
            str += " " + "<a href='javascript:;' class='snmp snmp_error' title='" + port.snmp_error + "'>snmp</a>";
        }
        if (getPortIcmpStatus(port) != 1) {
            str += " " + "<a href='javascript:;' class='ping ping_error'>ping</a>";
        }
    }
    return str;
}

function getPortSnmpStatus(port) {
    var item_sysUpTime = itemData[port.tjId + "_sysUpTime"];
    if (item_sysUpTime && (!item_sysUpTime.overtime || item_sysUpTime.overtime <= 0)) {
        if (item_sysUpTime.lastvalue>0) {
            return "normal";//正常
        } else {
            return "error";//异常
        }
    } else {
        return "unknow";
    }

}
function getPortIcmpStatus(port) {
    var item_icmpping = itemData[port.tjId + "_icmpping"];
    if (item_icmpping && (!item_icmpping.overtime || item_icmpping.overtime <= 0)) {
        if (item_icmpping.lastvalue == 1) {
            return 1;//正常
        } else {
            return 2;//异常,无法ping
        }
    } else {
        return 0;
    }

}


function getPortStatus(port) {
    var status = null;
    if (port.star != 1) {//未监视
        return "noStar";
    }
    if (!window.itemData) {
        return "waitResult";//等待结果
    }
    if (!port.tjId) {
        return "waitSyn";//等待监视
    }


    var item_icmpping = itemData[port.tjId + "_icmpping"];
    var item_sysUpTime = itemData[port.tjId + "_sysUpTime"];

    if (!item_icmpping && !item_sysUpTime) {
        return "waitResult";//等待结果
    }

    if (port.checkType == "icmp") {
        if (item_icmpping && (!item_icmpping.overtime || item_icmpping.overtime <= 0) && item_icmpping.lastvalue == 1) {
            return "normal";//状态正常
        }
    } else if (port.checkType == "snmp") {
        if (item_sysUpTime && (!item_sysUpTime.overtime || item_sysUpTime.overtime <= 0) && item_sysUpTime.lastvalue > 0) {
            return "normal";//状态正常
        }
    }else{
        return "waitResult";
    }
    
    return "error";//状态异常

}


function showSwitchPortCanvas() {

    $("#canvas_switchPort").dialog({ resizable: true });
    $("#dlg_device").dialog("resize");

}
function switchTrend(type, switchPortId) {
    var content = "<iframe frameborder='0' scrolling='yes' style='width:99.8%;height:98.8%;' src='"+"/page/topology/chart/SwitchTrend.html?type=" + type + "&switchPortId=" + switchPortId+"&dialog=true'></iframe>";

    window.parent.parent.showDialog({
        iconCls:"icon-search",
        modal:true,
        title: "流量趋势",
        width: 1000,
        height: 720,
        content:content
        });

    //window.open("/page/topology/chart/SwitchTrend.html?type=" + type + "&switchPortId=" + switchPortId);
}
function lookGjjl(deviceId) {
    if (!deviceId) {
        var curEle = $("#nodeMenu").data("curEle");
        var tn = curEle.findDeviceNode();

        if (tn == null) {
            $.messager.alert("提示", "设备不存在", "info");
            return;
        }
        deviceId = tn.entity.id;

    }


    var content = "<iframe frameborder='0' scrolling='no' style='width:100%;height:100%;' src='/page/alarm/AlarmManager.html?dialog=true&deviceId=" + deviceId + "'></iframe>";
    content = "<div style='height:100%;overflow-y:hidden;' fit='true'>" + content + "</div>"

    showDialog({
        title: "告警记录",
        icon:"icon-gjjl",
        width: 800,
        height: 500,
        content: content
    })

}