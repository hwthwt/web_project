package com.itany.netClass.constant;

/**
 * 常量接口
 */
public interface Constant {
	/**
	 * 启用状态
	 */
	int STATUS_ENABLE = 0;
	/**
	 * 禁用状态
	 */
	int STATUS_DISABLE = 1;
	/**
	 * 积分
	 */
	int COST_TYPE_SCORE = 0;
	/**
	 * 金币
	 */
	int COST_TYPE_GOLD = 1;
	/**
	 * 课程推荐等级(0普通)
	 */
	int COURSE_RECOMMEND_LV0 = 0;
	/**
	 * 课程推荐等级(1推荐)
	 */
	int COURSE_RECOMMEND_LV1 = 1;
	/**
	 * 评论状态-待审核-2
	 */
	int COMMENT_STATUS_WAITING = 2;
	/**
	 * 用户角色admin
	 */
	String USER_ROLE_ADMIN = "admin";
	/**
	 * 用户角色normal
	 */
	String USER_ROLE_NORMAL = "normal";
	
	/**
	 * 过滤器默认字符集
	 */
	String FILTER_CHARSET_UTF8 = "UTF-8";
	
	/**
	 * 获取文件上传路径的前缀
	 */
	String UPLOAD_PATH_PREFIX = "environment";
	/**
	 * 获取文件上传路径的后缀
	 */
	String UPLOAD_PATH_SUFFIX = ".path";
	/**
	 * 分页
	 */
	int PAGE_SIZE = 2;
	
	/**
	 * 响应成功
	 */
	boolean RESPONSE_SUCCESS=true;
	/**
	 * 响应失败
	 */
	boolean RESPONSE_FAIL=false;
}
