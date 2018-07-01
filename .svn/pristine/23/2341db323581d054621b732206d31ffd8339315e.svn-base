package com.itany.netClass.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;
import com.itany.netClass.dao.UserDao;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.DataAccessException;

public class MyBatisUtil {
	private static SqlSessionFactory sf;
	private static ThreadLocal<SqlSession> threadLocal;
	
	static{
		try {
			threadLocal = new ThreadLocal<SqlSession>();
			sf = new SqlSessionFactoryBuilder()
				.build(MyBatisUtil.class
								  .getClassLoader()
								  .getResourceAsStream("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("MyBaitsUtil初始化错误");
		}
	}
	
	public static SqlSession getSession(){
		SqlSession session = threadLocal.get();
		try {
			if(session == null){
				session = sf.openSession(false);
				threadLocal.set(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("获取SqlSession失败",e);
		}
		return session;
	}
	
	public static void closeSession(){
		SqlSession session = threadLocal.get();
		if(session != null){
			session.close();
			threadLocal.remove();
		}
	}
	
	
	public static void main(String[] args) {
		SqlSession session = MyBatisUtil.getSession();
		UserDao dao = session.getMapper(UserDao.class);
		//id,name,status,parent_id,children
		//获取一级类别
		List<CourseType> list = dao.selectTypes();
		
		String json = JSON.toJSONString(list);
		System.out.println(json);
		
		session.commit();
		int size = list.size();
		System.out.println("size=" + size);
		for (int i = 0; i < size; i++) {
			CourseType p1 = list.get(i);
			System.out.println("一级类别:" + p1.getTypeName() + ",parentId1=" + p1.getParentId());
			List<CourseType> p2s = p1.getChildren();
			int size2 = p2s.size();
			for (int j = 0; j < size2; j++) {
				CourseType p2 = p2s.get(j);
				System.out.println("\t二级类别:" + p2.getTypeName()  + ",parentId2=" + p2.getParentId() + ":");
				List<CourseType> p3s = p2.getChildren();
				int size3 = p3s.size();
				System.out.print("\t");
				for (int k = 0; k < size3; k++) {
					CourseType p3 = p3s.get(k);
					System.out.print("\t三级类别:" + p3.getTypeName() + ",parentId=" + p3.getParentId() + ",");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
}






