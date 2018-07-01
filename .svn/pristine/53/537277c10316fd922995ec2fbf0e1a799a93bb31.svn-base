

create table t_user (
  id number,
  login_name varchar2(40),
  nickname varchar2(60) not null,
  header_image_url varchar2(200),
  password varchar2(40),
  role varchar2(60),
  email varchar2(60),
  login_date date,
  create_date date,
  status number(1,0),
  constraint t_user_pk primary key (id)
);
comment on column t_user.id is '用户主键';
comment on column t_user.login_name is '登录名';
comment on column t_user.nickname is '用户昵称';
comment on column t_user.header_image_url is '头像';
comment on column t_user.password is '密码';
comment on column t_user.role is '角色';
comment on column t_user.email is '邮箱';
comment on column t_user.login_date is '最近一次登录的日期';
comment on column t_user.create_date is '用户创建日期';
comment on column t_user.status is '0启用,1禁用';


create table t_course_type (
  id number,
  type_name varchar2(60),
  parent_id number,
  status number(1,0),
  constraint t_course_type_pk primary key (id),
  constraint t_course_type_parent_fk foreign key (parent_id) references t_course_type (id)
);
comment on column t_course_type.id is '类型主键';
comment on column t_course_type.type_name is '类型名称';
comment on column t_course_type.parent_id is '父类别id';
comment on column t_course_type.status is '0启用1禁用';

create table t_course (
  id number,
  course_name varchar2(100),
  course_info varchar2(400),
  author varchar2(100),
  cover_image_url varchar2(200),
  create_date date,
  click_number number default '0',
  status number(1,0),
  recommendation_grade number(1,0),
  course_type_id number,
  constraint t_course_pk primary key (id),
  constraint t_type_fk foreign key (course_type_id) references t_course_type (id)
);
comment on column t_course.id is '课程主键';
comment on column t_course.course_name is '课程名称';
comment on column t_course.course_info is '课程简介';
comment on column t_course.author is '课程的作者';
comment on column t_course.cover_image_url is '课程封面图片的相对路径';
comment on column t_course.create_date is '课程发布时间';
comment on column t_course.click_number is '课程点击量';
comment on column t_course.status is '课程状态(0启用,1禁用)';
comment on column t_course.recommendation_grade is '课程推荐等级(0普通,1推荐)';
comment on column t_course.course_type_id is '所属的课程类别的id';


create table t_chapter (
  id number,
  course_id number,
  title varchar2(100),
  info varchar2(300),
  create_date date,
  status number(1,0),
  constraint t_chapter_pk primary key (id),
  constraint t_chapter_course_fk foreign key (course_id) references t_course (id)
);
comment on column t_chapter.id is '课程章节表主键';
comment on column t_chapter.course_id is '课程的id';
comment on column t_chapter.title is '课程章节标题';
comment on column t_chapter.info is '课程章节简介';
comment on column t_chapter.create_date is '课程章节创建时间';
comment on column t_chapter.status is '0启用1禁用';


create table t_gold_points (
  id number,
  user_id number,
  point_count number default '0',
  gold_count number default '0',
  info varchar2(200),
  create_date date,
  constraint t_gold_points_pk primary key (id),
  constraint t_gold_points_user_fk foreign key (user_id) references t_user (id)
);
comment on column t_gold_points.id is '积分金币表主键';
comment on column t_gold_points.id is '积分金币所属用户id';
comment on column t_gold_points.id is '使用或获得的积分数';
comment on column t_gold_points.id is '使用或获得的金币数';
comment on column t_gold_points.id is '操作的内容简单说明';
comment on column t_gold_points.id is '操作时间';


create table t_resource (
  id number,
  title varchar2(200),
  path varchar2(200),
  cover_image_url varchar2(200),
  original_name varchar2(200),
  file_size number,
  file_type varchar2(50),
  click_count number,
  create_date date,
  cost_type number(1,0),
  cost_number number default '0',
  user_id number,
  chapter_id number,
  status number(1,0),
  constraint t_resource_pk primary key (id),
  constraint t_resource_user_fk foreign key (user_id) references t_user (id),
  constraint t_resource_chapter_fk foreign key (chapter_id) references t_chapter (id)
);
comment on column t_resource.id is '资源主键';
comment on column t_resource.title is '资源标题';
comment on column t_resource.path is '资源相对路径';
comment on column t_resource.cover_image_url is '资源封面图片地址';
comment on column t_resource.original_name is '文件原始名称';
comment on column t_resource.file_size is '文件大小(字节)';
comment on column t_resource.file_type is '文件类型';
comment on column t_resource.click_count is '点击量';
comment on column t_resource.create_date is '上传时间';
comment on column t_resource.cost_type is '0积分,1金币';
comment on column t_resource.cost_number is '下载文件或查看视频需要的积分或金币';
comment on column t_resource.user_id is '上传用户id';
comment on column t_resource.chapter_id is '章节id';
comment on column t_resource.status is '0启用1禁用';


create table t_comment (
  id number,
  context varchar2(2000),
  create_date date,
  user_id number,
  resource_id number,
  status number(1,0),
  constraint t_comment_pk primary key (id),
  constraint t_comment_user_fk foreign key (user_id) references t_user (id),
  constraint t_comment_resource_fk foreign key (resource_id) references t_resource (id)
);
comment on column t_comment.id is '评论表的主键';
comment on column t_comment.context is '评论内容';
comment on column t_comment.create_date is '创建时间';
comment on column t_comment.user_id is '发布的用户id';
comment on column t_comment.resource_id is '被评论的资源id';
comment on column t_comment.status is '0启用1禁用2待审核';


create table t_praise (
  id number,
  user_id number,
  comment_id number,
  create_date date,
  constraint t_praise_pk primary key (id),
  constraint t_praise_user_fk foreign key (user_id) references t_user (id),
  constraint t_praise_comment_fk foreign key (comment_id) references t_comment (id)
);
comment on column t_praise.id is '点赞记录表主键';
comment on column t_praise.user_id is '用户id';
comment on column t_praise.comment_id is '被点赞的评论的id';
comment on column t_praise.create_date is '点赞时间';


create table t_user_resource (
  id number,
  user_id number,
  resource_id number,
  create_date date,
  update_date date,
  constraint t_user_resource_pk primary key (id),
  constraint t_user_resource_user_fk foreign key (user_id) references t_user (id),
  constraint t_user_resource_resource_fk foreign key (resource_id) references t_resource (id)
);
comment on column t_user_resource.id is '用户已购买的资源表主键';
comment on column t_user_resource.user_id is '用户id';
comment on column t_user_resource.resource_id is '资源id';
comment on column t_user_resource.create_date is '购买日期';
comment on column t_user_resource.update_date is '最近一次查看的日期';

