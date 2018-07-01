package com.itany.netClass.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.util.MyBatisUtil;

public class ObjectFactory {

	private static Map<String, Class<?>> clazzes = new HashMap<String, Class<?>>();
	private static Map<String, Object> objects = new HashMap<String, Object>();

	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(ObjectFactory.class
					.getClassLoader()
					.getResourceAsStream("objects-mybatis.txt")));
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] entry = s.split("=");
				if(entry.length != 2){//空的行?
					//System.out.println("空的行?" + s);
					continue;
				}
				String key = entry[0].trim();
				String value = entry[1].trim();
				Class<?> c = Class.forName(value);
				clazzes.put(key, c);
				if(c.isInterface()){//dao接口
					objects.put(key, c);
					continue;
				}
				objects.put(key, c.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化错误" + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Object getObject(String key) {
		Object obj = objects.get(key);
		if(null == obj){
			synchronized (ObjectFactory.class) {
				if(null == obj){
					try {
						obj = clazzes.get(key).newInstance();
						objects.put(key, obj);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//返回dao的代理对象
		if (obj instanceof Class) {
			Object daoProxy = Proxy.newProxyInstance(
					ObjectFactory.class.getClassLoader(), 
					new Class[] { (Class<?>) obj },
					new DaoHandler(obj)
				);
			return daoProxy;
		}
		return obj;
	}
}

class DaoHandler implements InvocationHandler {
	
	private Object target;
	
	public DaoHandler(Object target) {
		super();
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method method,
			Object[] args) throws Throwable {
		Object result = null;
		try {
			SqlSession session = MyBatisUtil.getSession();
			Object dao = session.getMapper((Class<?>) target);
			Class<?> c = dao.getClass();
			String name = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			Method daoMethod = c.getMethod(name, parameterTypes);
			result = daoMethod.invoke(dao, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("数据访问异常");
		}
		return result;
	}
}
