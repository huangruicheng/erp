package cn.java.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONUtils {
	
	
	/**
	 * ������ת��Ϊjson�ַ���
	 * @param response
	 * @param coll
	 * @param excludes
	 */
	
	public static void printJSON(HttpServletResponse response, Collection coll,String[] excludes){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(coll, jc);
		String result = jsonArray.toString();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������ת��Ϊjson�ַ���
	 * @param response
	 * @param coll
	 * @param excludes
	 */
	
	public static void printJSONObj(HttpServletResponse response, Object object,String[] excludes){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(object, jc);
		String result = jsonObject.toString();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
