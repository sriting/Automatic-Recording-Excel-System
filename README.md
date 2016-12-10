# Register-in-Excel-System 简介
一个可自动读取/写入excel数据的社员领资料次数登记系统，包含自动多科目登记、合并表格数据和数据分析。
A java project that can read/write excel data, including registering multiple subjects, merging two excel sheets and analysing data.
#版本
- mathClubSystem 1.0
- mathClubSystem 2.0
- mathClubSystem 3.0
- mathClubSystem 4.0
+ mathClubSystem 5.0

#使用文档
1. 可执行文件mathClubSystem.exe在dist文件夹里，直接双击就可进入系统使用了。注意，所有数据也写在子文件夹excel里的名为mathclubMember.xls的excel文件，但dist 
里的mathClubSystem.jar是java文件，只有在应用程序exe使用不了时（如双击后出现The main startup class could not be found错误时，即电脑的jdk版本过低），这时才使用jar文件，有两种方法可打开系统：                                        
（1）右击mathClubSystem.jar,选择“打开方式”，选择其中“Java(TM) Platform SE binary”即可（如没有这个选项，试试第2条）                               
（2）在dist文件夹下，按住Shift键加鼠标右击，选择“在此处打开命令窗口”，输入 “java -jar mathClubSystem.jar”回车，程序就出来了。如果这两个方法都不行就说明你的java版本太低，或者没有装好。版本低的请看（3）		       
（3）把java低于1.8版本的重装成1.8版本，确保jdk和jvm是1.8的。或者有编程能力的同学可尝试用编辑器打开源代码（在scr文件夹），把所有.java文件重新构建新的jar，这个jar就能使用在你的java版本上了（exe也是如此）。               
2. 整个mathClubSystem文件夹里的其余部分请不要随意改动，否则可能出现问题。只有dist下的叫excel文件夹里的mathclubMember.xls这个文件我们可以进行改动。                         
3. 如果想要对程序首页的图片进行更换，只需将mathClubSystem文件夹下的mathclub.png改成新的图片，注意图片比例要为700*200，且名字、后缀必须为mathclub.png。      
4. ~~（2.0版本已解决）若出现这种情况：表格里有此id但查询出查无此人，应该是该id格式设错了。id的默认格式是文本，不是数字，所以如果id为数字时该id就不能被检索到 。以下网址有怎么把格式从数字转成文本的教程，推荐教程里的第二个方法：http://jingyan.baidu.com/article/ae97a646b3d0b7bbfc461d68.html~~	      
5. 两表合并功能请注意查看合并页面上的注意事项，要记录重复领取的表格请放到sheet1 ，另一个表格放sheet2，合并后所有sheet1和sheet2的记录会写在sheet1里，两表同时有yes记录的人的最后一列“重复领取”会写上“是”。当第二次使用合并功能前，请手动清空“重复领取”这一列。两张excel表格的id号顺序和数量要一致，否则会记错             
