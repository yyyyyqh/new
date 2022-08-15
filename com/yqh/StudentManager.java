package com.yqh;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {


    public static void main(String[] args) {

        List<Student> list  = new ArrayList<>();

        // initialize
        init(list);
        Student student = new Student("213030534", "admin", "address", 20);
        list.add(student);

        //简单界面
        System.out.println("Please enter the following numbers (1-5):");
        System.out.println("1. Add student.");
        System.out.println("2. Remove student.");
        System.out.println("3. Update Info.");
        System.out.println("4. Show all student Info.");
        System.out.println("5. Exit.");
        System.out.print("Input > ");

        //接收输入处理
        int index = 0;
        while(true){
            if(index > 0){
                System.out.println("Please enter the following numbers (1-5):");
                System.out.println("1. Add student.");
                System.out.println("2. Remove student.");
                System.out.println("3. Update Info.");
                System.out.println("4. Show all student Info.");
                System.out.println("5. Exit.");
                System.out.print("Input > ");
            }
            index++;

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    addStudent(list);
                    break;
                case "2":
                    removeStudent(list);
                    break;
                case "3":
                    updateStudent(list);
                    break;
                case "4":
                    showInfo(list);
                    break;
                case "5":
                    System.out.println("See you.");
                    System.exit(0);
            }


        }
    }

    private static void addStudent(List<Student> list){
        // 获取新数据，填入Student类
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name: ");
        String name = scanner.nextLine();
        System.out.println("Input sid: ");
        String sid = scanner.nextLine();
        System.out.println("Input address: ");
        String address = scanner.nextLine();
        System.out.println("Input age: ");
        int age = scanner.nextInt();

        Student student = new Student(sid, name, address, age);
        list.add(student);
        //文件里也要add一份，文件的输入

        System.out.println("成功添加: " + student.getSid() + " " + student.getName() + " " + student.getAge() +
                " " + student.getAddress());
    }

    /**
     * 后期可优化。
     */

    private static void removeStudent(List<Student> list){
        System.out.println("当前保存人数：" + list.size());

        //如果遍历完都没找到，那么没有该学号（flag == true）
        boolean flag = false;
        System.out.println("Please enter your sid to delete this Info.");

        Scanner scanner = new Scanner(System.in);
        String inputSid = scanner.nextLine();

        // for循环遍历，找到-删除
        for(int i = 0; i < list.size(); i++){
            Student tmp = list.get(i);
            if(tmp.getSid().equals(inputSid)){
                list.remove(i);
                flag = true;
                System.out.println("成功删除:" + tmp.getSid() + tmp.getName() + "!!!");
                break;
            }
        }
        if(!flag){
            System.out.println("没找到这个人。");
        }
    }

    /**
     * 输入sid，输入修改字段，输入修改后的名字
     */

    private static void updateStudent(List<Student> list){
        boolean flag = true;

        Scanner scanner = new Scanner(System.in);

        System.out.println("输入sid选择更新用户:");
        String inputSid = scanner.nextLine();

        if(isExist(inputSid, list)){
            System.out.println("输入被更改的变量名:");
            String fillField = scanner.nextLine();

            System.out.println("输入更改后的变量值:");
            String fill = scanner.nextLine();

            for(int i = 0; i < list.size(); i++){
                Student tmp = list.get(i);
                if(tmp.getSid().equals(inputSid)){
                    switch (fillField){
                        case "sid":
                            Student s1 = new Student(fill, tmp.getName(), tmp.getAddress(), tmp.getAge());
                            list.set(i, s1);
                            break;
                        case "name":
                            Student s2 = new Student(tmp.getSid(), fill, tmp.getAddress(), tmp.getAge());
                            list.set(i, s2);
                            break;
                        case "address":
                            Student s3 = new Student(tmp.getSid(), tmp.getName(), fill, tmp.getAge());
                            list.set(i, s3);
                            break;
                        case "age":
                            Student s = new Student(tmp.getSid(), tmp.getName(), tmp.getAddress(), Integer.valueOf(fill));
                            list.set(i, s);
                            break;
                        default:
                            System.out.println("没有该变量名");
                            flag = false;
                    }
                }
            }

            if(flag){
                for(int i = 0; i < list.size(); i++){
                    System.out.println("修改后：" +
                            list.get(i).getSid() + " " +
                            list.get(i).getName() + " " +
                            list.get(i).getAge() +  " " +
                            list.get(i).getAddress()
                    );
                }
            }
        }else{
            System.out.println("没有该用户。");
        }
    }

    /**
     * 显示所有学生信息
     */
    private static void showInfo(List<Student> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(String.valueOf(i + 1) + ". " +
                    list.get(i).getSid() + " " +
                    list.get(i).getName() + " " +
                    list.get(i).getAge() + " " +
                    list.get(i).getAge());
        }
        System.out.println();
    }

    //功能函数
    /**
     * 用学号判断是否存在（boolean）
     */
    private static Boolean isExist(String sid, List<Student> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getSid().equals(sid)){
                return true;
            }
        }
        return false;
    }
    private static void init(List<Student> list){
        //外部定义，以供内部使用
        Student stu;
        File file = new File("test1.txt");

        try{
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            String[] words = null;
            while( (line = in.readLine()) != null){
                //使用':'作为单个数据的划分依据
                words = line.split(":");

                //或者使用Student(sid, name, address, age)构造器，直接创建这个对象
                stu = new Student();
                //这块位置需要注意一下，如果word没有成功化成4个存入，就会报错
                stu.setSid(words[0]);
                stu.setName(words[1]);
                stu.setAddress(words[2]);
                stu.setAge(Integer.valueOf(words[3]));

                list.add(stu);
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
