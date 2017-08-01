package com.tydic.dbs.system.web.action.dataconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fins.gt.util.StringUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.service.DataPermissonCfgService;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.buyer.vo.DataPermissonCfg;
import com.tydic.dbs.commons.Constants;
import com.tydic.dbs.commons.Message;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.system.operator.mapper.SysOperator;

@Controller
@RequestMapping("/sys")
public class DataConfigAction extends BaseAnnotationAction{

	@Autowired
	private DataPermissonCfgService dataPermissonCfgService;
	
	@RequestMapping("/dataConfig.do")
	public String toConfigList(HttpServletRequest request){
		return "system/dataPermission/dataConfigList";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody Map<String,Object> getList(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> params = new HashMap<String,Object>();
		Page dataCfgListPage = new Page();
		String dataFileName = request.getParameter("dataFileName");
		String dataType = request.getParameter("dataType");
		String page = request.getParameter("page");
		
		if(StringUtils.isNotEmpty(dataFileName)){
			params.put("nameLike",dataFileName);
		}
		if(StringUtils.isNotEmpty(dataType)){
			params.put("dataType", dataType);
		}
		if(StringUtils.isNotEmpty(page)){
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		params.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
		
		try {
			dataCfgListPage = dataPermissonCfgService.getPageByParamMap(params);
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("totalNumber", dataCfgListPage.getTotalNumber());
		map.put("totalPage", dataCfgListPage.getTotalPage());
		map.put("rows", dataCfgListPage.getList());
		
		return map;
		
	}
	
	@RequestMapping("/toConfigDetail")
	public @ResponseBody DataPermissonCfg toConfigDetail(HttpServletRequest request, String id)throws Exception {
		DataPermissonCfg dataPerCfg = new DataPermissonCfg();
		try {
			dataPerCfg = dataPermissonCfgService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataPerCfg;
	}
	
	@RequestMapping("/delConfig")
	public @ResponseBody Message delConfig(HttpServletRequest request,HttpServletResponse response){
		Message msg = new Message();
		HttpSession session = request.getSession();
		SysOperator user = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if (null == user) {
			msg.setFlag(false);
			msg.setMsg("删除数据配置信息失败！");
			return msg;
		}
		final String id = request.getParameter("id");
		if (null == id||"".equals(id)) {
			msg.setFlag(false);
			msg.setMsg("删除数据配置信息失败！");
			return msg;
		}
		try {
			boolean falg = dataPermissonCfgService.delete(id);
			if (falg) {
				msg.setFlag(true);
				msg.setMsg("成功删除数据配置信息！");
				return msg;
			}else{
				msg.setFlag(false);
				msg.setMsg("删除数据配置信息失败！");
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(false);
			msg.setMsg("系统异常，操作失败！");
			return msg;
		}
	}
	
	@RequestMapping("/dataCfgAdd")
	public String dataCfgAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String operType=request.getParameter("operType");
		if (Constants.OPER_TYPE_UPDATE.equals(operType)){
			String id=request.getParameter("id");
			DataPermissonCfg dataCfg = dataPermissonCfgService.get(id);
			request.setAttribute("dataCfg",dataCfg);
			request.setAttribute("flag",Constants.OPER_TYPE_UPDATE);
		}else {
			request.setAttribute("flag",Constants.OPER_TYPE_SAVE);
		}
		return "system/dataPermission/dataCfgAddOrModify";
	}
	
	@RequestMapping("/insertDataCfg")
	@ResponseBody
	public Map<String ,Object> insertDataCfg(HttpServletRequest request)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		
		String operType = request.getParameter("type");
		String oldDataNames = request.getParameter("oldDataNames");
		String dataName = request.getParameter("dataName");
		String dataType = request.getParameter("dataType");
		String dataFileName = request.getParameter("dataFileName");
		String id = request.getParameter("id");
		
		String[] oldData = ("".equals(oldDataNames) || null == oldDataNames)?null:oldDataNames.split(",");
		String[] newData = ("".equals(dataName) || null == dataName)?null:(dataName.substring(1)).split(",");
		
		DataPermissonCfg dataCfg = new DataPermissonCfg();
		dataCfg.setName(dataFileName);
		dataCfg.setParentId(dataType);
		
		
		
		try{
			
			List<DataPermissonCfg> list = dataPermissonCfgService.get(dataCfg);
			
			
			if(Constants.OPER_TYPE_SAVE.equals(operType)){//新增
				
				if(list !=null && list.size()>0){
					map.put("message",dataFileName+"已经存在！请重新输入数据文件名称！");
					return map;
				}
				
				long maxId = dataPermissonCfgService.getMaxId();
				
				//1、先增加根节点
				dataPermissonCfgService.save(dataCfg,1);
				//2、增加子节点
				for(int i=0; i<newData.length; i++){
					DataPermissonCfg dataCfgTmp = new DataPermissonCfg();
					dataCfgTmp.setName(newData[i]);
					dataCfgTmp.setParentId(Long.toString(maxId + 1));
					dataPermissonCfgService.save(dataCfgTmp, 1);
				}
			}else {//修改
				if(list !=null && list.size()>0){
					DataPermissonCfg d = list.get(0);
					if(d.getId()!=Long.parseLong(id)){
						map.put("message",dataFileName+"已经存在！请重新输入数据文件名称！");
						return map;
					}
					
				}
				
				//1、先更新父节点
				dataCfg.setName(dataFileName);
				dataCfg.setId(Long.parseLong(id));
				dataCfg.setParentId(dataType);
				dataPermissonCfgService.save(dataCfg,0);
				//2、删除子节点
				dataPermissonCfgService.deleteChild(id);
				//3、增加新的子节点
				for(int i=0; i<newData.length; i++){
					DataPermissonCfg dataCfgTmp = new DataPermissonCfg();
					dataCfgTmp.setName(newData[i]);
					dataCfgTmp.setParentId(id);
					dataPermissonCfgService.save(dataCfgTmp, 0);
				}
				
			}
			
			map.put("success",true);
			if (Constants.OPER_TYPE_SAVE.equals(operType)){
				map.put("message","新增成功");
			}else {
				map.put("message","更新成功");
			}

		}catch (Exception e){
			e.printStackTrace();
			map.put("success",false);
			if (Constants.OPER_TYPE_SAVE.equals(operType)){
				map.put("message","新增失败");
			}else {
				map.put("message","更新失败");
			}
		}
		return map;
	}
	
	public List<Object> compareArray(String[] a, String[] b) {
        List<Object> temp = new ArrayList<Object>();
        for (int i = 0; i < b.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length; j++) {
                if (b[i].equals(a[j])) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                temp.add(b[i]);
            }
        }
        return temp;
    }
	
	
}
