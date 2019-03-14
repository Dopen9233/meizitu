package top.dopen.meizitu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.dopen.meizitu.MeiZiTuConfig;
import top.dopen.util.DopenFileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 图集实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work implements Runnable {
    private String name;
    private String url;
    private Integer pictureCount;
    private List<Picture> pictures;

    @Override
    public void run() {
        try {
            getPictureUrlByWork();
            downloadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void downloadImage(){
        File path = new File(MeiZiTuConfig.FILE_UPLOAD_PATH + this.getName());
        System.out.println(path);
        if (!path.exists()) {
            path.mkdirs();
        }
        for(Picture picture:pictures){
            try {
                URL url = new URL(picture.getUrl());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("referer", "https://www.mzitu.com");
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(9000);
                InputStream inStream = conn.getInputStream();
                String pictureLocalPath = path.getPath() + "\\" + picture.getName();
                System.out.println("开始写入："+pictureLocalPath);
                DopenFileUtil.writeFIleWithoutInput(pictureLocalPath,inStream);
            } catch (IOException e) {
                System.out.println("下载【"+this.getName()+"】   "+picture.getName()+"出错");
            }
        }
    }
    /**
     * 获取work下的图片数量，所有图片信息
     * @throws IOException
     */
    public void getPictureUrlByWork() throws IOException {
        Document document = Jsoup.connect(this.getUrl()).get();
        //获取当前work的所有图片数量
        Integer picTotal = Integer.parseInt(document.getElementsByClass("pagenavi").select("a").last().previousElementSibling().select("span").text());
        this.setPictureCount(picTotal);
        System.out.println("共"+picTotal+"张图片");
        List<Picture> pictures = new ArrayList<>();
        for(int i = 1; i <= this.getPictureCount(); ++i) {

            String picUrl = Jsoup.connect(this.getUrl() + "/" + i).get().getElementsByClass("main-image").select("img").attr("src");
            pictures.add(new Picture(picUrl.substring(picUrl.lastIndexOf("/")+1,picUrl.length()),picUrl));

        }
        this.setPictures(pictures);
    }
}