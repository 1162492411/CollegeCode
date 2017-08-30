# StudentManage_JavaEdition

This is a Student-Manage-System designed by J2SE.

# StudentScoreManageSystem

# 学生成绩管理系统
-------------
## <a name="TOP">目录</a>
###  1. [功能阐述](#title_1)
####    1.1 [功能阐述](#title_1.1)
####    1.2 [功能结构](#title_1.2)
####    1.3 [文件组织结构](#title_1.3)	
### 2. [数据表设计](#title_2)	
####    2.1 [tb_class(班级信息表)](#title_2.1)
####    2.2 [tb_examkinds(考试种类表)](#title_2.2)
####    2.3 [tb_grade(年级信息表)](#title_2.3)
####    2.4 [tb_grade_sub(考试科目成绩表)](#title_2.4)
####    2.5 [tb_student(学生信息表)](#title_2.5)	
####    2.6 [tb_subject(科目信息表)](#title_2.6)	
####    2.7 [tb_teacher(教师信息表)](#title_2.7)	
####    2.8 [tb_user(用户信息表)](#title_2.8)	

------------

## <a name = "title_1"> 1. 功能阐述 </a>
### <a name = "title_1.1"> 1.1 功能阐述 </a>
学生管理系统主要包括5个模块：
> * 用户登录模块  
>   验证用户是否存在，密码是否正确，防止非法登录
> * 参数设置模块  
>   年级设置、班级设置、课程信息、考试类别
> * 基本信息模块	
>	学生信息、教师信息、考试成绩
> * 系统查询模块  
>   基本信息、成绩信息、汇总信息
> * 系统管理模块  
>   用户维护、退出系统

######<a href="#TOP">∧</a>

### <a name = "title_1.2"> 1.2功能结构 </a>
![功能结构](http://7xshqh.com2.z0.glb.clouddn.com/SMSS-1-1.png)

######<a href="#TOP">∧</a>

### <a name = "title_1.3"> 1.3文件组织结构 </a>
> * model	
Obj_class.java-------------------------------------班级信息封装类    
Obj_examkinds.java---------------------------------考试类型封装类    
Obj_grade_sub.java---------------------------------学生成绩封装类    
Obj_grade.java-------------------------------------年级信息封装类    
Obj_student.java-----------------------------------学生信息封装类    
Obj_subject.java-----------------------------------课程信息封装类    
Obj_teacher.java-----------------------------------教师信息封装类    
Obj_user.java--------------------------------------用户信息封装类    
> * util	
CommonJdbc.java------------------------------------JDBC连接工具类    
JdbcAdapter.java-----------------------------------JDBC操作工具类    
ProduceLsh.java------------------------------------生成流水号工具类    
ProduceMaxBh.java--------------------------------生成最大编号工具类    
RetrieveObject.java--------------------------------检索数据工具类    
SendFocusAdapter.java------------------------------焦点工具类    
> * view
AppMain.java---------------------------------------程序主窗体    
JF_login.java--------------------------------------系统登录窗体    
JF_view_error.java---------------------------------错误提示窗体    
JF_view_gradesub.java------------------------------考试成绩窗体    
JF_view_query_grade_hz.java------------------------汇总查询窗体    
JF_view_query_grade_mx.java------------------------成绩信息窗体    
JF_view_query_jbqk.java----------------------------基本信息窗体    
JF_view_student.java-------------------------------学生信息窗体    
JF_view_sysset_class.java--------------------------班级信息设置窗体    
JF_view_sysset_examkinds.java----------------------考试类型设置窗体    
JF_view_sysset_grade.java--------------------------年级信息设置窗体    
JF_view_sysset_subject.java------------------------课程信息设置窗体    
JF_view_sysset.java--------------------------------系统信息设置窗体    
JF_view_teacher.java-------------------------------教师信息窗体    
JF_view_user_modify.java---------------------------用户修改窗体    

######<a href="#TOP">∧</a>

------------

## <a name = "title_2"> 2. 数据表设计 </a>
### <a name = "title_2.1"> 2.1 tb_class(班级信息表) </a>

| 字段名称 | 数据类型 | 字段大小 | 是否主键 |    说明    |
|----------|----------|----------|----------|------------|
| classname  | varchar  | 10       |    是    |  班级编号  |
| gradename  | varchar  | 10       |          |  年级编号  |
| className| varchar  | 20       |          |  班级名称  |

######<a href="#TOP">∧</a>

### <a name = "title_2.2"> 2.2 tb_examkinds(考试种类表) </a> 
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |     说明   |
|----------|----------|----------|----------|------------|
| kindname   | varchar  | 20       |    是    |考试类别编号|
| kindName | varchar  | 20       |          |考试类别名称|

######<a href="#TOP">∧</a>

### <a name = "title_2.3"> 2.3 tb_grade(年级信息表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |   说明     |
|----------|----------|----------|----------|------------|
| gradename  | varchar  | 10       |    是    |  年级编号  |
| gradeName| varchar  | 20       |          |  年级名称  |

######<a href="#TOP">∧</a>

### <a name = "title_2.4"> 2.4 tb_grade_sub(考试科目成绩表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |     说明   |
|----------|----------|----------|----------|  --------  |
| stuname    | varchar  | 10       |    是    |  学生编号  |
| stuName  | varchar  | 50       |          |  学生姓名  |
| kindname   | varchar  | 10       |    是    |考试类别编号|
| code     | varchar  | 10       |    是    |考试科目编号|
| grade    | float    | 6        |          |  考试成绩  |
| examDate | datetime | 6        |          |  考试日期  |

######<a href="#TOP">∧</a>

### <a name = "title_2.5"> 2.5 tb_student(学生信息表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |     说明   |
|----------|----------|----------|----------|  --------  |
| stuname    | varchar  | 10       |    是    |  学生编号  |
| classname  | varchar  | 10       |          |  班级编号  |
| stuName  | varchar  | 20       |          |  学生姓名  |
| sex      | varchar  | 10       |          |  学生性别  |
| age      |   int    | 4        |          |  学生年龄  |
| addr     | varchar  | 50       |          |  家庭住址  |
| phone    | varchar  | 20       |          |  联系电话  |

######<a href="#TOP">∧</a>

### <a name = "title_2.6"> 2.6 tb_subject(科目信息表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |     说明   |
|----------|----------|----------|----------|------------|
| code     | varchar  | 10       |    是    |  科目编号  |
| subject  | varchar  | 40       |          |  科目名称  |

######<a href="#TOP">∧</a>

### <a name = "title_2.7"> 2.7 tb_teacher(教师信息表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |     说明   |
|----------|----------|----------|----------|  --------  |
| teaname    | varchar  | 10       |    是    |  教师编号  |
| classname  | varchar  | 10       |          |  班级编号  |
| teaName  | varchar  | 20       |          |  教师姓名  |
| sex      | varchar  | 10       |          |  教师性别  |
| knowledge| varchar  | 20       |          |  教师职称  |
| knowLevel| varchar  | 20       |          |  教师等级  |

######<a href="#TOP">∧</a>

### <a name = "title_2.8"> 2.8 tb_user(用户信息表) </a>
| 字段名称 | 数据类型 | 字段大小 | 是否主键 |    说明    |
|----------|----------|----------|----------|------------|
| username   | varchar  | 50       |    是    |  用户编号  |
| userName | varchar  | 50       |          |  用户姓名  |
| pass     | varchar  | 50       |          |  用户口令  |

######<a href="#TOP">∧</a>

----------


