package top.dopen.meizitu.entity;

import top.dopen.meizitu.MeiZiTuConfig;
import top.dopen.util.DopenFileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadRunnable implements Runnable {

    private Work work;
    @Override
    public void run() {
        File path = new File(MeiZiTuConfig.FILE_UPLOAD_PATH + work.getName());
        if (!path.exists()) {
            path.mkdir();
        }
        for(Picture picture:work.getPictures()){
            try {
                URL url = new URL(picture.getUrl());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("referer", "https://www.mzitu.com");
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(9000);
                InputStream inStream = conn.getInputStream();
                String pictureLocalPath = path.getPath() + "\\" + picture.getName().substring(31);
                System.out.println("开始写入："+pictureLocalPath);
                DopenFileUtil.writeFIleWithoutInput(pictureLocalPath,inStream);
            } catch (IOException e) {
                System.out.println("下载【"+work.getName()+"】   "+picture.getName()+"出错");
            }
        }
    }
}
