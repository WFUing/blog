# 解决 Port `8080` is already in use  问题

搭建网站基本占用的都是 `8080` 端口，所以很容易产生该端口被占用的现象，现在就来总结一下这个的解决方法。

![watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70](%E8%A7%A3%E5%86%B3%20Port%208080%20is%20already%20in%20use%20%20%E9%97%AE%E9%A2%98.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70-16677165219604-16677165234925.png)

## 1. 找到占用 `8080` 端口的应用

### 1.1 第一种方法

`win+R` 打开，输入`cmd`进入命令窗口，然后输入 `netstat -ano` 回车

![watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70](%E8%A7%A3%E5%86%B3%20Port%208080%20is%20already%20in%20use%20%20%E9%97%AE%E9%A2%98.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70-16677167877956-16677167886947.png)

找到对应的进程 `PID` 为14472，使用 `tasklist` 命令

![watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70](%E8%A7%A3%E5%86%B3%20Port%208080%20is%20already%20in%20use%20%20%E9%97%AE%E9%A2%98.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70-166771713284310-166771713362511.png)

找到是 `node.exe` 程序

### 1.2 第二种方法

`win+R` 打开，输入`cmd`进入命令窗口，然后输入`netstat -o -n -a | findstr :8080 ` 回车

![watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70](%E8%A7%A3%E5%86%B3%20Port%208080%20is%20already%20in%20use%20%20%E9%97%AE%E9%A2%98.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70-16677169649048-16677169657869.png)

## 2. 关闭占用端口的线程

### 2.1 第一种方法

如上图所示

可以用 `taskkill /F /PID 22260` 关闭 `22260` 端口，但是这种方法很可能出现权限不够，关闭不了线程的情况。

### 2.2 第一种方法

打开任务管理器，点击详细信息，根据查到的进程名称和PID找到正在运行的任务，点击结束任务

![img](%E8%A7%A3%E5%86%B3%20Port%208080%20is%20already%20in%20use%20%20%E9%97%AE%E9%A2%98.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pqeDgyOA==,size_16,color_FFFFFF,t_70-166771722912412-166771723052414.png)

