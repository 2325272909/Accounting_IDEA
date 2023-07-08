package Accounting.controller;

import Accounting.common.R;
import Accounting.entity.Spending;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


/**
 * @ClassName:OCRController
 * @Description:接收图片进行OCR识别
 * @Author:liumengying
 * @Date: 2023/5/7 17:36
 * Version v1.0
 */
@RestController
@RequestMapping("/ocr")
@Slf4j
public class OCRController {

    /**
     * 计算总消费金额
     */
    @GetMapping("/ocr_test")
    public R<String> test(@RequestParam String imgName) {

        String basePath="D://reggie/";
//        imgByte=imgByte.replaceAll("data:image/png;base64,","");
//        BASE64Decoder decoder =  new BASE64Decoder();
//        byte[] imageByte = null;
//        try{
//            imageByte = decoder.decodeBuffer(imgByte);
//            for (int i = 0; i < imageByte.length; ++i) {
//                if (imageByte[i] < 0) {// 调整异常数据
//                    imageByte[i] += 256;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        log.info("进入OCR识别函数");
        try {
//            InputStream sbs = new ByteArrayInputStream(imageByte);
            String imgPath = basePath+imgName;
            BufferedImage img = ImageIO.read(Files.newInputStream(Paths.get(imgPath)));
            ITesseract instance = new Tesseract();
            //设置语言库所在的文件夹位置，最好是绝对的，不然加载不到就直接报错了
            instance.setDatapath("D:\\Android Project\\tessdata");
            //设置使用的语言库类型：chi_sim 中文简体
            instance.setLanguage("chi_sim");
            String result = instance.doOCR(img);
            log.info("扫描的文本：" + result);
        } catch (Exception e) {
            log.error("扫描图片文本错误:", e);
        }
        return R.success("OCR识别成功");
    }

    @PostMapping("ocr")
    public R<String> OCR(@RequestBody Map<String,String> pictures){
        String picture = pictures.get("picture");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByte = null;
        try{
            imageByte = decoder.decodeBuffer(picture);
            for (int i = 0; i < imageByte.length; ++i) {
                if (imageByte[i] < 0) {// 调整异常数据
                    imageByte[i] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("进入OCR识别函数");
        try {
            InputStream sbs = new ByteArrayInputStream(imageByte);
            BufferedImage img = ImageIO.read(sbs);
            ITesseract instance = new Tesseract();
            //设置语言库所在的文件夹位置，最好是绝对的，不然加载不到就直接报错了
            instance.setDatapath("D:\\Android Project\\tessdata");
            instance.setTessVariable("user_defined_dpi","300");
            //设置使用的语言库类型：chi_sim 中文简体
            instance.setLanguage("chi_sim");
            String result = instance.doOCR(img);
            log.info("扫描的文本：" + result);
            return R.success(result);

        } catch (Exception e) {
            log.error("扫描图片文本错误:", e);
        }
        return R.error("识别错误");
    }
}

