package top.dopen.meizitu;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.dopen.meizitu.entity.Work;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * 1.
 * 1.获取妹子图总页数
 * 2.获取指定页数图片
 */
public class MeiZiTuService {
    private ExecutorService executorService = Executors.newFixedThreadPool(20);;
    /**
     *  获取总页数
     * @return
     */
    public Integer getPageCount() {
        Connection connect = Jsoup.connect(MeiZiTuConfig.INDEX_PAGE_URL).timeout(9000);
        Document document ;
        int totalPage = 0;
        try {
            //获取document文档
            document = connect.get();
            Element indexTotalPageElement = document.getElementsByClass("nav-links").select("a").last().previousElementSibling();
            totalPage = Integer.parseInt(indexTotalPageElement.text().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalPage;

    }

    /**
     * 获取指定页面的所有列表地址
     * @param pageNow
     */
    public List<Work> getAllWorkByPageNum (int pageNow) throws IOException {
        List<Work> workList = new ArrayList<Work>();
        String pageNowUrl = MeiZiTuConfig.BASE_INDEX_PAGE_URL + pageNow;
        Document document = Jsoup.connect(pageNowUrl).get();
        Element pins = document.getElementById("pins");
        Elements lis = pins.getElementsByTag("li");
        Iterator var6 = lis.iterator();
        while(var6.hasNext()) {
            Element a = (Element)var6.next();
            Element first = a.children().first();
            Work work = new Work(first.getElementsByTag("img").attr("alt"),first.attr("href"),null,null);
            System.out.println("");
            System.out.println("=======================");
            System.out.println("图集："+work.getName());
            executorService.execute(work);
            workList.add(work);

            System.out.println("");
            System.out.println("");
        }
        return  workList;
    }





}
