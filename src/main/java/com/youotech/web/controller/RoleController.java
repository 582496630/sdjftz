package com.youotech.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.Resources;
import com.youotech.entity.RoleInfo;
import com.youotech.service.ResourcesService;
import com.youotech.service.RoleService;
import com.youotech.shiro.utils.Const;
import com.youotech.util.Constant;
import com.youotech.util.LogAnnotation;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourcesService resourcesService;

	@RequestMapping("selectRoleAll.do")
	@ResponseBody
	public Map<String, Object> selectRoleAll() {
		Map<String, Object> map = new HashMap<>();
		List<RoleInfo> list = roleService.selectRoleAll();
		if (list.size() > 0) {
			map.put(Constant.DEFAULT_SUCCESS_MSG, list);
		}
		return map;
	}

	@RequestMapping("selectRoleForPage.do")
	@ResponseBody
	public Map<String, Object> selectRoleForPage(Integer pageNumer, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		if (pageNumer == null || "".equals(pageNumer)) {
			pageNumer = 1;
		}
		if (pageSize == null || "".equals(pageSize)) {
			pageSize = 10;
		}
		PageInfo<RoleInfo> info = roleService.selectRoleForPage(pageNumer, pageSize);
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		map.put(Constant.DEFAULT_ROWS, info.getList());
		return map;
	}

	@RequestMapping("rolePermission.do")
	@ResponseBody
	public Object rolePermission(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Resources resource = new Resources();
		resource.setDisabled(0);
		List<Resources> list = resourcesService.selectAll();
		if(id!=null){
			List<Integer> resourcesIds = resourcesService.selectRRByRole(id);
			for (Resources r : list) {
				if (resourcesIds.contains(r.getId())) {
					r.setChecked(Const.CHECKED);
				}
			}
		}
		map.put("list", list);
		return map;
	}

	@RequestMapping("updateRoleResources.do")
	@ResponseBody
	public Object updateRoleResources(Integer id, String nodes) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (id == null || id.equals("")) {
			map.put("code", 400);
			return map;
		}
		String[] array = nodes.replace("[", "").replace("]", "").split(",");
		List<Map<String, Object>> list = new ArrayList<>();
		for (String resourcesId : array) {
			Map<String, Object> item = new HashMap<>();
			item.put("roleId", id);
			item.put("resourcesId", resourcesId.trim());
			list.add(item);
		}
		// 批量删除
		int m = roleService.deleteRoleResources(id);

		// 批量插入resourcesid
		int n = roleService.insertRoleResources(list);

		if (n > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	@RequestMapping("updateRoleInfo.do")
	@ResponseBody
	public Object updateRoleInfo(Integer id, String rolename, String dec, String authodec) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (id == null || id.equals("")) {
			map.put("code", 400);
			return map;
		}

		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setId(id);
		roleInfo.setDec(dec);
		roleInfo.setAuthodec(authodec);

		// 批量删除
		int i = roleService.updateRoleInfo(roleInfo);

		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	@RequestMapping("addRoleInfo.do")
	@ResponseBody
	public Object addRoleInfo(String rolename, String dec, String authodec) {
		Map<String, Object> map = new HashMap<String, Object>();

		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setRolename(rolename);
		roleInfo.setDec(dec);
		roleInfo.setAuthodec(authodec);

		// 批量删除
		int i = roleService.addRoleInfo(roleInfo);

		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	@RequestMapping("delRoleInfo.do")
	@ResponseBody
	public Object delRoleInfo(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (id == null || id.equals("")) {
			map.put("code", 400);
			return map;
		}

		// 删除
		int i = roleService.delRoleInfo(id);

		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}
}
