# 企业办公自动化系统

## 项目简介  
本项目旨在根据企业日常办公需求，实现多功能、一体化的办公自动化系统，提升办公效率和管理能力。  

## 功能模块  
系统包含以下 11 大功能模块：  

1. **用户注册模块**  
   - 实现用户注册，支持用户填写基本个人信息完成注册。  

2. **登录模块**  
   - 支持已注册用户登录系统。  

3. **收发文管理模块**  
   - 功能包括浏览发文、创建发文、删除发文等操作。  

4. **会议管理模块**  
   - 支持查看会议信息、录入会议信息、删除会议信息等功能。  

5. **公告管理模块**  
   - 实现公告列表的查看、新增公告、删除公告等操作。  

6. **人力资源管理模块**  
   - 支持对员工信息的浏览、修改、添加和删除。  

7. **资产管理模块**  
   - 涵盖办公用品与车辆管理的浏览、修改、添加和删除功能。  

8. **文档管理模块**  
   - 实现文件的浏览、上传、下载和删除操作。  

9. **内部邮件管理模块**  
   - 支持邮件的浏览、发送和删除功能。  

10. **意见管理模块**  
   - 提供查看意见箱、发送建议、删除建议等功能。  

11. **系统退出功能**  
   - 提供安全退出系统的功能。

## 技术栈  
- **Java**: 17  
- **Tomcat**: 9.0.93  
- **MySQL**: 8.0.39  

## 快速启动  

1. **环境准备**  
   - 安装 Java 17、Tomcat 9.0.93 和 MySQL 8.0.39。  

2. **数据库配置**  
   - 创建数据库并导入初始数据脚本（`eoas.sql`）。  

3. **项目部署**  
   - 将项目 WAR 包部署到 Tomcat 的 `webapps` 目录。  

4. **运行项目**  
   - 启动 Tomcat，访问系统入口：`http://<服务器地址>:<端口>/`。  
