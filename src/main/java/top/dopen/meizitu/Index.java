package top.dopen.meizitu;


import java.util.Scanner;
public class Index {

    private static Integer pageCount;
    private static Scanner scanner = new Scanner(System.in);
    private static MeiZiTuController controller = new MeiZiTuController();
    public static void main(String[] args) throws InterruptedException {
        System.out.print("初始化资源请稍后");

        System.out.println(".");
        pageCount = controller.getPageCount();
        System.out.println("..");
        boolean flag;
        do {
            System.out.println("共有【"+pageCount+"】页数据");
            System.out.println("如何获取资源");
            System.out.println("【1】按照范围页数");
            System.out.println("【2】按照指定页数");
            System.out.println("【3】手动获取");
            String next = scanner.next();
            switch (next){
                case "1":
                    flag = false;
                    fun1();
                    break;
                case "2":
                    flag = false;
                    fun2();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    flag = true;
                    System.out.println("【您的输入有误】");
                    Thread.sleep(1000);
            }
        }while (flag);
        System.out.println("下载全部完成");


    }

    private static void fun1 (){
        int startPage = 1;
        int endPage = 0;
        boolean flag ;
        do{
            try {
                System.out.println("请输入初始页数     页数范围【"+1+"~"+pageCount+"】");
                startPage = scanner.nextInt();
                if(startPage < 1 || startPage > pageCount ){
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("页码输入有误");
                flag = true;
            }
        }while (flag);
        do{
            try {
                System.out.println("请输入结束页数     页数范围【"+startPage+"~"+pageCount+"】");
                endPage = scanner.nextInt();
                if(endPage < startPage || endPage > pageCount ){
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("页码输入有误");
                flag = true;
            }
        }while (flag);
        controller.getData(startPage,endPage);
    }

    private static void fun2 (){
        int pageNow = 1;
        boolean flag ;
        do{
            try {
                System.out.println("请输入页数     页数范围【"+1+"~"+pageCount+"】");
                pageNow = scanner.nextInt();
                if(pageNow < 1 || pageNow > pageCount ){
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("页码输入有误");
                flag = true;
            }
        }while (flag);

        controller.getData(pageNow);
    }

}
