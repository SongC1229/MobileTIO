 void dbInit(){

        idDbHelper dbId=new idDbHelper(this);
        userDbhelper dbUser=new userDbhelper(this);
        courseDBHelper dbCour=new courseDBHelper(this);
        cour_stuDbHelper dbCS=new cour_stuDbHelper(this);

        dbId.delAll();
        dbUser.delAll();
        dbCour.delAll();
        dbCS.delAll();

        dbId.InsertID("1000001",1);
        dbId.InsertID("1000001",2);
        dbId.InsertID("3",3);
        String a=dbId.getID(1);
        dbUser.insertUser(a,"方皓成","123","469248584@qq.com","15030692226",1,2015,"物联网","物联网1501");
        dbUser.insertUser(dbId.getID(1),"甘霖","123","469248584@qq.com","15030692226",1,2015,"物联网","物联网1501");
        dbUser.insertUser(dbId.getID(1),"宋高明","123","469248584@qq.com","15030692226",1,2015,"物联网","物联网1501");
        dbUser.insertUser(dbId.getID(1),"伍传丽","123","469248584@qq.com","15030692226",2,2015,"物联网","物联网1502");
        dbUser.insertUser(dbId.getID(1),"温皓","123","469248584@qq.com","15030692226",1,2015,"物联网","物联网1502");
        dbUser.insertUser(dbId.getID(1),"曾佑熹","123","469248584@qq.com","15030692226",1,2015,"物联网","物联网1501");

        course c;
        c=new course(dbId.getID(2),"物联网概论","伍新华",5.0,"5-105",1,16,112342,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"高等数学","李玉光",5.0,"5-107",1,16,113251,0);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"线性代数","曾轶可",3.0,"5-109",1,16,22,0);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"RFID基础","陈建军",1.0,"5-113",1,16,24,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"无线传感器网络","章阳",2.0,"5-115",1,16,23,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"体育1","成龙",2.0,"操场",1,16,25,0);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"钢琴艺术与名曲赏析","郎朗",2.0,"5-201",1,16,31,-1);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"人工智能与模式识别方法","周志华",2.0,"5-202",1,16,32,-2);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"计算机组成与系统结构","大佬",2.0,"5-203",1,16,33,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"算法设计与分析A","嫦娥",2.0,"5-204",1,16,34,-4);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"JAVA语言程序设计D","C++",2.0,"5-205",1,16,35,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"Python语言程序设计D","PHP",2.0,"5-206",1,16,41,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"嵌入式系统设计A","章阳",2.0,"5-208",1,16,42,0);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"计算机网络G","马云",2.0,"5-207",1,16,43,0);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"电工电子实习A","Bill Gates",2.0,"5-209",1,16,44,-2);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"模拟电子技术基础C","李彦宏",2.0,"5-211",1,16,45,-1);
        dbCour.insertCourse(c);

        //12.31
        c=new course(dbId.getID(2),"Matlab数值计算方法与实践(JD)","毛树华",1.0,"5-213",1,16,51,-2);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"机器人控制技术概论","陈浩民",2.0,"5-215",1,16,52,-4);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"马克思主义基本原理","王福",3.0,"5-217",1,16,53,-1);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"云计算与服务计算","陆平",2.0,"5-301",1,16,54,-3);
        dbCour.insertCourse(c);
        c=new course(dbId.getID(2),"操作系统","王霞",2.5,"5-303",1,16,55,-3);
        dbCour.insertCourse(c);
         c=new course(dbId.getID(2),"数据库系统原理C","夏坤",2.0,"5-304",1,16,11,-3);
        dbCour.insertCourse(c);
         c=new course(dbId.getID(2),"单片机原理及应用","曹广",2.0,"5-306",1,16,12,-4);
        dbCour.insertCourse(c);
         c=new course(dbId.getID(2),"英语影视赏析与批评","李丽",3.0,"5-307",1,16,13,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"数据结构C","李丽",4.0,"5-308",1,16,14,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"通信原理","颜广坤",5.5,"5-309",1,16,15,-4);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"数字逻辑","曹一",3.0,"5-311",1,16,21,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"离散结构","巩俐",4.0,"5-313",1,16,22,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"高级语言程序设计A","刘稳",3.0,"5-315",1,16,23,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"中国近现代史纲要","李丽",3.0,"5-401",1,16,24,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"电路原理B","李萌",4.0,"5-403",1,16,25,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"计算机导论","伍丽",3.0,"5-405",1,16,31,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"思想道德修养与法律基础","李比干",3.0,"5-407",1,16,32,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"体育2","李丽",1.0,"操场",1,16,33,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"心理健康教育","周树人",1.0,"5-413",1,16,34,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"科学技术史(GX)","李丽",1.5.0,"5-415",1,16,35,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"模拟电子技术基础C","孔孟",3.0,"5-416",1,16,41,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"物联网控制基础","陈俊",2.5,"5-417",1,16,42,-4);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"汇编语言与接口技术B","杨清",3.0,"5-419",1,16,43,-3);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"概论","高巍翔",4.0,"5-501",1,16,44,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"网络协议软件编程","李桥",3.0,"5-503",1,16,45,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"射频识别与传感器技术B","陈建军",3.5,"5-505",1,16,51,-3);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"物联网工程与组网技术","唐三星",3.0,"5-507",1,16,52,-4);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"面向对象程序设计C","李法",2.5,"5-509",1,16,53,0);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"军事训练","李丽",1.0,"操场",1,16,54,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"计算机基础与编程综合实验","李丽",1.0,"5-513",1,16,55,-3);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"计算机网络G","何兰",3.0,"5-515",1,16,11,-3);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"数据结构与算法综合实验","伍教授",3.0,"5-517",1,16,12,-3);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"高级网球","郑发",3.0,"操场",1,16,13,-1);
        dbCour.insertCourse(c);
           c=new course(dbId.getID(2),"逻辑与计算机设计基础","周润发",3.0,"5-603",1,16,14,-2);
        dbCour.insertCourse(c);

// 第一个学生
         dbCS.insertCourStu("1000001","1000047",90,6);
         dbCS.insertCourStu("1000001","1000046",80,6);
        dbCS.insertCourStu("1000001","1000045",87,6);
        dbCS.insertCourStu("1000001","1000047",89,6);
        dbCS.insertCourStu("1000001","1000046",91,6);
        dbCS.insertCourStu("1000001","1000029",92,6);
        dbCS.insertCourStu("1000001","1000028",95,6);
        dbCS.insertCourStu("1000001","1000027",77,6);


        dbCS.insertCourStu("1000001","1000021",88,5);
        dbCS.insertCourStu("1000001","1000023",99,5);
        dbCS.insertCourStu("1000001","1000026",76,5);
        dbCS.insertCourStu("1000001","1000033",75,5);
        dbCS.insertCourStu("1000001","1000035",74,5);
        dbCS.insertCourStu("1000001","1000037",76,5);
        dbCS.insertCourStu("1000001","1000038",77,5);
        dbCS.insertCourStu("1000001","1000041",77,5);




        dbCS.insertCourStu("1000001","1000013",80,4);
        dbCS.insertCourStu("1000001","1000014",59.9,4);
        dbCS.insertCourStu("1000001","1000015",0,4);
        dbCS.insertCourStu("1000001","1000016",100,4);
        dbCS.insertCourStu("1000001","1000044",82,4);
        dbCS.insertCourStu("1000001","1000034",59.9,4);
        dbCS.insertCourStu("1000001","1000046",77,4);
        dbCS.insertCourStu("1000001","1000022",65,4);


        dbCS.insertCourStu("1000001","1000009",65,3);
        dbCS.insertCourStu("1000001","1000010",92,3);
        dbCS.insertCourStu("1000001","1000011",100,3);
        dbCS.insertCourStu("1000001","1000012",76,3);
        dbCS.insertCourStu("1000001","1000008",65,3);
        dbCS.insertCourStu("1000001","1000039",92,3);
        dbCS.insertCourStu("1000001","1000038",100,3);
        dbCS.insertCourStu("1000001","1000037",66,3);


        dbCS.insertCourStu("1000001","1000041",88,2);
        dbCS.insertCourStu("1000001","1000042",88,2);
        dbCS.insertCourStu("1000001","1000045",92,2);
        dbCS.insertCourStu("1000001","1000040",70,2);
         dbCS.insertCourStu("1000001","1000023",72,2);
        dbCS.insertCourStu("1000001","1000025",66,2);
        dbCS.insertCourStu("1000001","1000031",72,2);
        dbCS.insertCourStu("1000001","1000032",99,2);




        dbCS.insertCourStu("1000001","10000012",66,1);
        dbCS.insertCourStu("1000001","10000015",69,1);
        dbCS.insertCourStu("1000001","1000017",78,1);
        dbCS.insertCourStu("1000001","1000019",10,1);
        dbCS.insertCourStu("1000001","1000016",59,1);
        dbCS.insertCourStu("1000001","1000023",88,1);
        dbCS.insertCourStu("1000001","1000024",77,1);
        dbCS.insertCourStu("1000001","1000027",90,1);









//第二个学生
        dbCS.insertCourStu("1000002","1000001",99,6);
        dbCS.insertCourStu("1000002","1000003",98,6);
        dbCS.insertCourStu("1000002","1000002",95,6);
        dbCS.insertCourStu("1000002","1000006",96,6);
        dbCS.insertCourStu("1000002","1000007",99,6);
        dbCS.insertCourStu("1000002","1000009",97,6);
        dbCS.insertCourStu("1000002","1000011",94,6);
        dbCS.insertCourStu("1000002","1000012",93,6);




        dbCS.insertCourStu("1000002","1000013",67,5);
        dbCS.insertCourStu("1000002","1000015",60,5);
        dbCS.insertCourStu("1000002","1000014",67,5);
        dbCS.insertCourStu("1000002","1000023",68,5);
        dbCS.insertCourStu("1000002","1000024",78,5);
        dbCS.insertCourStu("1000002","1000027",85,5);
        dbCS.insertCourStu("1000002","1000029",66,5);
        dbCS.insertCourStu("1000002","1000034",99,5);

        dbCS.insertCourStu("1000002","1000049",99,4);
        dbCS.insertCourStu("1000002","1000048",88,4);
        dbCS.insertCourStu("1000002","1000040",89.9,4);
        dbCS.insertCourStu("1000002","1000047",91,4);
        dbCS.insertCourStu("1000002","1000045",92,4);
        dbCS.insertCourStu("1000002","1000043",77,4);
        dbCS.insertCourStu("1000002","1000042",65,4);

         dbCS.insertCourStu("1000002","1000030",99,3);
         dbCS.insertCourStu("1000002","1000032",89,3);
         dbCS.insertCourStu("1000002","1000039",79,3);
         dbCS.insertCourStu("1000002","1000029",99,3);
         dbCS.insertCourStu("1000002","1000028",69,3);
         dbCS.insertCourStu("1000002","1000027",97,3);
         dbCS.insertCourStu("1000002","1000025",93,3);
         dbCS.insertCourStu("1000002","1000024",94,3);


         dbCS.insertCourStu("1000002","1000023",99,2);
         dbCS.insertCourStu("1000002","1000022",99,2);
         dbCS.insertCourStu("1000002","1000020",73,2);
         dbCS.insertCourStu("1000002","1000019",94,2);
         dbCS.insertCourStu("1000002","1000024",99,2);
         dbCS.insertCourStu("1000002","1000028",66,2);
         dbCS.insertCourStu("1000002","1000029",99,2);

        dbCS.insertCourStu("1000002","1000015",75,1);
        dbCS.insertCourStu("1000002","1000017",88.3,1);
        dbCS.insertCourStu("1000002","1000012",99,1);
        dbCS.insertCourStu("1000002","1000013",84,1);
        dbCS.insertCourStu("1000002","1000010",99,1);
        dbCS.insertCourStu("1000002","1000020",96,1);
        dbCS.insertCourStu("1000002","1000011",89,1);










//第三个学生v
         dbCS.insertCourStu("1000003","1000001",90,6);
         dbCS.insertCourStu("1000003","1000003",80,6);
         dbCS.insertCourStu("1000003","1000004",88,6);
         dbCS.insertCourStu("1000003","1000008",89.5,6);
         dbCS.insertCourStu("1000003","1000009",57,6);
         dbCS.insertCourStu("1000003","1000010",68,6);
         dbCS.insertCourStu("1000003","1000015",65,6);


         dbCS.insertCourStu("1000003","1000045",71,5);
          dbCS.insertCourStu("1000003","1000046",62,5);
         dbCS.insertCourStu("1000003","1000047",88,5);
          dbCS.insertCourStu("1000003","1000042",66,5);
        dbCS.insertCourStu("1000003","1000048",77,5);
        dbCS.insertCourStu("1000003","1000040",86,5);
        dbCS.insertCourStu("1000003","1000039",89,5);
        dbCS.insertCourStu("1000003","1000034",78,5);

        dbCS.insertCourStu("1000003","1000038",72,4);
        dbCS.insertCourStu("1000003","1000037",65,4);
        dbCS.insertCourStu("1000003","1000040",65,4);
        dbCS.insertCourStu("1000003","1000050",77,4);
        dbCS.insertCourStu("1000003","1000048",52,4);
        dbCS.insertCourStu("1000003","1000047",63,4);
        dbCS.insertCourStu("1000003","1000044",84,4);
        dbCS.insertCourStu("1000003","1000034",81,4);

        dbCS.insertCourStu("1000003","1000044",78,3);
        dbCS.insertCourStu("1000003","1000034",92,3);
        dbCS.insertCourStu("1000003","1000033",63,3);
        dbCS.insertCourStu("1000003","1000037",81,3);
        dbCS.insertCourStu("1000003","1000033",66,3);
        dbCS.insertCourStu("1000003","1000022",67,3);
        dbCS.insertCourStu("1000003","1000025",78,3);
        dbCS.insertCourStu("1000003","1000035",81,3);


        dbCS.insertCourStu("1000003","1000027",88,2);
        dbCS.insertCourStu("1000003","1000021",66,2);
        dbCS.insertCourStu("1000003","1000020",77.5,2);
        dbCS.insertCourStu("1000003","1000014",88,2);
        dbCS.insertCourStu("1000003","1000018",82,2);
        dbCS.insertCourStu("1000003","1000017",93,2);
        dbCS.insertCourStu("1000003","1000013",68,2);

         dbCS.insertCourStu("1000003","1000012",64,1);
         dbCS.insertCourStu("1000003","1000018",63,1);
         dbCS.insertCourStu("1000003","1000019",78,1);
         dbCS.insertCourStu("1000003","1000044",77,1);
         dbCS.insertCourStu("1000003","1000034",78,1);
         dbCS.insertCourStu("1000003","1000024",89,1);
         dbCS.insertCourStu("1000003","1000022",77,1);
         dbCS.insertCourStu("1000003","1000042",66,1);


//第四个学生
          dbCS.insertCourStu("1000004","1000001",78,6);
          dbCS.insertCourStu("1000004","1000014",77,6);
          dbCS.insertCourStu("1000004","1000015",86,6);
          dbCS.insertCourStu("1000004","1000016",78,6);
          dbCS.insertCourStu("1000004","1000017",86,6);
          dbCS.insertCourStu("1000004","1000019",75,6);


        dbCS.insertCourStu("1000004","1000048",66,5);
        dbCS.insertCourStu("1000004","1000047",78,5);
         dbCS.insertCourStu("1000004","1000049",69,5);
        dbCS.insertCourStu("1000004","1000040",68,5);
        dbCS.insertCourStu("1000004","1000033",60,5);
        dbCS.insertCourStu("1000004","1000019",99,5);
        dbCS.insertCourStu("1000004","1000037",78,5);
         dbCS.insertCourStu("1000004","1000033",88,5);


         dbCS.insertCourStu("1000004","1000024",68,4);
       dbCS.insertCourStu("1000004","1000023",58,4);
       dbCS.insertCourStu("1000004","1000033",78,4);
         dbCS.insertCourStu("1000004","1000038",90,4);
         dbCS.insertCourStu("1000004","1000039",82,4);
        dbCS.insertCourStu("1000004","1000037",83,4);
         dbCS.insertCourStu("1000004","1000031",84,4);

        dbCS.insertCourStu("1000004","1000048",62,3);
        dbCS.insertCourStu("1000004","1000024",84,3);
        dbCS.insertCourStu("1000004","1000015",77,3);
        dbCS.insertCourStu("1000004","1000019",64,3);
        dbCS.insertCourStu("1000004","1000018",76,3);
        dbCS.insertCourStu("1000004","1000017",78,3);
        dbCS.insertCourStu("1000004","1000025",80,3);
        dbCS.insertCourStu("1000004","1000030",86,3);


        dbCS.insertCourStu("1000004","1000011",86,2);
        dbCS.insertCourStu("1000004","1000015",75,2);
        dbCS.insertCourStu("1000004","1000017",76,2);
        dbCS.insertCourStu("1000004","1000019",77,2);
        dbCS.insertCourStu("1000004","1000022",81,2);
        dbCS.insertCourStu("1000004","1000028",88,2);
        dbCS.insertCourStu("1000004","1000029",87,2);

        dbCS.insertCourStu("1000004","1000033",86,1);
        dbCS.insertCourStu("1000004","1000024",78,1);
        dbCS.insertCourStu("1000004","1000028",75,1);
        dbCS.insertCourStu("1000004","1000027",77,1);
        dbCS.insertCourStu("1000004","1000034",78,1);
        dbCS.insertCourStu("1000004","1000037",99,1);
        dbCS.insertCourStu("1000004","1000044",91,1);








//第五个学生
        dbCS.insertCourStu("1000005","1000022",87,6);
        dbCS.insertCourStu("1000005","1000033",89,6);
        dbCS.insertCourStu("1000005","1000044",86,6);
        dbCS.insertCourStu("1000005","1000048",78,6);
        dbCS.insertCourStu("1000005","1000049",77,6);
        dbCS.insertCourStu("1000005","1000047",65,6);
        dbCS.insertCourStu("1000005","1000032",91,6);




        dbCS.insertCourStu("1000005","1000045",80,5);
        dbCS.insertCourStu("1000005","1000031",64,5);
        dbCS.insertCourStu("1000005","1000046",67,5);
        dbCS.insertCourStu("1000005","1000013",74,5);
        dbCS.insertCourStu("1000005","1000012",88,5);
        dbCS.insertCourStu("1000005","1000018",99,5);
        dbCS.insertCourStu("1000005","1000027",78,5);
        dbCS.insertCourStu("1000005","1000019",72,5);

        dbCS.insertCourStu("1000005","1000017",78,4);
        dbCS.insertCourStu("1000005","1000019",79,4);
        dbCS.insertCourStu("1000005","1000025",84,4);
        dbCS.insertCourStu("1000005","1000038",85,4);
        dbCS.insertCourStu("1000005","1000040",86,4);
        dbCS.insertCourStu("1000005","1000056",69,4);
        dbCS.insertCourStu("1000005","1000034",71,4);
        dbCS.insertCourStu("1000005","1000039",72,4);

        dbCS.insertCourStu("1000005","1000041",68,4);
        dbCS.insertCourStu("1000005","1000042",64,4);
        dbCS.insertCourStu("1000005","1000043",78,4);
        dbCS.insertCourStu("1000005","1000021",64,4);
        dbCS.insertCourStu("1000005","1000047",88,4);
        dbCS.insertCourStu("1000005","1000038",57,4);
        dbCS.insertCourStu("1000005","1000037",72,4);
        dbCS.insertCourStu("1000005","1000023",78,4);

        dbCS.insertCourStu("1000005","1000018",88,3);
        dbCS.insertCourStu("1000005","1000019",77,3);
        dbCS.insertCourStu("1000005","1000013",75,3);
        dbCS.insertCourStu("1000005","1000009",89,3);
        dbCS.insertCourStu("1000005","1000007",87,3);
        dbCS.insertCourStu("1000005","1000004",89,3);
        dbCS.insertCourStu("1000005","1000023",91,3);
        dbCS.insertCourStu("1000005","1000049",95,3);

        dbCS.insertCourStu("1000005","1000014",88,2);
        dbCS.insertCourStu("1000005","1000015",79,2);
        dbCS.insertCourStu("1000005","1000016",89,2);
        dbCS.insertCourStu("1000005","1000017",57,2);
        dbCS.insertCourStu("1000005","1000018",85,2);
        dbCS.insertCourStu("1000005","1000019",57,2);
        dbCS.insertCourStu("1000005","1000020",88,2);
        dbCS.insertCourStu("1000005","1000021",87,2);

        dbCS.insertCourStu("1000005","1000022",89,1);
        dbCS.insertCourStu("1000005","1000023",85,1);
        dbCS.insertCourStu("1000005","1000025",84,1);
        dbCS.insertCourStu("1000005","1000026",83,1);
        dbCS.insertCourStu("1000005","1000027",88,1);
        dbCS.insertCourStu("1000005","1000028",78,1);
        dbCS.insertCourStu("1000005","1000029",98,1);
        dbCS.insertCourStu("1000005","1000030",99,1);



//第六个学生
        dbCS.insertCourStu("1000006","1000001",90,6);
        dbCS.insertCourStu("1000006","1000002",88,6);
        dbCS.insertCourStu("1000006","1000003",59,6);
        dbCS.insertCourStu("1000006","1000004",62,6);
        dbCS.insertCourStu("1000006","1000005",63,6);
        dbCS.insertCourStu("1000006","1000006",67,6);
        dbCS.insertCourStu("1000006","1000007",77,6);
        dbCS.insertCourStu("1000006","1000008",78,6);

        dbCS.insertCourStu("1000006","1000009",99,5);
        dbCS.insertCourStu("1000006","1000010",88,5);
        dbCS.insertCourStu("1000006","1000011",89,5);
        dbCS.insertCourStu("1000006","1000012",78,5);
        dbCS.insertCourStu("1000006","1000013",89,5);
        dbCS.insertCourStu("1000006","1000014",76,5);
        dbCS.insertCourStu("1000006","1000015",67,5);
        dbCS.insertCourStu("1000006","1000016",85,5);

        dbCS.insertCourStu("1000006","1000017",84,4);
        dbCS.insertCourStu("1000006","1000018",78,4);
        dbCS.insertCourStu("1000006","1000019",81,4);
        dbCS.insertCourStu("1000006","1000020",52,4);
        dbCS.insertCourStu("1000006","1000021",63,4);
        dbCS.insertCourStu("1000006","1000022",69,4);
        dbCS.insertCourStu("1000006","1000023",72,4);
        dbCS.insertCourStu("1000006","1000024",73,4);

        dbCS.insertCourStu("1000006","1000025",74,3);
        dbCS.insertCourStu("1000006","1000026",75,3);
        dbCS.insertCourStu("1000006","1000027",79,3);
        dbCS.insertCourStu("1000006","1000028",82,3);
        dbCS.insertCourStu("1000006","1000029",83,3);
        dbCS.insertCourStu("1000006","1000030",84,3);
        dbCS.insertCourStu("1000006","1000031",89,3);
        dbCS.insertCourStu("1000006","1000032",87,3);


        dbCS.insertCourStu("1000006","1000033",87,2);
        dbCS.insertCourStu("1000006","1000034",85,2);
        dbCS.insertCourStu("1000006","1000035",89,2);
        dbCS.insertCourStu("1000006","1000036",91,2);
        dbCS.insertCourStu("1000006","1000037",93,2);
        dbCS.insertCourStu("1000006","1000038",94,2);
        dbCS.insertCourStu("1000006","1000039",87,2);
        dbCS.insertCourStu("1000006","1000040",84,2);

        dbCS.insertCourStu("1000006","1000041",89,1);
        dbCS.insertCourStu("1000006","1000042",88,1);
        dbCS.insertCourStu("1000006","1000043",87,1);
        dbCS.insertCourStu("1000006","1000044",85,1);
        dbCS.insertCourStu("1000006","1000045",75,1);
        dbCS.insertCourStu("1000006","1000046",78,1);
        dbCS.insertCourStu("1000006","1000047",88,1);
        dbCS.insertCourStu("1000006","1000048",94,1);
    }
