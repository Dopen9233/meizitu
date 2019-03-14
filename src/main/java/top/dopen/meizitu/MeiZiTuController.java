package top.dopen.meizitu;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import top.dopen.meizitu.entity.Work;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



@Controller
public class MeiZiTuController {

    MeiZiTuService meiZiTuService = new MeiZiTuService();



    public void getData (int startPage,int endPage){
        List<Work> works = null;
        for (int i = 0; i < (endPage - startPage); i ++ ){
            try {
                System.out.println("开始获取第["+(startPage+i)+"]页图集信息....");
                works = meiZiTuService.getAllWorkByPageNum(startPage + i);
                System.out.println("共获取"+works.size()+"");
            } catch (IOException e) {
                System.err.println(Thread.currentThread().getName()+": 获取第"+ (startPage + i) +"页word出错");
                e.printStackTrace();
            }
        }


    }

    public void getData (int pageNow){
        List<Work> works = null;
        try {
            System.out.println("开始获取第["+(pageNow)+"]页图集信息....");
            works = meiZiTuService.getAllWorkByPageNum(pageNow);
            System.out.println("共获取"+works.size()+"");
        } catch (IOException e) {
            System.err.println(Thread.currentThread().getName()+": 获取第"+ pageNow +"页word出错");
            e.printStackTrace();
        }

    }

    public int getPageCount(){
        return meiZiTuService.getPageCount();
    }

}
