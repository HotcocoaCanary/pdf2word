# Pdf2Word项目简介
## 功能模块
- **pdf转word**  
  通过Java实现pdf转word，支持一次性处理多个pdf文件。
## 说明
- **相关依赖**  
  - org.apache.pdfbox:pdfbox:2.0.27
  - org.apache.poi:poi-ooxml:5.3.0
  - com.aspose-pdf:24.8.0
- **javaFX界面**  
  打包适配windows以及mac，通过JavaFX构建图形用户界面，使用打包工具打包项目。
  - **相关依赖**  
    - org.openifx:javafx-controls:17.0.6
    - org.openifx:javafx-fxml:17.0.6
---
## 项目文件简介
### 解压后项目结构
```
PDF转Word工具
├─打包软件
│  ├─mocOS_aarch64+javafx-sdk-22.0.2 (适用于mocOS_aarch64系统的打包程序，包含javaFX组件环境）
│  │   ├─ log （日志文件）
│  │   └─ run.sh （运行程序）
│  ├─mocOS_x64+javafx-sdk-22.0.2 (适用于mocOS_x64系统的程序，包含javaFX组件环境）
│  │   ├─ log （日志文件）
│  │   └─ run.sh（运行程序）
│  └─Wiondows_64x+javafx-sdk-22.0.2 (适用于Wiondows_64x系统的程序，包含javaFX组件环境）
│      ├─ log （日志文件）
│      └─ run.bat（运行程序）
└─源代码
    ├─lib （阿里云的Maven仓库中没有的jar依赖，可能需要安装到Maven中，或者导入到IDEA的JDK中）
    └─pdf2word （源代码）
        │  pom.xml (Maven配置文件)
        │
        └─src
            └─main
                ├─java（代码）
                │  │  module-info.java （JavaFX模块导出配置文件）
                │  │
                │  └─org
                │      └─example
                │          └─pdf2word
                │              │  PDF2WordApplication.java （应用主类）
                │              ├─controller（控制器层）
                │              │      PDF2WordController.java （控制器类）
                │              ├─service（服务层）
                │              │      Log.java （日志编写类）
                │              │      PDF2WordService.java （pdf转word服务类）
                │              └─util（工具包）
                │
                └─resources（资源）
                    └─org
                        └─example
                            └─pdf2word
                                ├─static
                                │      index.css（样式文件）
                                │      logo.png（logo图片）
                                │
                                └─view
                                        index.fxml（主界面）
```
## 项目运行及功能演示
### 环境配置
- **JDK**  
  本项目采用JDK17编写，两种启动方式都需要下载和配置JDK17的环境变量。
- **Maven**  
  pom.xml依赖中，绝大多数依赖都可以从阿里云仓库中获取到，依赖com.aspose-pdf:24.8.0需要手动安装。  
  依赖jar包位于"/pdf2word/lib"中。  
  安装指令：将PATHPATH替换为aspose-pdf-24.8.jar的绝对路径  
  ```
  mvn install:install-file -Dfile=PATHPATH -DgroupId=com.aspose -DartifactId=aspose-pdf -Dversion=24.8.0 -Dpackaging=jar
  ```
### 项目启动
- **运行方式一：使用打包好的工具进行启动，需要配置JDK**  
  1. Windows系统：双击运行run.bat文件
  2. MacOS系统：双击运行ru.sh文件
- **运行方式二：编译运行源代码，需要配置JDK和Maven**