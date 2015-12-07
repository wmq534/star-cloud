package com.github.requestserialid;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MerchantRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    private Map<String, String[]> externParametersMap;

  
    public MerchantRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
       
    }

    @Override
    public String getParameter(String name) {

        if (externParametersMap.containsKey(name)) {
            String[] values = externParametersMap.get(name);
            if (values == null || values.length == 0)
                return null;
            return values[0];
        }
        return this.request.getParameter(name);
    }

    // 扩展方法;原request不存在setParameter方法;不属于J2EE应用规范
    public void setParameter(String name, String value) {
        setParameter(name, new String[] { value });
    }

    // 扩展方法;原request不存在setParameter方法;不属于J2EE应用规范
    public void setParameter(String name, String[] values) {

        if (externParametersMap == null) {
            externParametersMap = new HashMap<String, String[]>();
        }
        externParametersMap.put(name, values);
    }

    @Override
    public Enumeration<String> getParameterNames() {
    	MerchantEnumeration<String> merchantEnumeration  = new MerchantEnumeration<String>(this.request.getParameterNames());
    	if(merchantEnumeration!=null){
    		Set<String> keys  = externParametersMap.keySet();
    		for(String key : keys){
    			merchantEnumeration.addNames(key);
    		}
    	}
    	return merchantEnumeration;
    }

    @Override
    public String[] getParameterValues(String name) {
        if (externParametersMap.containsKey(name))
            return externParametersMap.get(name);
        return this.request.getParameterValues(name);

    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> sourceMap = this.request.getParameterMap();
        if (sourceMap == null)
            return externParametersMap;
        if (externParametersMap != null){
        	Map<String, String[]>  returnMap = new HashMap<String, String[]>(sourceMap);
        	returnMap.putAll(externParametersMap);
        	return returnMap;
        }
        return sourceMap;

    }

}
