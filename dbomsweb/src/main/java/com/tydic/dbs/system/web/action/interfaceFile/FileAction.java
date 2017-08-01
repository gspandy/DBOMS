package com.tydic.dbs.system.web.action.interfaceFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.vo.InfFileLogVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tydic.commons.utils.Page;

/**
 * 
 * @ClassName: BuyerAction 
 * @Description: TODO(后台-商户管理控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-18 下午2:56:59 
 *
 */
@Controller
@RequestMapping("/InfFile")
public class FileAction extends BaseAnnotationAction {
	private final Log log = LogFactory.getLog(FileAction.class);
	@Autowired
	private InfFileLogService infFileLogService;
	@Autowired
    private BussInfoService bussInfoService;
	
	/**
	 * 
	 * @Title: toDownLoad 
	 * @Description: TODO(后台-文件接口下载列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/toDownLoad")
	public String toDownLoad(HttpServletRequest request) throws Exception {
				
		return "system/interfaceFile/downloadfile";
	}
	
	/**
	 * 
	 * @Title: getFileIFDownList 
	 * @Description: TODO(后台-加载文件接口下载列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getFileIFDownList")
	@ResponseBody
	public Map<String,Object> getFileIFDownList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		List<InfFileLogVo> dwonFileVoList = new ArrayList<InfFileLogVo>();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String bussId = "";
		
		if (StringUtils.isNotBlank(page)){
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		if (StringUtils.isNotBlank(rows)){
			params.put(Page.PAGE_SIZE, Integer.parseInt(rows));
		}
		
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	log.info("params:"+params);
    	Page inFilePage = infFileLogService.getPageByParamMap(params);
    	List<InfFileLog> inFileList = inFilePage.getList();
    	if(inFileList !=null && inFileList.size() >0){
    		for(InfFileLog infFileLog : inFileList){
    			InfFileLogVo vo = new InfFileLogVo();
    			BeanUtils.copyProperties(vo,infFileLog);
    			bussId = infFileLog.getBussId();
    			BussInfo bussInfo = bussInfoService.get(bussId);
    			vo.setBussAccount(bussInfo.getBussAccount());
    			dwonFileVoList.add(vo);
    		}
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalNumber", inFilePage.getTotalNumber());
		map.put("totalPage", inFilePage.getTotalPage());
		map.put("fileDownList", dwonFileVoList);
    	
		return map;
	}
	
	/**
	 * 
	 * @Title: downFileInterface
	 * @Description: TODO(后台-文件接口下载) 
	 * @param @param request
	 * @param @param response
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Page    返回类型 
	 * @throws
	 */
	@RequestMapping("/downFileInterface")
	@ResponseBody
	public Map<String,Object> downFileInterface(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		String param = request.getParameter("params");
		if(StringUtils.isNotBlank(param)){
			String[] infiles = param.split(";");
			for (int i=0;i<infiles.length;i++){
				Map<String,String> maps = new HashMap<String,String>();
				String temp = infiles[i]; 
				String[] itempsplit = temp.split("=");
				maps.put(itempsplit[0], itempsplit[1]);
				mapList.add(maps);
			}
			
		}
		//todo调用下载附件方法
		FileUtil fileUtil = new FileUtil();
		String msg = fileUtil.fileDown(request, response, mapList);
		map.put("msg", msg);
		
		return map;
	}
	
	
}


